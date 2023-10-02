package cs3500.pa02;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/**
 * used to walk the file system and collect all .md files
*/
public class FileWalker implements FileVisitor<Path> {
  /**
   * represents the list of md files as Paths
   */
  private ArrayList<Path> filePaths;
  /**
   * represents how the files should be sorted
   */
  private final SortType sortType;

  /**
   * represents all md files with their creation time
   */
  public ArrayList<MarkDownFile> mdFiles;

  /**
   * constructor for FileWalker
   *
   * @param sortType how the files should be sorted when the sort method is used
   */
  public FileWalker(SortType sortType) {
    this.filePaths = new ArrayList<>();
    this.sortType = sortType;
    this.mdFiles = new ArrayList<>();
  }

  /**
   * for testing only!
   *
   * @return filePaths
   */
  public ArrayList<Path> getFilePaths() {
    return filePaths;
  }

  /**
   * returns CONTINUE
   *
   * @param dir   a reference to the directory
   * @param attrs the directory's basic attributes
   * @return FileVisitResult.CONTINUE
   */
  @Override
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
    return FileVisitResult.CONTINUE;
  }

  /**
   * adds file to filePaths and mdFiles if it is a regular .md file
   *
   * @param file  a reference to the file
   * @param attrs the file's basic attributes
   * @return FileVisitResult.CONTINUE
   */
  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
    if (attrs.isRegularFile() && file.getFileName().toString().endsWith(".md")) {
      this.filePaths.add(file);
      this.mdFiles.add(new MarkDownFile(file, attrs.creationTime()));
    }
    return FileVisitResult.CONTINUE;
  }

  /**
   * for when a file fails to be visited
   *
   * @param file a reference to the file
   * @param exc  the I/O exception that prevented the file from being visited
   * @return  FileVisitResult.CONTINUE
   */
  @Override
  public FileVisitResult visitFileFailed(Path file, IOException exc) {
    this.handleVisitFileFailed(file, exc);
    return FileVisitResult.CONTINUE;
  }

  /**
   * returns CONTINUE
   *
   * @param dir a reference to the directory
   * @param exc {@code null} if the iteration of the directory completes without
   *            an error; otherwise the I/O exception that caused the iteration
   *            of the directory to complete prematurely
   * @return FileVisitResult.CONTINUE
   */
  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
    return FileVisitResult.CONTINUE;
  }

  /**
   * handles the given error by throwing it
   *
   * @param file the file that could be visitted
   * @param exc the exception that will be thrown
   * @return if the error was handled
   */
  public boolean handleVisitFileFailed(Path file, IOException exc) {
    System.err.println("could not visit file: " + file);
    exc.printStackTrace();
    return true;
  }

  /**
   * sorts filePaths based on the sortType
   *
   * @return the sorted list of paths
   */
  public ArrayList<Path> sort() {
    switch (this.sortType) {
      case FILENAME -> this.filePaths.sort(new FileNameComparator());
      case CREATED -> {
        this.mdFiles.sort(new CreatedComparator());
        this.updateFilePaths();
      }

      default -> this.filePaths.sort(new ModifiedComparator());
    }
    return this.filePaths;
  }

  /**
   * updates filePaths to match the order of mdFiles
   */
  private void updateFilePaths() {
    ArrayList<Path> paths = new ArrayList<>();
    for (MarkDownFile mdFile : this.mdFiles) {
      paths.add(mdFile.getPath());
    }
    this.filePaths = paths;
  }
}
