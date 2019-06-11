package org.dkimi;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.instrument.Instrumentation;
import java.util.Map;
import java.util.Properties;
import java.util.jar.JarFile;

public final class DkimiBootstrap
{
    public static void premain(String agentOps, Instrumentation inst)
            throws Exception
    {
        String banner = readBanner();
        System.out.println(banner);

        Properties properties = loadConfigFromFile(agentOps);
        appendToBootstrapClassLoaderSearch(properties.getProperty("dkimi.home"), inst);

        DkimiAgent.init(properties, inst);
    }

    private static Properties loadConfigFromFile(String options) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        FileInputStream fis = new FileInputStream(options);
        copyStream(fis, bos);

        String content = new String(bos.toByteArray(), "UTF-8");
        content = replace(content, System.getenv());

        System.out.println(content);

        Properties properties = new Properties();
        properties.load(new ByteArrayInputStream(content.getBytes()));
        return properties;
    }

    private static void appendToBootstrapClassLoaderSearch(String home, Instrumentation inst) throws IOException {
        JarFile[] jars = listBootJars(home);
        JarFile[] arrayOfJarFile1 = jars; int i = arrayOfJarFile1.length; for (int j = 0; j < i; ++j) { JarFile jar = arrayOfJarFile1[j];
            inst.appendToBootstrapClassLoaderSearch(jar);
        }
    }

    private static JarFile[] listBootJars(String home) throws IOException {
        File dir = new File(new StringBuilder().append(home).append("/boot-libs").toString());
        File[] files = dir.listFiles(new FileFilter()
        {
            public boolean accept(File f) {
                return f.getName().endsWith(".jar");
            }

        });
        JarFile[] jars = new JarFile[files.length];
        for (int i = 0; i < files.length; ++i)
            jars[i] = new JarFile(files[i]);

        return jars;
    }

    public static void copyStream(InputStream source, OutputStream dest) throws IOException {
        int bytesToWrite;
        int bufferSize = 2048;

        byte[] buffer = new byte[bufferSize];
        try {
            while (true) {
                label56:while (true) { if ((bytesToWrite = source.read(buffer)) == -1)
                    break label56;

                    if (bytesToWrite != 0) break;
                    bytesToWrite = source.read();
                    if (bytesToWrite < 0)
                        break label56;
                    dest.write(bytesToWrite);
                }

                dest.write(buffer, 0, bytesToWrite);
            }
        } catch (IOException e) {
        }
        finally {
            try {
                label56: dest.flush();
            }
            catch (IOException localIOException4) {
            }
            try {
                source.close();
            }
            catch (IOException localIOException5) {
            }
            try {
                dest.close();
            }
            catch (IOException localIOException6) {
            }
        }
    }

    public static String replace(String text, Map<String, String> map) {
        return parse("${", "}", text, map);
    }

    public static String parse(String openToken, String closeToken, String text, Map<String, String> map) {
        if ((map == null) || (map.size() == 0))
            return text;

        if ((text == null) || (text.isEmpty()))
            return "";

        char[] src = text.toCharArray();
        int offset = 0;

        int start = text.indexOf(openToken, offset);
        if (start == -1)
            return text;

        StringBuilder builder = new StringBuilder();
        StringBuilder expression = null;
        while (start > -1) {
            if ((start > 0) && (src[(start - 1)] == '\\'))
            {
                builder.append(src, offset, start - offset - 1).append(openToken);
                label273: offset = start + openToken.length();
            }
            else {
                if (expression == null)
                    expression = new StringBuilder();
                else
                    expression.setLength(0);

                builder.append(src, offset, start - offset);
                offset = start + openToken.length();
                int end = text.indexOf(closeToken, offset);
                label273: while (true) { if (end <= -1) break label273;
                    if ((end <= offset) || (src[(end - 1)] != '\\'))
                        break;

                    expression.append(src, offset, end - offset - 1).append(closeToken);
                    offset = end + closeToken.length();
                    end = text.indexOf(closeToken, offset);
                }
                expression.append(src, offset, end - offset);
                offset = end + closeToken.length();

                if (end == -1)
                {
                    builder.append(src, start, src.length - start);
                    offset = src.length;
                } else {
                    String key = text.substring(start + openToken.length(), end);
                    String value = (String)map.get(key);
                    builder.append((value == null) ? "" : value);
                    offset = end + closeToken.length();
                }
            }
            start = text.indexOf(openToken, offset);
        }
        if (offset < src.length)
            builder.append(src, offset, src.length - offset);

        return builder.toString();
    }

    private static String readBanner() throws IOException {
        InputStream is = DkimiEnvironment.class.getResourceAsStream("/dkimi-banner.txt");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        copyStream(is, bos);
        return new String(bos.toByteArray());
    }
}