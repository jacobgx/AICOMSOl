/*
 * homogenization_of_periodic_microstructures.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:59 by COMSOL 6.3.0.290. */
public class homogenization_of_periodic_microstructures {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("cc", "CurvilinearCoordinates", "geom1");
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/cc", true);
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);

    model.param().label("General Parameters");
    model.param().set("pA0", "1[bar]");
    model.param().descr("pA0", "Absolute pressure");
    model.param().set("T0", "293.15[K]");
    model.param().descr("T0", "Temperature");
    model.param().set("useUniFibSqrPac", "1");
    model.param().descr("useUniFibSqrPac", "Unidirectional fiber composite square packing, activation boolean");
    model.param().set("useUniFibHexPac", "0");
    model.param().descr("useUniFibHexPac", "Unidirectional fiber composite hexagonal packing, activation boolean");
    model.param().set("useBiFib", "0");
    model.param().descr("useBiFib", "Bidirectional fiber composite, activation boolean");
    model.param().set("useBiNonCrimpFib", "0");
    model.param().descr("useBiNonCrimpFib", "Bidirectional noncrimp fiber composite, activation boolean");
    model.param().set("useBiPlainWeaveFib", "0");
    model.param().descr("useBiPlainWeaveFib", "Bidirectional plain weave fiber composite, activation boolean");
    model.param().set("usePrimCubicPart", "0");
    model.param().descr("usePrimCubicPart", "Particulate composite primitive cubic, activation boolean");
    model.param().set("useBodyCubicPart", "0");
    model.param().descr("useBodyCubicPart", "Particulate composite body centered cubic, activation boolean");
    model.param().set("useFaceCubicPart", "0");
    model.param().descr("useFaceCubicPart", "Particulate composite face centered cubic, activation boolean");
    model.param().set("useHoneycomb", "0");
    model.param().descr("useHoneycomb", "Honeycomb, activation boolean");
    model.param().set("useSandwich", "0");
    model.param().descr("useSandwich", "Sandwich, activation boolean");
    model.param().create("par2");
    model.param("par2").label("Parameters: Unidirectional Fiber Composite, Square Packing");
    model.param("par2").set("df1", "0.71365[mm]");
    model.param("par2").descr("df1", "Fiber diameter");
    model.param("par2").set("wm1", "1[mm]");
    model.param("par2").descr("wm1", "Cell width");
    model.param("par2").set("dm1", "1[mm]");
    model.param("par2").descr("dm1", "Cell depth");
    model.param("par2").set("hm1", "1[mm]");
    model.param("par2").descr("hm1", "Cell height");
    model.param("par2").set("internal_voids1", "0");
    model.param("par2").descr("internal_voids1", "Set 1 to create internal voids");
    model.param().create("par3");
    model.param("par3").label("Parameters: Unidirectional Fiber Composite, Hexagonal Packing");
    model.param("par3").set("df2", "0.61804[mm]");
    model.param("par3").descr("df2", "Fiber diameter");
    model.param("par3").set("sf2", "1");
    model.param("par3").descr("sf2", "Ratio of corner fiber diameter to center fiber diameter");
    model.param("par3").set("wm2", "1[mm]");
    model.param("par3").descr("wm2", "Cell width");
    model.param("par3").set("dm2", "1[mm]");
    model.param("par3").descr("dm2", "Cell depth");
    model.param("par3").set("hm2", "1.5[mm]");
    model.param("par3").descr("hm2", "Cell height");
    model.param().create("par4");
    model.param("par4").label("Parameters: Bidirectional Fiber Composite");
    model.param("par4").set("df3", "0.61804[mm]");
    model.param("par4").descr("df3", "Fiber diameter");
    model.param("par4").set("sf3", "1");
    model.param("par4").descr("sf3", "Ratio of corner fiber diameter to center fiber diameter");
    model.param("par4").set("wm3", "1[mm]");
    model.param("par4").descr("wm3", "Cell width");
    model.param("par4").set("dm3", "1[mm]");
    model.param("par4").descr("dm3", "Cell depth");
    model.param("par4").set("hm3", "1.5[mm]");
    model.param("par4").descr("hm3", "Cell height");
    model.param().create("par5");
    model.param("par5").label("Parameters: Bidirectional Noncrimp Fiber Composite");
    model.param("par5").set("wfx4", "0.65[mm]");
    model.param("par5").descr("wfx4", "Width of fiber strand in X direction");
    model.param("par5").set("hfx4", "0.065[mm]");
    model.param("par5").descr("hfx4", "Height of fiber strand in X direction");
    model.param("par5").set("wfy4", "0.65[mm]");
    model.param("par5").descr("wfy4", "Width of fiber strand in Y direction");
    model.param("par5").set("hfy4", "0.065[mm]");
    model.param("par5").descr("hfy4", "Height of fiber strand in Y direction");
    model.param("par5").set("wm4", "1[mm]");
    model.param("par5").descr("wm4", "Cell width");
    model.param("par5").set("dm4", "1[mm]");
    model.param("par5").descr("dm4", "Cell depth");
    model.param("par5").set("hm4", "0.2[mm]");
    model.param("par5").descr("hm4", "Cell height");
    model.param().create("par6");
    model.param("par6").label("Parameters: Bidirectional Plain Weave Fiber Composite");
    model.param("par6").set("afx5", "0.06[mm]");
    model.param("par6").descr("afx5", "First semiaxis of fiber strand in X direction");
    model.param("par6").set("bfx5", "0.03[mm]");
    model.param("par6").descr("bfx5", "Second semiaxis of fiber strand in X direction");
    model.param("par6").set("afy5", "0.06[mm]");
    model.param("par6").descr("afy5", "First semiaxis of fiber strand in Y direction");
    model.param("par6").set("bfy5", "0.03[mm]");
    model.param("par6").descr("bfy5", "Second semiaxis of fiber strand in Y direction");
    model.param("par6").set("nfx5", "2");
    model.param("par6").descr("nfx5", "Number of fibers strands in X direction");
    model.param("par6").set("nfy5", "2");
    model.param("par6").descr("nfy5", "Number of fibers strands in Y direction");
    model.param("par6").set("gap5", "0.03[mm]");
    model.param("par6").descr("gap5", "Gap between crossing fiber strands");
    model.param("par6").set("wm5", "1[mm]");
    model.param("par6").descr("wm5", "Cell width");
    model.param("par6").set("dm5", "1[mm]");
    model.param("par6").descr("dm5", "Cell depth");
    model.param("par6").set("hm5", "0.2[mm]");
    model.param("par6").descr("hm5", "Cell height");
    model.param().create("par7");
    model.param("par7").label("Parameters: Particulate Composite, Primitive Cubic");
    model.param("par7").set("dp6", "0.57588[mm]");
    model.param("par7").descr("dp6", "Particle diameter");
    model.param("par7").set("wm6", "1[mm]");
    model.param("par7").descr("wm6", "Cell width");
    model.param("par7").set("dm6", "1[mm]");
    model.param("par7").descr("dm6", "Cell depth");
    model.param("par7").set("hm6", "1[mm]");
    model.param("par7").descr("hm6", "Cell height");
    model.param("par7").set("internal_voids6", "0");
    model.param("par7").descr("internal_voids6", "Set 1 to create internal voids");
    model.param().create("par8");
    model.param("par8").label("Parameters: Particulate Composite, Body-Centered Cubic");
    model.param("par8").set("dp7", "0.45708[mm]");
    model.param("par8").descr("dp7", "Particle diameter");
    model.param("par8").set("sf7", "1");
    model.param("par8").descr("sf7", "Ratio of corner particle diameter to center particle diameter");
    model.param("par8").set("wm7", "1[mm]");
    model.param("par8").descr("wm7", "Cell width");
    model.param("par8").set("dm7", "1[mm]");
    model.param("par8").descr("dm7", "Cell depth");
    model.param("par8").set("hm7", "1[mm]");
    model.param("par8").descr("hm7", "Cell height");
    model.param().create("par9");
    model.param("par9").label("Parameters: Particulate Composite, Face-Centered Cubic");
    model.param("par9").set("dp8", "0.45708[mm]");
    model.param("par9").descr("dp8", "Particle diameter");
    model.param("par9").set("sf8", "1");
    model.param("par9").descr("sf8", "Ratio of corner particle diameter to center particle diameter");
    model.param("par9").set("wm8", "1[mm]");
    model.param("par9").descr("wm8", "Cell width");
    model.param("par9").set("dm8", "1[mm]");
    model.param("par9").descr("dm8", "Cell depth");
    model.param("par9").set("hm8", "1[mm]");
    model.param("par9").descr("hm8", "Cell height");
    model.param().create("par10");
    model.param("par10").label("Parameters: Honeycomb");
    model.param("par10").set("lh9", "0.5[mm]");
    model.param("par10").descr("lh9", "Length of horizontal wall of honeycomb cross section");
    model.param("par10").set("lo9", "0.5[mm]");
    model.param("par10").descr("lo9", "Length of oblique wall of honeycomb cross section");
    model.param("par10").set("theta9", "120[deg]");
    model.param("par10").descr("theta9", "Angle between horizontal and oblique walls of honeycomb cross section");
    model.param("par10").set("th9", "0.05[mm]");
    model.param("par10").descr("th9", "Thickness of honeycomb walls");
    model.param("par10").set("hm9", "1[mm]");
    model.param("par10").descr("hm9", "Cell height");
    model.param().create("par11");
    model.param("par11").label("Parameters: Sandwich");
    model.param("par11").set("nm10", "3");
    model.param("par11").descr("nm10", "Number of layers");
    model.param("par11").set("wm10", "1[mm]");
    model.param("par11").descr("wm10", "Cell width");
    model.param("par11").set("dm10", "1[mm]");
    model.param("par11").descr("dm10", "Cell depth");
    model.param("par11").set("hm10", "1[mm]");
    model.param("par11").descr("hm10", "Cell height");

    model.geom()
         .load(new String[]{"part1"}, "COMSOL_Multiphysics/Unit_Cells_and_RVEs/Fiber_Composites/unidirectional_fiber_square_packing.mph", new String[]{"part1"});
    model.geom()
         .load(new String[]{"part2"}, "COMSOL_Multiphysics/Unit_Cells_and_RVEs/Fiber_Composites/unidirectional_fiber_hexagonal_packing.mph", new String[]{"part1"});
    model.geom()
         .load(new String[]{"part3"}, "COMSOL_Multiphysics/Unit_Cells_and_RVEs/Fiber_Composites/bidirectional_fiber.mph", new String[]{"part1"});
    model.geom()
         .load(new String[]{"part4"}, "COMSOL_Multiphysics/Unit_Cells_and_RVEs/Fiber_Composites/bidirectional_non_crimp_fiber.mph", new String[]{"part1"});
    model.geom()
         .load(new String[]{"part5"}, "COMSOL_Multiphysics/Unit_Cells_and_RVEs/Fiber_Composites/bidirectional_plain_weave_fiber.mph", new String[]{"part1"});
    model.geom()
         .load(new String[]{"part6"}, "COMSOL_Multiphysics/Unit_Cells_and_RVEs/Particulate_Composites/particulate_primitive_cubic.mph", new String[]{"part1"});
    model.geom()
         .load(new String[]{"part7"}, "COMSOL_Multiphysics/Unit_Cells_and_RVEs/Particulate_Composites/particulate_body_centered_cubic.mph", new String[]{"part1"});
    model.geom()
         .load(new String[]{"part8"}, "COMSOL_Multiphysics/Unit_Cells_and_RVEs/Particulate_Composites/particulate_face_centered_cubic.mph", new String[]{"part1"});
    model.geom()
         .load(new String[]{"part9"}, "COMSOL_Multiphysics/Unit_Cells_and_RVEs/Honeycombs/honeycomb_hexagonal_cross_section.mph", new String[]{"part1"});
    model.geom()
         .load(new String[]{"part10"}, "COMSOL_Multiphysics/Unit_Cells_and_RVEs/Miscellaneous/sandwich.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("if1", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif1", "EndIf", "if1");
    model.component("comp1").geom("geom1").feature("if1").set("condition", "useUniFibSqrPac==1");
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "df", "df1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "wm", "wm1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "dm", "dm1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "hm", "hm1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "internal_voids", "internal_voids1");
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("if2", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif2", "EndIf", "if2");
    model.component("comp1").geom("geom1").feature().move("endif2", 4);
    model.component("comp1").geom("geom1").feature().move("if2", 3);
    model.component("comp1").geom("geom1").feature("if2").set("condition", "useUniFibHexPac==1");
    model.component("comp1").geom("geom1").run("if2");
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "df1", "df2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "sf", "sf2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "wm", "wm2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "dm", "dm2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "hm", "hm2");
    model.component("comp1").geom("geom1").run("pi2");
    model.component("comp1").geom("geom1").create("if3", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif3", "EndIf", "if3");
    model.component("comp1").geom("geom1").feature("if3").set("condition", "useBiFib==1");
    model.component("comp1").geom("geom1").feature().move("endif3", 7);
    model.component("comp1").geom("geom1").feature().move("if3", 6);
    model.component("comp1").geom("geom1").run("if3");
    model.component("comp1").geom("geom1").create("pi3", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi3").set("part", "part3");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "df1", "df3");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "sf", "sf3");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "wm", "wm3");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "dm", "dm3");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "hm", "hm3");
    model.component("comp1").geom("geom1").run("pi3");
    model.component("comp1").geom("geom1").create("if4", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif4", "EndIf", "if4");
    model.component("comp1").geom("geom1").feature("if4").set("condition", "useBiNonCrimpFib==1");
    model.component("comp1").geom("geom1").feature().move("endif4", 10);
    model.component("comp1").geom("geom1").feature().move("if4", 9);
    model.component("comp1").geom("geom1").run("if4");
    model.component("comp1").geom("geom1").create("pi4", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi4").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi4").set("part", "part4");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "wfx", "wfx4");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "hfx", "hfx4");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "wfy", "wfy4");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "hfy", "hfy4");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "wm", "wm4");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "dm", "dm4");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "hm", "hm4");
    model.component("comp1").geom("geom1").run("pi4");
    model.component("comp1").geom("geom1").create("if5", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif5", "EndIf", "if5");
    model.component("comp1").geom("geom1").feature("if5").set("condition", "useBiPlainWeaveFib==1");
    model.component("comp1").geom("geom1").feature().move("endif5", 13);
    model.component("comp1").geom("geom1").feature().move("if5", 12);
    model.component("comp1").geom("geom1").run("if5");
    model.component("comp1").geom("geom1").create("pi5", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi5").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi5").set("part", "part5");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "afx", "afx5");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "bfx", "bfx5");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "afy", "afy5");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "bfy", "bfy5");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "nfx", "nfx5");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "nfy", "nfy5");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "gap", "gap5");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "wm", "wm5");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "dm", "dm5");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "hm", "hm5");
    model.component("comp1").geom("geom1").run("pi5");
    model.component("comp1").geom("geom1").create("if6", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif6", "EndIf", "if6");
    model.component("comp1").geom("geom1").feature("if6").set("condition", "usePrimCubicPart==1");
    model.component("comp1").geom("geom1").feature().move("endif6", 16);
    model.component("comp1").geom("geom1").feature().move("if6", 15);
    model.component("comp1").geom("geom1").run("if6");
    model.component("comp1").geom("geom1").create("pi6", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi6").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi6").set("part", "part6");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "dp", "dp6");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "wm", "wm6");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "dm", "dm6");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "hm", "hm6");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "internal_voids", "internal_voids6");
    model.component("comp1").geom("geom1").run("pi6");
    model.component("comp1").geom("geom1").create("if7", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif7", "EndIf", "if7");
    model.component("comp1").geom("geom1").feature("if7").set("condition", "useBodyCubicPart==1");
    model.component("comp1").geom("geom1").feature().move("endif7", 19);
    model.component("comp1").geom("geom1").feature().move("if7", 18);
    model.component("comp1").geom("geom1").run("if7");
    model.component("comp1").geom("geom1").create("pi7", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi7").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi7").set("part", "part7");
    model.component("comp1").geom("geom1").feature("pi7").setEntry("inputexpr", "dp1", "dp7");
    model.component("comp1").geom("geom1").feature("pi7").setEntry("inputexpr", "sp", "sf7");
    model.component("comp1").geom("geom1").feature("pi7").setEntry("inputexpr", "wm", "wm7");
    model.component("comp1").geom("geom1").feature("pi7").setEntry("inputexpr", "dm", "dm7");
    model.component("comp1").geom("geom1").feature("pi7").setEntry("inputexpr", "hm", "hm7");
    model.component("comp1").geom("geom1").run("pi7");
    model.component("comp1").geom("geom1").create("if8", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif8", "EndIf", "if8");
    model.component("comp1").geom("geom1").feature("if8").set("condition", "useFaceCubicPart==1");
    model.component("comp1").geom("geom1").feature().move("endif8", 22);
    model.component("comp1").geom("geom1").feature().move("if8", 21);
    model.component("comp1").geom("geom1").run("if8");
    model.component("comp1").geom("geom1").create("pi8", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi8").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi8").set("part", "part8");
    model.component("comp1").geom("geom1").feature("pi8").setEntry("inputexpr", "dp1", "dp8");
    model.component("comp1").geom("geom1").feature("pi8").setEntry("inputexpr", "sp", "sf8");
    model.component("comp1").geom("geom1").feature("pi8").setEntry("inputexpr", "wm", "wm8");
    model.component("comp1").geom("geom1").feature("pi8").setEntry("inputexpr", "dm", "dm8");
    model.component("comp1").geom("geom1").feature("pi8").setEntry("inputexpr", "hm", "hm8");
    model.component("comp1").geom("geom1").run("pi8");
    model.component("comp1").geom("geom1").create("if9", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif9", "EndIf", "if9");
    model.component("comp1").geom("geom1").feature("if9").set("condition", "useHoneycomb==1");
    model.component("comp1").geom("geom1").feature().move("endif9", 25);
    model.component("comp1").geom("geom1").feature().move("if9", 24);
    model.component("comp1").geom("geom1").run("if9");
    model.component("comp1").geom("geom1").create("pi9", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi9").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi9").set("part", "part9");
    model.component("comp1").geom("geom1").feature("pi9").setEntry("inputexpr", "lh", "lh9");
    model.component("comp1").geom("geom1").feature("pi9").setEntry("inputexpr", "lo", "lo9");
    model.component("comp1").geom("geom1").feature("pi9").setEntry("inputexpr", "theta", "theta9");
    model.component("comp1").geom("geom1").feature("pi9").setEntry("inputexpr", "th", "th9");
    model.component("comp1").geom("geom1").feature("pi9").setEntry("inputexpr", "hm", "hm9");
    model.component("comp1").geom("geom1").run("pi9");
    model.component("comp1").geom("geom1").create("if10", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif10", "EndIf", "if10");
    model.component("comp1").geom("geom1").feature("if10").set("condition", "useSandwich==1");
    model.component("comp1").geom("geom1").feature().move("endif10", 28);
    model.component("comp1").geom("geom1").feature().move("if10", 27);
    model.component("comp1").geom("geom1").run("if10");
    model.component("comp1").geom("geom1").create("pi10", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi10").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi10").set("part", "part10");
    model.component("comp1").geom("geom1").feature("pi10").setEntry("inputexpr", "nm", "nm10");
    model.component("comp1").geom("geom1").feature("pi10").setEntry("inputexpr", "wm", "wm10");
    model.component("comp1").geom("geom1").feature("pi10").setEntry("inputexpr", "dm", "dm10");
    model.component("comp1").geom("geom1").feature("pi10").setEntry("inputexpr", "hm", "hm10");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("Matrix Union");
    model.component("comp1").geom("geom1").feature("unisel1")
         .set("input", new String[]{"pi1_dif1", "pi2_dif1", "pi3_dif1", "pi4_dif1", "pi5_difsel1", "pi6_dif1", "pi7_dif1", "pi8_dif1", "pi9_dif1"});

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("Selection: Domain Material");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("Selection: All Domains");
    model.component("comp1").selection("sel2").all();
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("Selection: Reinforcement");
    model.component("comp1").selection("uni1")
         .set("input", new String[]{"geom1_pi1_cyl1_dom", "geom1_pi2_ext1_dom", "geom1_pi3_uni1_dom", "geom1_pi4_uni1_dom", "geom1_pi5_uni1_dom", "geom1_pi6_sph1_dom", "geom1_pi7_uni1_dom", "geom1_pi8_uni1_dom", "geom1_pi9_ext1_dom"});
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("Selection: Matrix");
    model.component("comp1").selection("uni2")
         .set("input", new String[]{"geom1_pi1_dif1_dom", "geom1_pi2_dif1_dom", "geom1_pi3_dif1_dom", "geom1_pi4_dif1_dom", "geom1_pi5_difsel1", "geom1_pi6_dif1_dom", "geom1_pi7_dif1_dom", "geom1_pi8_dif1_dom", "geom1_pi9_dif1_dom", "geom1_pi10_uni1_dom"});
    model.component("comp1").selection().create("uni3", "Union");
    model.component("comp1").selection("uni3").label("Selection: All");
    model.component("comp1").selection("uni3")
         .set("input", new String[]{"geom1_pi1_boxsel1", "geom1_pi2_boxsel1", "geom1_pi3_boxsel1", "geom1_pi4_boxsel1", "geom1_pi5_boxsel1", "geom1_pi6_boxsel1", "geom1_pi7_boxsel1", "geom1_pi8_boxsel1", "geom1_pi9_boxsel1", "geom1_pi10_boxsel7"});
    model.component("comp1").selection().create("uni4", "Union");
    model.component("comp1").selection("uni4").label("Selection: Pair 1");
    model.component("comp1").selection("uni4").set("entitydim", 2);
    model.component("comp1").selection("uni4")
         .set("input", new String[]{"geom1_pi1_unisel1", "geom1_pi2_unisel1", "geom1_pi3_unisel1", "geom1_pi4_unisel1", "geom1_pi5_unisel2", "geom1_pi6_unisel1", "geom1_pi7_unisel1", "geom1_pi8_unisel1", "geom1_pi9_unisel1", "geom1_pi10_unisel1"});
    model.component("comp1").selection().create("uni5", "Union");
    model.component("comp1").selection("uni5").label("Selection: Pair 2");
    model.component("comp1").selection("uni5").set("entitydim", 2);
    model.component("comp1").selection("uni5")
         .set("input", new String[]{"geom1_pi1_unisel2", "geom1_pi2_unisel2", "geom1_pi3_unisel2", "geom1_pi4_unisel2", "geom1_pi5_unisel3", "geom1_pi6_unisel2", "geom1_pi7_unisel2", "geom1_pi8_unisel2", "geom1_pi9_unisel2", "geom1_pi10_unisel2"});
    model.component("comp1").selection().create("uni6", "Union");
    model.component("comp1").selection("uni6").label("Selection: Pair 3");
    model.component("comp1").selection("uni6").set("entitydim", 2);
    model.component("comp1").selection("uni6")
         .set("input", new String[]{"geom1_pi1_unisel3", "geom1_pi2_unisel3", "geom1_pi3_unisel3", "geom1_pi4_unisel3", "geom1_pi5_unisel4", "geom1_pi6_unisel3", "geom1_pi7_unisel3", "geom1_pi8_unisel3", "geom1_pi9_unisel3", "geom1_pi10_unisel3"});
    model.component("comp1").selection().create("uni7", "Union");
    model.component("comp1").selection("uni7").label("Selection: Pair 1, Source");
    model.component("comp1").selection("uni7").set("entitydim", 2);
    model.component("comp1").selection("uni7")
         .set("input", new String[]{"geom1_pi1_sel1", "geom1_pi2_sel1", "geom1_pi3_sel1", "geom1_pi4_sel1", "geom1_pi5_sel1", "geom1_pi6_sel1", "geom1_pi7_sel1", "geom1_pi8_sel1", "geom1_pi9_sel1", "geom1_pi10_boxsel1"});
    model.component("comp1").selection().create("uni8", "Union");
    model.component("comp1").selection("uni8").label("Selection: Pair 1, Destination");
    model.component("comp1").selection("uni8").set("entitydim", 2);
    model.component("comp1").selection("uni8")
         .set("input", new String[]{"geom1_pi1_sel2", "geom1_pi2_sel2", "geom1_pi3_sel2", "geom1_pi4_sel2", "geom1_pi5_sel2", "geom1_pi6_sel2", "geom1_pi7_sel2", "geom1_pi8_sel2", "geom1_pi9_sel2", "geom1_pi10_boxsel2"});
    model.component("comp1").selection().create("uni9", "Union");
    model.component("comp1").selection("uni9").label("Selection: Pair 2, Source");
    model.component("comp1").selection("uni9").set("entitydim", 2);
    model.component("comp1").selection("uni9")
         .set("input", new String[]{"geom1_pi1_sel3", "geom1_pi2_sel3", "geom1_pi3_sel3", "geom1_pi4_sel3", "geom1_pi5_sel3", "geom1_pi6_sel3", "geom1_pi7_sel3", "geom1_pi8_sel3", "geom1_pi9_sel3", "geom1_pi10_boxsel3"});
    model.component("comp1").selection().create("uni10", "Union");
    model.component("comp1").selection("uni10").label("Selection: Pair 2, Destination");
    model.component("comp1").selection("uni10").set("entitydim", 2);
    model.component("comp1").selection("uni10")
         .set("input", new String[]{"geom1_pi1_sel4", "geom1_pi2_sel4", "geom1_pi3_sel4", "geom1_pi4_sel4", "geom1_pi5_sel4", "geom1_pi6_sel4", "geom1_pi7_sel4", "geom1_pi8_sel4", "geom1_pi9_sel4", "geom1_pi10_boxsel4"});
    model.component("comp1").selection().create("uni11", "Union");
    model.component("comp1").selection("uni11").label("Selection: Pair 3, Source");
    model.component("comp1").selection("uni11").set("entitydim", 2);
    model.component("comp1").selection("uni11")
         .set("input", new String[]{"geom1_pi1_sel5", "geom1_pi2_sel5", "geom1_pi3_sel5", "geom1_pi4_sel5", "geom1_pi5_sel5", "geom1_pi6_sel5", "geom1_pi7_sel5", "geom1_pi8_sel5", "geom1_pi9_sel5", "geom1_pi10_boxsel5"});
    model.component("comp1").selection().create("uni12", "Union");
    model.component("comp1").selection("uni12").label("Selection: Pair 3, Destination");
    model.component("comp1").selection("uni12").set("entitydim", 2);
    model.component("comp1").selection("uni12")
         .set("input", new String[]{"geom1_pi1_sel6", "geom1_pi2_sel6", "geom1_pi3_sel6", "geom1_pi4_sel6", "geom1_pi5_sel6", "geom1_pi6_sel6", "geom1_pi7_sel6", "geom1_pi8_sel6", "geom1_pi9_sel6", "geom1_pi10_boxsel6"});

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().named("uni1");
    model.component("comp1").cpl("intop1").label("Integration over reinforcement");
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().named("uni2");
    model.component("comp1").cpl("intop2").label("Integration over matrix");
    model.component("comp1").cpl().create("intop3", "Integration");
    model.component("comp1").cpl("intop3").set("axisym", true);
    model.component("comp1").cpl("intop3").selection().named("uni3");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").cpl("intop3").label("Integration over all");
    model.component("comp1").cpl().create("intop4", "Integration");
    model.component("comp1").cpl("intop4").set("axisym", true);
    model.component("comp1").cpl("intop4").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop4").selection().named("uni4");
    model.component("comp1").cpl("intop4").label("Integration over pair 1");
    model.component("comp1").cpl().create("intop5", "Integration");
    model.component("comp1").cpl("intop5").set("axisym", true);
    model.component("comp1").cpl("intop5").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop5").selection().named("uni5");
    model.component("comp1").cpl("intop5").label("Integration over pair 2");
    model.component("comp1").cpl().create("intop6", "Integration");
    model.component("comp1").cpl("intop6").set("axisym", true);
    model.component("comp1").cpl("intop6").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop6").selection().named("uni6");
    model.component("comp1").cpl("intop6").label("Integration over pair 3");

    model.component("comp1").coordSystem().create("sys2", "VectorBase");
    model.component("comp1").coordSystem("sys2").setIndex("base", 0, 0, 0);
    model.component("comp1").coordSystem("sys2").setIndex("base", 1, 0, 1);
    model.component("comp1").coordSystem("sys2").setIndex("base", -1, 1, 0);
    model.component("comp1").coordSystem("sys2").setIndex("base", 0, 1, 1);
    model.component("comp1").coordSystem("sys2").set("orthonormal", true);
    model.component("comp1").coordSystem("sys2").set("frametype", "material");

    model.component("comp1").view("view1").hideObjects().create("hide1");
    model.component("comp1").view("view1").hideObjects("hide1").init(3);
    model.component("comp1").view("view1").hideObjects("hide1").named("unisel1");
    model.component("comp1").view("view1").hideObjects("hide1").active(false);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup()
         .create("IsotropicStrengthParameters", "IsotropicStrengthParameters", "Isotropic strength parameters");
    model.component("comp1").material("mat1").label("A-glass fiber");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2440[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("density", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"7.3E-6[1/K]", "0", "0", "0", "7.3E-6[1/K]", "0", "0", "0", "7.3E-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "796[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("heatcapacity", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "68.9[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu")
         .setPropertyInfo("E", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.2");
    model.component("comp1").material("mat1").propertyGroup("IsotropicStrengthParameters")
         .set("sigmat", "3310[MPa]");
    model.component("comp1").material("mat1").propertyGroup("IsotropicStrengthParameters")
         .setPropertyInfo("sigmat", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").label("Acrylic plastic");
    model.component("comp1").material("mat2").set("family", "custom");
    model.component("comp1").material("mat2")
         .set("customspecular", new double[]{0.9803921568627451, 0.9803921568627451, 0.9803921568627451});
    model.component("comp1").material("mat2").set("diffuse", "custom");
    model.component("comp1").material("mat2")
         .set("customdiffuse", new double[]{0.39215686274509803, 0.7843137254901961, 0.39215686274509803});
    model.component("comp1").material("mat2").set("ambient", "custom");
    model.component("comp1").material("mat2")
         .set("customambient", new double[]{0.39215686274509803, 0.7843137254901961, 0.39215686274509803});
    model.component("comp1").material("mat2").set("noise", true);
    model.component("comp1").material("mat2").set("lighting", "phong");
    model.component("comp1").material("mat2").set("shininess", 1000);
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"7.0e-5[1/K]", "0", "0", "0", "7.0e-5[1/K]", "0", "0", "0", "7.0e-5[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "1470[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "1190[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.18[W/(m*K)]", "0", "0", "0", "0.18[W/(m*K)]", "0", "0", "0", "0.18[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "3.2[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat3").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat3").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat3").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat3").label("Air");
    model.component("comp1").material("mat3").set("family", "air");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat3").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat3").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat3").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat3").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat3").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat3").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat3").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat3").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat3").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat3").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat3").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat3").materialType("nonSolid");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat4").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat4").label("Aluminum");
    model.component("comp1").material("mat4").set("family", "aluminum");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat4").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat4").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat4").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat4").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat5").propertyGroup()
         .create("IsotropicStrengthParameters", "IsotropicStrengthParameters", "Isotropic strength parameters");
    model.component("comp1").material("mat5").label("Aluminum oxide ceramic fiber ");
    model.component("comp1").material("mat5").propertyGroup("def").set("density", "3900[kg/m^3]");
    model.component("comp1").material("mat5").propertyGroup("def")
         .setPropertyInfo("density", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"7.5E-6[1/K]", "0", "0", "0", "7.5E-6[1/K]", "0", "0", "0", "7.5E-6[1/K]"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat5").propertyGroup("def").set("heatcapacity", "");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("E", "370[GPa]");
    model.component("comp1").material("mat5").propertyGroup("Enu")
         .setPropertyInfo("E", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat5").propertyGroup("Enu").set("nu", "0.22");
    model.component("comp1").material("mat5").propertyGroup("IsotropicStrengthParameters")
         .set("sigmat", "3100[MPa]");
    model.component("comp1").material("mat5").propertyGroup("IsotropicStrengthParameters")
         .setPropertyInfo("sigmat", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material().create("mat6", "Common");
    model.component("comp1").material("mat6").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat6").propertyGroup()
         .create("IsotropicStrengthParameters", "IsotropicStrengthParameters", "Isotropic strength parameters");
    model.component("comp1").material("mat6").label("AR-glass fiber");
    model.component("comp1").material("mat6").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat6").propertyGroup("def")
         .setPropertyInfo("density", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material("mat6").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"6.5E-6[1/K]", "0", "0", "0", "6.5E-6[1/K]", "0", "0", "0", "6.5E-6[1/K]"});
    model.component("comp1").material("mat6").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material("mat6").propertyGroup("def").set("heatcapacity", "732[J/(kg*K)]");
    model.component("comp1").material("mat6").propertyGroup("def")
         .setPropertyInfo("heatcapacity", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material("mat6").propertyGroup("Enu").set("E", "73.1[GPa]");
    model.component("comp1").material("mat6").propertyGroup("Enu")
         .setPropertyInfo("E", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material("mat6").propertyGroup("Enu").set("nu", "0.2");
    model.component("comp1").material("mat6").propertyGroup("IsotropicStrengthParameters")
         .set("sigmat", "3241[MPa]");
    model.component("comp1").material("mat6").propertyGroup("IsotropicStrengthParameters")
         .setPropertyInfo("sigmat", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material().create("mat7", "Common");
    model.component("comp1").material("mat7").propertyGroup()
         .create("TransverseIsotropic", "TransverseIsotropic", "Transversely isotropic");
    model.component("comp1").material("mat7").label("AS-4 carbon fiber");
    model.component("comp1").material("mat7").propertyGroup("def").set("density", "1810[kg/m^3]");
    model.component("comp1").material("mat7").propertyGroup("def")
         .setPropertyInfo("density", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"-0.5E-6[1/K]", "0", "0", "0", "15E-6[1/K]", "0", "0", "0", "15E-6[1/K]"});
    model.component("comp1").material("mat7").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.component("comp1").material("mat7").propertyGroup("def").set("heatcapacity", "");
    model.component("comp1").material("mat7").propertyGroup("TransverseIsotropic")
         .set("Evect", new String[]{"235[GPa]", "15[GPa]"});
    model.component("comp1").material("mat7").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("Evect", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.component("comp1").material("mat7").propertyGroup("TransverseIsotropic")
         .set("nuvect", new String[]{"0.2", "0.0714"});
    model.component("comp1").material("mat7").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("nuvect", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.component("comp1").material("mat7").propertyGroup("TransverseIsotropic").set("Gvect1", "27[GPa]");
    model.component("comp1").material("mat7").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("Gvect1", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.component("comp1").material().create("mat8", "Common");
    model.component("comp1").material("mat8").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat8").propertyGroup()
         .create("IsotropicStrengthParameters", "IsotropicStrengthParameters", "Isotropic strength parameters");
    model.component("comp1").material("mat8").label("Boron fiber");
    model.component("comp1").material("mat8").propertyGroup("def").set("density", "2600[kg/m^3]");
    model.component("comp1").material("mat8").propertyGroup("def")
         .setPropertyInfo("density", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat8").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"8.3E-6[1/K]", "0", "0", "0", "8.3E-6[1/K]", "0", "0", "0", "8.3E-6[1/K]"});
    model.component("comp1").material("mat8").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat8").propertyGroup("def").set("heatcapacity", "");
    model.component("comp1").material("mat8").propertyGroup("Enu").set("E", "390[GPa]");
    model.component("comp1").material("mat8").propertyGroup("Enu")
         .setPropertyInfo("E", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat8").propertyGroup("Enu").set("nu", "0.13");
    model.component("comp1").material("mat8").propertyGroup("Enu")
         .setPropertyInfo("nu", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat8").propertyGroup("IsotropicStrengthParameters")
         .set("sigmat", "3500[MPa]");
    model.component("comp1").material("mat8").propertyGroup("IsotropicStrengthParameters")
         .setPropertyInfo("sigmat", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material().create("mat9", "Common");
    model.component("comp1").material("mat9").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat9").propertyGroup()
         .create("IsotropicStrengthParameters", "IsotropicStrengthParameters", "Isotropic strength parameters");
    model.component("comp1").material("mat9").label("Borosilicate glass ceramic");
    model.component("comp1").material("mat9").propertyGroup("def").set("density", "2300[kg/m^3]");
    model.component("comp1").material("mat9").propertyGroup("def")
         .setPropertyInfo("density", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat9").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"3.5E-6[1/K]", "0", "0", "0", "3.5E-6[1/K]", "0", "0", "0", "3.5E-6[1/K]"});
    model.component("comp1").material("mat9").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat9").propertyGroup("def").set("heatcapacity", "");
    model.component("comp1").material("mat9").propertyGroup("Enu").set("E", "60[GPa]");
    model.component("comp1").material("mat9").propertyGroup("Enu")
         .setPropertyInfo("E", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat9").propertyGroup("Enu").set("nu", "0.2");
    model.component("comp1").material("mat9").propertyGroup("IsotropicStrengthParameters").set("sigmat", "100[MPa]");
    model.component("comp1").material("mat9").propertyGroup("IsotropicStrengthParameters")
         .setPropertyInfo("sigmat", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material().create("mat10", "Common");
    model.component("comp1").material("mat10").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat10").label("Brick");
    model.component("comp1").material("mat10").set("family", "brick");
    model.component("comp1").material("mat10").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"6e-6[1/K]", "0", "0", "0", "6e-6[1/K]", "0", "0", "0", "6e-6[1/K]"});
    model.component("comp1").material("mat10").propertyGroup("def").set("density", "2000[kg/m^3]");
    model.component("comp1").material("mat10").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat10").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.5[W/(m*K)]", "0", "0", "0", "0.5[W/(m*K)]", "0", "0", "0", "0.5[W/(m*K)]"});
    model.component("comp1").material("mat10").propertyGroup("Enu").set("E", "17[GPa]");
    model.component("comp1").material("mat10").propertyGroup("Enu").set("nu", "0.2");
    model.component("comp1").material().create("mat11", "Common");
    model.component("comp1").material("mat11").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat11").propertyGroup()
         .create("IsotropicStrengthParameters", "IsotropicStrengthParameters", "Isotropic strength parameters");
    model.component("comp1").material("mat11").label("C-glass fiber");
    model.component("comp1").material("mat11").propertyGroup("def").set("density", "2520[kg/m^3]");
    model.component("comp1").material("mat11").propertyGroup("def")
         .setPropertyInfo("density", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material("mat11").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"6.3E-6[1/K]", "0", "0", "0", "6.3E-6[1/K]", "0", "0", "0", "6.3E-6[1/K]"});
    model.component("comp1").material("mat11").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material("mat11").propertyGroup("def").set("heatcapacity", "787[J/(kg*K)]");
    model.component("comp1").material("mat11").propertyGroup("def")
         .setPropertyInfo("heatcapacity", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");

    return model;
  }

  public static Model run3(Model model) {
    model.component("comp1").material("mat11").propertyGroup("Enu").set("E", "68.9[GPa]");
    model.component("comp1").material("mat11").propertyGroup("Enu")
         .setPropertyInfo("E", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material("mat11").propertyGroup("Enu").set("nu", "0.2");
    model.component("comp1").material("mat11").propertyGroup("IsotropicStrengthParameters")
         .set("sigmat", "3310[MPa]");
    model.component("comp1").material("mat11").propertyGroup("IsotropicStrengthParameters")
         .setPropertyInfo("sigmat", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material().create("mat12", "Common");
    model.component("comp1").material("mat12").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat12").label("Cast iron");
    model.component("comp1").material("mat12").set("family", "custom");
    model.component("comp1").material("mat12")
         .set("customspecular", new double[]{0.9803921568627451, 0.9803921568627451, 0.9803921568627451});
    model.component("comp1").material("mat12").set("diffuse", "custom");
    model.component("comp1").material("mat12")
         .set("customdiffuse", new double[]{0.39215686274509803, 0.39215686274509803, 0.39215686274509803});
    model.component("comp1").material("mat12").set("ambient", "custom");
    model.component("comp1").material("mat12")
         .set("customambient", new double[]{0.39215686274509803, 0.39215686274509803, 0.39215686274509803});
    model.component("comp1").material("mat12").set("noise", true);
    model.component("comp1").material("mat12").set("noisescale", 0.14);
    model.component("comp1").material("mat12").set("noisefreq", 2.1);
    model.component("comp1").material("mat12").set("fresnel", 0.1);
    model.component("comp1").material("mat12").set("roughness", 0.1);
    model.component("comp1").material("mat12").set("diffusewrap", 0);
    model.component("comp1").material("mat12").set("reflectance", 0);
    model.component("comp1").material("mat12").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"11e-6[1/K]", "0", "0", "0", "11e-6[1/K]", "0", "0", "0", "11e-6[1/K]"});
    model.component("comp1").material("mat12").propertyGroup("def").set("density", "7000[kg/m^3]");
    model.component("comp1").material("mat12").propertyGroup("def").set("heatcapacity", "420[J/(kg*K)]");
    model.component("comp1").material("mat12").propertyGroup("def")
         .set("thermalconductivity", new String[]{"50[W/(m*K)]", "0", "0", "0", "50[W/(m*K)]", "0", "0", "0", "50[W/(m*K)]"});
    model.component("comp1").material("mat12").propertyGroup("Enu").set("E", "140[GPa]");
    model.component("comp1").material("mat12").propertyGroup("Enu").set("nu", "0.25");
    model.component("comp1").material().create("mat13", "Common");
    model.component("comp1").material("mat13").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat13").label("Concrete");
    model.component("comp1").material("mat13").set("family", "concrete");
    model.component("comp1").material("mat13").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"10e-6[1/K]", "0", "0", "0", "10e-6[1/K]", "0", "0", "0", "10e-6[1/K]"});
    model.component("comp1").material("mat13").propertyGroup("def").set("density", "2300[kg/m^3]");
    model.component("comp1").material("mat13").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.8[W/(m*K)]", "0", "0", "0", "1.8[W/(m*K)]", "0", "0", "0", "1.8[W/(m*K)]"});
    model.component("comp1").material("mat13").propertyGroup("def").set("heatcapacity", "880[J/(kg*K)]");
    model.component("comp1").material("mat13").propertyGroup("Enu").set("E", "25[GPa]");
    model.component("comp1").material("mat13").propertyGroup("Enu").set("nu", "0.20");
    model.component("comp1").material().create("mat14", "Common");
    model.component("comp1").material("mat14").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat14").propertyGroup()
         .create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat14").label("Copper");
    model.component("comp1").material("mat14").set("family", "copper");
    model.component("comp1").material("mat14").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat14").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat14").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat14").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat14").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat14").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat14").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat14").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat14").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat14").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat14").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat14").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat14").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material().create("mat15", "Common");
    model.component("comp1").material("mat15").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat15").propertyGroup()
         .create("IsotropicStrengthParameters", "IsotropicStrengthParameters", "Isotropic strength parameters");
    model.component("comp1").material("mat15").label("CVD silicon carbide fiber ");
    model.component("comp1").material("mat15").propertyGroup("def").set("density", "3300[kg/m^3]");
    model.component("comp1").material("mat15").propertyGroup("def")
         .setPropertyInfo("density", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat15").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"5.7E-6[1/K]", "0", "0", "0", "5.7E-6[1/K]", "0", "0", "0", "5.7E-6[1/K]"});
    model.component("comp1").material("mat15").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat15").propertyGroup("def").set("heatcapacity", "");
    model.component("comp1").material("mat15").propertyGroup("Enu").set("E", "430[GPa]");
    model.component("comp1").material("mat15").propertyGroup("Enu")
         .setPropertyInfo("E", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat15").propertyGroup("Enu").set("nu", "0.12");
    model.component("comp1").material("mat15").propertyGroup("IsotropicStrengthParameters")
         .set("sigmat", "3500[MPa]");
    model.component("comp1").material("mat15").propertyGroup("IsotropicStrengthParameters")
         .setPropertyInfo("sigmat", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material().create("mat16", "Common");
    model.component("comp1").material("mat16").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat16").propertyGroup()
         .create("IsotropicStrengthParameters", "IsotropicStrengthParameters", "Isotropic strength parameters");
    model.component("comp1").material("mat16").label("D-glass fiber");
    model.component("comp1").material("mat16").propertyGroup("def").set("density", "2120[kg/m^3]");
    model.component("comp1").material("mat16").propertyGroup("def")
         .setPropertyInfo("density", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material("mat16").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"2.5E-6[1/K]", "0", "0", "0", "2.5E-6[1/K]", "0", "0", "0", "2.5E-6[1/K]"});
    model.component("comp1").material("mat16").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material("mat16").propertyGroup("def").set("heatcapacity", "733[J/(kg*K)]");
    model.component("comp1").material("mat16").propertyGroup("def")
         .setPropertyInfo("heatcapacity", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material("mat16").propertyGroup("Enu").set("E", "51.7[GPa]");
    model.component("comp1").material("mat16").propertyGroup("Enu")
         .setPropertyInfo("E", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material("mat16").propertyGroup("Enu").set("nu", "0.2");
    model.component("comp1").material("mat16").propertyGroup("IsotropicStrengthParameters")
         .set("sigmat", "2415[MPa]");
    model.component("comp1").material("mat16").propertyGroup("IsotropicStrengthParameters")
         .setPropertyInfo("sigmat", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material().create("mat17", "Common");
    model.component("comp1").material("mat17").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat17").propertyGroup()
         .create("IsotropicStrengthParameters", "IsotropicStrengthParameters", "Isotropic strength parameters");
    model.component("comp1").material("mat17").label("E-glass fiber");
    model.component("comp1").material("mat17").propertyGroup("def").set("density", "2580[kg/m^3]");
    model.component("comp1").material("mat17").propertyGroup("def")
         .setPropertyInfo("density", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material("mat17").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"5.4E-6[1/K]", "0", "0", "0", "5.4E-6[1/K]", "0", "0", "0", "5.4E-6[1/K]"});
    model.component("comp1").material("mat17").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material("mat17").propertyGroup("def").set("heatcapacity", "810[J/(kg*K)]");
    model.component("comp1").material("mat17").propertyGroup("def")
         .setPropertyInfo("heatcapacity", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material("mat17").propertyGroup("Enu").set("E", "72.3[GPa]");
    model.component("comp1").material("mat17").propertyGroup("Enu")
         .setPropertyInfo("E", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material("mat17").propertyGroup("Enu").set("nu", "0.22");
    model.component("comp1").material("mat17").propertyGroup("IsotropicStrengthParameters")
         .set("sigmat", "3445[MPa]");
    model.component("comp1").material("mat17").propertyGroup("IsotropicStrengthParameters")
         .setPropertyInfo("sigmat", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material().create("mat18", "Common");
    model.component("comp1").material("mat18").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat18").propertyGroup()
         .create("IsotropicStrengthParameters", "IsotropicStrengthParameters", "Isotropic strength parameters");
    model.component("comp1").material("mat18").label("Epoxy polymer");
    model.component("comp1").material("mat18").propertyGroup("def").set("density", "1250[kg/m^3]");
    model.component("comp1").material("mat18").propertyGroup("def")
         .setPropertyInfo("density", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat18").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"75E-6[1/K]", "0", "0", "0", "75E-6[1/K]", "0", "0", "0", "75E-6[1/K]"});
    model.component("comp1").material("mat18").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat18").propertyGroup("def").set("heatcapacity", "796[J/(kg*K)]");
    model.component("comp1").material("mat18").propertyGroup("Enu").set("E", "3.25[GPa]");
    model.component("comp1").material("mat18").propertyGroup("Enu")
         .setPropertyInfo("E", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat18").propertyGroup("Enu").set("nu", "0.265");
    model.component("comp1").material("mat18").propertyGroup("Enu")
         .setPropertyInfo("nu", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat18").propertyGroup("IsotropicStrengthParameters")
         .set("sigmat", "87.5[MPa]");
    model.component("comp1").material("mat18").propertyGroup("IsotropicStrengthParameters")
         .setPropertyInfo("sigmat", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material().create("mat19", "Common");
    model.component("comp1").material("mat19").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat19").label("Iron");
    model.component("comp1").material("mat19").set("family", "iron");
    model.component("comp1").material("mat19").propertyGroup("def")
         .set("relpermeability", new String[]{"4000", "0", "0", "0", "4000", "0", "0", "0", "4000"});
    model.component("comp1").material("mat19").propertyGroup("def")
         .set("electricconductivity", new String[]{"1.12e7[S/m]", "0", "0", "0", "1.12e7[S/m]", "0", "0", "0", "1.12e7[S/m]"});
    model.component("comp1").material("mat19").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.2e-6[1/K]", "0", "0", "0", "12.2e-6[1/K]", "0", "0", "0", "12.2e-6[1/K]"});
    model.component("comp1").material("mat19").propertyGroup("def").set("heatcapacity", "440[J/(kg*K)]");
    model.component("comp1").material("mat19").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat19").propertyGroup("def").set("density", "7870[kg/m^3]");
    model.component("comp1").material("mat19").propertyGroup("def")
         .set("thermalconductivity", new String[]{"76.2[W/(m*K)]", "0", "0", "0", "76.2[W/(m*K)]", "0", "0", "0", "76.2[W/(m*K)]"});
    model.component("comp1").material("mat19").propertyGroup("Enu").set("E", "200[GPa]");
    model.component("comp1").material("mat19").propertyGroup("Enu").set("nu", "0.29");
    model.component("comp1").material().create("mat20", "Common");
    model.component("comp1").material("mat20").propertyGroup()
         .create("TransverseIsotropic", "TransverseIsotropic", "Transversely isotropic");
    model.component("comp1").material("mat20").label("K-29 aramid fiber");
    model.component("comp1").material("mat20").propertyGroup("def").set("density", "1440[kg/m^3]");
    model.component("comp1").material("mat20").propertyGroup("def")
         .setPropertyInfo("density", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat20").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"-4E-6[1/K]", "0", "0", "0", "59E-6[1/K]", "0", "0", "0", "59E-6[1/K]"});
    model.component("comp1").material("mat20").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat20").propertyGroup("def").set("heatcapacity", "");
    model.component("comp1").material("mat20").propertyGroup("TransverseIsotropic")
         .set("Evect", new String[]{"65[GPa]", "2[GPa]"});
    model.component("comp1").material("mat20").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("Evect", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat20").propertyGroup("TransverseIsotropic")
         .set("nuvect", new String[]{"0.36", "0"});
    model.component("comp1").material("mat20").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("nuvect", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat20").propertyGroup("TransverseIsotropic").set("Gvect1", "10[GPa]");
    model.component("comp1").material().create("mat21", "Common");
    model.component("comp1").material("mat21").propertyGroup()
         .create("TransverseIsotropic", "TransverseIsotropic", "Transversely isotropic");
    model.component("comp1").material("mat21").label("K-49 aramid fiber");
    model.component("comp1").material("mat21").propertyGroup("def").set("density", "1450[kg/m^3]");
    model.component("comp1").material("mat21").propertyGroup("def")
         .setPropertyInfo("density", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat21").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"-4.9E-6[1/K]", "0", "0", "0", "59E-6[1/K]", "0", "0", "0", "59E-6[1/K]"});
    model.component("comp1").material("mat21").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat21").propertyGroup("def").set("heatcapacity", "");
    model.component("comp1").material("mat21").propertyGroup("TransverseIsotropic")
         .set("Evect", new String[]{"125[GPa]", "4[GPa]"});
    model.component("comp1").material("mat21").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("Evect", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat21").propertyGroup("TransverseIsotropic")
         .set("nuvect", new String[]{"0.36", "0"});
    model.component("comp1").material("mat21").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("nuvect", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat21").propertyGroup("TransverseIsotropic").set("Gvect1", "21[GPa]");
    model.component("comp1").material().create("mat22", "Common");
    model.component("comp1").material("mat22").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat22").propertyGroup()
         .create("IsotropicStrengthParameters", "IsotropicStrengthParameters", "Isotropic strength parameters");
    model.component("comp1").material("mat22").label("Lithium aluminosilicate glass ceramic");
    model.component("comp1").material("mat22").propertyGroup("def").set("density", "2000[kg/m^3]");
    model.component("comp1").material("mat22").propertyGroup("def")
         .setPropertyInfo("density", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat22").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"1.5E-6[1/K]", "0", "0", "0", "1.5E-6[1/K]", "0", "0", "0", "1.5E-6[1/K]"});
    model.component("comp1").material("mat22").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat22").propertyGroup("def").set("heatcapacity", "");
    model.component("comp1").material("mat22").propertyGroup("Enu").set("E", "100[GPa]");
    model.component("comp1").material("mat22").propertyGroup("Enu")
         .setPropertyInfo("E", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat22").propertyGroup("Enu").set("nu", "0.2");
    model.component("comp1").material("mat22").propertyGroup("IsotropicStrengthParameters")
         .set("sigmat", "125[MPa]");
    model.component("comp1").material("mat22").propertyGroup("IsotropicStrengthParameters")
         .setPropertyInfo("sigmat", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material().create("mat23", "Common");
    model.component("comp1").material("mat23").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat23").propertyGroup()
         .create("IsotropicStrengthParameters", "IsotropicStrengthParameters", "Isotropic strength parameters");
    model.component("comp1").material("mat23").label("Magnesium aluminosilicate glass ceramic");
    model.component("comp1").material("mat23").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat23").propertyGroup("def")
         .setPropertyInfo("density", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat23").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"4E-6[1/K]", "0", "0", "0", "4E-6[1/K]", "0", "0", "0", "4E-6[1/K]"});
    model.component("comp1").material("mat23").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat23").propertyGroup("def").set("heatcapacity", "");
    model.component("comp1").material("mat23").propertyGroup("Enu").set("E", "120[GPa]");
    model.component("comp1").material("mat23").propertyGroup("Enu")
         .setPropertyInfo("E", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat23").propertyGroup("Enu").set("nu", "0.2");
    model.component("comp1").material("mat23").propertyGroup("IsotropicStrengthParameters")
         .set("sigmat", "140[MPa]");
    model.component("comp1").material("mat23").propertyGroup("IsotropicStrengthParameters")
         .setPropertyInfo("sigmat", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material().create("mat24", "Common");
    model.component("comp1").material("mat24").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat24").propertyGroup()
         .create("IsotropicStrengthParameters", "IsotropicStrengthParameters", "Isotropic strength parameters");
    model.component("comp1").material("mat24").label("Magnesium oxide ceramic");
    model.component("comp1").material("mat24").propertyGroup("def").set("density", "3600[kg/m^3]");
    model.component("comp1").material("mat24").propertyGroup("def")
         .setPropertyInfo("density", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat24").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"13.8E-6[1/K]", "0", "0", "0", "13.8E-6[1/K]", "0", "0", "0", "13.8E-6[1/K]"});
    model.component("comp1").material("mat24").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat24").propertyGroup("def").set("heatcapacity", "");
    model.component("comp1").material("mat24").propertyGroup("Enu").set("E", "255[GPa]");
    model.component("comp1").material("mat24").propertyGroup("Enu")
         .setPropertyInfo("E", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat24").propertyGroup("Enu").set("nu", "0.18");
    model.component("comp1").material("mat24").propertyGroup("IsotropicStrengthParameters")
         .set("sigmat", "110[MPa]");
    model.component("comp1").material("mat24").propertyGroup("IsotropicStrengthParameters")
         .setPropertyInfo("sigmat", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material().create("mat25", "Common");
    model.component("comp1").material("mat25").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat25").propertyGroup()
         .create("IsotropicStrengthParameters", "IsotropicStrengthParameters", "Isotropic strength parameters");
    model.component("comp1").material("mat25").label("Mullite ceramic");
    model.component("comp1").material("mat25").propertyGroup("def").set("density", "3200[kg/m^3]");
    model.component("comp1").material("mat25").propertyGroup("def")
         .setPropertyInfo("density", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat25").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"5.3E-6[1/K]", "0", "0", "0", "5.3E-6[1/K]", "0", "0", "0", "5.3E-6[1/K]"});
    model.component("comp1").material("mat25").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat25").propertyGroup("def").set("heatcapacity", "");
    model.component("comp1").material("mat25").propertyGroup("Enu").set("E", "143[GPa]");
    model.component("comp1").material("mat25").propertyGroup("Enu")
         .setPropertyInfo("E", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat25").propertyGroup("Enu").set("nu", "0.2");
    model.component("comp1").material("mat25").propertyGroup("IsotropicStrengthParameters").set("sigmat", "83[MPa]");
    model.component("comp1").material("mat25").propertyGroup("IsotropicStrengthParameters")
         .setPropertyInfo("sigmat", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material().create("mat26", "Common");
    model.component("comp1").material("mat26").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat26").propertyGroup()
         .create("IsotropicStrengthParameters", "IsotropicStrengthParameters", "Isotropic strength parameters");
    model.component("comp1").material("mat26").label("NL-200 silicon carbide fiber");
    model.component("comp1").material("mat26").propertyGroup("def").set("density", "2250[kg/m^3]");
    model.component("comp1").material("mat26").propertyGroup("def")
         .setPropertyInfo("density", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat26").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"3.2E-6[1/K]", "0", "0", "0", "3.2E-6[1/K]", "0", "0", "0", "3.2E-6[1/K]"});
    model.component("comp1").material("mat26").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat26").propertyGroup("def").set("heatcapacity", "");
    model.component("comp1").material("mat26").propertyGroup("Enu").set("E", "200[GPa]");
    model.component("comp1").material("mat26").propertyGroup("Enu")
         .setPropertyInfo("E", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat26").propertyGroup("Enu").set("nu", "0.18");
    model.component("comp1").material("mat26").propertyGroup("IsotropicStrengthParameters")
         .set("sigmat", "2000[MPa]");
    model.component("comp1").material("mat26").propertyGroup("IsotropicStrengthParameters")
         .setPropertyInfo("sigmat", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material().create("mat27", "Common");
    model.component("comp1").material("mat27").propertyGroup()
         .create("TransverseIsotropic", "TransverseIsotropic", "Transversely isotropic");
    model.component("comp1").material("mat27").label("Polyacrylonitrile (PAN) carbon fiber");
    model.component("comp1").material("mat27").propertyGroup("def").set("density", "1740[kg/m^3]");
    model.component("comp1").material("mat27").propertyGroup("def")
         .setPropertyInfo("density", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat27").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"0.3E-6[1/K]", "0", "0", "0", "9.5E-6[1/K]", "0", "0", "0", "9.5E-6[1/K]"});
    model.component("comp1").material("mat27").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");

    return model;
  }

  public static Model run4(Model model) {
    model.component("comp1").material("mat27").propertyGroup("def").set("heatcapacity", "");
    model.component("comp1").material("mat27").propertyGroup("TransverseIsotropic")
         .set("Evect", new String[]{"250[GPa]", "20[GPa]"});
    model.component("comp1").material("mat27").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("Evect", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat27").propertyGroup("TransverseIsotropic")
         .set("nuvect", new String[]{"0.2", "0"});
    model.component("comp1").material("mat27").propertyGroup("TransverseIsotropic").set("Gvect1", "20[GPa]");
    model.component("comp1").material().create("mat28", "Common");
    model.component("comp1").material("mat28").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat28").propertyGroup()
         .create("IsotropicStrengthParameters", "IsotropicStrengthParameters", "Isotropic strength parameters");
    model.component("comp1").material("mat28").label("Polyster polymer");
    model.component("comp1").material("mat28").propertyGroup("def").set("density", "1250[kg/m^3]");
    model.component("comp1").material("mat28").propertyGroup("def")
         .setPropertyInfo("density", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat28").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"75E-6[1/K]", "0", "0", "0", "75E-6[1/K]", "0", "0", "0", "75E-6[1/K]"});
    model.component("comp1").material("mat28").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat28").propertyGroup("def").set("heatcapacity", "");
    model.component("comp1").material("mat28").propertyGroup("Enu").set("E", "3[GPa]");
    model.component("comp1").material("mat28").propertyGroup("Enu")
         .setPropertyInfo("E", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat28").propertyGroup("Enu").set("nu", "0.265");
    model.component("comp1").material("mat28").propertyGroup("Enu")
         .setPropertyInfo("nu", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat28").propertyGroup("IsotropicStrengthParameters").set("sigmat", "65[MPa]");
    model.component("comp1").material("mat28").propertyGroup("IsotropicStrengthParameters")
         .setPropertyInfo("sigmat", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material().create("mat29", "Common");
    model.component("comp1").material("mat29").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat29").propertyGroup()
         .create("IsotropicStrengthParameters", "IsotropicStrengthParameters", "Isotropic strength parameters");
    model.component("comp1").material("mat29").label("R-glass fiber");
    model.component("comp1").material("mat29").propertyGroup("def").set("density", "2540[kg/m^3]");
    model.component("comp1").material("mat29").propertyGroup("def")
         .setPropertyInfo("density", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material("mat29").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"3.3E-6[1/K]", "0", "0", "0", "3.3E-6[1/K]", "0", "0", "0", "3.3E-6[1/K]"});
    model.component("comp1").material("mat29").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material("mat29").propertyGroup("def").set("heatcapacity", "732[J/(kg*K)]");
    model.component("comp1").material("mat29").propertyGroup("def")
         .setPropertyInfo("heatcapacity", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material("mat29").propertyGroup("Enu").set("E", "85.5[GPa]");
    model.component("comp1").material("mat29").propertyGroup("Enu")
         .setPropertyInfo("E", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material("mat29").propertyGroup("Enu").set("nu", "0.2");
    model.component("comp1").material("mat29").propertyGroup("IsotropicStrengthParameters")
         .set("sigmat", "4135[MPa]");
    model.component("comp1").material("mat29").propertyGroup("IsotropicStrengthParameters")
         .setPropertyInfo("sigmat", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material().create("mat30", "Common");
    model.component("comp1").material("mat30").propertyGroup()
         .create("TransverseIsotropic", "TransverseIsotropic", "Transversely isotropic");
    model.component("comp1").material("mat30").label("Rayon carbon fiber");
    model.component("comp1").material("mat30").propertyGroup("def").set("density", "1660[kg/m^3]");
    model.component("comp1").material("mat30").propertyGroup("def")
         .setPropertyInfo("density", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat30").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"-0.3E-6[1/K]", "0", "0", "0", "9.5E-6[1/K]", "0", "0", "0", "9.5E-6[1/K]"});
    model.component("comp1").material("mat30").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat30").propertyGroup("def").set("heatcapacity", "");
    model.component("comp1").material("mat30").propertyGroup("TransverseIsotropic")
         .set("Evect", new String[]{"390[GPa]", "12[GPa]"});
    model.component("comp1").material("mat30").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("Evect", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat30").propertyGroup("TransverseIsotropic")
         .set("nuvect", new String[]{"0.2", "0"});
    model.component("comp1").material("mat30").propertyGroup("TransverseIsotropic").set("Gvect1", "10[GPa]");
    model.component("comp1").material().create("mat31", "Common");
    model.component("comp1").material("mat31").propertyGroup().create("Lame", "Lame", "Lam\u00e9 parameters");
    model.component("comp1").material("mat31").propertyGroup()
         .create("MooneyRivlin", "MooneyRivlin", "Mooney-Rivlin");
    model.component("comp1").material("mat31").propertyGroup().create("Yeoh", "Yeoh", "Yeoh");
    model.component("comp1").material("mat31").propertyGroup().create("Varga", "Varga", "Varga");
    model.component("comp1").material("mat31").propertyGroup().create("ArrudaBoyce", "ArrudaBoyce", "Arruda-Boyce");
    model.component("comp1").material("mat31").label("Rubber");
    model.component("comp1").material("mat31").set("family", "rubber");
    model.component("comp1").material("mat31").propertyGroup("def").set("density", "");
    model.component("comp1").material("mat31").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat31").propertyGroup("def").set("lossfactor", "");
    model.component("comp1").material("mat31").propertyGroup("def").set("thermalconductivity", "");
    model.component("comp1").material("mat31").propertyGroup("def").set("heatcapacity", "");
    model.component("comp1").material("mat31").propertyGroup("def").set("density", "1100[kg/m^3]");
    model.component("comp1").material("mat31").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"200e-6[1/K]", "0", "0", "0", "200e-6[1/K]", "0", "0", "0", "200e-6[1/K]"});
    model.component("comp1").material("mat31").propertyGroup("def").set("lossfactor", "0.06");
    model.component("comp1").material("mat31").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.5[W/(m*K)]", "0", "0", "0", "0.5[W/(m*K)]", "0", "0", "0", "0.5[W/(m*K)]"});
    model.component("comp1").material("mat31").propertyGroup("def").set("heatcapacity", "1900[J/(kg*K)]");
    model.component("comp1").material("mat31").propertyGroup("Lame").set("lambLame", "24.5[MPa]");
    model.component("comp1").material("mat31").propertyGroup("Lame").set("muLame", "0.5[MPa]");
    model.component("comp1").material("mat31").propertyGroup("MooneyRivlin").label("Mooney-Rivlin");
    model.component("comp1").material("mat31").propertyGroup("MooneyRivlin").set("C10", "0.219[MPa]");
    model.component("comp1").material("mat31").propertyGroup("MooneyRivlin").set("C01", "0.031[MPa]");
    model.component("comp1").material("mat31").propertyGroup("MooneyRivlin").set("C11", "0[Pa]");
    model.component("comp1").material("mat31").propertyGroup("MooneyRivlin").set("C20", "0[Pa]");
    model.component("comp1").material("mat31").propertyGroup("MooneyRivlin").set("C02", "0[Pa]");
    model.component("comp1").material("mat31").propertyGroup("MooneyRivlin").set("C21", "0[Pa]");
    model.component("comp1").material("mat31").propertyGroup("MooneyRivlin").set("C12", "0[Pa]");
    model.component("comp1").material("mat31").propertyGroup("MooneyRivlin").set("C30", "0[Pa]");
    model.component("comp1").material("mat31").propertyGroup("MooneyRivlin").set("C03", "0[Pa]");
    model.component("comp1").material("mat31").propertyGroup("Yeoh").set("c1YE", "100[kPa]");
    model.component("comp1").material("mat31").propertyGroup("Yeoh").set("c2YE", "6[kPa]");
    model.component("comp1").material("mat31").propertyGroup("Yeoh").set("c3YE", "0[Pa]");
    model.component("comp1").material("mat31").propertyGroup("Varga").set("c1VA", "1.05[MPa]");
    model.component("comp1").material("mat31").propertyGroup("Varga").set("c2VA", "0.45[MPa]");
    model.component("comp1").material("mat31").propertyGroup("ArrudaBoyce").label("Arruda-Boyce");
    model.component("comp1").material("mat31").propertyGroup("ArrudaBoyce").set("Nseg", "32");
    model.component("comp1").material("mat31").propertyGroup("ArrudaBoyce").set("mu0", "0.5[MPa]");
    model.component("comp1").material().create("mat32", "Common");
    model.component("comp1").material("mat32").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat32").propertyGroup()
         .create("IsotropicStrengthParameters", "IsotropicStrengthParameters", "Isotropic strength parameters");
    model.component("comp1").material("mat32").label("S-glass fiber");
    model.component("comp1").material("mat32").propertyGroup("def").set("density", "2460[kg/m^3]");
    model.component("comp1").material("mat32").propertyGroup("def")
         .setPropertyInfo("density", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material("mat32").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"1.6E-6[1/K]", "0", "0", "0", "1.6E-6[1/K]", "0", "0", "0", "1.6E-6[1/K]"});
    model.component("comp1").material("mat32").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material("mat32").propertyGroup("def").set("heatcapacity", "737[J/(kg*K)]");
    model.component("comp1").material("mat32").propertyGroup("def")
         .setPropertyInfo("heatcapacity", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material("mat32").propertyGroup("Enu").set("E", "86.9[GPa]");
    model.component("comp1").material("mat32").propertyGroup("Enu")
         .setPropertyInfo("E", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material("mat32").propertyGroup("Enu").set("nu", "0.23");
    model.component("comp1").material("mat32").propertyGroup("IsotropicStrengthParameters")
         .set("sigmat", "4890[MPa]");
    model.component("comp1").material("mat32").propertyGroup("IsotropicStrengthParameters")
         .setPropertyInfo("sigmat", "Reference: D. Hartman, M. E. Greenwood, and D. M. Miller, Technical Paper: High Strength Glass Fibers, https://www.agy.com/wp-content/uploads/2014/03/High_Strength_Glass_Fibers-Technical.pdf");
    model.component("comp1").material().create("mat33", "Common");
    model.component("comp1").material("mat33").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat33").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat33").label("Silica glass");
    model.component("comp1").material("mat33").set("family", "custom");
    model.component("comp1").material("mat33").set("diffuse", "custom");
    model.component("comp1").material("mat33").set("ambient", "custom");
    model.component("comp1").material("mat33").set("noise", true);
    model.component("comp1").material("mat33").set("fresnel", 0.99);
    model.component("comp1").material("mat33").set("roughness", 0.02);
    model.component("comp1").material("mat33").set("diffusewrap", 0);
    model.component("comp1").material("mat33").set("reflectance", 0);
    model.component("comp1").material("mat33").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat33").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-14[S/m]", "0", "0", "0", "1e-14[S/m]", "0", "0", "0", "1e-14[S/m]"});
    model.component("comp1").material("mat33").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"0.55e-6[1/K]", "0", "0", "0", "0.55e-6[1/K]", "0", "0", "0", "0.55e-6[1/K]"});
    model.component("comp1").material("mat33").propertyGroup("def").set("heatcapacity", "703[J/(kg*K)]");
    model.component("comp1").material("mat33").propertyGroup("def")
         .set("relpermittivity", new String[]{"3.75", "0", "0", "0", "3.75", "0", "0", "0", "3.75"});
    model.component("comp1").material("mat33").propertyGroup("def").set("density", "2203[kg/m^3]");
    model.component("comp1").material("mat33").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.38[W/(m*K)]", "0", "0", "0", "1.38[W/(m*K)]", "0", "0", "0", "1.38[W/(m*K)]"});
    model.component("comp1").material("mat33").propertyGroup("Enu").set("E", "73.1[GPa]");
    model.component("comp1").material("mat33").propertyGroup("Enu").set("nu", "0.17");
    model.component("comp1").material("mat33").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1.45", "0", "0", "0", "1.45", "0", "0", "0", "1.45"});
    model.component("comp1").material().create("mat34", "Common");
    model.component("comp1").material("mat34").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat34").propertyGroup()
         .create("IsotropicStrengthParameters", "IsotropicStrengthParameters", "Isotropic strength parameters");
    model.component("comp1").material("mat34").label("Silicon carbide ceramic");
    model.component("comp1").material("mat34").propertyGroup("def").set("density", "3200[kg/m^3]");
    model.component("comp1").material("mat34").propertyGroup("def")
         .setPropertyInfo("density", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat34").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"8.5E-6[1/K]", "0", "0", "0", "8.5E-6[1/K]", "0", "0", "0", "8.5E-6[1/K]"});
    model.component("comp1").material("mat34").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat34").propertyGroup("def").set("heatcapacity", "");
    model.component("comp1").material("mat34").propertyGroup("Enu").set("E", "420[GPa]");
    model.component("comp1").material("mat34").propertyGroup("Enu")
         .setPropertyInfo("E", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat34").propertyGroup("Enu").set("nu", "0.18");
    model.component("comp1").material("mat34").propertyGroup("IsotropicStrengthParameters")
         .set("sigmat", "310[MPa]");
    model.component("comp1").material("mat34").propertyGroup("IsotropicStrengthParameters")
         .setPropertyInfo("sigmat", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material().create("mat35", "Common");
    model.component("comp1").material("mat35").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat35").propertyGroup()
         .create("IsotropicStrengthParameters", "IsotropicStrengthParameters", "Isotropic strength parameters");
    model.component("comp1").material("mat35").label("Silicon nitride ceramic");
    model.component("comp1").material("mat35").propertyGroup("def").set("density", "3200[kg/m^3]");
    model.component("comp1").material("mat35").propertyGroup("def")
         .setPropertyInfo("density", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat35").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"2.56E-6[1/K]", "0", "0", "0", "2.56E-6[1/K]", "0", "0", "0", "2.56E-6[1/K]"});
    model.component("comp1").material("mat35").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat35").propertyGroup("def").set("heatcapacity", "");
    model.component("comp1").material("mat35").propertyGroup("Enu").set("E", "310[GPa]");
    model.component("comp1").material("mat35").propertyGroup("Enu")
         .setPropertyInfo("E", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat35").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material("mat35").propertyGroup("IsotropicStrengthParameters")
         .set("sigmat", "410[MPa]");
    model.component("comp1").material("mat35").propertyGroup("IsotropicStrengthParameters")
         .setPropertyInfo("sigmat", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material().create("mat36", "Common");
    model.component("comp1").material("mat36").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat36").propertyGroup()
         .create("IsotropicStrengthParameters", "IsotropicStrengthParameters", "Isotropic strength parameters");
    model.component("comp1").material("mat36").label("Soda lime glass ceramic");
    model.component("comp1").material("mat36").propertyGroup("def").set("density", "2500[kg/m^3]");
    model.component("comp1").material("mat36").propertyGroup("def")
         .setPropertyInfo("density", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat36").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"8.9E-6[1/K]", "0", "0", "0", "8.9E-6[1/K]", "0", "0", "0", "8.9E-6[1/K]"});
    model.component("comp1").material("mat36").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat36").propertyGroup("def").set("heatcapacity", "");
    model.component("comp1").material("mat36").propertyGroup("Enu").set("E", "60[GPa]");
    model.component("comp1").material("mat36").propertyGroup("Enu")
         .setPropertyInfo("E", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat36").propertyGroup("Enu").set("nu", "0.23");
    model.component("comp1").material("mat36").propertyGroup("IsotropicStrengthParameters")
         .set("sigmat", "100[MPa]");
    model.component("comp1").material("mat36").propertyGroup("IsotropicStrengthParameters")
         .setPropertyInfo("sigmat", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material().create("mat37", "Common");
    model.component("comp1").material("mat37").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat37").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.component("comp1").material("mat37").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.component("comp1").material("mat37").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat37").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.component("comp1").material("mat37").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat37").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.component("comp1").material("mat37").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.component("comp1").material("mat37").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.component("comp1").material("mat37").propertyGroup().create("Swift", "Swift", "Swift");
    model.component("comp1").material("mat37").propertyGroup().create("Voce", "Voce", "Voce");
    model.component("comp1").material("mat37").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.component("comp1").material("mat37").propertyGroup()
         .create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.component("comp1").material("mat37").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.component("comp1").material("mat37").propertyGroup()
         .create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp1").material("mat37").propertyGroup("ArmstrongFrederick").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat37").propertyGroup().create("Norton", "Norton", "Norton");
    model.component("comp1").material("mat37").propertyGroup()
         .create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat37").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.component("comp1").material("mat37").label("Structural steel");
    model.component("comp1").material("mat37").set("family", "custom");
    model.component("comp1").material("mat37")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat37").set("diffuse", "custom");
    model.component("comp1").material("mat37")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat37").set("ambient", "custom");
    model.component("comp1").material("mat37")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat37").set("noise", true);
    model.component("comp1").material("mat37").set("fresnel", 0.9);
    model.component("comp1").material("mat37").set("roughness", 0.3);
    model.component("comp1").material("mat37").set("diffusewrap", 0);
    model.component("comp1").material("mat37").set("reflectance", 0);
    model.component("comp1").material("mat37").propertyGroup("def").set("lossfactor", "0.02");
    model.component("comp1").material("mat37").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat37").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat37").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat37").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat37").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat37").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat37").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat37").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.component("comp1").material("mat37").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.component("comp1").material("mat37").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat37").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.component("comp1").material("mat37").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat37").propertyGroup("Enu").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat37").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.component("comp1").material("mat37").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.component("comp1").material("mat37").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.component("comp1").material("mat37").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat37").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat37").propertyGroup("Enu").set("E", "E(T)");
    model.component("comp1").material("mat37").propertyGroup("Enu").set("nu", "nu(T)");
    model.component("comp1").material("mat37").propertyGroup("Enu").addInput("temperature");
    model.component("comp1").material("mat37").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.component("comp1").material("mat37").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.component("comp1").material("mat37").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.component("comp1").material("mat37").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.component("comp1").material("mat37").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat37").propertyGroup("ElastoplasticModel").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat37").propertyGroup("ElastoplasticModel").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat37").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.component("comp1").material("mat37").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat37").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat37").propertyGroup("ElastoplasticModel")
         .set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.component("comp1").material("mat37").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.component("comp1").material("mat37").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.component("comp1").material("mat37").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.component("comp1").material("mat37").propertyGroup("ElastoplasticModel")
         .addInput("effectiveplasticstrain");
    model.component("comp1").material("mat37").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp1").material("mat37").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat37").propertyGroup("Ludwik").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat37").propertyGroup("Ludwik").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat37").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.component("comp1").material("mat37").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.component("comp1").material("mat37").propertyGroup("Ludwik").addInput("temperature");
    model.component("comp1").material("mat37").propertyGroup("JohnsonCook").label("Johnson-Cook");
    model.component("comp1").material("mat37").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.component("comp1").material("mat37").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.component("comp1").material("mat37").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.component("comp1").material("mat37").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.component("comp1").material("mat37").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.component("comp1").material("mat37").propertyGroup("Swift").set("e0_swi", "0.021");
    model.component("comp1").material("mat37").propertyGroup("Swift").set("n_swi", "0.2");
    model.component("comp1").material("mat37").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.component("comp1").material("mat37").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});

    return model;
  }

  public static Model run5(Model model) {
    model.component("comp1").material("mat37").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat37").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat37").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp1").material("mat37").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp1").material("mat37").propertyGroup("Voce").addInput("temperature");
    model.component("comp1").material("mat37").propertyGroup("HockettSherby").label("Hockett-Sherby");
    model.component("comp1").material("mat37").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.component("comp1").material("mat37").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat37").propertyGroup("HockettSherby").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat37").propertyGroup("HockettSherby").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat37").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.component("comp1").material("mat37").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.component("comp1").material("mat37").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.component("comp1").material("mat37").propertyGroup("HockettSherby").addInput("temperature");
    model.component("comp1").material("mat37").propertyGroup("ArmstrongFrederick").label("Armstrong-Frederick");
    model.component("comp1").material("mat37").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.component("comp1").material("mat37").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat37").propertyGroup("ArmstrongFrederick").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat37").propertyGroup("ArmstrongFrederick").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat37").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.component("comp1").material("mat37").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.component("comp1").material("mat37").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.component("comp1").material("mat37").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.component("comp1").material("mat37").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.component("comp1").material("mat37").propertyGroup("Norton").set("n_nor", "4.5");
    model.component("comp1").material("mat37").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.component("comp1").material("mat37").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.component("comp1").material("mat37").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.component("comp1").material("mat37").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.component("comp1").material("mat37").propertyGroup("ChabocheViscoplasticity")
         .set("sigRef_cha", "490[MPa]");
    model.component("comp1").material("mat37").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");
    model.component("comp1").material().create("mat38", "Common");
    model.component("comp1").material("mat38").propertyGroup()
         .create("TransverseIsotropic", "TransverseIsotropic", "Transversely isotropic");
    model.component("comp1").material("mat38").label("T-300 carbon fiber");
    model.component("comp1").material("mat38").propertyGroup("def").set("density", "1760[kg/m^3]");
    model.component("comp1").material("mat38").propertyGroup("def")
         .setPropertyInfo("density", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.component("comp1").material("mat38").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"-0.7E-6[1/K]", "0", "0", "0", "12E-6[1/K]", "0", "0", "0", "12E-6[1/K]"});
    model.component("comp1").material("mat38").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.component("comp1").material("mat38").propertyGroup("def").set("heatcapacity", "");
    model.component("comp1").material("mat38").propertyGroup("TransverseIsotropic")
         .set("Evect", new String[]{"230[GPa]", "15[GPa]"});
    model.component("comp1").material("mat38").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("Evect", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.component("comp1").material("mat38").propertyGroup("TransverseIsotropic")
         .set("nuvect", new String[]{"0.2", "0.0714"});
    model.component("comp1").material("mat38").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("nuvect", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.component("comp1").material("mat38").propertyGroup("TransverseIsotropic").set("Gvect1", "27[GPa]");
    model.component("comp1").material("mat38").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("Gvect1", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.component("comp1").material().create("mat39", "Common");
    model.component("comp1").material("mat39").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat39").propertyGroup()
         .create("IsotropicStrengthParameters", "IsotropicStrengthParameters", "Isotropic strength parameters");
    model.component("comp1").material("mat39").label("Vinylester");
    model.component("comp1").material("mat39").propertyGroup("def").set("density", "1150[kg/m^3]");
    model.component("comp1").material("mat39").propertyGroup("def")
         .setPropertyInfo("density", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.component("comp1").material("mat39").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"125E-6[1/K]", "0", "0", "0", "125E-6[1/K]", "0", "0", "0", "125E-6[1/K]"});
    model.component("comp1").material("mat39").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.component("comp1").material("mat39").propertyGroup("def").set("heatcapacity", "");
    model.component("comp1").material("mat39").propertyGroup("Enu").set("E", "3.5[GPa]");
    model.component("comp1").material("mat39").propertyGroup("Enu")
         .setPropertyInfo("E", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.component("comp1").material("mat39").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat39").propertyGroup("Enu")
         .setPropertyInfo("nu", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.component("comp1").material("mat39").propertyGroup("IsotropicStrengthParameters").set("sigmat", "75[MPa]");
    model.component("comp1").material("mat39").propertyGroup("IsotropicStrengthParameters")
         .setPropertyInfo("sigmat", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.component("comp1").material().create("mat40", "Common");
    model.component("comp1").material("mat40").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat40").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat40").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp1").material("mat40").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat40").propertyGroup("def").func().create("cs", "Interpolation");
    model.component("comp1").material("mat40").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat40").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat40").propertyGroup("def").func().create("an3", "Analytic");
    model.component("comp1").material("mat40").label("Water, liquid");
    model.component("comp1").material("mat40").set("family", "water");
    model.component("comp1").material("mat40").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat40").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.component("comp1").material("mat40").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat40").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat40").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat40").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.component("comp1").material("mat40").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat40").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat40").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp1").material("mat40").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.component("comp1").material("mat40").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.component("comp1").material("mat40").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp1").material("mat40").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat40").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat40").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.component("comp1").material("mat40").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat40").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat40").propertyGroup("def").func("cs")
         .set("table", new String[][]{{"273", "1403"}, 
         {"278", "1427"}, 
         {"283", "1447"}, 
         {"293", "1481"}, 
         {"303", "1507"}, 
         {"313", "1526"}, 
         {"323", "1541"}, 
         {"333", "1552"}, 
         {"343", "1555"}, 
         {"353", "1555"}, 
         {"363", "1550"}, 
         {"373", "1543"}});
    model.component("comp1").material("mat40").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.component("comp1").material("mat40").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.component("comp1").material("mat40").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat40").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat40").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.component("comp1").material("mat40").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp1").material("mat40").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat40").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat40").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat40").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat40").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.component("comp1").material("mat40").propertyGroup("def").func("an2")
         .set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.component("comp1").material("mat40").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat40").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp1").material("mat40").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat40").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat40").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat40").propertyGroup("def").func("an3").set("funcname", "muB");
    model.component("comp1").material("mat40").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.component("comp1").material("mat40").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.component("comp1").material("mat40").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.component("comp1").material("mat40").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat40").propertyGroup("def").func("an3")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat40").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.component("comp1").material("mat40").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat40").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat40").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.component("comp1").material("mat40").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat40").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat40").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.component("comp1").material("mat40").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.component("comp1").material("mat40").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat40").propertyGroup("def").set("density", "rho(T)");
    model.component("comp1").material("mat40").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat40").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat40").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat13").selection().named("uni2");
    model.component("comp1").material("mat37").selection().named("uni1");

    model.component("comp1").physics("cc").selection().named("uni1");
    model.component("comp1").physics("cc").prop("Settings").set("CreateBasis", true);
    model.component("comp1").physics("cc").feature("css1").set("SecondVector", "z");
    model.component("comp1").physics("cc").create("diff1", "DiffusionMethod", 3);
    model.component("comp1").physics("cc").feature("diff1").selection().named("uni1");
    model.component("comp1").physics("cc").feature("diff1").create("inl1", "Inlet", 2);
    model.component("comp1").physics("cc").feature("diff1").feature("inl1").selection().named("uni7");
    model.component("comp1").physics("cc").feature("diff1").feature().duplicate("inl2", "inl1");
    model.component("comp1").physics("cc").feature("diff1").feature("inl2").selection().named("uni9");
    model.component("comp1").physics("cc").feature("diff1").create("out1", "Outlet", 2);
    model.component("comp1").physics("cc").feature("diff1").feature("out1").selection().named("uni8");
    model.component("comp1").physics("cc").feature("diff1").feature().duplicate("out2", "out1");
    model.component("comp1").physics("cc").feature("diff1").feature("out2").selection().named("uni10");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 4);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("uni11");
    model.component("comp1").mesh("mesh1").create("id1", "IdenticalMesh");
    model.component("comp1").mesh("mesh1").feature("id1").selection("group1").named("uni11");
    model.component("comp1").mesh("mesh1").feature("id1").selection("group2").named("uni12");
    model.component("comp1").mesh("mesh1").create("ftri2", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri2").selection().named("uni7");
    model.component("comp1").mesh("mesh1").create("id2", "IdenticalMesh");
    model.component("comp1").mesh("mesh1").feature("id2").selection("group1").named("uni7");
    model.component("comp1").mesh("mesh1").feature("id2").selection("group2").named("uni8");
    model.component("comp1").mesh("mesh1").create("ftri3", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri3").selection().named("uni9");
    model.component("comp1").mesh("mesh1").create("id3", "IdenticalMesh");
    model.component("comp1").mesh("mesh1").feature("id3").selection("group1").named("uni9");
    model.component("comp1").mesh("mesh1").feature("id3").selection("group2").named("uni10");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("Study: Curvilinear Coordinates");
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("Vector Field (cc)");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("udist", 0.1);
    model.result("pg1").feature("str1").set("linetype", "tube");
    model.result("pg1").feature("str1").set("smooth", "internal");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").label("Coordinate system (cc)");
    model.result("pg2").create("sys1", "CoordSysVolume");
    model.result("pg2").feature("sys1").set("sys", "cc_cs");
    model.result("pg1").run();

    model.component("comp1").physics("solid").create("rd1", "RigidDomain", 3);
    model.component("comp1").physics("solid").feature("rd1").selection().named("uni3");
    model.component("comp1").physics("solid").feature("rd1").set("minput_strainreferencetemperature_src", "userdef");
    model.component("comp1").physics("solid").feature("rd1").set("minput_strainreferencetemperature", "T0");
    model.component("comp1").physics("solid").feature("rd1").create("fix1", "FixedConstraint", -1);

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/cc", true);
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std2").label("Study: Homogenized Density");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").setSolveFor("/physics/cc", false);
    model.study("std2").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("Homogenized Density");
    model.result().evaluationGroup("eg1").set("data", "dset2");
    model.result().evaluationGroup("eg1").set("includeparameters", false);
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup("eg1").feature("gev1")
         .setIndex("expr", "(intop1(solid.rho)+intop2(solid.rho))/(intop1(1)+intop2(1))", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", "kg/m^3", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "Density", 0);
    model.result().evaluationGroup("eg1").run();

    model.component("comp1").physics("solid").feature("lemm1").set("minput_pressure", "pA0");
    model.component("comp1").physics("solid").feature("lemm1").set("SolidModel", "Orthotropic");
    model.component("comp1").physics("solid").feature().duplicate("lemm2", "lemm1");
    model.component("comp1").physics("solid").feature("lemm2").set("coordinateSystem", "sys2");
    model.component("comp1").physics("solid").feature("lemm2").active(false);
    model.component("comp1").physics("solid").feature().move("lemm2", 3);
    model.component("comp1").physics("solid").create("cp1", "CellPeriodicity", 3);
    model.component("comp1").physics("solid").feature("cp1").label("Cell Periodicity: Elasticity Matrix");
    model.component("comp1").physics("solid").feature("cp1").selection().named("uni3");
    model.component("comp1").physics("solid").feature("cp1").set("BoundaryExpansion", "PrescribedStrain");
    model.component("comp1").physics("solid").feature("cp1").set("computeElasticityMatrixStandard", true);
    model.component("comp1").physics("solid").feature("cp1").runCommand("createLoadGroupsandStudy");
    model.component("comp1").physics("solid").feature("cp1").create("bp1", "BoundaryPair", 2);
    model.component("comp1").physics("solid").feature("cp1").feature("bp1").selection().named("uni4");
    model.component("comp1").physics("solid").feature("cp1").feature("bp1")
         .set("constraintOptions", "weakConstraints");
    model.component("comp1").physics("solid").feature("cp1").create("bp2", "BoundaryPair", 2);
    model.component("comp1").physics("solid").feature("cp1").feature("bp2").selection().named("uni5");
    model.component("comp1").physics("solid").feature("cp1").feature("bp2")
         .set("constraintOptions", "weakConstraints");
    model.component("comp1").physics("solid").feature("cp1").create("bp3", "BoundaryPair", 2);
    model.component("comp1").physics("solid").feature("cp1").feature("bp3").selection().named("uni6");
    model.component("comp1").physics("solid").feature("cp1").feature("bp3")
         .set("constraintOptions", "weakConstraints");

    model.study("solidcp1std").feature("solidcp1stat").set("useadvanceddisable", true);
    model.study("solidcp1std").feature("solidcp1stat").set("disabledphysics", new String[]{"solid/rd1"});
    model.study("solidcp1std").feature("solidcp1stat").setSolveFor("/physics/cc", false);
    model.study("solidcp1std").feature("solidcp1stat").setSolveFor("/physics/ht", false);
    model.study("solidcp1std").feature("solidcp1stat").set("usesol", true);
    model.study("solidcp1std").feature("solidcp1stat").set("notsolmethod", "sol");
    model.study("solidcp1std").feature("solidcp1stat").set("notstudy", "std1");
    model.study("solidcp1std").label("Study: Homogenized Elasticity Matrix");
    model.study("solidcp1std").createAutoSequences("all");

    model.sol("solidcp1sol").runAll();

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevel", 6, 0);
    model.result("pg3").label("Stress (solid)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg3").feature("vol1").set("threshold", "manual");
    model.result("pg3").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("vol1").set("colortable", "Rainbow");
    model.result("pg3").feature("vol1").set("colortabletrans", "none");
    model.result("pg3").feature("vol1").set("colorscalemode", "linear");
    model.result("pg3").feature("vol1").set("resolution", "custom");
    model.result("pg3").feature("vol1").set("refine", 2);
    model.result("pg3").feature("vol1").set("colortable", "Prism");
    model.result("pg3").feature("vol1").create("def", "Deform");
    model.result("pg3").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg3").feature("vol1").feature("def").set("descr", "Displacement field");
    model.result().evaluationGroup().create("solidcp1stdEg", "EvaluationGroup");
    model.result().evaluationGroup("solidcp1stdEg").set("data", "dset3");
    model.result().evaluationGroup("solidcp1stdEg").set("includeparameters", "off");
    model.result().evaluationGroup("solidcp1stdEg").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("solidcp1stdEg")
         .label("Effective Material Properties (Study: Homogenized Elasticity Matrix)");
    model.result().evaluationGroup("solidcp1stdEg").create("gmevescp1", "EvalGlobalMatrix");
    model.result().evaluationGroup("solidcp1stdEg").feature("gmevescp1").set("expr", "root.comp1.solid.cp1.D");
    model.result().evaluationGroup("solidcp1stdEg").feature("gmevescp1").set("descr", "Elasticity matrix");
    model.result().evaluationGroup("solidcp1stdEg").run();
    model.result("pg3").run();
    model.result().evaluationGroup("solidcp1stdEg").label("Homogenized Elasticity Matrix");

    model.component("comp1").physics("solid").feature("lemm1").create("te1", "ThermalExpansion", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("te1").set("minput_pressure", "pA0");
    model.component("comp1").physics("solid").feature("lemm1").feature("te1")
         .set("minput_temperature_src", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("te1")
         .set("minput_temperature", "293.15[K] +1[K]");
    model.component("comp1").physics("solid").feature("lemm2").create("te1", "ThermalExpansion", 3);
    model.component("comp1").physics("solid").feature("lemm2").feature("te1").set("minput_pressure", "pA0");
    model.component("comp1").physics("solid").feature("lemm2").feature("te1")
         .set("minput_temperature_src", "userdef");
    model.component("comp1").physics("solid").feature("lemm2").feature("te1")
         .set("minput_temperature", "293.15[K] +1[K]");
    model.component("comp1").physics("solid").feature().duplicate("cp2", "cp1");
    model.component("comp1").physics("solid").feature("cp2")
         .label("Cell Periodicity: Coefficient of Thermal Expansion");
    model.component("comp1").physics("solid").feature("cp2").set("BoundaryExpansion", "FreeExpansion");
    model.component("comp1").physics("solid").feature("cp2").set("computeThermalExpansion", true);

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/cc", true);
    model.study("std3").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std3").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std3").label("Study: Homogenized Coefficient of Thermal Expansion");
    model.study("std3").feature("stat").set("useadvanceddisable", true);
    model.study("std3").feature("stat").set("disabledphysics", new String[]{"solid/rd1", "solid/cp1"});
    model.study("std3").feature("stat").setSolveFor("/physics/cc", false);
    model.study("std3").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std3").feature("stat").set("usesol", true);
    model.study("std3").feature("stat").set("notsolmethod", "sol");
    model.study("std3").feature("stat").set("notstudy", "std1");
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset4");
    model.result("pg4").label("Stress (solid) 1");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg4").feature("vol1").set("threshold", "manual");
    model.result("pg4").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("vol1").set("colortable", "Rainbow");
    model.result("pg4").feature("vol1").set("colortabletrans", "none");
    model.result("pg4").feature("vol1").set("colorscalemode", "linear");
    model.result("pg4").feature("vol1").set("resolution", "custom");
    model.result("pg4").feature("vol1").set("refine", 2);
    model.result("pg4").feature("vol1").set("colortable", "Prism");
    model.result("pg4").feature("vol1").create("def", "Deform");
    model.result("pg4").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg4").feature("vol1").feature("def").set("descr", "Displacement field");

    return model;
  }

  public static Model run6(Model model) {
    model.result().evaluationGroup().create("std3Eg", "EvaluationGroup");
    model.result().evaluationGroup("std3Eg").set("data", "dset4");
    model.result().evaluationGroup("std3Eg").set("includeparameters", "off");
    model.result().evaluationGroup("std3Eg")
         .label("Effective Material Properties (Study: Homogenized Coefficient of Thermal Expansion)");
    model.result().evaluationGroup("std3Eg").create("gmevtcp2", "EvalGlobalMatrix");
    model.result().evaluationGroup("std3Eg").feature("gmevtcp2").set("expr", "root.comp1.solid.cp2.alpha");
    model.result().evaluationGroup("std3Eg").feature("gmevtcp2").set("descr", "Coefficient of thermal expansion");
    model.result().evaluationGroup("std3Eg").run();
    model.result("pg4").run();
    model.result().evaluationGroup("std3Eg").label("Homogenized Coefficient of Thermal Expansion");

    model.component("comp1").physics("ht").feature("solid1").set("minput_pressure", "pA0");
    model.component("comp1").physics("ht").feature().duplicate("solid2", "solid1");
    model.component("comp1").physics("ht").feature("solid2").set("coordinateSystem", "sys2");
    model.component("comp1").physics("ht").feature("solid2").active(false);
    model.component("comp1").physics("ht").create("constr1", "PointwiseConstraint", 0);
    model.component("comp1").physics("ht").feature("constr1").set("constraintExpression", "T-T0");
    model.component("comp1").physics("ht").feature("constr1").selection().set(1);

    model.study().create("std4");
    model.study("std4").create("stat", "Stationary");
    model.study("std4").feature("stat").setSolveFor("/physics/cc", true);
    model.study("std4").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std4").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std4").label("Study: Homogenized Heat Capacity");
    model.study("std4").setGenPlots(false);
    model.study("std4").feature("stat").set("useadvanceddisable", true);
    model.study("std4").feature("stat").setSolveFor("/physics/cc", false);
    model.study("std4").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std4").feature("stat").set("usesol", true);
    model.study("std4").feature("stat").set("notsolmethod", "sol");
    model.study("std4").feature("stat").set("notstudy", "std1");
    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().evaluationGroup().create("eg2", "EvaluationGroup");
    model.result().evaluationGroup("eg2").label("Homogenized Heat Capacity");
    model.result().evaluationGroup("eg2").set("data", "dset5");
    model.result().evaluationGroup("eg2").set("includeparameters", false);
    model.result().evaluationGroup("eg2").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg2").run();
    model.result().evaluationGroup("eg2").feature("gev1")
         .setIndex("expr", "(intop1(ht.rho*ht.Cp)+intop2(ht.rho*ht.Cp))/(intop1(ht.rho)+intop2(ht.rho))", 0);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("unit", "J/(kg*K)", 0);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("descr", "Heat Capacity", 0);
    model.result().evaluationGroup("eg2").run();

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("dTx", "1[K]");
    model.component("comp1").variable("var1").descr("dTx", "Temperature offset, x direction");
    model.component("comp1").variable("var1").set("dTy", "1[K]");
    model.component("comp1").variable("var1").descr("dTy", "Temperature offset, y direction");
    model.component("comp1").variable("var1").set("dTz", "1[K]");
    model.component("comp1").variable("var1").descr("dTz", "Temperature offset, z direction");
    model.component("comp1").variable("var1").set("wm", "2*intop3(1)/intop4(1)");
    model.component("comp1").variable("var1").descr("wm", "Cell width");
    model.component("comp1").variable("var1").set("dm", "2*intop3(1)/intop5(1)");
    model.component("comp1").variable("var1").descr("dm", "Cell depth");
    model.component("comp1").variable("var1").set("hm", "2*intop3(1)/intop6(1)");
    model.component("comp1").variable("var1").descr("hm", "Cell height");
    model.component("comp1").variable("var1")
         .set("k11", "withsol('sol5',intop4(-ht.ndflux*ht.nx)/(2*dm*hm*dTx/wm),setval(loadcase,1))");
    model.component("comp1").variable("var1").descr("k11", "Thermal conductivity, 11 component");
    model.component("comp1").variable("var1")
         .set("k12", "withsol('sol5',intop4(-ht.ndflux*ht.nx)/(2*dm*hm*dTy/dm),setval(loadcase,2))");
    model.component("comp1").variable("var1").descr("k12", "Thermal conductivity, 12 component");
    model.component("comp1").variable("var1")
         .set("k13", "withsol('sol5',intop4(-ht.ndflux*ht.nx)/(2*dm*hm*dTz/hm),setval(loadcase,3))");
    model.component("comp1").variable("var1").descr("k13", "Thermal conductivity, 13 component");
    model.component("comp1").variable("var1")
         .set("k22", "withsol('sol5',intop5(-ht.ndflux*ht.ny)/(2*wm*hm*dTy/dm),setval(loadcase,2))");
    model.component("comp1").variable("var1").descr("k22", "Thermal conductivity, 22 component");
    model.component("comp1").variable("var1")
         .set("k23", "withsol('sol5',intop5(-ht.ndflux*ht.ny)/(2*wm*hm*dTz/hm),setval(loadcase,3))");
    model.component("comp1").variable("var1").descr("k23", "Thermal conductivity, 23 component");
    model.component("comp1").variable("var1")
         .set("k33", "withsol('sol5',intop6(-ht.ndflux*ht.nz)/(2*wm*dm*dTz/hm),setval(loadcase,3))");
    model.component("comp1").variable("var1").descr("k33", "Thermal conductivity, 33 component");

    model.component("comp1").common().create("mat1", "Matrix");
    model.component("comp1").common("mat1").set("format", "symmetric");
    model.component("comp1").common("mat1").setIndex("matrix", "k11", 0, 0);
    model.component("comp1").common("mat1").setIndex("matrix", "k12", 0, 1);
    model.component("comp1").common("mat1").setIndex("matrix", "k13", 0, 2);
    model.component("comp1").common("mat1").setIndex("matrix", "k22", 1, 1);
    model.component("comp1").common("mat1").setIndex("matrix", "k23", 1, 2);
    model.component("comp1").common("mat1").setIndex("matrix", "k33", 2, 2);

    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T0");
    model.component("comp1").physics("ht").create("pc1", "PeriodicHeat", 2);
    model.component("comp1").physics("ht").feature("pc1").selection().named("uni4");
    model.component("comp1").physics("ht").feature("pc1").set("DeltaT", "-dTx*group.cp11");
    model.component("comp1").physics("ht").create("pc2", "PeriodicHeat", 2);
    model.component("comp1").physics("ht").feature("pc2").selection().named("uni5");
    model.component("comp1").physics("ht").feature("pc2").set("DeltaT", "-dTy*group.cp22");
    model.component("comp1").physics("ht").create("pc3", "PeriodicHeat", 2);
    model.component("comp1").physics("ht").feature("pc3").selection().named("uni6");
    model.component("comp1").physics("ht").feature("pc3").set("DeltaT", "-dTz*group.cp33");

    model.study().create("std5");
    model.study("std5").create("stat", "Stationary");
    model.study("std5").feature("stat").setSolveFor("/physics/cc", true);
    model.study("std5").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std5").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std5").feature("stat").set("useadvanceddisable", true);
    model.study("std5").feature("stat").setSolveFor("/physics/cc", false);
    model.study("std5").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std5").feature("stat").set("usesol", true);
    model.study("std5").feature("stat").set("notsolmethod", "sol");
    model.study("std5").feature("stat").set("notstudy", "std1");
    model.study("std5").feature("stat").set("useloadcase", true);
    model.study("std5").feature("stat").setIndex("loadcase", "Load case 1", 0);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 0, 1);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 0, 1);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 0, 2);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 0, 2);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 0, 3);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 0, 3);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 0, 4);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 0, 4);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 0, 5);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 0, 5);
    model.study("std5").feature("stat").setIndex("loadcase", "Load case 1", 0);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 0, 1);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 0, 1);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 0, 2);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 0, 2);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 0, 3);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 0, 3);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 0, 4);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 0, 4);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 0, 5);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 0, 5);
    model.study("std5").feature("stat").setIndex("loadgroup", true, 0, 0);
    model.study("std5").feature("stat").setIndex("loadcase", "Load case 2", 1);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 1, 0);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 1, 0);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 1, 1);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 1, 1);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 1, 2);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 1, 2);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 1, 3);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 1, 3);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 1, 4);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 1, 4);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 1, 5);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 1, 5);
    model.study("std5").feature("stat").setIndex("loadcase", "Load case 2", 1);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 1, 0);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 1, 0);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 1, 1);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 1, 1);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 1, 2);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 1, 2);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 1, 3);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 1, 3);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 1, 4);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 1, 4);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 1, 5);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 1, 5);
    model.study("std5").feature("stat").setIndex("loadgroup", true, 1, 1);
    model.study("std5").feature("stat").setIndex("loadcase", "Load case 3", 2);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 2, 0);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 2, 0);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 2, 1);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 2, 1);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 2, 2);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 2, 2);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 2, 3);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 2, 3);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 2, 4);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 2, 4);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 2, 5);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 2, 5);
    model.study("std5").feature("stat").setIndex("loadcase", "Load case 3", 2);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 2, 0);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 2, 0);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 2, 1);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 2, 1);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 2, 2);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 2, 2);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 2, 3);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 2, 3);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 2, 4);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 2, 4);
    model.study("std5").feature("stat").setIndex("loadgroup", false, 2, 5);
    model.study("std5").feature("stat").setIndex("loadgroupweight", "1.0", 2, 5);
    model.study("std5").feature("stat").setIndex("loadgroup", true, 2, 2);
    model.study("std5").label("Study: Homogenized Thermal Conductivity");
    model.study("std5").createAutoSequences("all");

    model.sol("sol5").runAll();

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("Temperature (ht)");
    model.result("pg5").set("data", "dset6");
    model.result("pg5").setIndex("looplevel", 3, 0);
    model.result("pg5").feature().create("vol1", "Volume");
    model.result("pg5").feature("vol1").set("showsolutionparams", "on");
    model.result("pg5").feature("vol1").set("solutionparams", "parent");
    model.result("pg5").feature("vol1").set("expr", "T");
    model.result("pg5").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("vol1").set("smooth", "internal");
    model.result("pg5").feature("vol1").set("showsolutionparams", "on");
    model.result("pg5").feature("vol1").set("data", "parent");
    model.result("pg5").run();
    model.result().evaluationGroup().create("eg3", "EvaluationGroup");
    model.result().evaluationGroup("eg3").label("Homogenized Thermal Conductivity");
    model.result().evaluationGroup("eg3").set("data", "dset6");
    model.result().evaluationGroup("eg3").setIndex("looplevelinput", "first", 0);
    model.result().evaluationGroup("eg3").set("includeparameters", false);
    model.result().evaluationGroup("eg3").create("gpev1", "EvalPointMatrix");
    model.result().evaluationGroup("eg3").feature("gpev1").selection().set(1);
    model.result().evaluationGroup("eg3").feature("gpev1").set("expr", "T1");
    model.result().evaluationGroup("eg3").feature("gpev1").set("descr", "T1 - Matrix 1");
    model.result().evaluationGroup("eg3").feature("gpev1").set("descractive", true);
    model.result().evaluationGroup("eg3").feature("gpev1").set("descr", "Thermal Conductivity");
    model.result().evaluationGroup("eg3").run();

    model.study("std2").feature("stat").set("disabledphysics", new String[]{"solid/cp1", "solid/cp2"});
    model.study("solidcp1std").feature("solidcp1stat")
         .set("disabledphysics", new String[]{"solid/rd1", "solid/lemm1/te1", "solid/lemm2/te1", "solid/cp2"});
    model.study("std4").feature("stat").set("disabledphysics", new String[]{"ht/pc1", "ht/pc2", "ht/pc3"});

    model.result("pg3").run();
    model.result("pg3").stepFirst(0);
    model.result("pg3").run();

    model.title("\u5468\u671f\u6027\u5fae\u7ed3\u6784\u7684\u5747\u5300\u5316");

    model
         .description("\u672c\u6a21\u578b\u8ba1\u7b97\u7531\u57fa\u672c\u5355\u5143\u8868\u793a\u7684\u5f02\u8d28\u5468\u671f\u6027\u5fae\u7ed3\u6784\u7684\u5747\u5300\u673a\u68b0\u548c\u70ed\u6750\u6599\u5c5e\u6027\u3002");

    model.label("homogenization_of_periodic_microstructures.mph");

    model.result("pg3").run();
    model.result().report().create("rpt1", "Report");
    model.result().report("rpt1").set("templatesource", "brief");
    model.result().report("rpt1").set("format", "docx");
    model.result().report("rpt1").set("filename", "user:///homogenization_of_periodic_microstructures.docx");
    model.result().report("rpt1").feature().create("tp1", "TitlePage");
    model.result().report("rpt1").feature("tp1").set("titleimage", "none");
    model.result().report("rpt1").feature().create("toc1", "TableOfContents");
    model.result().report("rpt1").feature("toc1").label("\u76ee\u5f55");
    model.result().report("rpt1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").label("\u8f6f\u4ef6\u4fe1\u606f");
    model.result().report("rpt1").feature("sec1").feature().create("root1", "Model");
    model.result().report("rpt1").feature("sec1").feature("root1").label("\u5173\u4e8e\u8f6f\u4ef6");
    model.result().report("rpt1").feature("sec1").feature().create("std1", "Study");
    model.result().report("rpt1").feature("sec1").feature("std1").set("noderef", "std2");
    model.result().report("rpt1").feature("sec1").feature("std1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec1").feature().create("std2", "Study");
    model.result().report("rpt1").feature("sec1").feature("std2").set("noderef", "solidcp1std");
    model.result().report("rpt1").feature("sec1").feature("std2").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec1").feature().create("std3", "Study");
    model.result().report("rpt1").feature("sec1").feature("std3").set("noderef", "std3");
    model.result().report("rpt1").feature("sec1").feature("std3").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec1").feature().create("std4", "Study");
    model.result().report("rpt1").feature("sec1").feature("std4").set("noderef", "std4");
    model.result().report("rpt1").feature("sec1").feature("std4").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec1").feature().create("std5", "Study");
    model.result().report("rpt1").feature("sec1").feature("std5").set("noderef", "std5");
    model.result().report("rpt1").feature("sec1").feature("std5").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").label("App \u53c2\u6570");
    model.result().report("rpt1").feature("sec2").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec1").label("\u53c2\u6570");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature().create("param1", "Parameter");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .label("\u901a\u7528\u53c2\u6570");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 3, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 4, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 5, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 6, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 7, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 8, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 9, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 10, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param1")
         .setIndex("children", false, 11, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature().create("param2", "Parameter");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param2")
         .label("\u53c2\u6570\uff1a\u5355\u5411\u7ea4\u7ef4\u590d\u5408\u6750\u6599\uff0c\u65b9\u5f62\u5806\u79ef");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param2").set("noderef", "par2");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param2")
         .setIndex("children", false, 4, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature().create("param3", "Parameter");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param3")
         .label("\u53c2\u6570\uff1a\u5355\u5411\u7ea4\u7ef4\u590d\u5408\u6750\u6599\uff0c\u516d\u65b9\u5806\u79ef");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param3").set("noderef", "par3");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature().create("param4", "Parameter");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param4")
         .label("\u53c2\u6570\uff1a\u53cc\u5411\u7ea4\u7ef4\u590d\u5408\u6750\u6599");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param4").set("noderef", "par4");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature().create("param5", "Parameter");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param5")
         .label("\u53c2\u6570\uff1a\u53cc\u5411\u65e0\u538b\u63a5\u7ea4\u7ef4\u590d\u5408\u6750\u6599");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param5").set("noderef", "par5");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature().create("param6", "Parameter");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param6")
         .label("\u53c2\u6570\uff1a\u53cc\u5411\u5e73\u7eb9\u7ea4\u7ef4\u590d\u5408\u6750\u6599");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param6").set("noderef", "par6");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature().create("param7", "Parameter");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param7")
         .label("\u53c2\u6570\uff1a\u590d\u5408\u5fae\u7c92\uff0c\u7b80\u5355\u7acb\u65b9");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param7").set("noderef", "par7");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param7")
         .setIndex("children", false, 4, 1);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature().create("param8", "Parameter");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param8")
         .label("\u53c2\u6570\uff1a\u590d\u5408\u5fae\u7c92\uff0c\u4f53\u5fc3\u7acb\u65b9");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param8").set("noderef", "par8");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature().create("param9", "Parameter");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param9")
         .label("\u53c2\u6570\uff1a\u590d\u5408\u5fae\u7c92\uff0c\u9762\u5fc3\u7acb\u65b9");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param9").set("noderef", "par9");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature().create("param10", "Parameter");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param10")
         .label("\u53c2\u6570\uff1a\u8702\u7a9d");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param10").set("noderef", "par10");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature().create("param11", "Parameter");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param11")
         .label("\u53c2\u6570\uff1a\u5939\u5fc3");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param11").set("noderef", "par11");
    model.result().report("rpt1").feature("sec2").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec2").set("heading", "Geometry");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature().create("geom1", "Geometry");
    model.result().report("rpt1").feature("sec2").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec3").label("\u672c\u6784\u6750\u6599");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec1").set("source", "firstchild");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec1").feature()
         .create("mat1", "Material");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec1").feature("mat1")
         .set("includeimage", false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec1").feature("mat1")
         .set("noderef", "mat13");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec2", "sec1");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec2").feature("mat1")
         .set("noderef", "mat2");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec3", "sec2");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec3").feature("mat1")
         .set("noderef", "mat1");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec4", "sec3");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec4").feature("mat1")
         .set("noderef", "mat3");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec5", "sec4");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec5").feature("mat1")
         .set("noderef", "mat28");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec6", "sec5");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec6").feature("mat1")
         .set("noderef", "mat17");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec7", "sec6");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec7").feature("mat1")
         .set("noderef", "mat22");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec8", "sec7");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec8").feature("mat1")
         .set("noderef", "mat20");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec9", "sec8");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec9").feature("mat1")
         .set("noderef", "mat31");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec10", "sec9");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec10").feature("mat1")
         .set("noderef", "mat4");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec11", "sec10");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec11").feature("mat1")
         .set("noderef", "mat14");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec12", "sec11");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec12").feature("mat1")
         .set("noderef", "mat5");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec13", "sec12");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec13").feature("mat1")
         .set("noderef", "mat6");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec14", "sec13");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec14").feature("mat1")
         .set("noderef", "mat7");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec15", "sec14");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec15").feature("mat1")
         .set("noderef", "mat26");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec16", "sec15");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec16").feature("mat1")
         .set("noderef", "mat15");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec17", "sec16");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec17").feature("mat1")
         .set("noderef", "mat16");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec18", "sec17");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec18").feature("mat1")
         .set("noderef", "mat29");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec19", "sec18");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec19").feature("mat1")
         .set("noderef", "mat8");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec20", "sec19");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec20").feature("mat1")
         .set("noderef", "mat24");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec21", "sec20");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec21").feature("mat1")
         .set("noderef", "mat25");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec22", "sec21");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec22").feature("mat1")
         .set("noderef", "mat33");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec23", "sec22");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec23").feature("mat1")
         .set("noderef", "mat34");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec24", "sec23");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec24").feature("mat1")
         .set("noderef", "mat35");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec25", "sec24");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec25").feature("mat1")
         .set("noderef", "mat27");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec26", "sec25");

    return model;
  }

  public static Model run7(Model model) {
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec26").feature("mat1")
         .set("noderef", "mat21");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec27", "sec26");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec27").feature("mat1")
         .set("noderef", "mat30");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec28", "sec27");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec28").feature("mat1")
         .set("noderef", "mat18");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec29", "sec28");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec29").feature("mat1")
         .set("noderef", "mat9");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec30", "sec29");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec30").feature("mat1")
         .set("noderef", "mat19");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec31", "sec30");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec31").feature("mat1")
         .set("noderef", "mat10");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec32", "sec31");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec32").feature("mat1")
         .set("noderef", "mat37");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec33", "sec32");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec33").feature("mat1")
         .set("noderef", "mat36");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec34", "sec33");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec34").feature("mat1")
         .set("noderef", "mat32");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec35", "sec34");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec35").feature("mat1")
         .set("noderef", "mat11");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec36", "sec35");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec36").feature("mat1")
         .set("noderef", "mat23");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec37", "sec36");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec37").feature("mat1")
         .set("noderef", "mat38");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().duplicate("sec38", "sec37");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec38").feature("mat1")
         .set("noderef", "mat12");
    model.result().report("rpt1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").set("heading", "Results");
    model.result().report("rpt1").feature("sec3").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").set("heading", "Homogenized Density");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature().create("evgr1", "EvaluationGroup");
    model.result().report("rpt1").feature("sec3").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec2").set("heading", "Homogenized Elasticity Tensor");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature().create("evgr1", "EvaluationGroup");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("evgr1").set("noderef", "solidcp1stdEg");
    model.result().report("rpt1").feature("sec3").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec3")
         .set("heading", "Homogenized Coefficient of Thermal Expansion");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature().create("evgr1", "EvaluationGroup");
    model.result().report("rpt1").feature("sec3").feature("sec3").feature("evgr1").set("noderef", "std3Eg");
    model.result().report("rpt1").feature("sec3").feature().create("sec4", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec4").set("heading", "Homogenized Heat Capacity");
    model.result().report("rpt1").feature("sec3").feature("sec4").feature().create("evgr1", "EvaluationGroup");
    model.result().report("rpt1").feature("sec3").feature("sec4").feature("evgr1").set("noderef", "eg2");
    model.result().report("rpt1").feature("sec3").feature().create("sec5", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec5").set("heading", "Homogenized Thermal Conductivity");
    model.result().report("rpt1").feature("sec3").feature("sec5").feature().create("evgr1", "EvaluationGroup");
    model.result().report("rpt1").feature("sec3").feature("sec5").feature("evgr1").set("noderef", "eg3");
    model.result().report("rpt1").feature("sec1").feature("std1").active(false);
    model.result().report("rpt1").feature("sec1").feature("std2").active(false);
    model.result().report("rpt1").feature("sec1").feature("std3").active(false);
    model.result().report("rpt1").feature("sec1").feature("std4").active(false);
    model.result().report("rpt1").feature("sec1").feature("std5").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param3").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param4").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param5").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param6").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param7").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param8").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param9").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param10").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec1").feature("param11").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec1").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec2").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec3").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec4").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec5").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec6").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec7").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec8").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec9").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec10").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec11").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec12").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec13").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec14").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec15").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec16").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec17").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec18").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec19").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec20").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec21").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec22").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec23").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec24").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec25").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec26").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec27").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec28").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec29").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec30").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec31").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec32").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec33").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec34").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec35").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec36").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec37").active(false);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("sec38").active(false);
    model.result().report("rpt1").feature("sec3").feature("sec1").active(false);
    model.result().report("rpt1").feature("sec3").feature("sec2").active(false);
    model.result().report("rpt1").feature("sec3").feature("sec3").active(false);
    model.result().report("rpt1").feature("sec3").feature("sec4").active(false);
    model.result().report("rpt1").feature("sec3").feature("sec5").active(false);

    model.title("\u5468\u671f\u6027\u5fae\u7ed3\u6784\u7684\u5747\u8d28\u6750\u6599\u5c5e\u6027");

    model
         .description("\u6211\u4eec\u7ecf\u5e38\u53ef\u4ee5\u5728\u590d\u5408\u6750\u6599\u3001\u91d1\u5c5e\u6ce1\u6cab\u548c\u5939\u5c42\u7ed3\u6784\u4e2d\u53d1\u73b0\u5468\u671f\u6027\u5fae\u7ed3\u6784\uff0c\u5b83\u4eec\u53ef\u4ee5\u7528\u6cbf\u4e09\u4e2a\u7b1b\u5361\u5c14\u65b9\u5411\u91cd\u590d\u7684\u4ee3\u8868\u6027\u4f53\u79ef\u5355\u5143 (RVE) \u6765\u8868\u793a\u3002\n\n\u6b64 App \u8ba1\u7b97\u5404\u79cd\u5468\u671f\u6027\u5fae\u7ed3\u6784\u7684\u5747\u8d28\u6750\u6599\u5c5e\u6027\uff0c\u5e76\u5c06\u5b83\u4eec\u4ee5 MPH \u6216 XML \u6587\u4ef6\u7684\u5f62\u5f0f\u63d0\u4f9b\uff0c\u968f\u540e\u53ef\u5c06\u5176\u5bfc\u5165 COMSOL Multiphysics \u4f1a\u8bdd\u4e2d\u3002\n\n\u672c\u4f8b\u63d0\u4f9b\u7531\u5468\u671f\u6027 RVE \u8868\u793a\u7684\u5341\u79cd\u4e0d\u540c\u7684\u5fae\u89c2\u7ed3\u6784\uff0c\u7528\u6237\u53ef\u4ee5\u4fee\u6539 RVE \u5c3a\u5bf8\u548c\u7ec4\u6210\u6750\u6599\u3002\u8be5 App \u8ba1\u7b97 RVE \u7684\u5747\u5300\u5bc6\u5ea6\u3001\u5f39\u6027\u77e9\u9635\u3001\u70ed\u81a8\u80c0\u7cfb\u6570\u3001\u70ed\u5bb9\u548c\u5bfc\u70ed\u7cfb\u6570\uff0c\u8fd9\u4e9b\u6750\u6599\u5c5e\u6027\u968f\u540e\u53ef\u7528\u4e8e\u5b8f\u89c2\u6a21\u578b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("solidcp1sol").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("homogenization_of_periodic_microstructures.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    model = run3(model);
    model = run4(model);
    model = run5(model);
    model = run6(model);
    run7(model);
  }

}
