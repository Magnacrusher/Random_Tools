package mediaRenamePackage;

import junit.framework.TestCase;
import static org.junit.Assert.*;
import org.junit.Test;

public class NameUpdateTest extends TestCase {
	
	
//	public void testFailure() throws Exception
//	{
//	     fail();
//	}
	
	@Test
	public void testGetUpdatedName1()
	{
	     
		String titleraw="2 Guns (2013) DVDRip XviD-MAXSPEED www.torentz.3xforum.ro.avi";
		String titlecorrected=mediaRename.getUpdatedName(titleraw);
		String titletruth="2 Guns.avi";
		assertEquals(titlecorrected,titletruth); 
	} 
	
	@Test
	public void testGetUpdatedName2()
	{
	     
		String titleraw="12 Years a Slave.avi";
		String titlecorrected=mediaRename.getUpdatedName(titleraw);
		String titletruth="12 Years a Slave.avi";
		assertEquals(titlecorrected,titletruth); 
	} 
	
	@Test
	public void testGetUpdatedName3()
	{
	     
		String titleraw="Airplane! (1980).avi";
		String titlecorrected=mediaRename.getUpdatedName(titleraw);
		String titletruth="Airplane!.avi";
		assertEquals(titlecorrected,titletruth); 
	} 
	
	@Test
	public void testGetUpdatedName4()
	{
	     
		String titleraw="Flight.2012.DVDSCR.x264.AAC-BiGKATS.mp4";
		String titlecorrected=mediaRename.getUpdatedName(titleraw);
		String titletruth="Flight.mp4";
		assertEquals(titlecorrected,titletruth); 
	} 
}
