package net.benfro.lab.translationfrontend.security

import org.springframework.security.core.GrantedAuthority
import javax.persistence.*

@Entity
class Authority(
        var auth: String,
        @ManyToMany(mappedBy = "authorities") val roles: MutableSet<Role> = mutableSetOf(),
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
) : GrantedAuthority {
    override fun getAuthority(): String {
        return auth
    }
}