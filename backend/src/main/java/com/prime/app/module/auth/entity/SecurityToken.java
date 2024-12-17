package com.prime.app.module.auth.entity;

//@Getter
//@Setter
//@Entity
//@Table(indexes = {@Index(columnList = "token")})
public class SecurityToken {

//    @Id
//    private String token;
//
//    private Boolean deleted;
//
//    private Date createdDate;
//
//    private String userId;
//
//    private Date expiryDate;
//
//    private Date usedDate;
//
//    @Version
//    @Column(name = "optlock", nullable = false)
//    private long version = 0L;
//
//    public SecurityToken() {
//        this.deleted = Boolean.FALSE;
//    }
//
//
//    public SecurityToken(AuthUserProjection user) {
//        this.userId = user.getId();
//        Calendar exp = Calendar.getInstance();
//        exp.add(Calendar.HOUR, 3);
//        this.expiryDate = exp.getTime();
//        this.createdDate = new Date();
//        this.token = UUID.randomUUID().toString();
//    }
//
//    public SecurityToken(Date expiryDate) {
//        this();
//        this.expiryDate = expiryDate;
//        this.createdDate = new Date();
//    }
//
//    public SecurityToken(Date expiryDate, User tokenUser) {
//        this(expiryDate);
//        this.userId = tokenUser.getId();
//    }
//
//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((token == null) ? 0 : token.hashCode());
//        return result;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (obj == null) return false;
//        if (getClass() != obj.getClass()) return false;
//        SecurityToken other = (SecurityToken) obj;
//        if (token == null) {
//            return other.token == null;
//        } else return token.equals(other.token);
//    }

}
