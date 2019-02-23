package com.github.Magi1053.SCB200;

import com.dalite.scb200.SCB200Server;

import static spark.Spark.post;

public class API {
    public static void main(String[] args) throws Exception {
        int i = 0;
        SCB200Server server = new SCB200Server(args[0]);
        post("/preset/:id", (request, response) -> {
            try {
                String param = request.params(":id");
                int id = Integer.parseInt(param);
                if (id >= 1 && id <= 5) {
                    server.applyCustomAspectRatio(i, id + 4);
                    return "Activated Preset: " + param;
                }
                return "Unknown Preset " + param;
            } catch (NumberFormatException ignored) {
                return "Preset must be a number";
            }
        });
        post("/relay/:cmd", (request, response) -> {
            String param = request.params(":cmd").toLowerCase();
            String cmd = "";
            switch (param) {
                case "stop":
                    cmd = "ST";
                    break;
                case "up":
                    cmd = "UP";
                    break;
                case "down":
                    cmd = "DN";
                    break;
            }
            if (!cmd.isEmpty()) {
                server.setRelayStatus(i, cmd);
                return "Sending relay: " + param;
            }
            return "Unknown relay " + param;
        });
    }
}
