package com.example.cinevibes.util;


import androidx.room.TypeConverter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Converters {

    // Converte List<Integer> in String per il database
    @TypeConverter
    public static String fromList(List<Integer> genreIds) {
        if (genreIds == null || genreIds.isEmpty()) return "";
        return genreIds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));  // Converte in "1,2,3"
    }

    // Converte String in List<Integer> quando viene letta dal database
    @TypeConverter
    public static List<Integer> toList(String data) {
        if (data == null || data.isEmpty()) return null;
        return Arrays.stream(data.split(","))  // Converte "1,2,3" in [1,2,3]
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
