package edu.illinois.cs.cs124.ay2022.mp.models;

import android.content.Intent;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/*
 * Model storing information about a place retrieved from the backend server.
 *
 * You will need to understand some of the code in this file and make changes starting with MP1.
 */
@SuppressWarnings("unused")
public final class Place {
  /*
   * The Jackson JSON serialization library that we are using requires an empty constructor.
   * So don't remove this!
   */
  public Place() {
  }

  public Place(
      final String setId,
      final String setName,
      final double setLatitude,
      final double setLongitude,
      final String setDescription) {
    id = setId;
    name = setName;
    latitude = setLatitude;
    longitude = setLongitude;
    description = setDescription;
  }

  public Place(
      final String setId,
      final String setName,
      final double setLatitude,
      final double setLongitude,
      final String setDescription,
      final int setCheck,
      final String setType) {
    id = setId;
    name = setName;
    latitude = setLatitude;
    longitude = setLongitude;
    description = setDescription;
    check = setCheck;
    type = setType;
  }

  private int count = 0;

  public Place(String setName, String setComment, String setRate) {
    name = setName;
    if (setComment != null) {
      comment = setComment;
    }
    rate = Double.parseDouble(setRate);
  }

  public Place(String setName, String setNote, int setMark) {
    name = setName;
    note = setNote;
    mark = setMark;
  }

  // ID of the place
  private String id;

  public String getId() {
    return id;
  }

  // Name of the person who submitted this favorite place
  private String name;

  public String getName() {
    return name;
  }

  // Latitude and longitude of the place
  private double latitude = 190;

  public double getLatitude() {
    return latitude;
  }

  private double longitude = 190;

  public double getLongitude() {
    return longitude;
  }

  // Description of the place
  private String description;

  public String getDescription() {
    return description;
  }

  private int check;

  public int getCheck() {
    return check;
  }

  private String type;

  public String getType() {
    return type;
  }

  private String comment = "";

  public String getComment() {
    return comment;
  }

  private double rate;

  public double getRate() {
    return rate;
  }

  private int number;

  private int total = 0;

  public void setNumber(int input) {
    number = input;
  }

  public int getNumber() {
    return number;
  }

  public void writeComment(String input) {
    if (input != null && input.length() != 0 ) {
      total++;
      comment += ("\n" + String.valueOf(total) + ", " + input);
    }
  }

  public void writeRate(double input) {
      rate = (rate * count + input) / (count + 1);
      count++;
  }

  private String note;

  public String getNote() {
    return note;
  }

  public void writeNote(String input) {
    if (input != null) {
      note = input;
    }
  }

  private int mark;

  public int getMark() {
    return mark;
  }

  public void writeMark(int input) {
    mark = input;
  }

  public static List<Place> search(final List<Place> places, final String searchInput) {
    if (places == null || searchInput == null) {
      throw new IllegalArgumentException();
    }
    if (places.size() == 0 || searchInput.trim().length() == 0) {
      return places;
    }
    List<Place> list = new ArrayList<>();
    String sub = searchInput.trim();
    for (Place i : places) {
      // System.out.println(i.getDescription());
      StringBuilder deD = new StringBuilder();
      StringBuilder deE = new StringBuilder();
      char[] charA = i.getDescription().toCharArray();
      if (i.getType() != null) {
        // Add Type to search
        char[] charB = i.getType().toCharArray();
        for (char j : charB) {
          if (Character.isAlphabetic(j) || Character.isDigit(j) || j == ' ') {
            deE.append(j);
          }
          // '.', '!', '?', ',', ':', ';' and '/'
          if (j == '.' || j == '!' || j == '?' || j == ',' || j == ':' || j == ';' || j == '/') {
            deE.append(' ');
          }
        }
        // System.out.println(deD);
        String[] str1 = deE.toString().split(" ");
        for (String j : str1) {
          if (j.toLowerCase().equals(sub.toLowerCase(Locale.ROOT))) {
            list.add(i);
            break;
          }
        }
      }
      for (char j : charA) {
        if (Character.isAlphabetic(j) || Character.isDigit(j) || j == ' ') {
          deD.append(j);
        }
        // '.', '!', '?', ',', ':', ';' and '/'
        if (j == '.' || j == '!' || j == '?' || j == ',' || j == ':' || j == ';' || j == '/') {
          deD.append(' ');
        }
      }
      // System.out.println(deD);
      String[] str = deD.toString().split(" ");
      for (String j : str) {
        if (j.toLowerCase().equals(sub.toLowerCase(Locale.ROOT))) {
          list.add(i);
          break;
        }
      }
    }
    return list;
  }
}
