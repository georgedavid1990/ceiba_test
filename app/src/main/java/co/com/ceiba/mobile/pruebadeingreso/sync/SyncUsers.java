package co.com.ceiba.mobile.pruebadeingreso.sync;

import java.util.List;
import java.util.UUID;

import co.com.ceiba.mobile.pruebadeingreso.common.ActivityBase;
import co.com.ceiba.mobile.pruebadeingreso.data.dao.AddressDao;
import co.com.ceiba.mobile.pruebadeingreso.data.dao.CeibaDataBase;
import co.com.ceiba.mobile.pruebadeingreso.data.dao.CompanyDao;
import co.com.ceiba.mobile.pruebadeingreso.data.dao.GeoDao;
import co.com.ceiba.mobile.pruebadeingreso.data.dao.UserDao;
import co.com.ceiba.mobile.pruebadeingreso.data.model.Address;
import co.com.ceiba.mobile.pruebadeingreso.data.model.Company;
import co.com.ceiba.mobile.pruebadeingreso.data.model.Geo;
import co.com.ceiba.mobile.pruebadeingreso.data.model.User;
import co.com.ceiba.mobile.pruebadeingreso.rest.RestClient;
import co.com.ceiba.mobile.pruebadeingreso.rest.result.AddressResult;
import co.com.ceiba.mobile.pruebadeingreso.rest.result.CompanyResult;
import co.com.ceiba.mobile.pruebadeingreso.rest.result.GeoResult;
import co.com.ceiba.mobile.pruebadeingreso.rest.result.UsersResult;

public class SyncUsers implements ISyncData {

    @Override
    public void download(ActivityBase context) throws Exception {

        RestClient restClient = RestClient.getRestClient();
        List<UsersResult> users = restClient.getUsers().execute().body();

        if(users != null){
            CeibaDataBase appDatabase = CeibaDataBase.getDatabase(context.getApplication());

            UserDao userDao = appDatabase.getUserDao();
            AddressDao addressDao = appDatabase.getAddressDao();
            GeoDao geoDao = appDatabase.getGeoDao();
            CompanyDao companyDao = appDatabase.getCompanyDao();
            userDao.deleteAll();
            addressDao.deleteAll();
            geoDao.deleteAll();
            companyDao.deleteAll();

            for (UsersResult u: users) {
                User user = new User();
                user.setId(u.getId());
                user.setEmail(u.getEmail());
                user.setName(u.getName());
                user.setPhone(u.getPhone());
                user.setUsername(u.getUsername());
                user.setWebsite(u.getWebsite());
                userDao.insert(user);

                AddressResult a = u.getAddress();
                if(a != null){
                    String aid = UUID.randomUUID().toString();
                    Address address = new Address(aid);
                    address.setCity(a.getCity());
                    address.setId(aid);
                    address.setStreet(a.getStreet());
                    address.setSuite(a.getSuite());
                    address.setUserId(u.getId());
                    address.setZipcode(a.getZipcode());
                    addressDao.insert(address);

                    GeoResult g = a.getGeo();
                    if(g != null){
                        String gid = UUID.randomUUID().toString();
                        Geo geo = new Geo(gid);
                        geo.setAddressId(aid);
                        geo.setId(gid);
                        geo.setLat(g.getLat());
                        geo.setLng(g.getLng());
                        geoDao.insert(geo);
                    }
                }

                CompanyResult c = u.getCompany();
                if(c != null){
                    String cid = UUID.randomUUID().toString();
                    Company company = new Company();
                    company.setBs(c.getBs());
                    company.setCatchPhrase(c.getCatchPhrase());
                    company.setId(cid);
                    company.setName(c.getName());
                    company.setUserId(u.getId());
                    companyDao.insert(company);
                }
            }
        }

    }
}
