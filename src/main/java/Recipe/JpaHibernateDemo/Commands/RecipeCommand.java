package Recipe.JpaHibernateDemo.Commands;

import java.util.HashSet;
import java.util.Set;


import Recipe.JpaHibernateDemo.Entities.Difficulty;
//A Command Object for Recipe Entity
public class RecipeCommand {
	public Long id;	
	 private Difficulty difficulty;
	 private String name;
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
   private String directions;
   private NotesCommand recipeNotes;
   private Set<IngredientCommand> ingredients =new HashSet<IngredientCommand>();
   private Set<CategoryCommand> categories=new HashSet<CategoryCommand>();
  public Set<CategoryCommand> getCategories() {
		return categories;
	}
	 public Integer getCookTime() {
		return cookTime;
	}	
   public String getDescription() {
		return description;
	}
   public Difficulty getDifficulty() {
		return difficulty;
	}
   
	public String getDirections() {
		return directions;
	}
	public Long getId() {
		return id;
	}
   
	public Set<IngredientCommand> getIngredients() {
		return ingredients;
	}
   public String getName() {
		return name;
	}
	public Integer getPrepTime() {
		return prepTime;
	}
	public NotesCommand getRecipeNotes() {
		return recipeNotes;
	}
	public Integer getServings() {
		return servings;
	}
	public String getSource() {
		return source;
	}
	public String getUrl() {
		return url;
	}
	public void setCategories(Set<CategoryCommand> categories) {
		this.categories = categories;
	}
	public void setCookTime(Integer cookTime) {
		this.cookTime = cookTime;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}
	public void setDirections(String directions) {
		this.directions = directions;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setIngredients(Set<IngredientCommand> ingredients) {
		this.ingredients = ingredients;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPrepTime(Integer prepTime) {
		this.prepTime = prepTime;
	}
	public void setRecipeNotes(NotesCommand recipeNotes) {
		this.recipeNotes = recipeNotes;
	}
	public void setServings(Integer servings) {
		this.servings = servings;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	
}
