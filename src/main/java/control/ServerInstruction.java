package control;

public class ServerInstruction implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private ServerInstructionType instruction;
	private Object data;

	public ServerInstruction(ServerInstructionType instruction,Object data) {
		// TODO Auto-generated constructor stub
		this.instruction = instruction;
		this.data = data;
	}

	public ServerInstructionType getInstruction() {
		return instruction;
	}

	public void setInstruction(ServerInstructionType instruction) {
		this.instruction = instruction;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}

