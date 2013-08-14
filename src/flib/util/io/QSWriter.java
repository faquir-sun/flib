package flib.util.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class QSWriter extends Writer{
	public File 				outFile = null;
	public String				NEW_LINE = "\r\n";
	public BufferedWriter 		bw=null;
	public static String 		ENCODING="UTF-8";
	
	public QSWriter(File of, boolean append) throws Exception
	{
		this.outFile = of;		
		bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(of, append), ENCODING));		
	}
	
	public QSWriter(File of)throws Exception{this(of, false);}
	
	public QSWriter(String of, boolean append)throws Exception
	{
		this(new File(of), append);
	}
	public QSWriter(String of)throws Exception{this(of, false);}
	
	public void reopen(File of)throws IOException {reopen(of, false);}
	public void reopen(File of, boolean append) throws IOException
	{
		close();
		this.outFile = of;
		bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(of, append), ENCODING));
	}
	
	public void line(String line) throws IOException
	{
		if(bw!=null)
		{
			bw.append(String.format("%s%s", line, NEW_LINE));
		}
		else throw new IOException("Not open yet");
	}
	
	@Override
	public void close()
	{
		if(bw!=null)
		{
			try
			{
				bw.flush();
				bw.close();
				bw = null;
			}
			catch(IOException ioe){ioe.printStackTrace();}
		}		
	}

	@Override
	public void flush() throws IOException {
		if(bw!=null)
		{
			bw.flush();	
		}
		else throw new IOException("Not open yet");
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		if(bw!=null)
		{
			bw.write(cbuf, off, len);
		}
		else throw new IOException("Not open yet");
	}
}