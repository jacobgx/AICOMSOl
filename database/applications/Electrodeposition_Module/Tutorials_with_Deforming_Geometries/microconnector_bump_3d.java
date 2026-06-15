/*
 * microconnector_bump_3d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:00 by COMSOL 6.3.0.290. */
public class microconnector_bump_3d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrodeposition_Module\\Tutorials_with_Deforming_Geometries");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("tcd", "TertiaryCurrentDistributionNernstPlanck", "geom1");
    model.component("comp1").physics("tcd").prop("SpeciesProperties")
         .set("ChargeTransportModel", "SupportingElectrolyte");
    model.component("comp1").physics("tcd").field("concentration").field("c");
    model.component("comp1").physics("tcd").field("concentration").component(new String[]{"c"});
    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");

    model.component("comp1").multiphysics().create("ndbdg1", "NonDeformingBoundaryDeformedGeometry", 2);
    model.component("comp1").multiphysics("ndbdg1").set("Echem_physics", "tcd");
    model.component("comp1").multiphysics("ndbdg1").selection().all();
    model.component("comp1").multiphysics().create("desdg1", "DeformingElectrodeSurfaceDeformedGeometry", 2);
    model.component("comp1").multiphysics("desdg1").set("Echem_physics", "tcd");
    model.component("comp1").multiphysics("desdg1").selection().all();

    model.component("comp1").common().create("free1", "DeformingDomainDeformedGeometry");
    model.component("comp1").common("free1").set("smoothingType", "hyperelastic");
    model.component("comp1").common("free1").selection().all();

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L1", "100[um]", "\u7535\u6781\u957f\u5ea6");
    model.param().set("L2", "50[um]", "\u51fa\u53e3\u533a\u57df\u957f\u5ea6");
    model.param().set("L3", "30[um]", "\u5165\u53e3\u533a\u57df\u957f\u5ea6");
    model.param().set("Ltot", "L1+L2+L3", "\u7535\u6c60\u603b\u957f\u5ea6");
    model.param().set("h1", "10[um]", "\u5149\u523b\u80f6\u819c\u539a\u5ea6");
    model.param().set("h2", "40[um]", "\u6d41\u4f53\u5165\u53e3/\u51fa\u53e3\u539a\u5ea6");
    model.param().set("rho", "1.1e3[kg/m^3]", "\u6db2\u4f53\u5bc6\u5ea6");
    model.param().set("nu", "1.394e-6[m^2/s]", "\u8fd0\u52a8\u9ecf\u5ea6");
    model.param().set("mu", "nu*rho", "\u52a8\u529b\u9ecf\u5ea6");
    model.param()
         .set("h_Pe", "10[um]", "\u4e0e\u901f\u5ea6\u8868\u9762\u7684\u8ddd\u79bb\uff08\u7528\u4f69\u514b\u83b1\u7279\u6570\u8868\u793a\uff09");
    model.param().set("Pe", "41.6", "\u4f69\u514b\u83b1\u7279\u6570");
    model.param().set("D", "4.5e-10[m^2/s]", "\u6269\u6563\u7cfb\u6570");
    model.param().set("c_bulk", "0.6[mol/dm^3]", "\u672c\u4f53\u6d53\u5ea6");
    model.param().set("u_bulk", "h2/h_Pe*Pe*D/(h1)", "\u672c\u4f53\u901f\u5ea6");
    model.param().set("r_edge", "h1/4", "\u8fb9\u534a\u5f84\uff08\u4e8c\u7ef4\u4e2d\u672a\u4f7f\u7528\uff09");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("u_profile", "u_bulk*(Zg-h1)/h2", "\u5165\u53e3/\u51fa\u53e3\u901f\u5ea6\u66f2\u7ebf");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"Ltot", "Ltot", "h2"});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"0", "0", "h1"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "L1/2");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "h1");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"L3+L1/2", "Ltot/2", "0"});
    model.component("comp1").geom("geom1").run("cyl1");

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").geom("geom1").feature().duplicate("cyl2", "cyl1");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "L1/2+r_edge");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "r_edge");
    model.component("comp1").geom("geom1").feature("cyl2").setIndex("pos", "h1-r_edge", 2);
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("tor1", "Torus");
    model.component("comp1").geom("geom1").feature("tor1").set("rmaj", "L1/2+r_edge");
    model.component("comp1").geom("geom1").feature("tor1").set("rmin", "r_edge");
    model.component("comp1").geom("geom1").feature("tor1").set("pos", new String[]{"L3+L1/2", "Ltot/2", "0"});
    model.component("comp1").geom("geom1").feature("tor1").setIndex("pos", "h1-r_edge", 2);
    model.component("comp1").geom("geom1").run("tor1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("cyl2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("tor1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("blk1", "cyl1", "dif1");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp1").set("quicky", "Ltot/2");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("par1").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("par1", 1);
    model.component("comp1").geom("geom1").run("del1");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(1);
    model.component("comp1").selection("sel1").label("\u5165\u53e3");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(11);
    model.component("comp1").selection("sel2").label("\u51fa\u53e3");
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(2, 5);
    model.component("comp1").selection("sel3").label("\u5bf9\u79f0\u58c1");
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").set(8);
    model.component("comp1").selection("sel4").label("\u9634\u6781");
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").geom(2);
    model.component("comp1").selection("sel5").set(4);
    model.component("comp1").selection("sel5").label("\u672c\u4f53\u7535\u89e3\u8d28");
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("input", new String[]{"sel1", "sel5"});
    model.component("comp1").selection("uni1").label("\u5165\u53e3 + \u672c\u4f53\u7535\u89e3\u8d28");
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").geom(2);
    model.component("comp1").selection("sel6").set(6, 7, 9, 10);
    model.component("comp1").selection("sel6").label("\u7edd\u7f18\u5b54\u58c1");

    model.component("comp1").physics("tcd").feature("ice1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_c", new String[]{"D", "0", "0", "0", "D", "0", "0", "0", "D"});
    model.component("comp1").physics("tcd").feature("ice1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("tcd").feature("ice1").set("sigmal", new int[]{1, 0, 0, 0, 1, 0, 0, 0, 1});
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c_bulk", 0);
    model.component("comp1").physics("tcd").create("conc1", "Concentration", 2);
    model.component("comp1").physics("tcd").feature("conc1").label("\u6d53\u5ea6 - \u672c\u4f53");
    model.component("comp1").physics("tcd").feature("conc1").selection().named("uni1");
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "c_bulk", 0);
    model.component("comp1").physics("tcd").create("eip1", "ElectrolytePotential", 2);
    model.component("comp1").physics("tcd").feature("eip1").label("\u7535\u89e3\u8d28\u7535\u4f4d - \u672c\u4f53");
    model.component("comp1").physics("tcd").feature("eip1").selection().named("sel5");
    model.component("comp1").physics("tcd").create("es1", "ElectrodeSurface", 2);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("tcd").feature("es1")
         .set("SolveForDissolvingDepositingConcentrationVariable", false);
    model.component("comp1").physics("tcd").feature("es1").selection().named("sel4");
    model.component("comp1").physics("tcd").feature("es1").set("phisext0", -0.45);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("nm", 2);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vi0", -1, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vib", 1, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("cref", "c_bulk", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("i0_ref", "10[A/m^2]");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("alphaa", 1.5);
    model.component("comp1").physics("tcd").create("out1", "Outflow", 2);
    model.component("comp1").physics("tcd").feature("out1").selection().named("sel2");
    model.component("comp1").physics("tcd").prop("ShapeProperty").set("order_concentration", 1);
    model.component("comp1").physics("tcd").prop("ShapeProperty").set("order_electricpotentialionicphase", 1);
    model.component("comp1").physics("tcd").prop("ShapeProperty").set("order_electricpotential", 1);
    model.component("comp1").physics("spf").feature("fp1").set("rho_mat", "userdef");
    model.component("comp1").physics("spf").feature("fp1").set("rho", "rho");
    model.component("comp1").physics("spf").feature("fp1").set("mu_mat", "userdef");
    model.component("comp1").physics("spf").feature("fp1").set("mu", "mu");
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("spf").feature("sym1").selection().named("sel3");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().named("sel1");
    model.component("comp1").physics("spf").feature("inl1").set("ComponentWise", "VelocityFieldComponentWise");
    model.component("comp1").physics("spf").feature("inl1").set("u0", new String[]{"u_profile", "0", "0"});
    model.component("comp1").physics("spf").create("wallbc2", "WallBC", 2);
    model.component("comp1").physics("spf").feature("wallbc2").selection().named("sel5");
    model.component("comp1").physics("spf").feature("wallbc2").set("TranslationalVelocityOption", "Manual");
    model.component("comp1").physics("spf").feature("wallbc2").set("utr", new String[]{"u_bulk", "0", "0"});
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().named("sel2");

    model.component("comp1").multiphysics().create("ndbdg2", "NonDeformingBoundaryDeformedGeometry", 2);
    model.component("comp1").multiphysics("ndbdg2").selection().set(1, 2, 4, 5, 11);
    model.component("comp1").multiphysics("ndbdg2").set("BoundaryCondition", "ZeroNormalDisplacement");

    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size1").selection().named("sel6");
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", "3E-6");
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "8E-6");
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 1.1);
    model.component("comp1").mesh("mesh1").feature("size").set("hcurve", 0.7);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("sel6");
    model.component("comp1").mesh("mesh1").feature("map1").set("interpmethod", "transfinite3D");
    model.component("comp1").mesh("mesh1").create("conv1", "Convert");
    model.component("comp1").mesh("mesh1").feature("conv1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("conv1").selection().named("sel6");
    model.component("comp1").mesh("mesh1").run("conv1");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").run("ftet1");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").set("sharpcorners", "trim");
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(3, 6, 7, 8, 9, 10);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 1);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "0.5E-6");
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/tcd", true);
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/ndbdg1", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/desdg1", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/ndbdg2", true);
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").setSolveFor("/physics/tcd", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/ndbdg1", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/desdg1", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/ndbdg2", false);
    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("stat2").set("useadvanceddisable", true);
    model.study("std1").feature("stat2").setSolveFor("/physics/spf", false);
    model.study("std1").feature("stat2").setSolveFor("/multiphysics/ndbdg1", false);
    model.study("std1").feature("stat2").setSolveFor("/multiphysics/desdg1", false);
    model.study("std1").feature("stat2").setSolveFor("/multiphysics/ndbdg2", false);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("tlist", "range(0,10,120)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").feature("se1").set("maxsegiter", 15);
    model.sol("sol1").feature("t1").feature("se1").feature("ss1").set("subjtech", "onfirst");
    model.sol("sol1").feature("t1").feature("se1").feature("ss2").set("subtermconst", "tol");

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u6d53\u5ea6");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "c");
    model.result("pg1").run();

    model.component("comp1").view("view1").set("transparency", false);

    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u5fae\u578b\u63a5\u5934\u51f8\u5757\u8868\u9762");
    model.result("pg2").setIndex("looplevel", 13, 0);
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("edges", false);
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "1");
    model.result("pg2").feature("surf1").set("coloring", "uniform");
    model.result("pg2").feature("surf1").set("color", "custom");
    model.result("pg2").feature("surf1").set("customcolor", new double[]{0.501960813999176, 0, 0});
    model.result("pg2").feature("surf1").set("resolution", "fine");
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().set(8);
    model.result("pg2").run();
    model.result("pg2").create("surf2", "Surface");
    model.result("pg2").feature("surf2").set("data", "dset2");
    model.result("pg2").feature("surf2").set("expr", "1");
    model.result("pg2").feature("surf2").set("coloring", "uniform");
    model.result("pg2").feature("surf2").set("color", "white");
    model.result("pg2").feature("surf2").set("resolution", "fine");
    model.result("pg2").feature("surf2").create("sel1", "Selection");
    model.result("pg2").feature("surf2").feature("sel1").selection().set(3, 6, 7, 9, 10);
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("surf3", "surf2");
    model.result("pg2").run();
    model.result("pg2").feature("surf3").set("color", "black");
    model.result("pg2").run();
    model.result("pg2").feature("surf3").feature("sel1").selection().set(8);
    model.result("pg2").run();
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").set("data", "dset2");
    model.result("pg2").feature("line1").set("expr", "1");
    model.result("pg2").feature("line1").set("coloring", "uniform");
    model.result("pg2").feature("line1").set("color", "black");
    model.result("pg2").feature("line1").create("sel1", "Selection");
    model.result("pg2").feature("line1").feature("sel1").selection().set(2, 3, 7, 9, 11, 12, 13, 16, 20, 21, 22, 24);
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "on");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "on");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "on");
    model.result().export("anim1").set("legend3d", "on");
    model.result().export("anim1").set("logo3d", "on");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "on");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("plotgroup", "pg2");
    model.result().export("anim1").set("maxframes", 13);
    model.result().export("anim1").set("showframe", 13);
    model.result().export("anim1").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").run();
    model.result("pg3").feature("surf1").active(false);
    model.result("pg3").run();
    model.result("pg3").set("edges", true);
    model.result("pg3").run();
    model.result().remove("pg3");

    model
         .title("\u5fae\u578b\u63a5\u5934\u51f8\u5757\u7535\u9540\uff08\u542b\u53d8\u5f62\u51e0\u4f55\uff09- \u4e09\u7ef4");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u5fae\u578b\u63a5\u53e3\u51f8\u5757\u7531\u4e8e\u7535\u6781\u8868\u9762\u4e0a\u7684\u94dc\u6c89\u79ef\u968f\u65f6\u95f4\u7684\u5f62\u72b6\u6f14\u53d8\u3002\u7535\u89e3\u8d28\u4e2d\u7684\u94dc\u79bb\u5b50\u901a\u8fc7\u5bf9\u6d41\u548c\u6269\u6563\u8fdb\u884c\u4f20\u9012\uff0c\u7535\u6781\u52a8\u529b\u5b66\u7531\u6d53\u5ea6\u76f8\u5173\u7684 Butler-Volmer \u8868\u8fbe\u5f0f\u63cf\u8ff0\u3002\n\n\u8fd9\u662f\u5c06\u201c\u5fae\u578b\u63a5\u5934\u51f8\u5757\u7535\u9540 - \u4e8c\u7ef4\u201d\u793a\u4f8b\u8fdb\u884c\u6269\u5c55\u7684\u4e09\u7ef4\u6a21\u578b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("microconnector_bump_3d.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
