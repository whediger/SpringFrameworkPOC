package Recipe.JpaHibernateDemo.Controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import Recipe.JpaHibernateDemo.Entities.Ingredient;
import Recipe.JpaHibernateDemo.Entities.Recipe;
import Recipe.JpaHibernateDemo.Entities.UnitOfMeasure;
import Recipe.JpaHibernateDemo.Repository.RecipeRepository;
import Recipe.JpaHibernateDemo.Service.RecipeService;
@Controller
public class RecipeController {
	
	private RecipeService recpie_service;// Constructor based Dependency Injection
	@Autowired
	private List<Recipe> recipe_list;
	@Autowired
	private Recipe recipe;
    public RecipeController(RecipeService recpie_service) {
		super();
		this.recpie_service = recpie_service;
	}


	@RequestMapping("/getRecipe")
	public String getRecipeList(Model model) {
    	this.recipe_list =  this.recpie_service.findAll();
		model.addAttribute("Recipes",this.recipe_list);
		return "RecipeList";
		
	
	}
	
	@RequestMapping("/getRecipeById{id}")
	public String getRecipeById(@PathVariable String id, Model model) throws Exception {
    	
		this.recipe = this.recpie_service.findById(id);
		model.addAttribute("Recipe",this.recipe);
		return "RecipeById";
		
	
	}
	
	
}
