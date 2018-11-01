package edu.uno.omahelp.organization;

import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("organization/")
public class OrganizationController {

	@Autowired
    private OrganizationDao organizationDao;

    @RequestMapping("list") 
    public List<Organization> listAllOrganizations() throws URISyntaxException, SQLException {
        return organizationDao.listAllOrganizations();
    }

}