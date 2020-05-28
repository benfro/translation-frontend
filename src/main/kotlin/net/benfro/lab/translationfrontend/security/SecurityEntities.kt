package net.benfro.lab.translationfrontend.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime
import javax.persistence.*
import kotlin.streams.toList

@Entity
class User (
        private var username: String,
        var firstName: String,
        var lastName: String,
        var email: String,
        private var password: String,
        @ManyToMany val roles: Set<Role> = mutableSetOf(),
        var validFrom: LocalDateTime? = null,
        var validTo: LocalDateTime? = null,
        var locked: Boolean? = false,
        var enabled: Boolean? = true,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return ArrayList(roles.stream()
                .flatMap { it.authorities.stream() }
                .toList())
    }

    override fun isEnabled(): Boolean {
        return enabled!!
    }

    override fun getUsername(): String {
        return username
    }

    override fun isCredentialsNonExpired(): Boolean {
        if (validTo === null) {
            return true
        }
        return LocalDateTime.now().isBefore(validTo)
    }

    override fun getPassword(): String {
        return password
    }

    override fun isAccountNonExpired(): Boolean {
        if (validTo === null) return true
        return LocalDateTime.now().isAfter(validTo)
    }

    override fun isAccountNonLocked(): Boolean {
        return !locked!!
    }

    /**
     * This side handles the relation
     */
    fun addRole(role: Role) {
        roles.plus(role)
        role.users.plus(this)
    }

    fun removeRole(role: Role) {
        roles.minus(role)
        role.users.minus(this)
    }
}

@Entity
class Role (
        var role: String,
        @ManyToMany val users: Set<User> = mutableSetOf(),
        @ManyToMany  val authorities: Set<Authority> = mutableSetOf(),
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
)

@Entity
class Authority(
        var auth: String,
        @ManyToMany val roles: Set<Role> = mutableSetOf(),
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null
) : GrantedAuthority {
    override fun getAuthority(): String {
        return auth
    }

}
