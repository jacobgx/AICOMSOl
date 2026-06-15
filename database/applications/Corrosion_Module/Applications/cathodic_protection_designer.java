/*
 * cathodic_protection_designer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:39 by COMSOL 6.3.0.290. */
public class cathodic_protection_designer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Corrosion_Module\\Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("cd", "SecondaryCurrentDistribution", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/cd", true);

    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1")
         .set("filename", "cathodic_protection_designer_geometry.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").feature("imp1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("imp1").set("selresultshow", "all");
    model.component("comp1").geom("geom1").feature("imp1").set("selindividual", true);
    model.component("comp1").geom("geom1").feature("imp1").set("selindividualshow", "all");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{100, 100, 100});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new int[]{-50, -50, -100});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", 55, 0);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", -1);
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmin", -49.5);
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmax", 49.5);
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymin", -49.5);
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymax", 49.5);
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmin", -109.5);
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmax", 9.5);
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2").set("entitydim", -1);
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").set("entitydim", -1);
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"boxsel2"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"boxsel1"});
    model.component("comp1").geom("geom1").run("difsel1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").named("difsel1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").named("boxsel1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").run("fin");

    model.param().set("condWater", "3.33[S/m]");
    model.param().descr("condWater", "Water conductivity");
    model.param().set("condMud", "0.75[S/m]");
    model.param().descr("condMud", "Mud conductivity");
    model.param().set("simCase", "1");
    model.param().descr("simCase", "Simulation case identifier");
    model.param().set("t", "(simCase==0)*0+(simCase==1)*tfinal/2+(simCase==2)*tfinal");
    model.param().descr("t", "Simulation time");
    model.param().set("tfinal", "20[year]");
    model.param().descr("tfinal", "Expected time of life");
    model.param().set("protectionLimit", "-800[mV]");
    model.param().descr("protectionLimit", "Cathodic protection limit vs. Ag/AgCl");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("Mud");
    model.component("comp1").selection("sel1").set(1);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("SelectedDomain");
    model.component("comp1").selection("sel2").set(1);
    model.component("comp1").selection().create("box1", "Box");
    model.component("comp1").selection("box1").set("xmin", -49.5);
    model.component("comp1").selection("box1").set("xmax", 49.5);
    model.component("comp1").selection("box1").set("ymin", -49.5);
    model.component("comp1").selection("box1").set("ymax", 49.5);
    model.component("comp1").selection("box1").set("zmin", -109.5);
    model.component("comp1").selection("box1").set("zmax", 9.5);
    model.component("comp1").selection("box1").set("entitydim", 2);
    model.component("comp1").selection("box1").set("condition", "inside");
    model.component("comp1").selection("box1").label("Geometry surfaces");
    model.component("comp1").selection().duplicate("box2", "box1");
    model.component("comp1").selection("box2").set("entitydim", 1);
    model.component("comp1").selection("box2").label("Geometry edges");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("Seawater");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElectrolyteConductivity", "ElectrolyteConductivity", "Electrolyte_conductivity");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .set("sigmal", new String[]{"condWater"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("Mud");
    model.component("comp1").material("mat2").selection().set(1);
    model.component("comp1").material("mat2").selection().named("sel2");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ElectrolyteConductivity", "ElectrolyteConductivity", "Electrolyte_conductivity");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity")
         .set("sigmal", new String[]{"condMud"});
    model.component("comp1").material("mat1").set("family", "water");
    model.component("comp1").material("mat2").set("family", "concrete");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").selection().geom("geom1", 2);
    model.component("comp1").variable("var1").selection().named("geom1_imp1_1_bnd");
    model.component("comp1").variable("var1").set("Eeq", "-600[mV]");
    model.component("comp1").variable("var1").descr("Eeq", "Equilibrium potential");
    model.component("comp1").variable("var1").set("i_initial", "180[mA/m^2]");
    model.component("comp1").variable("var1").descr("i_initial", "Initial current density");
    model.component("comp1").variable("var1").set("i_mean", "100[mA/m^2]");
    model.component("comp1").variable("var1").descr("i_mean", "Mean current density");
    model.component("comp1").variable("var1").set("i_final", "120[mA/m^2]");
    model.component("comp1").variable("var1").descr("i_final", "Final current density");
    model.component("comp1").variable("var1").set("cbfA", "0.02");
    model.component("comp1").variable("var1").descr("cbfA", "Coating break down factor A");
    model.component("comp1").variable("var1").set("cbfB", "0.012[1/year]");
    model.component("comp1").variable("var1").descr("cbfB", "Coating breakdown factor B");
    model.component("comp1").variable("var1").set("cbfOn", "1");
    model.component("comp1").variable("var1").descr("cbfOn", "Coating breakdown factor switch variable (1==On)");
    model.component("comp1").variable("var1")
         .set("iloc", "-(if(cbfOn,if((cbfA+cbfB*t)<1,cbfA+cbfB*t,1),1)*(i_initial*(simCase==0)+i_mean*(simCase==1)+i_final*(simCase==2)))");
    model.component("comp1").variable("var1").descr("iloc", "Local current density on cathode.");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").selection().geom("geom1", 1);
    model.component("comp1").variable("var2").selection().named("geom1_imp1_2_edg");
    model.component("comp1").variable("var2").set("edgeRadius", "0.15[m]");
    model.component("comp1").variable("var2").descr("edgeRadius", "Anode radius");
    model.component("comp1").variable("var2").set("edgeAnodeFinalRadius", "0.05[m]");
    model.component("comp1").variable("var2").descr("edgeAnodeFinalRadius", "Anode final radius");
    model.component("comp1").variable("var2").set("Eeq", "-1050[mV]", "Equilibrium potential");
    model.component("comp1").variable("var2").set("anodePolGrad", "100[mA/(m^2*mV)]");
    model.component("comp1").variable("var2").descr("anodePolGrad", "Anode polarization gradient");
    model.component("comp1").variable("var2")
         .set("iloc", "anodePolGrad*cd.eta_er1", "Local current density on cathode.");
    model.component("comp1").variable("var2").descr("iloc", "Local current density on anode.");
    model.component("comp1").variable().duplicate("var3", "var1");
    model.component("comp1").variable("var3").selection().named("geom1_imp1_3_bnd");
    model.component("comp1").variable("var3").set("cbfOn", "0");
    model.component("comp1").variable().duplicate("var4", "var3");
    model.component("comp1").variable("var4").selection().named("geom1_imp1_4_bnd");
    model.component("comp1").variable("var4").set("i_initial", "10[mA/m^2]");
    model.component("comp1").variable("var4").set("i_mean", "10[mA/m^2]");
    model.component("comp1").variable("var4").set("i_final", "10[mA/m^2]");

    model.component("comp1").physics("cd").prop("ShapeProperty").set("order_electricpotentialionicphase", 2);
    model.component("comp1").physics("cd").create("es1", "ElectrodeSurface", 2);
    model.component("comp1").physics("cd").feature("es1").selection().named("geom1_imp1_1_bnd");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("Eeq", "Eeq");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("ilocmat_mat", "userdef");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("ilocmat", "-iloc");
    model.component("comp1").physics("cd").create("edge1", "EdgeElectrode", 1);
    model.component("comp1").physics("cd").feature("edge1")
         .set("redge", "edgeRadius*(simCase==0||simCase==1)+edgeAnodeFinalRadius*(simCase==2)");
    model.component("comp1").physics("cd").feature("edge1").set("ElectricPotentialModelSelection", "Fixed");
    model.component("comp1").physics("cd").feature("edge1").selection().named("geom1_imp1_2_edg");
    model.component("comp1").physics("cd").feature("edge1").feature("er1").set("Eeq", "Eeq");
    model.component("comp1").physics("cd").feature("edge1").feature("er1").set("ilocmat_mat", "userdef");
    model.component("comp1").physics("cd").feature("edge1").feature("er1").set("ilocmat", "iloc");
    model.component("comp1").physics("cd").create("es2", "ElectrodeSurface", 2);
    model.component("comp1").physics("cd").feature("es2").selection().named("geom1_imp1_3_bnd");
    model.component("comp1").physics("cd").feature("es2").feature("er1").set("Eeq", "Eeq");
    model.component("comp1").physics("cd").feature("es2").feature("er1").set("ilocmat_mat", "userdef");
    model.component("comp1").physics("cd").feature("es2").feature("er1").set("ilocmat", "iloc");
    model.component("comp1").physics("cd").feature().duplicate("es3", "es2");
    model.component("comp1").physics("cd").feature("es3").selection().named("geom1_imp1_4_bnd");

    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature().remove("size1");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 0.05);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").view("view1").set("showmaterial", true);

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "condWater", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "S/m", 0);
    model.study("std1").feature("stat").setIndex("pname", "condWater", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "S/m", 0);
    model.study("std1").feature("stat").setIndex("pname", "simCase", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "0 1 2", 0);

    model.sol().create("sol1");
    model.sol("sol1").study("std1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "stat");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").feature("comp1_phil").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_phil").set("scaleval", "1");
    model.sol("sol1").feature("v1").set("control", "stat");
    model.sol("sol1").create("s1", "Stationary");
    model.sol("sol1").feature("s1").set("stol", 1.0E-4);
    model.sol("sol1").feature("s1").create("p1", "Parametric");
    model.sol("sol1").feature("s1").feature().remove("pDef");
    model.sol("sol1").feature("s1").feature("p1").set("porder", "constant");
    model.sol("sol1").feature("s1").feature("p1").set("control", "stat");
    model.sol("sol1").feature("s1").set("control", "stat");
    model.sol("sol1").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "auto");
    model.sol("sol1").feature("s1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("s1").feature("fc1").set("minstep", 1.0E-6);
    model.sol("sol1").feature("s1").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("d1").set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s1").feature("d1").label("Direct (cd)");
    model.sol("sol1").feature("s1").create("i1", "Iterative");
    model.sol("sol1").feature("s1").feature("i1").set("maxlinit", 1000);
    model.sol("sol1").feature("s1").feature("i1").set("nlinnormuse", "on");
    model.sol("sol1").feature("s1").feature("i1").label("Algebraic Multigrid (cd)");
    model.sol("sol1").feature("s1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("prefun", "saamg");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("maxcoarsedof", 50000);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("saamgcompwise", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").set("compactaggregation", true);
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("pr").create("sl1", "SORLine");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("po").create("sl1", "SORLine");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s1").create("i2", "Iterative");
    model.sol("sol1").feature("s1").feature("i2").set("maxlinit", 1000);
    model.sol("sol1").feature("s1").feature("i2").set("nlinnormuse", "on");
    model.sol("sol1").feature("s1").feature("i2").label("Geometric Multigrid (cd)");
    model.sol("sol1").feature("s1").feature("i2").create("mg1", "Multigrid");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("pr").create("sl1", "SORLine");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("po").create("sl1", "SORLine");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol1").feature("s1").feature("i2").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-13);
    model.sol("sol1").feature("s1").feature("fc1").set("linsolver", "d1");
    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "auto");
    model.sol("sol1").feature("s1").feature("fc1").set("maxiter", 50);
    model.sol("sol1").feature("s1").feature("fc1").set("minstep", 1.0E-6);
    model.sol("sol1").feature("s1").feature().remove("fcDef");
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 3, 0);
    model.result("pg1").label("Electrolyte Potential (cd)");
    model.result("pg1").create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("expr", new String[]{"phil"});
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("expr", new String[]{"cd.Ilx", "cd.Ily", "cd.Ilz"});
    model.result("pg1").feature("str1").set("posmethod", "start");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("str1").set("color", "gray");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 3, 0);
    model.result("pg2").label("Electrolyte Current Density (cd)");
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").set("expr", new String[]{"cd.Ilx", "cd.Ily", "cd.Ilz"});
    model.result("pg2").feature("str1").set("posmethod", "start");
    model.result("pg2").feature("str1").set("pointtype", "arrow");
    model.result("pg2").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg2").feature("str1").set("color", "gray");
    model.result("pg2").feature("str1").create("col1", "Color");
    model.result("pg2").feature("str1").feature("col1").set("expr", "root.comp1.cd.IlMag");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"abs(cd.itot)"});
    model.result("pg2").feature("surf1").set("inheritplot", "str1");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 3, 0);
    model.result("pg3").label("Electrode Potential vs. Adjacent Reference (cd)");
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("expr", new String[]{"cd.Ilx", "cd.Ily", "cd.Ilz"});
    model.result("pg3").feature("str1").set("posmethod", "start");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("color", "gray");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"cd.Evsref"});
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", new String[]{"cd.Evsref"});
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").set("radiusexpr", "root.comp1.cd.redge");
    model.result("pg3").feature("line1").set("tuberadiusscaleactive", "on");
    model.result("pg3").feature("line1").set("tuberadiusscale", "1");
    model.result("pg3").feature("line1").set("inheritplot", "surf1");
    model.result("pg1").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("Protected Surfaces");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "cd.Evsref<protectionLimit");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature().remove("str1");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").set("expr", "cd.itot_edge");
    model.result("pg2").feature("line1").set("colortable", "Twilight");
    model.result("pg2").feature("line1").set("linetype", "tube");
    model.result("pg2").feature("line1").set("radiusexpr", "cd.redge");
    model.result("pg2").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").feature().remove("str1");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").set("edges", false);
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "Electrode potential vs. adjacent reference");
    model.result("pg3").set("paramindicator", "Final Current Density");
    model.result("pg2").run();
    model.result("pg2").set("edges", false);
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "Surface: abs(cd.itot) (A/m<sup>2</sup>)");
    model.result("pg2").set("paramindicator", "Final Current Density");
    model.result("pg2").set("title", "Surface: Current Density (A/m<sup>2</sup>)");
    model.result().table().create("tbl1", "Table");
    model.result("pg4").run();
    model.result("pg4").create("line1", "Line");
    model.result("pg4").feature("line1").set("expr", "cd.Evsref<protectionLimit");
    model.result("pg4").feature("line1").set("colortable", "Twilight");
    model.result("pg4").run();
    model.result("pg4").feature("line1").set("linetype", "tube");
    model.result("pg4").feature("line1").set("radiusexpr", "cd.redge");
    model.result("pg4").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").set("edges", false);
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "Surface: Protected surfaces");
    model.result("pg4").set("paramindicator", "Final Current Density");
    model.result("pg4").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().named("box1");
    model.result("pg2").run();
    model.result("pg2").feature("line1").create("sel1", "Selection");
    model.result("pg2").feature("line1").feature("sel1").selection().named("box2");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").create("sel1", "Selection");
    model.result("pg3").feature("surf1").feature("sel1").selection().named("box1");
    model.result("pg3").run();
    model.result("pg3").feature("line1").create("sel1", "Selection");
    model.result("pg3").feature("line1").feature("sel1").selection().named("box2");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg4").feature("surf1").create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection().named("box1");
    model.result("pg4").run();
    model.result("pg4").feature("line1").create("sel1", "Selection");
    model.result("pg4").feature("line1").feature("sel1").selection().named("box2");
    model.result("pg3").run();

//    Started to run method changeVarTags

    model.component("comp1").variable("var1").tag("var_imp1_1");
    model.component("comp1").variable("var2").tag("var_imp1_2");
    model.component("comp1").variable("var3").tag("var_imp1_3");
    model.component("comp1").variable("var4").tag("var_imp1_4");

    model.result("pg3").run();

//    Finished running method changeVarTags

    model.title(null);

    model.description("");

    model.label("cathodic_protection_designer_embedded.mph");

    model.result("pg3").run();

    model.title("\u9634\u6781\u4fdd\u62a4\u8bbe\u8ba1\u5668");

    model
         .description("\u5f53\u4f7f\u7528\u4e0d\u540c\u8fb9\u754c\u6761\u4ef6\u7684\u6240\u6709\u4e0d\u540c\u90e8\u5206\u5728\u51e0\u4f55\u4e2d\u88ab\u5206\u79bb\u4e3a\u5355\u72ec\u7684\u5bf9\u8c61\u65f6\uff0c\u8be5 App \u53ef\u7528\u4e8e\u5bf9\u4e00\u822c\u7684\u9634\u6781\u4fdd\u62a4\u8bbe\u8ba1\u8fdb\u884c\u7b80\u5316\u4eff\u771f\u3002\n\n\u672c\u4f8b\u5c06\u51e0\u4f55\u4f5c\u4e3a CAD \u6587\u4ef6\u5bfc\u5165\uff0c\u6ee1\u8db3\u786e\u5b9a\u7684\u9700\u6c42\u3002\u7136\u540e\uff0c\u7528\u6237\u53ef\u4ee5\u9009\u62e9\u5404\u4e2a\u51e0\u4f55\u90e8\u5206\uff0c\u5e76\u6839\u636e\u8150\u8680\u5de5\u7a0b\u5e08\u7528\u4e8e\u9634\u6781\u4fdd\u62a4\u8bbe\u8ba1\u7684\u5178\u578b\u4eff\u771f\u65b9\u6848\u6765\u8bbe\u7f6e\u8fb9\u754c\u6761\u4ef6\uff0c\u4ece\u800c\u53ef\u4ee5\u6c42\u89e3\u521d\u59cb\u3001\u5e73\u5747\u548c\u6700\u7ec8\u7535\u6d41\u5bc6\u5ea6\u3002\u7ed3\u679c\u53ef\u4ee5\u5bfc\u51fa\u4e3a HTML\u3001Word \u6216 Powerpoint \u683c\u5f0f\u7684\u62a5\u544a\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("cathodic_protection_designer.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
