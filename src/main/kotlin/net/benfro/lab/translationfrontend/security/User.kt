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
        @ManyToMany val roles: MutableSet<Role> = mutableSetOf(),
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
        roles.add(role)
        role.users.add(this)
    }

    fun removeRole(role: Role) {
        roles.remove(role)
        role.users.remove(this)
    }
}