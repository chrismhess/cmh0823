public record Tool(String toolCode, String toolType, String toolBrand) {
    /**
     * Constructor for a tool object requiring the following parameters
     *
     * @param toolCode  the unique tool code used for looking up tools, uniqueness is managed by the collection that
     *                  stores the tool inventory in the point of sale object
     * @param toolType  the type tool this tool code represents, used for looking up additional tool info in tool info
     *                  collection in point of sale object
     * @param toolBrand the brand of this specific tool such as DeWalt, Stihl, Ridgid etc.
     */
    public Tool {
    }

    /**
     * This class is purposefully missing the setter methods as the fields are set as part of the constructor's logic
     * and once these fields are created they should not be edited by any other object given they represent tools in
     * the tool inventory.
     */

    /**
     * Gets the tool code for the given tool object
     * @return the string representing the tool code
     */
    @Override
    public String toolCode() {
        return toolCode;
    }

    /**
     * Gets the tool type for the given tool object
     * @return the string representing the tool type
     */
    @Override
    public String toolType() {
        return toolType;
    }

    /**
     * Gets the tool brand for the given tool object
     * @return the string representing the tool brand
     */
    @Override
    public String toolBrand() {
        return toolBrand;
    }
}
