/*
 * geothermal_heating_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:57 by COMSOL 6.3.0.290. */
public class geothermal_heating_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Pipe_Flow_Module\\Heat_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").create("pc1", "ParametricCurve");
    model.component("comp1").geom("geom1").feature("pc1").set("parmax", 24);
    model.component("comp1").geom("geom1").feature("pc1").set("coord", new String[]{"cos(pi*s)", "sin(pi*s)", ""});
    model.component("comp1").geom("geom1").feature("pc1").setIndex("coord", "0.1*s", 2);
    model.component("comp1").geom("geom1").run("pc1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 1, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 2);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 1.1, 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 2);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 1.1, 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 2.6, 2, 2);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 1.1, 3, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 1.5, 3, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 2.6, 3, 2);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 1, 0, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 2.4, 0, 2);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 1, 1, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 1.5, 1, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 2.4, 1, 2);
    model.component("comp1").geom("geom1").run("pol2");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("pc1", "pol1", "pol2");
    model.component("comp1").geom("geom1").feature("mir1").set("pos", new double[]{0, 1.5, 0});
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{0, 1, 0});
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("mir1", "pc1", "pol1", "pol2");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{4, 1, 1});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new int[]{-3, 0, 0});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("pol3", "Polygon");
    model.component("comp1").geom("geom1").feature("pol3").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 1, 0, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 1.5, 0, 1);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 2.4, 0, 2);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", -15, 1, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 1.5, 1, 1);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 2.4, 1, 2);
    model.component("comp1").geom("geom1").run("pol3");
    model.component("comp1").geom("geom1").create("pol4", "Polygon");
    model.component("comp1").geom("geom1").feature("pol4").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol4").setIndex("table", 1.1, 0, 0);
    model.component("comp1").geom("geom1").feature("pol4").setIndex("table", 1.5, 0, 1);
    model.component("comp1").geom("geom1").feature("pol4").setIndex("table", 2.6, 0, 2);
    model.component("comp1").geom("geom1").feature("pol4").setIndex("table", -15, 1, 0);
    model.component("comp1").geom("geom1").feature("pol4").setIndex("table", 1.5, 1, 1);
    model.component("comp1").geom("geom1").feature("pol4").setIndex("table", 2.6, 1, 2);
    model.component("comp1").geom("geom1").run("pol4");
    model.component("comp1").geom("geom1").create("arr2", "Array");
    model.component("comp1").geom("geom1").feature("arr2").selection("input").set("arr1", "pol3", "pol4");
    model.component("comp1").geom("geom1").feature("arr2").set("fullsize", new int[]{1, 2, 1});
    model.component("comp1").geom("geom1").feature("arr2").set("displ", new int[]{0, 10, 0});
    model.component("comp1").geom("geom1").run("arr2");
    model.component("comp1").geom("geom1").create("pol5", "Polygon");
    model.component("comp1").geom("geom1").feature("pol5").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol5").setIndex("table", -15, 0, 0);
    model.component("comp1").geom("geom1").feature("pol5").setIndex("table", 1.5, 0, 1);
    model.component("comp1").geom("geom1").feature("pol5").setIndex("table", 2.4, 0, 2);
    model.component("comp1").geom("geom1").feature("pol5").setIndex("table", -15, 1, 0);
    model.component("comp1").geom("geom1").feature("pol5").setIndex("table", 11.5, 1, 1);
    model.component("comp1").geom("geom1").feature("pol5").setIndex("table", 2.4, 1, 2);
    model.component("comp1").geom("geom1").feature("pol5").setIndex("table", -28, 2, 0);
    model.component("comp1").geom("geom1").feature("pol5").setIndex("table", 11.5, 2, 1);
    model.component("comp1").geom("geom1").feature("pol5").setIndex("table", 6, 2, 2);
    model.component("comp1").geom("geom1").feature("pol5").setIndex("table", -35, 3, 0);
    model.component("comp1").geom("geom1").feature("pol5").setIndex("table", 11.5, 3, 1);
    model.component("comp1").geom("geom1").feature("pol5").setIndex("table", 10, 3, 2);
    model.component("comp1").geom("geom1").feature("pol5").setIndex("table", -45, 4, 0);
    model.component("comp1").geom("geom1").feature("pol5").setIndex("table", 9, 4, 1);
    model.component("comp1").geom("geom1").feature("pol5").setIndex("table", 10, 4, 2);
    model.component("comp1").geom("geom1").run("pol5");
    model.component("comp1").geom("geom1").create("pol6", "Polygon");
    model.component("comp1").geom("geom1").feature("pol6").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol6").setIndex("table", -15, 0, 0);
    model.component("comp1").geom("geom1").feature("pol6").setIndex("table", 1.5, 0, 1);
    model.component("comp1").geom("geom1").feature("pol6").setIndex("table", 2.6, 0, 2);
    model.component("comp1").geom("geom1").feature("pol6").setIndex("table", -15, 1, 0);
    model.component("comp1").geom("geom1").feature("pol6").setIndex("table", 11.5, 1, 1);
    model.component("comp1").geom("geom1").feature("pol6").setIndex("table", 2.6, 1, 2);
    model.component("comp1").geom("geom1").feature("pol6").setIndex("table", -28, 2, 0);
    model.component("comp1").geom("geom1").feature("pol6").setIndex("table", 11.5, 2, 1);
    model.component("comp1").geom("geom1").feature("pol6").setIndex("table", 6.2, 2, 2);
    model.component("comp1").geom("geom1").feature("pol6").setIndex("table", -35, 3, 0);
    model.component("comp1").geom("geom1").feature("pol6").setIndex("table", 11.5, 3, 1);
    model.component("comp1").geom("geom1").feature("pol6").setIndex("table", 10.2, 3, 2);
    model.component("comp1").geom("geom1").feature("pol6").setIndex("table", -45, 4, 0);
    model.component("comp1").geom("geom1").feature("pol6").setIndex("table", 9, 4, 1);
    model.component("comp1").geom("geom1").feature("pol6").setIndex("table", 10.2, 4, 2);
    model.component("comp1").geom("geom1").run("pol6");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input")
         .set("arr2(1,2,1,1)", "arr2(1,2,1,10)", "arr2(1,2,1,11)", "arr2(1,2,1,12)", "arr2(1,2,1,13)", "arr2(1,2,1,14)", "arr2(1,2,1,15)", "arr2(1,2,1,16)", "arr2(1,2,1,17)", "arr2(1,2,1,18)", "arr2(1,2,1,19)", "arr2(1,2,1,2)", "arr2(1,2,1,20)", "arr2(1,2,1,21)", "arr2(1,2,1,22)", "arr2(1,2,1,23)", "arr2(1,2,1,24)", "arr2(1,2,1,25)", "arr2(1,2,1,26)", "arr2(1,2,1,3)", "arr2(1,2,1,4)", "arr2(1,2,1,5)", "arr2(1,2,1,6)", "arr2(1,2,1,7)", "arr2(1,2,1,8)", "arr2(1,2,1,9)");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", 30);
    model.component("comp1").geom("geom1").feature("rot1").set("pos", new double[]{-15, 11.5, 0});
    model.component("comp1").geom("geom1").run("rot1");

    model.title(null);

    model.description("");

    model.label("geothermal_heating_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
