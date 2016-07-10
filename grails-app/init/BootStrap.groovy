import com.paperbox.*

class BootStrap {

    def assetResourceLocator

    def init = {

        def adminRole = new Role('ROLE_ADMIN').save()
        def userRole = new Role('ROLE_USER').save()
        def organiserRole = new Role('ROLE_ORGANISER').save()
        def vendorRole = new Role('ROLE_VENDOR').save()
        def facebookRole = new Role('ROLE_FACEBOOK').save()

        def testVendor = new User(username: 'vendor', email:'vendor@gmail.com', password: 'password', firstName:
                'Jane', lastName: 'Dow').save()
        def testOrganiser = new User(username:'organiser', email:'organiser@gmail.com', password:'password',
                firstName: 'Alla', lastName: 'Sha').save()
        def testAdmin = new User(username: 'admin', email:'barbara.vk@gmail.com', password: 'password', firstName:
                'Babs', lastName: 'Kushnarenko').save()

        UserRole.create testVendor, vendorRole
        UserRole.create testOrganiser, organiserRole
        UserRole.create testAdmin, adminRole

        UserRole.withSession {
            it.flush()
            it.clear()
        }

        def photography = new ListingCategory('Photography').save()
        def florist = new ListingCategory('Floristry').save()
        def hire = new ListingCategory('Styling').save()
        def makeup = new ListingCategory('Bridal Makeup').save()
        def stationary = new ListingCategory('Stationary').save()

        def vendor = new Vendor()
        vendor.user = testVendor
        vendor.businessName = "Test Vendor Co"
        vendor.contactFirstName = testVendor.firstName
        vendor.contactLastName = testVendor.lastName
        vendor.phoneNumber = "0422448302"
        vendor.addressLine1 = "3/24 Moray St"
        vendor.about = "Lorem ipsum"
        vendor.cityStatePostcode = "New Farm, 4101"
        vendor.status = 'A'
        vendor.save(flush: true)

        def tag1 = new Tag(tagString: 'photography', tagType: 'listing').save()
        def tag2 = new Tag(tagString: 'special', tagType: 'listing').save()

        //test vendor listings

        /* Black Bird Co */
        byte[] blackBird_logo = assetResourceLocator.findAssetForURI('test/bb_logo.jpg').inputStream.bytes
        def blackBird_user = new User(username: 'black_bird', email:'bb@gmail.com', password: '111', firstName:
                'Jane', lastName: 'Smith', picture: blackBird_logo).save()
        UserRole.create blackBird_user, vendorRole
        def blackBird_vendor = new Vendor(businessName: "Black Bird Co", contactFirstName: "Jane", contactLastName:
                "Smith", phoneNumber: "123", addressLine1: "3/24 Moray St", about: "Lorem ipsum.", cityStatePostcode:
                "New Farm, 4101", status: "A")
        blackBird_vendor.user = blackBird_user

        def blackBird_list = new Listing()
        blackBird_list.listingCategory = hire
        blackBird_list.listingName = "Black Bird Styling and Hire"
        blackBird_list.listingDescription ="Life is too short to have boring parties"
        blackBird_list.listingTagline ="Life is too short to have boring parties"
        blackBird_list.vendor = blackBird_vendor
        blackBird_list.status = 'A'
        def blackBird_img = new ListingImage(image: assetResourceLocator.findAssetForURI('test/bb_pic1.jpg').inputStream
                .bytes)
        blackBird_list.addToImage(blackBird_img)
        blackBird_list.save(flush: true)

        /* Mon Cheri Makeup Co */
        byte[] monCheri_logo = assetResourceLocator.findAssetForURI('test/mc_logo.jpg').inputStream.bytes
        def monCheri_user = new User(username: 'mon_cheri', email:'mc@gmail.com', password: '111', firstName:
                'Jane', lastName: 'Smith', picture: monCheri_logo).save()
        UserRole.create monCheri_user, vendorRole
        def monCheri_vendor = new Vendor(businessName: "Mon Cheri Makeup Co", contactFirstName: "Jane", contactLastName:
                "Smith", phoneNumber: "123", addressLine1: "3/24 Moray St", about: "Lorem ipsum.", cityStatePostcode:
                "New Farm, 4101", status: "A")
        monCheri_vendor.user = monCheri_user

        def monCheri_list = new Listing()
        monCheri_list.listingCategory = makeup
        monCheri_list.listingName = "Mon Cheri Bridal Makeup"
        monCheri_list.listingDescription ="Beautifully natural"
        monCheri_list.listingTagline ="Beautifully natural"
        monCheri_list.vendor = monCheri_vendor
        monCheri_list.status = 'A'
        def monCheri_img = new ListingImage(image: assetResourceLocator.findAssetForURI('test/mc_pic1.jpg').inputStream
                .bytes)
        monCheri_list.addToImage(monCheri_img)
        monCheri_list.save(flush: true)

        /* White Cat Photography */
        byte[] whiteCat_logo = assetResourceLocator.findAssetForURI('test/wc_logo.jpg').inputStream.bytes
        def whiteCat_user = new User(username: 'white_cat', email:'wc@gmail.com', password: '111', firstName:
                'Jane', lastName: 'Smith', picture: whiteCat_logo).save()
        UserRole.create whiteCat_user, vendorRole
        def whiteCat_vendor = new Vendor(businessName: "Black Bird Co", contactFirstName: "Jane", contactLastName:
                "Smith", phoneNumber: "123", addressLine1: "3/24 Moray St", about: "Lorem ipsum.", cityStatePostcode:
                "New Farm, 4101", status: "A")
        whiteCat_vendor.user = whiteCat_user

        def whiteCat_list = new Listing()
        whiteCat_list.listingCategory = photography
        whiteCat_list.listingName = "White Cat Photography"
        whiteCat_list.listingDescription ="Best even photography in Brisbane"
        whiteCat_list.listingTagline ="Best even photography in Brisbane"
        whiteCat_list.vendor = whiteCat_vendor
        whiteCat_list.status = 'A'
        def whiteCat_img = new ListingImage(image: assetResourceLocator.findAssetForURI('test/wc_pic1.jpg').inputStream
                .bytes)
        whiteCat_list.addToImage(whiteCat_img)
        whiteCat_list.save(flush: true)
        def whiteCat_calendar = new CalendarEntry(periodStart:  Date.parse('dd/MM/yyyy', '01/04/2016'), periodEnd:
                Date.parse('dd/MM/yyyy', '30/04/2016'), periodStatus: 'Booked',vendor: whiteCat_vendor).save(flush:
                true)

        /* White Poppy Flowers */
        byte[] whitePoppy_logo = assetResourceLocator.findAssetForURI('test/wpf_logo.jpg').inputStream.bytes
        def whitePoppy_user = new User(username: 'white_poppy', email:'wpf@gmail.com', password: '111', firstName:
                'Jane', lastName: 'Smith', picture: whitePoppy_logo).save()
        UserRole.create whitePoppy_user, vendorRole
        def whitePoppy_vendor = new Vendor(businessName: "Black Bird Co", contactFirstName: "Jane", contactLastName:
                "Smith", phoneNumber: "123", addressLine1: "3/24 Moray St", about: "Lorem ipsum.", cityStatePostcode:
                "New Farm, 4101", status: "A")
        whitePoppy_vendor.user = whitePoppy_user

        def whitePoppy_list = new Listing()
        whitePoppy_list.listingCategory = florist
        whitePoppy_list.listingName = "White Poppy Floral Design"
        whitePoppy_list.listingDescription ="The most romantic blooms"
        whitePoppy_list.listingTagline ="The most romantic blooms"
        whitePoppy_list.vendor = whitePoppy_vendor
        whitePoppy_list.status = 'A'
        def whitePoppy_img = new ListingImage(image: assetResourceLocator.findAssetForURI('test/wpf_pic1.jpg')
                .inputStream
                .bytes)
        whitePoppy_list.addToImage(whitePoppy_img)
        whitePoppy_list.save(flush: true)

        /* Winter California Calligraphy */
        byte[] winterCalifornia_logo = assetResourceLocator.findAssetForURI('test/wcs_logo.jpg').inputStream.bytes
        def winterCalifornia_user = new User(username: 'winter_california', email:'wcs@gmail.com', password: '111',
                firstName:
                'Jane', lastName: 'Smith', picture: winterCalifornia_logo).save()
        UserRole.create winterCalifornia_user, vendorRole
        def winterCalifornia_vendor = new Vendor(businessName: "Black Bird Co", contactFirstName: "Jane", contactLastName:
                "Smith", phoneNumber: "123", addressLine1: "3/24 Moray St", about: "Lorem ipsum.", cityStatePostcode:
                "New Farm, 4101", status: "A")
        winterCalifornia_vendor.user = winterCalifornia_user

        def winterCalifornia_list = new Listing()
        winterCalifornia_list.listingCategory = stationary
        winterCalifornia_list.listingName = "Winter California Calligraphy"
        winterCalifornia_list.listingDescription ="Hand written stationary"
        winterCalifornia_list.listingTagline ="Hand written stationary"
        winterCalifornia_list.vendor = winterCalifornia_vendor
        winterCalifornia_list.status = 'A'
        def winterCalifornia_img = new ListingImage(image: assetResourceLocator.findAssetForURI('test/wcs_pic1.jpg')
                .inputStream
                .bytes)
        winterCalifornia_list.addToImage(winterCalifornia_img)
        winterCalifornia_list.save(flush: true)

    }
    def destroy = {
    }
}
