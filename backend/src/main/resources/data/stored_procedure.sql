DELIMITER
DROP FUNCTION IF EXISTS  login_user_data;
DELIMITER
CREATE OR REPLACE FUNCTION login_user_data
(
  	input_id varchar,
    OUT output_data varchar
)
AS $$
begin
	output_data:=(select
					json_build_object(
								'loginId', u.login_id,
								'role', (r.name ::jsonb ),
								'firstName',(u.name::jsonb ),
								'timeZone', u.time_zone,
								'langCode', u.lang_code,
								'code', u.code,
								'lastLoginTime', u.last_login_time,
								'lastFailedLoginTime', u.last_failed_login_time,
								'tenantType', u.tenant_type,
								'decimalScale', u.decimal_scale,
								'permissions',(select json_agg(distinct a.acl) from app_role_acl_mapping a where a.role_id = u.user_role_id)
	)
from
	app_user u
inner join app_user_role r on
	r.id = u.user_role_id
where
	u.id = input_id
);
END;
$$ LANGUAGE plpgsql;