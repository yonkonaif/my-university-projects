
package UberSystemProgram;

public class UberTree {
    private CaptainNode root;
    
    public UberTree() {
    }
    
    //to add captain to the BST
    public String addCaptain(int id, String name){
        root = addCaptain(root, id, name);
        return searchCaptain(id).info();
    }
    
    private CaptainNode addCaptain(CaptainNode root, int id, String name){
        if(root == null)
            root = new CaptainNode(id, name);
        else{
            if(id < root.getId())
                root.setLeft(addCaptain(root.getLeft(), id, name));
            else
                root.setRight(addCaptain(root.getRight(), id, name));
        }
        return root;
    }
    
    //to display ALL the captains
    public String displayAllCaptainsInfo(){
        StringBuilder output = new StringBuilder();
        output = displayAllCaptainsInfo(root, output);
        return output.toString();
    }
    
    private StringBuilder displayAllCaptainsInfo(CaptainNode root, StringBuilder output){
        if(root != null){
            displayAllCaptainsInfo(root.getLeft(), output);
            output.append(root.info()).append("\n\n--------------------------------------------------------\n\n");
            displayAllCaptainsInfo(root.getRight(), output);
        }
        return output;
    }
    
    //to display one captain
    public String displayCaptainById(int id){
        return displayCaptainById(root, id);
    }
    
    private String displayCaptainById(CaptainNode root, int id){
        CaptainNode captain = searchCaptain(root, id);
        if(captain == null)
            return "Couldn’t find any captain with ID number " + id;
        return captain.info();
    }
    
    //to booking a ride
    public String bookRide(int id){
        return bookRide(root, id);
    }
            
    private String bookRide(CaptainNode root, int id){
        //search for captain
        CaptainNode captain = searchCaptain(root, id);
        
        //check if captain is not found
        if(captain == null)
            return "Couldn’t find any captain with ID number " + id;
        
        //check if captain is not available
        if(!captain.isAvailable())
            return "The captain " + captain.getName() + " is not available. He is on another ride!";
        
        //change captain to not available
        captain.setAvailable(false);
        
        //output message
        return "Book a new Ride with captain " + captain.getId();
    }
    
    //to search for a captain
    public CaptainNode searchCaptain(int id){
        return searchCaptain(root, id);
    }
    
    private CaptainNode searchCaptain(CaptainNode root, int id){
        //if not fount
        if(root == null)
            return null;
        
        //if found
        if(root.getId() == id)
            return root;
        
        else{
            //if id is less go left
            if(id < root.getId())
                return(searchCaptain(root.getLeft(), id));
            
            //if id is bigger go right
            else
                return(searchCaptain(root.getRight(), id));
        }
    }
    
    //to finish a ride
    public String finishRide(int id, int satisfaction){
        return finishRide(root, id, satisfaction);
    }
    
    private String finishRide(CaptainNode root, int id, int satisfaction){
        //search for captain
        CaptainNode captain = searchCaptain(root, id);
        
        //if captain is not found
        if(captain == null)
            return "Couldn’t find any captain with ID number " + id;
        
        //check if captain is available and not in s ride
        if(captain.isAvailable())
            return "The captain " + captain.getName() + " is not in a ride!";
        
        //change captain to available
        captain.setAvailable(true);
        
        //to store the satisfications new value
        int sat = 0;
        
        //change the zero to a -1 to add to the stars
        sat = (satisfaction == 0) ? -1 : 1;
        
        //to make sure stars do not go under 0
        if(captain.getStar() > 0 && sat == -1){
            captain.setStar(sat);
        }
        
        //to make sure stars do not go above 5
        if(captain.getStar() < 5 && sat == 1){
            captain.setStar(sat);
        }
        
        return "FInish ride with captain " + captain.getId() + "\n" + captain.info();
    }
    
    //to delete a node
    public String deleteCaptain(int id) {
        return deleteCaptain(root, id);
    }
    
    public String deleteCaptain(CaptainNode root, int id){
        //identifires
        CaptainNode nodeToDelete;
        CaptainNode newNodeToDelete;
        CaptainNode parent;
        CaptainNode savedNode;
        
        //find the captain to delete
        nodeToDelete = searchCaptain(root, id);
        
        //if captain does not exist
        if(nodeToDelete == null)
            return "Couldn’t find any captain with ID number " + id;
        
        //the output message
        String output = "the captain " + nodeToDelete.getName() + " left uber";
        
        //find the parent of the captain node
        parent = parent(root, nodeToDelete);
        
        //if captain has no nodes below him
        if(isLeaf(nodeToDelete)) {
            if(parent == null)
                return null; 
            if(id < parent.getId())
                parent.setLeft(null);
            else
                parent.setRight(null);
            return output;
        }
        
        //has only one left child
        if (captainHasOnlyLeftChild(nodeToDelete)) {
            if (parent == null)
                nodeToDelete.getLeft();
            if (id <parent.getId())
		parent.setLeft(parent.getLeft().getLeft());
            else
		parent.setRight(parent.getRight().getLeft());
            return output;
        }
        
        //has only one right child
        if (captainHasOnlyRightChild(nodeToDelete)) {
            if (parent == null)
		return output;
            if (id <parent.getId())
		parent.setLeft(parent.getLeft().getRight());
            else
		parent.setRight(parent.getRight().getRight());
            return output;
        }
        
        newNodeToDelete = minNode(nodeToDelete.getRight());
        savedNode = newNodeToDelete;
        
        return output;
    }
    
    //----------------------------------------------------------------------------------------
    //methods related to the delete method
    public CaptainNode parent(CaptainNode root, CaptainNode captain){
        if (root == null || root == captain)
	    return null; 		
        if (root.getLeft()== captain || root.getRight() == captain)
	    return root; 		
        if (captain.getId() <root.getId())
	    return parent(root.getLeft(), captain);
        else if (captain.getId() >root.getId())
	    return parent(root.getRight(), captain);
        else
            return null;
    }
    
    //check if the captain node is the last node
    public boolean isLeaf(CaptainNode captain){
        return(captain.getLeft() == null && captain.getRight() == null);
    }
    
    //check if the captain node has a only a left child
    public Boolean captainHasOnlyLeftChild(CaptainNode captain){
	return (captain.getLeft()!= null && captain.getRight() == null);
    }    

    //check if the captain node has a only a right child
    public Boolean captainHasOnlyRightChild(CaptainNode captain){
	return (captain.getLeft()== null && captain.getRight() != null);
    }
    
    //find the smallest node to replace the deleted one
    public CaptainNode minNode(CaptainNode captain){
        if(captain == null)
            return null;
        else if(captain.getLeft() == null)
            return captain;
        else
            return minNode(captain.getLeft());
    }
    
    //find the largest node to replace the deleted one
    public CaptainNode maxNode(CaptainNode captain){
        if(captain == null)
            return null;
        else if(captain.getRight() == null)
            return captain;
        else
            return maxNode(captain.getRight());
    }
}
