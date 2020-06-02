package net.benfro.lab.translationfrontend.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
//@Transactional(readOnly = true)
class SecurityService @Autowired constructor(val userRepo: UserRepository,
                                            val roleRepo: RoleRepository,
                                            val authRepo: AuthorityRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        return userRepo.findByUsername(username!!)
    }

    fun listUsers(): MutableIterable<User> {
        return userRepo.findAll()
    }

    fun listRoles(): MutableIterable<Role> {
        return roleRepo.findAll()
    }
}
