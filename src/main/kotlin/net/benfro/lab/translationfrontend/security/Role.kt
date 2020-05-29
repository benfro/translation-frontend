package net.benfro.lab.translationfrontend.security

import javax.persistence.*

@Entity
class Role (
        var role: String,
        @ManyToMany val users: MutableSet<User> = mutableSetOf(),
        @ManyToMany val authorities: MutableSet<Authority> = mutableSetOf(),
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
) {
    companion object Factory {
        fun of(roleStr: String): Role {
            return Role(role = roleStr)
        }
    }

    fun addAuthority(auth: Authority) {
        authorities.add(auth)
        auth.roles.add(this)
    }

    fun removeAuthority(auth: Authority) {
        authorities.remove(auth)
        auth.roles.remove(this)
    }
}