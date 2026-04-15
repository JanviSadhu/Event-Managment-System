package Util;

import Model.Participant;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileExportUtil {

    public static void exportParticipantsToCSV(int eventId, List<Participant> participants) {
        String fileName = "event_" + eventId + "_participants.csv";

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.append("Participant ID,Name,Email\n");

            for (Participant participant : participants) {
                writer.append(String.valueOf(participant.getId())).append(",");
                writer.append(participant.getName()).append(",");
                writer.append(participant.getEmail()).append("\n");
            }

            System.out.println("CSV file exported successfully: " + fileName);
        } catch (IOException e) {
            System.out.println("Error exporting CSV: " + e.getMessage());
        }
    }

    public static void exportParticipantsToJSON(int eventId, List<Participant> participants) {
        String fileName = "event_" + eventId + "_participants.json";

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("[\n");

            for (int i = 0; i < participants.size(); i++) {
                Participant participant = participants.get(i);

                writer.write("  {\n");
                writer.write("    \"id\": " + participant.getId() + ",\n");
                writer.write("    \"name\": \"" + participant.getName() + "\",\n");
                writer.write("    \"email\": \"" + participant.getEmail() + "\"\n");
                writer.write("  }");

                if (i < participants.size() - 1) {
                    writer.write(",");
                }

                writer.write("\n");
            }

            writer.write("]");

            System.out.println("JSON file exported successfully: " + fileName);
        } catch (IOException e) {
            System.out.println("Error exporting JSON: " + e.getMessage());
        }
    }
}
