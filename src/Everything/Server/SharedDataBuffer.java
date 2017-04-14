package Everything.Server;

import java.util.LinkedList;

public class SharedDataBuffer {

    private LinkedList<String> messageHistory = new LinkedList<>();

    public synchronized void add(String message) {
        messageHistory.add(message);
    }

    public synchronized boolean inputReady(final String gameId) {
        final TigerIslandProtocol tip = new TigerIslandProtocol();

        for (String message : messageHistory) {
            if (message.contains("GAME") && tip.parseGameId(message).equals(gameId)) {
                return true;
            }
        }

        return false;
    }

    public synchronized String getNextMessage(final String gameId) {
        final TigerIslandProtocol tip = new TigerIslandProtocol();

        for (int i = 0; i < messageHistory.size(); i++) {
            String message = messageHistory.get(i);

            if (message.contains("GAME") && tip.parseGameId(message).equals(gameId)) {
                messageHistory.remove(i);
                return message;
            }
        }

        throw new RuntimeException("Check for new messages before you pull... I don't want to give you nulls");
    }

    public synchronized void clear() {
        messageHistory.clear();
    }
}
