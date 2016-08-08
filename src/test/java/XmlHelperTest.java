import com.averoc.task.models.Developer;
import com.averoc.task.services.DevelopersService;
import com.averoc.task.utils.Utilities;
import com.averoc.task.utils.XmlHelper;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.w3c.dom.Document;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by de1mos on 8.08.16.
 */
public class XmlHelperTest extends Mockito {

    private static final Logger LOG = Logger.getLogger(XmlHelperTest.class);
    private static final String FILE_NAME = "/JavaTestAssignment.xml";
    DevelopersService developersService;


    @Before
    public void setUp() {
        developersService = new DevelopersService();
    }

    @Test
    public void readXmlFileTest() {
        File inputXmlFile = new File(this.getClass().getResource(FILE_NAME).getFile());
        Document xmlData = XmlHelper.readXmlFile(inputXmlFile.getAbsolutePath());

        LOG.debug("File reading is OK!");
    }

    @Test
    public void sortingTest() {
        File inputXmlFile = new File(this.getClass().getResource(FILE_NAME).getFile());
        Document xmlData = XmlHelper.readXmlFile(inputXmlFile.getAbsolutePath());

        List<Developer> developers = developersService.createDevelopersListData(xmlData);
        Collections.sort(developers);
        LOG.debug("File data: " + developers.toString());

        assertEquals(developers.get(0).getFullName(), "Squidward Tentacles");
        LOG.debug("Squidward is da best!");
    }

    @Test
    public void weighedAverageCalculationTest() {
        File inputXmlFile = new File(this.getClass().getResource(FILE_NAME).getFile());
        Document xmlData = XmlHelper.readXmlFile(inputXmlFile.getAbsolutePath());

        List<Developer> developers = developersService.createDevelopersListData(xmlData);
        Collections.sort(developers);

        LOG.debug("SponeBob weighted average: " + developers.get(1).getWeightedAverageOfBurnedStoryPoints());

        assertEquals(developers.get(1).getWeightedAverageOfBurnedStoryPoints(), 4.5f, 0.0f);
    }
}
