interface CommunicationDevice {
    void sendLocation(String id, String location);
}

class BluetoothDevice {
    public void transmit(String id, String location) {
        System.out.println("Bluetooth transmitting: " + id + " at " + location);
    }
}

class ZigbeeDevice implements CommunicationDevice {
    public void sendLocation(String id, String location) {
        System.out.println("Zigbee sending: " + id + " at " + location);
    }
}

public class BluetoothToZigbeeAdapter implements CommunicationDevice {
    private BluetoothDevice bluetoothDevice;

    public BluetoothToZigbeeAdapter(BluetoothDevice device) {
        this.bluetoothDevice = device;
    }

    public void sendLocation(String id, String location) {
        bluetoothDevice.transmit(id, location);
    }
}
