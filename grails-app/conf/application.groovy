

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.paperbox.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.paperbox.UserRole'
grails.plugin.springsecurity.authority.className = 'com.paperbox.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']],
	[pattern: '/**', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]

grails.plugin.springsecurity.logout.postOnly = false

grails.plugins.mail.default.from = "barbara.vk@gmail.com"

grails {
	mail {
		host = "smtp.gmail.com"
		port = 587
		username = "barbara.vk@gmail.com"
		password = "silmarilli"
		props = ["mail.smtp.auth":"true",
				 "mail.smtp.starttls.enable": "true",
				 "mail.smtp.socketFactory.port":"587"
		]
	}
}

grails.plugin.springsecurity.rememberMe.persistent = true
grails.plugin.springsecurity.rememberMe.persistentToken.domainClassName = 'com.paperbox.PersistentLogin'
grails.plugin.springsecurity.ui.password.validationRegex = '^.*(?=.*\\d)(?=.*[a-zA-Z]).*$'

grails.plugin.springsecurity.facebook.autoCreate.roles = [
        'ROLE_USER', 'ROLE_FACEBOOK'
]

grails.databinding.dateFormats = [
		'dd/mm/yyyy', 'yyyy-MM-dd HH:mm:ss.S', "yyyy-MM-dd'T'hh:mm:ss'Z'"
]

