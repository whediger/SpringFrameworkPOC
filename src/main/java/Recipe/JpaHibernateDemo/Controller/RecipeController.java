package Recipe.JpaHibernateDemo.Controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Recipe.JpaHibernateDemo.CommandConverters.CategoryEntityToCategoryCommand;
import Recipe.JpaHibernateDemo.CommandConverters.RecipeCommandToRecipeEntity;
import Recipe.JpaHibernateDemo.Commands.CategoryCommand;
import Recipe.JpaHibernateDemo.Commands.IngredientCommand;
import Recipe.JpaHibernateDemo.Commands.RecipeCommand;
import Recipe.JpaHibernateDemo.Entities.Category;
import Recipe.JpaHibernateDemo.Entities.Ingredient;
import Recipe.JpaHibernateDemo.Entities.Recipe;
import Recipe.JpaHibernateDemo.Entities.UnitOfMeasure;
import Recipe.JpaHibernateDemo.Repository.RecipeRepository;
import Recipe.JpaHibernateDemo.Service.CategoryService;
import Recipe.JpaHibernateDemo.Service.RecipeService;
import Recipe.JpaHibernateDemo.Service.UnitOfMeasureService;
@Controller
public class RecipeController {
	
	private RecipeService recpie_service;// Constructor based Dependency Injection
	@Autowired
	private List<Recipe> recipe_list;
	@Autowired
	private Recipe recipe;
	@Autowired
	private RecipeCommandToRecipeEntity recipeCon;
    public RecipeController(RecipeService recpie_service) {// Constructor based Dependency Injection
		super();
		this.recpie_service = recpie_service;
	}
    
    @Autowired
    private CategoryEntityToCategoryCommand categoryConverter;
    //added UOM Service
    @Autowired
    private UnitOfMeasureService uomService; // to do, Add this to the constructor and unit tests
    @Autowired
    private CategoryService catService;// to do, Add this to the constructor and unit tests
    private List<IngredientCommand> ingredientCommandList;  


	@RequestMapping("/getRecipe")
	public String getRecipeList(Model model) {
    	this.recipe_list =  this.recpie_service.findAll();
    	List<Category> catList= catService.findAll();
		model.addAttribute("Recipes",this.recipe_list);
		model.addAttribute("Categories",catList);
		return "RecipeList";
		}
	
	@RequestMapping("/getRecipeById{id}")
	public String getRecipeById(@PathVariable String id, Model model) throws Exception {
    	
		this.recipe = this.recpie_service.findById(id);
		model.addAttribute("Recipe",this.recipe);
		return "RecipeById";
	}
	

	@RequestMapping("/addNewRecipe")
	public String addNewRecipe(Model model) {
		RecipeCommand recipeCommand = new RecipeCommand();
		
		// Initializing Ingredient Command List, to set on the view
		ingredientCommandList = new ArrayList<IngredientCommand>();
		ingredientCommandList.add(new IngredientCommand()); // Adding one element to the list
		recipeCommand.setIngredients(ingredientCommandList);
		
		// Converting Category Entity to Category Command
		List<Category> catEntityList= catService.findAll();
		List<CategoryCommand> catCommandList = categoryConverter.converToCategoryCommandList(catEntityList);
		
		model.addAttribute("recipe", recipeCommand);
		model.addAttribute("uomList",uomService.findAll());
		model.addAttribute("catList",catCommandList);
	
		return "NewRecipe";
	}
	
	@PostMapping
	@RequestMapping("/saveOrUpdateRecipe")
	public String saveOrUpdateRecipe(@ModelAttribute RecipeCommand recipeCommand) {
		// Fixing the bug of getting the empty Category Objects
		//start
		List<CategoryCommand> resultCatCommandList = new ArrayList<CategoryCommand>();
		List<CategoryCommand> catCommandList = recipeCommand.getCategories();
		for(int i=0;i<catCommandList.size();i++) {
			CategoryCommand currentCatCommandObject;
			currentCatCommandObject =catCommandList.get(i);
			if(currentCatCommandObject.getId()==null || currentCatCommandObject.getId()==0) {
				
			}
			else {
				resultCatCommandList.add(currentCatCommandObject);
			}
		}
		recipeCommand.setCategories(resultCatCommandList);
		//end
		
		Recipe recipeEntity= recipeCon.convert(recipeCommand);
		this.recpie_service.save(recipeEntity);
	
		return "redirect:/getRecipe";
	}
	
	
	
	
	
	
	
}
