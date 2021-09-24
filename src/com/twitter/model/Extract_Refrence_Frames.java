package com.twitter.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;





//import com.video.watermark.main.Steganography;
import com.xuggle.mediatool.IMediaReader;
import com.xuggle.mediatool.MediaListenerAdapter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.mediatool.event.IVideoPictureEvent;
import com.xuggle.xuggler.Global;

public class Extract_Refrence_Frames 
{
	
	public static final double SECONDS_BETWEEN_FRAMES = 0;
	
	private static final String inputFilename = "E:/Harddisk Backup/workspace 2019/Swapnali Vazazkar Indira/microblogsummarization/WebContent/Files/videoplayback.mp4";
	public static final String framepath="E:/Harddisk Backup/workspace 2019/Swapnali Vazazkar Indira/microblogsummarization/WebContent/upload";
	public static final String ReferenceFrames_path = framepath+"/video/";
	public static int num = 0;
	public static String inputname=null;
	// The video stream index, used to ensure we display frames from one and
	// only one video stream from the media container.
	private static int mVideoStreamIndex = -1;
	
	// Time of last frame write
	private static long mLastPtsWrite = Global.NO_PTS;
	
	public static final long MICRO_SECONDS_BETWEEN_FRAMES = 2500000;
	    //(long)(Global.DEFAULT_PTS_PER_SECOND * SECONDS_BETWEEN_FRAMES);

	public static void main(String[] args) 
	{
		
		IMediaReader mediaReader = ToolFactory.makeReader(inputFilename);

		// stipulate that we want BufferedImages created in BGR 24bit color space
		mediaReader.setBufferedImageTypeToGenerate(BufferedImage.TYPE_3BYTE_BGR);
		
		mediaReader.addListener(new ImageSnapListener());

	    // read out the contents of the media file and
		// dispatch events to the attached listener
		while (mediaReader.readPacket() == null) ;

	}
        
public static void extractAllFramesFromVideo(String extractFrom) 
{
          
	         System.out.println(extractFrom);
             if(extractFrom.isEmpty())
             {
            	 JOptionPane.showMessageDialog(null, "Please select video");
            	 
             }
             else
             {
            	 System.out.println("Wait frames being extract...");
            	 IMediaReader mediaReader = ToolFactory.makeReader(extractFrom);
            	 
            	 File file=new File(extractFrom);
                 
                 String str=file.getName();
                 
                 System.out.println("input== "+str);
                 
                 inputname=str.substring(0,str.indexOf("."));
                 
                 System.out.println("\t path===="+str);

            	 // stipulate that we want BufferedImages created in BGR 24bit color space
            	 mediaReader.setBufferedImageTypeToGenerate(BufferedImage.TYPE_3BYTE_BGR);
		
            	 mediaReader.addListener(new ImageSnapListener());

            	 // read out the contents of the media file and
            	 // dispatch events to the attached listener
            	 while (mediaReader.readPacket() == null) ;
            	
 		         JOptionPane.showMessageDialog(null, "All Frames are extracted from Video");
             }
}

	public static class ImageSnapListener extends MediaListenerAdapter {

		public void onVideoPicture(IVideoPictureEvent event) {

			if (event.getStreamIndex() != mVideoStreamIndex) {
				// if the selected video stream id is not yet set, go ahead an
				// select this lucky video stream
				if (mVideoStreamIndex == -1){
					mVideoStreamIndex = event.getStreamIndex();
					System.out.println("ID :"+mVideoStreamIndex);
					System.out.println("Microsecond between frames"+MICRO_SECONDS_BETWEEN_FRAMES);
				}
				// no need to show frames from this video stream
				else
					return;
			}

			// if uninitialized, back date mLastPtsWrite so we get the very first frame
			if (mLastPtsWrite == Global.NO_PTS)
				mLastPtsWrite = event.getTimeStamp() - MICRO_SECONDS_BETWEEN_FRAMES;

			// if it's time to write the next frame
			if (event.getTimeStamp() - mLastPtsWrite >= MICRO_SECONDS_BETWEEN_FRAMES) {
								
				String outputFilename = dumpImageToFile(event.getImage());
			
				
				// indicate file written
				double seconds = ((double) event.getTimeStamp()) / Global.DEFAULT_PTS_PER_SECOND;
				System.out.printf(
						"at elapsed time of %6.3f seconds wrote: %s\n",
						seconds, outputFilename);

				// update last write time
				mLastPtsWrite += MICRO_SECONDS_BETWEEN_FRAMES;
			}

		}
		
		private String dumpImageToFile(BufferedImage image) {
			try {
				
				//String outputFilename = outputFilePrefix + System.currentTimeMillis() + ".png";
				String outputFilename = ReferenceFrames_path+ inputname+num + ".png";
				ImageIO.write(image, "png", new File(outputFilename));
				
				num = num + 1;
				return outputFilename;
			} 
			catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}

	}

}
