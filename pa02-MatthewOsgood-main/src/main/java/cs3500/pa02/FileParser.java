package cs3500.pa02;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * represents reading and writing from files
 */
public class FileParser {
  /**
   * used to write to the desired .md output path
   */
  private final Path outputPath;
  /**
   * the .sr file that will be created and written to
   */
  private final Path srOutput;

  /**
   * creates a FileParser
   *
   * @param outputPath the .md path that will be written to
   */
  public FileParser(Path outputPath) {
    this.outputPath = outputPath;
    this.srOutput = this.setSrOutput();
  }

  /**
   * converts this .md output path into a new .sr path
   *
   * @return the path to the .sr output file
   */
  private Path setSrOutput() {
    StringBuilder srString = new StringBuilder(this.outputPath.toString());
    srString.delete(srString.length() - 2, srString.length());
    srString.append("sr");
    return Path.of(srString.toString());
  }

  /**
   * reads all important lines and headers from the given path and writes them to the output path
   *
   * @param inputPath the path to the .md file that will be read
   * @throws IOException if file cannot be written to
   */
  public void parse(Path inputPath) throws IOException {
    FileWriter fileWriter = new FileWriter(this.outputPath.toFile(), true);
    Scanner scanner = new Scanner(inputPath);
    StringBuilder body = new StringBuilder();
    boolean onFirstHeader = true;

    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      if (line.startsWith("#")) { //header case
        this.parseBody(body, fileWriter);
        if (!onFirstHeader) {
          fileWriter.write("\n");
        }
        onFirstHeader = false;
        body = new StringBuilder();
        fileWriter.write(line + "\n");
      } else { //non header case
        body.append(" ");
        //handles bullet point that spans multiple lines and indents on subsequent lines
        body.append(line.stripLeading());
      }
    }
    this.parseBody(body, fileWriter);
    fileWriter.write("\n");
    fileWriter.close();
    scanner.close();
  }

  /**
   * parses the body of text under a  header and writes the bracketed phrases
   * using the given file writer
   *
   * @param body body of text under a header
   * @param fileWriter file writer used to write to the output file
   * @throws IOException if the output file cannot be written to
   */
  private void parseBody(StringBuilder body, FileWriter fileWriter) throws IOException {
    Pattern bracketed = Pattern.compile("\\[\\[(.*?)]]");
    Matcher matcher = bracketed.matcher(body);
    while (matcher.find()) {
      StringBuilder str = new StringBuilder(matcher.group());
      str.delete(0, 2);
      str.delete(str.length() - 2, str.length());
      if (str.toString().contains(":::")) {
        this.writeQuestion(str.toString());
      } else {
        str.insert(0, "- ");
        str.append("\n");
        fileWriter.write(str.toString());
      }
    }
  }

  /**
   * appends the given Q&A phrase to the path with the same name as the given path
   * questions are given a difficulty of hard
   *
   * @param question the Q&A phrase to be written
   */
  private void writeQuestion(String question) throws IOException {
    Scanner scanner = new Scanner(question);
    scanner.useDelimiter(":::");
    try (FileWriter writer = new FileWriter(this.srOutput.toFile(), true)) {
      HardQ hardQ = new HardQ(scanner.next(), scanner.next());
      writer.write(hardQ.toString());
    }
  }
}
