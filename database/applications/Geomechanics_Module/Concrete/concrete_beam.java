/*
 * concrete_beam.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:12 by COMSOL 6.3.0.290. */
public class concrete_beam {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Geomechanics_Module\\Concrete");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("truss", "Truss", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/truss", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("height", "200[mm]", "\u6881\u9ad8");
    model.param().set("width", "300[mm]", "\u6881\u5bbd");
    model.param().set("length", "4[m]", "\u6881\u957f");
    model.param()
         .set("height_reinforcement", "2*layer_spacing_first+(bar_layers-1)*layer_spacing", "\u52a0\u5f3a\u533a\u7684\u9ad8\u5ea6");
    model.param().set("diam_bar", "10[mm]", "\u94a2\u7b4b\u76f4\u5f84");
    model.param().set("area_bar", "pi*diam_bar^2/4", "\u94a2\u7b4b\u7684\u6a2a\u622a\u9762\u79ef");
    model.param().set("bar_layers", "2", "\u94a2\u7b4b\u5c42\u6570");
    model.param().set("layer_spacing", "20[mm]", "\u5c42\u95f4\u8ddd");
    model.param()
         .set("layer_spacing_first", "10[mm]", "\u4e0e\u7b2c\u4e00\u5c42\u94a2\u7b4b\u8868\u9762\u7684\u8ddd\u79bb");
    model.param().set("width_spacing", "50[mm]", "\u5bbd\u5ea6\u95f4\u8ddd");
    model.param()
         .set("width_spacing_firstmin", "25[mm]", "\u94a2\u7b4b\u5230\u6881\u8868\u9762\u7684\u6700\u5c0f\u6a2a\u5411\u8ddd\u79bb");
    model.param()
         .set("bars_across_width", "floor((width-2*width_spacing_firstmin)/width_spacing)+1", "\u5bbd\u5ea6\u65b9\u5411\u7684\u94a2\u7b4b\u6570");
    model.param()
         .set("width_spacing_first", "(width-(bars_across_width-1)*width_spacing)/2", "\u4e0e\u94a2\u7b4b\u8868\u9762\u7684 Y \u8ddd\u79bb");
    model.param()
         .set("v_reinforcement", "bar_layers*bars_across_width*area_bar/(height_reinforcement*width)", "\u52a0\u5f3a\u7b6f\u4f53\u79ef\u5206\u6570");
    model.param().set("max_defl", "-30[mm]", "\u6700\u5927\u6320\u5ea6");
    model.param().set("mesh_par", "1", "\u7f51\u683c\u5206\u5e03\u4e58\u6570");
    model.param().set("para", "0", "\u7528\u4e8e\u53c2\u6570\u5316\u626b\u63cf\u7684\u53c2\u6570");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"length/2", "width/2", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "height", 2);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", "height_reinforcement", 0);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", "height-2*height_reinforcement", 1);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"length/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("ls1")
         .set("coord1", new String[]{"0", "(bars_across_width-1)/2*width_spacing", "0"});
    model.component("comp1").geom("geom1").feature("ls1")
         .set("coord2", new String[]{"length/2", "(bars_across_width-1)/2*width_spacing", "0"});
    model.component("comp1").geom("geom1").feature("ls1").setIndex("coord1", "layer_spacing_first", 2);
    model.component("comp1").geom("geom1").feature("ls1").setIndex("coord2", "layer_spacing_first", 2);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u57df\u5185\u7684\u94a2\u7b4b");
    model.component("comp1").geom("geom1").feature("ls1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("arr1")
         .set("fullsize", new String[]{"1", "floor(bars_across_width/2)", "1"});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"0", "-width_spacing", "0"});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls2").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("coord2", new String[]{"length/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("ls2")
         .set("coord1", new String[]{"0", "0", "layer_spacing_first"});
    model.component("comp1").geom("geom1").feature("ls2")
         .set("coord2", new String[]{"length/2", "0", "layer_spacing_first"});
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2")
         .label("\u5bf9\u79f0\u5e73\u9762\u4e0a\u7684\u94a2\u7b4b");
    model.component("comp1").geom("geom1").feature("ls2").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("ls2");
    model.component("comp1").geom("geom1").create("arr2", "Array");
    model.component("comp1").geom("geom1").feature("arr2").selection("input").set("arr1", "ls2");
    model.component("comp1").geom("geom1").feature("arr2").set("fullsize", new String[]{"1", "1", "bar_layers"});
    model.component("comp1").geom("geom1").feature("arr2").set("displ", new String[]{"0", "0", "layer_spacing"});
    model.component("comp1").geom("geom1").run("arr2");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input")
         .set("arr2(1,1,1,1)", "arr2(1,1,1,2)", "arr2(1,1,1,3)", "arr2(1,1,2,1)", "arr2(1,1,2,2)", "arr2(1,1,2,3)");

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.component("comp1").geom("geom1").feature("mir1").set("pos", new String[]{"0", "0", "height/2"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("createpairs", false);
    model.component("comp1").geom("geom1").feature().createAfter("if1", "If", "arr1");
    model.component("comp1").geom("geom1").feature("if1").set("condition", "mod(bars_across_width,2)==1");
    model.component("comp1").geom("geom1").feature().createAfter("endif1", "EndIf", "ls2");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("uni1", "Union");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("uni1").label("\u94a2\u7b4b");
    model.component("comp1").selection("uni1").set("entitydim", 1);
    model.component("comp1").selection("uni1").set("input", new String[]{"geom1_csel1_edg", "geom1_csel2_edg"});

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").label("\u6320\u5ea6");
    model.component("comp1").cpl("intop1").selection().set(12);
    model.component("comp1").cpl("intop1").set("frame", "material");

    model.component("comp1").physics("solid").feature("lemm1").create("cm1", "Concrete", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("cm1").set("ConcreteCriterion", "Ottosen");
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().set(2, 5, 8, 14, 15, 16);
    model.component("comp1").physics("solid").create("rig1", "RigidConnector", 2);
    model.component("comp1").physics("solid").feature("rig1").selection().set(1, 4, 7);
    model.component("comp1").physics("solid").feature("rig1").setIndex("Direction", true, 1);
    model.component("comp1").physics("solid").feature("rig1").setIndex("Direction", true, 2);
    model.component("comp1").physics("solid").feature("rig1").set("RotationType", "ConstrainedRotationGroup");
    model.component("comp1").physics("solid").feature("rig1").setIndex("ConstrainedRotation", true, 0);
    model.component("comp1").physics("solid").feature("rig1").setIndex("ConstrainedRotation", true, 2);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(10);
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "0", "load"});
    model.component("comp1").physics("solid").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("solid").feature("ge1").setIndex("name", "load", 0, 0);
    model.component("comp1").physics("solid").feature("ge1").setIndex("equation", "intop1(w)-para*max_defl", 0, 0);
    model.component("comp1").physics("solid").feature("ge1").set("DependentVariableQuantity", "faceload");
    model.component("comp1").physics("solid").feature("ge1").set("SourceTermQuantity", "displacement");
    model.component("comp1").physics("truss").selection().named("uni1");
    model.component("comp1").physics("truss").prop("ShapeProperty").set("order_displacement", 2);
    model.component("comp1").physics("truss").feature("emm1").create("plsty1", "Plasticity", 1);
    model.component("comp1").physics("truss").feature("csd1").set("area", "pi*(diam_bar/2)^2*(0.5+0.5*Y>0.1[mm])");

    model.component("comp1").multiphysics().create("ere1", "EmbeddedReinforcement", -1);
    model.component("comp1").multiphysics("ere1").selection("dstEdgSel").named("geom1_csel1_edg");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("Concrete");
    model.component("comp1").material("mat1").set("family", "concrete");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"10e-6[1/K]", "0", "0", "0", "10e-6[1/K]", "0", "0", "0", "10e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2300[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.8[W/(m*K)]", "0", "0", "0", "1.8[W/(m*K)]", "0", "0", "0", "1.8[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "880[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "25[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.20");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.component("comp1").material("mat2").propertyGroup().create("Swift", "Swift", "Swift");
    model.component("comp1").material("mat2").propertyGroup().create("Voce", "Voce", "Voce");
    model.component("comp1").material("mat2").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup().create("Norton", "Norton", "Norton");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.component("comp1").material("mat2").label("Structural steel");
    model.component("comp1").material("mat2").set("family", "custom");
    model.component("comp1").material("mat2")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat2").set("diffuse", "custom");
    model.component("comp1").material("mat2")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat2").set("ambient", "custom");
    model.component("comp1").material("mat2")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat2").set("noise", true);
    model.component("comp1").material("mat2").set("fresnel", 0.9);
    model.component("comp1").material("mat2").set("roughness", 0.3);
    model.component("comp1").material("mat2").set("diffusewrap", 0);
    model.component("comp1").material("mat2").set("reflectance", 0);
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").set("lossfactor", "0.02");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "E(T)");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "nu(T)");
    model.component("comp1").material("mat2").propertyGroup("Enu").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel")
         .label("Elastoplastic material model");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel")
         .set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.component("comp1").material("mat2").propertyGroup("Swift").set("e0_swi", "0.021");
    model.component("comp1").material("mat2").propertyGroup("Swift").set("n_swi", "0.2");
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp1").material("mat2").propertyGroup("Voce").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("Norton").label("Norton");
    model.component("comp1").material("mat2").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.component("comp1").material("mat2").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.component("comp1").material("mat2").propertyGroup("Norton").set("n_nor", "4.5");
    model.component("comp1").material("mat2").propertyGroup("Garofalo").label("Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat2").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.component("comp1").material("mat2").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.component("comp1").material("mat2").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.component("comp1").material("mat2").propertyGroup("ChabocheViscoplasticity")
         .label("Chaboche viscoplasticity");
    model.component("comp1").material("mat2").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.component("comp1").material("mat2").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.component("comp1").material("mat2").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");
    model.component("comp1").material("mat1").propertyGroup()
         .create("YieldStressParameters", "YieldStressParameters", "Yield_stress_parameters");
    model.component("comp1").material("mat1").propertyGroup("YieldStressParameters")
         .set("sigmauc", new String[]{"20e6"});
    model.component("comp1").material("mat1").propertyGroup().create("Ottosen", "Ottosen", "Ottosen_criterion");
    model.component("comp1").material("mat1").propertyGroup("Ottosen").set("aOttosen", new String[]{"1.3"});
    model.component("comp1").material("mat1").propertyGroup("Ottosen").set("bOttosen", new String[]{"3.2"});
    model.component("comp1").material("mat1").propertyGroup("Ottosen").set("k1Ottosen", new String[]{"11.8"});
    model.component("comp1").material("mat1").propertyGroup("Ottosen").set("k2Ottosen", new String[]{"0.98"});
    model.component("comp1").material("mat2").selection().geom("geom1", 1);
    model.component("comp1").material("mat2").selection().named("uni1");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel")
         .set("sigmags", new String[]{"100[MPa]"});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").set("Et", new String[]{"20[GPa]"});

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().named("uni1");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", "20*mesh_par");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(1, 4, 7);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(4, 10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", "6*mesh_par");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(1, 7);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", "2*mesh_par");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", "20*mesh_par");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u65e0\u94a2\u7b4b");
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"solid/lemm1/cm1"});
    model.study("std1").feature("stat").setSolveFor("/physics/truss", false);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"solid/lemm1/cm1", "truss"});
    model.study("std1").feature("stat").setSolveFor("/multiphysics/ere1", false);
    model.study("std1").feature("stat").set("disabledcoupling", new String[]{"ere1"});
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "height", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "height", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,0.025,1)", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("p1").create("st1", "StopCondition");
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1")
         .setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1")
         .setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondarr", "comp1.load<-50e3", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").set("storestopcondsol", "stepafter");
    model.sol("sol1").feature("s1").feature("p1").feature("st1").set("stopcondwarn", false);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 15, 0);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").create("def", "Deform");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").set("quickplane", "zx");
    model.result().dataset().create("mir2", "Mirror3D");
    model.result().dataset("mir2").set("data", "mir1");
    model.result().dataset("mir2").set("quickx", "length/2");
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").label("\u5e94\u529b 1");
    model.result("pg1").set("data", "mir2");
    model.result("pg1").set("edges", false);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").label("\u65e0\u94a2\u7b4b");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").setSolveFor("/physics/truss", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/ere1", true);
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"solid/lemm1/cm1"});
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "height", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "height", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "para", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(0,0.025,1)", 0);
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("s1").feature("p1").create("st1", "StopCondition");
    model.sol("sol2").feature("s1").feature("p1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol2").feature("s1").feature("p1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol2").feature("s1").feature("p1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol2").feature("s1").feature("p1").feature("st1")
         .setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol2").feature("s1").feature("p1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol2").feature("s1").feature("p1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol2").feature("s1").feature("p1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol2").feature("s1").feature("p1").feature("st1")
         .setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol2").feature("s1").feature("p1").feature("st1").setIndex("stopcondarr", "comp1.load<-50e3", 0);
    model.sol("sol2").feature("s1").feature("p1").feature("st1").set("storestopcondsol", "stepafter");
    model.sol("sol2").feature("s1").feature("p1").feature("st1").set("stopcondwarn", false);

    model.study("std2").label("\u6709\u94a2\u7b4b");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 11, 0);
    model.result("pg2").label("\u5e94\u529b (solid)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg2").feature("vol1").set("threshold", "manual");
    model.result("pg2").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("vol1").set("colortable", "Rainbow");
    model.result("pg2").feature("vol1").set("colortabletrans", "none");
    model.result("pg2").feature("vol1").set("colorscalemode", "linear");
    model.result("pg2").feature("vol1").set("resolution", "custom");
    model.result("pg2").feature("vol1").set("refine", 2);
    model.result("pg2").feature("vol1").set("colortable", "Prism");
    model.result("pg2").feature("vol1").create("def", "Deform");
    model.result("pg2").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 11, 0);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", new String[]{"truss.misesGp"});
    model.result("pg3").feature("line1").set("threshold", "manual");
    model.result("pg3").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("line1").set("colortable", "Rainbow");
    model.result("pg3").feature("line1").set("colortabletrans", "none");
    model.result("pg3").feature("line1").set("colorscalemode", "linear");
    model.result("pg3").label("\u5e94\u529b (truss)");
    model.result("pg3").feature("line1").set("colortable", "Rainbow");
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").set("radiusexpr", "truss.re");
    model.result("pg3").feature("line1").set("resolution", "extrafine");
    model.result("pg3").feature("line1").set("smooth", "internal");
    model.result("pg3").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg3").feature("line1").set("tuberadiusscale", 1);
    model.result("pg3").feature("line1").set("tubeendcaps", false);
    model.result("pg3").feature("line1").create("def", "Deform");
    model.result("pg3").feature("line1").feature("def").set("expr", new String[]{"u2", "v2", "w2"});
    model.result("pg3").feature("line1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").run();
    model.result().dataset().create("mir3", "Mirror3D");
    model.result().dataset("mir3").set("data", "dset2");
    model.result().dataset("mir3").set("quickplane", "zx");
    model.result().dataset("mir3").set("quicky", "-1e-10");
    model.result().dataset().create("mir4", "Mirror3D");
    model.result().dataset("mir4").set("data", "mir3");
    model.result().dataset("mir4").set("quickx", "length/2");
    model.result("pg2").run();
    model.result("pg2").label("\u5e94\u529b 2");
    model.result("pg2").set("data", "mir4");
    model.result("pg2").set("edges", false);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").label("\u94a2\u7b4b\u4e2d\u7684\u5e94\u529b 2");
    model.result("pg3").set("data", "mir4");
    model.result("pg3").set("edges", false);
    model.result("pg3").run();
    model.result("pg3").feature("line1").set("expr", "truss.sn");
    model.result("pg3").feature("line1").set("colortable", "WaveLight");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 11, 0);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").create("line1", "Line");
    model.result("pg4").feature("line1").set("expr", new String[]{"truss.Nxl"});
    model.result("pg4").feature("line1").set("threshold", "manual");
    model.result("pg4").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("line1").set("colortable", "Wave");
    model.result("pg4").feature("line1").set("colortabletrans", "none");
    model.result("pg4").feature("line1").set("colorscalemode", "linearsymmetric");
    model.result("pg4").feature("line1").set("linetype", "tube");
    model.result("pg4").feature("line1").set("radiusexpr", "truss.re");
    model.result("pg4").feature("line1").set("resolution", "extrafine");
    model.result("pg4").feature("line1").set("smooth", "internal");
    model.result("pg4").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg4").feature("line1").set("tuberadiusscale", 1);
    model.result("pg4").feature("line1").set("tubeendcaps", false);
    model.result("pg4").feature("line1").create("def", "Deform");
    model.result("pg4").feature("line1").feature("def").set("expr", new String[]{"u2", "v2", "w2"});
    model.result("pg4").feature("line1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg4").label("\u529b (truss)");
    model.result("pg4").label("\u529b (truss)");
    model.result("pg4").run();
    model.result("pg4").label("\u94a2\u7b4b\u4e2d\u7684\u529b 2");
    model.result("pg4").set("data", "mir4");
    model.result("pg4").set("edges", false);
    model.result("pg4").run();
    model.result("pg4").feature("line1").set("expr", "(1+(y==0))*truss.Nxl");
    model.result("pg4").feature("line1").set("unit", "kN");
    model.result("pg4").feature("line1").set("colortable", "WaveLight");
    model.result("pg4").run();
    model.result("pg2").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("plotgroup", "pg2");
    model.nodeGroup("grp2").add("plotgroup", "pg3");
    model.nodeGroup("grp2").add("plotgroup", "pg4");
    model.nodeGroup("grp2").label("\u6709\u94a2\u7b4b");

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std3").feature("stat").setSolveFor("/physics/truss", true);
    model.study("std3").feature("stat").setSolveFor("/multiphysics/ere1", true);
    model.study("std3").feature("stat").set("useparam", true);
    model.study("std3").feature("stat").setIndex("pname", "height", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "m", 0);
    model.study("std3").feature("stat").setIndex("pname", "height", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "m", 0);
    model.study("std3").feature("stat").setIndex("pname", "para", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "range(0,0.025,1)", 0);
    model.study("std3").setGenPlots(false);
    model.study("std3").showAutoSequences("all");

    model.sol("sol3").feature("s1").feature("p1").set("porder", "constant");
    model.sol("sol3").feature("s1").feature("p1").create("st1", "StopCondition");
    model.sol("sol3").feature("s1").feature("p1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol3").feature("s1").feature("p1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol3").feature("s1").feature("p1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol3").feature("s1").feature("p1").feature("st1")
         .setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol3").feature("s1").feature("p1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol3").feature("s1").feature("p1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol3").feature("s1").feature("p1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol3").feature("s1").feature("p1").feature("st1")
         .setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol3").feature("s1").feature("p1").feature("st1").setIndex("stopcondarr", "comp1.load<-50e3", 0);
    model.sol("sol3").feature("s1").feature("p1").feature("st1").set("storestopcondsol", "stepafter");
    model.sol("sol3").feature("s1").feature("p1").feature("st1").set("stopcondwarn", false);

    model.study("std3").label("\u6709\u94a2\u7b4b\u548c Ottosen");
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().dataset().create("mir5", "Mirror3D");
    model.result().dataset("mir5").set("data", "dset3");
    model.result().dataset("mir5").set("quickplane", "zx");
    model.result().dataset("mir5").set("quicky", "-1e-10");
    model.result().dataset().create("mir6", "Mirror3D");
    model.result().dataset("mir6").set("data", "mir5");
    model.result().dataset("mir6").set("quickx", "length/2");

    model.nodeGroup().duplicate("grp3", "grp2");
    model.nodeGroup("grp3").label("\u6709\u94a2\u7b4b\u548c Ottosen");

    model.result("pg5").run();
    model.result("pg5").label("\u5e94\u529b 3");
    model.result("pg5").set("data", "mir6");
    model.result("pg5").stepLast(0);
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").label("\u94a2\u7b4b\u4e2d\u7684\u5e94\u529b 3");
    model.result("pg6").set("data", "mir6");
    model.result("pg6").stepLast(0);
    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg7").set("data", "mir6");
    model.result("pg7").label("\u94a2\u7b4b\u4e2d\u7684\u529b 3");
    model.result("pg7").stepLast(0);
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup3D");

    model.nodeGroup("grp3").add("plotgroup", "pg8");

    model.result("pg8").run();
    model.result("pg8").set("data", "mir6");
    model.result("pg8").label("\u5851\u6027\u533a");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", "solid.elemgpmax(solid.epe>0)");
    model.result("pg8").feature("surf1").set("colorlegend", false);
    model.result("pg8").feature("surf1").set("smooth", "none");
    model.result("pg8").feature("surf1").create("def1", "Deform");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").set("edges", false);
    model.result("pg8").stepLast(0);
    model.result("pg8").run();

    model.component("comp1").physics("solid").feature("lemm1").create("cm2", "Concrete", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("cm2").set("materialModel", "mazarsDamage");
    model.component("comp1").physics("solid").feature("lemm1").feature("cm2")
         .set("equivalentStrainMazars", "modifiedMazars");
    model.component("comp1").physics("solid").feature("lemm1").feature("cm2").set("epsilon0c_maz", "1e-4");
    model.component("comp1").physics("solid").feature("lemm1").feature("cm2").set("Ac_maz", 1.12);
    model.component("comp1").physics("solid").create("disc1", "Discretization", -1);
    model.component("comp1").physics("solid").feature("disc1").label("\u79bb\u6563\u5316\uff0c\u7ebf\u6027");
    model.component("comp1").physics("solid").feature("disc1").set("order_displacement", 1);
    model.component("comp1").physics("truss").create("disc1", "Discretization", -1);
    model.component("comp1").physics("truss").feature("disc1").label("\u79bb\u6563\u5316\uff0c\u7ebf\u6027");

    model.component("comp1").material("mat1").propertyGroup("YieldStressParameters")
         .set("sigmaut", new String[]{"2[MPa]"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("FractureParameters", "FractureParameters", "Fracture_parameters");
    model.component("comp1").material("mat1").propertyGroup("FractureParameters")
         .set("Gft", new String[]{"220[J/m^2]"});

    model.study().create("std4");
    model.study("std4").create("stat", "Stationary");
    model.study("std4").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std4").feature("stat").setSolveFor("/physics/truss", true);
    model.study("std4").feature("stat").setSolveFor("/multiphysics/ere1", true);
    model.study("std4").create("param", "Parametric");
    model.study("std4").feature("param").setIndex("pname", "height", 0);
    model.study("std4").feature("param").setIndex("plistarr", "", 0);
    model.study("std4").feature("param").setIndex("punit", "m", 0);
    model.study("std4").feature("param").setIndex("pname", "height", 0);
    model.study("std4").feature("param").setIndex("plistarr", "", 0);
    model.study("std4").feature("param").setIndex("punit", "m", 0);
    model.study("std4").feature("param").setIndex("pname", "mesh_par", 0);
    model.study("std4").feature("param").setIndex("plistarr", 2, 0);
    model.study("std4").feature("stat").set("useadvanceddisable", true);
    model.study("std4").feature("stat").setEntry("discretization", "solid", "disc1");
    model.study("std4").feature("stat").setEntry("discretization", "truss", "disc1");
    model.study("std4").feature("stat").set("useparam", true);
    model.study("std4").feature("stat").setIndex("pname", "height", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "", 0);
    model.study("std4").feature("stat").setIndex("punit", "m", 0);
    model.study("std4").feature("stat").setIndex("pname", "height", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "", 0);
    model.study("std4").feature("stat").setIndex("punit", "m", 0);
    model.study("std4").feature("stat").setIndex("pname", "para", 0);
    model.study("std4").feature("stat").setIndex("plistarr", "range(0,0.025,1)", 0);
    model.study("std4").setGenPlots(false);
    model.study("std4").showAutoSequences("all");

    model.sol("sol4").feature("s1").feature("p1").create("st1", "StopCondition");
    model.sol("sol4").feature("s1").feature("p1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol4").feature("s1").feature("p1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol4").feature("s1").feature("p1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol4").feature("s1").feature("p1").feature("st1")
         .setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol4").feature("s1").feature("p1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol4").feature("s1").feature("p1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol4").feature("s1").feature("p1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol4").feature("s1").feature("p1").feature("st1")
         .setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol4").feature("s1").feature("p1").feature("st1").setIndex("stopcondarr", "comp1.load<-50e3", 0);
    model.sol("sol4").feature("s1").feature("p1").feature("st1").set("storestopcondsol", "stepafter");
    model.sol("sol4").feature("s1").feature("p1").feature("st1").set("stopcondwarn", false);

    model.study("std4").label("\u6709\u94a2\u7b4b\u548c\u635f\u4f24");
    model.study("std4").createAutoSequences("all");

    model.sol().create("sol5");
    model.sol("sol5").study("std4");
    model.sol("sol5").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol5");
    model.batch("p1").run("compute");

    model.result().dataset().create("mir7", "Mirror3D");
    model.result().dataset("mir7").set("data", "dset4");
    model.result().dataset("mir7").set("quickplane", "zx");
    model.result().dataset("mir7").set("quicky", "-1e-10");
    model.result().dataset().create("mir8", "Mirror3D");
    model.result().dataset("mir8").set("data", "mir7");
    model.result().dataset("mir8").set("quickx", "length/2");

    model.nodeGroup().duplicate("grp4", "grp2");
    model.nodeGroup("grp4").label("\u6709\u94a2\u7b4b\u548c\u635f\u4f24");

    model.result("pg9").run();
    model.result("pg9").label("\u5e94\u529b 4");
    model.result("pg9").set("data", "mir8");
    model.result("pg9").run();
    model.result("pg9").feature("vol1").set("expr", "solid.misesdGp");
    model.result("pg9").feature("vol1").stepLast(0);
    model.result("pg9").run();
    model.result("pg10").run();
    model.result("pg10").label("\u94a2\u7b4b\u4e2d\u7684\u5e94\u529b 4");
    model.result("pg10").set("data", "mir8");
    model.result("pg10").stepLast(0);
    model.result("pg10").run();
    model.result("pg11").run();
    model.result("pg11").set("data", "mir8");
    model.result("pg11").label("\u94a2\u7b4b\u4e2d\u7684\u529b 4");
    model.result("pg11").stepLast(0);
    model.result("pg11").run();
    model.result().create("pg12", "PlotGroup3D");

    model.nodeGroup("grp4").add("plotgroup", "pg12");

    model.result("pg12").run();
    model.result("pg12").set("data", "mir8");
    model.result("pg12").label("\u635f\u4f24");
    model.result("pg12").create("surf1", "Surface");
    model.result("pg12").feature("surf1").set("expr", "solid.dmgGp");
    model.result("pg12").feature("surf1").set("resolution", "norefine");
    model.result("pg12").feature("surf1").set("smooth", "none");
    model.result("pg12").feature("surf1").create("def1", "Deform");
    model.result("pg12").run();
    model.result("pg12").run();
    model.result("pg12").set("edges", false);
    model.result("pg12").stepLast(0);
    model.result("pg12").run();
    model.result().create("pg13", "PlotGroup3D");

    model.nodeGroup("grp4").add("plotgroup", "pg13");

    model.result("pg13").run();
    model.result("pg13").set("data", "mir8");
    model.result("pg13").label("\u88c2\u7eb9");
    model.result("pg13").create("surf1", "Surface");
    model.result("pg13").feature("surf1").set("expr", "solid.eeqGp");
    model.result("pg13").feature("surf1").set("descr", "\u7b49\u6548\u5e94\u53d8");
    model.result("pg13").feature("surf1").set("expr", "solid.elemgpmax(solid.eeq>2e-3)");
    model.result("pg13").feature("surf1").set("colortable", "GrayScale");
    model.result("pg13").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg13").feature("surf1").set("resolution", "norefine");
    model.result("pg13").feature("surf1").set("smooth", "none");
    model.result("pg13").feature("surf1").create("def1", "Deform");
    model.result("pg13").run();
    model.result("pg13").run();
    model.result("pg13").set("edges", false);
    model.result("pg13").stepLast(0);
    model.result("pg13").run();

    model.component("comp1").physics("solid").feature("lemm1").create("fib1", "Fiber", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("fib1").selection().set(1, 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("fib1").set("Fiber_material", "mat2");
    model.component("comp1").physics("solid").feature("lemm1").feature("fib1").set("V", "v_reinforcement");
    model.component("comp1").physics("solid").feature("lemm1").feature("fib1").set("E_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("fib1").set("E", "mat1.Enu.E+mat2.Enu.E");

    model.study().create("std5");
    model.study("std5").create("stat", "Stationary");
    model.study("std5").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std5").feature("stat").setSolveFor("/physics/truss", false);
    model.study("std5").feature("stat").setSolveFor("/multiphysics/ere1", false);
    model.study("std5").feature("stat").set("useadvanceddisable", true);
    model.study("std5").feature("stat").set("disabledphysics", new String[]{"solid/lemm1/cm2", "solid/lemm1/cm1"});
    model.study("std5").feature("stat").set("useparam", true);
    model.study("std5").feature("stat").setIndex("pname", "height", 0);
    model.study("std5").feature("stat").setIndex("plistarr", "", 0);
    model.study("std5").feature("stat").setIndex("punit", "m", 0);
    model.study("std5").feature("stat").setIndex("pname", "height", 0);
    model.study("std5").feature("stat").setIndex("plistarr", "", 0);
    model.study("std5").feature("stat").setIndex("punit", "m", 0);
    model.study("std5").feature("stat").setIndex("pname", "para", 0);
    model.study("std5").feature("stat").setIndex("plistarr", "range(0,0.025,1)", 0);
    model.study("std5").showAutoSequences("all");

    model.sol("sol7").feature("s1").feature("p1").create("st1", "StopCondition");
    model.sol("sol7").feature("s1").feature("p1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol7").feature("s1").feature("p1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol7").feature("s1").feature("p1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol7").feature("s1").feature("p1").feature("st1")
         .setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol7").feature("s1").feature("p1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol7").feature("s1").feature("p1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol7").feature("s1").feature("p1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol7").feature("s1").feature("p1").feature("st1")
         .setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol7").feature("s1").feature("p1").feature("st1").setIndex("stopcondarr", "comp1.load<-50e3", 0);
    model.sol("sol7").feature("s1").feature("p1").feature("st1").set("storestopcondsol", "stepafter");
    model.sol("sol7").feature("s1").feature("p1").feature("st1").set("stopcondwarn", false);

    model.study("std5").label("\u6709\u7ea4\u7ef4");
    model.study("std5").createAutoSequences("all");

    model.sol("sol7").runAll();

    model.result().create("pg14", "PlotGroup3D");
    model.result("pg14").set("data", "dset6");
    model.result("pg14").setIndex("looplevel", 10, 0);
    model.result("pg14").label("\u5e94\u529b (solid)");
    model.result("pg14").set("frametype", "spatial");
    model.result("pg14").create("vol1", "Volume");
    model.result("pg14").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg14").feature("vol1").set("threshold", "manual");
    model.result("pg14").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg14").feature("vol1").set("colortable", "Rainbow");
    model.result("pg14").feature("vol1").set("colortabletrans", "none");
    model.result("pg14").feature("vol1").set("colorscalemode", "linear");
    model.result("pg14").feature("vol1").set("resolution", "custom");
    model.result("pg14").feature("vol1").set("refine", 2);
    model.result("pg14").feature("vol1").set("colortable", "Prism");
    model.result("pg14").feature("vol1").create("def", "Deform");
    model.result("pg14").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg14").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg14").run();
    model.result().dataset().duplicate("mir9", "mir1");
    model.result().dataset("mir9").set("data", "dset6");
    model.result().dataset().duplicate("mir10", "mir2");
    model.result().dataset("mir10").set("data", "mir9");
    model.result("pg14").run();
    model.result("pg14").label("\u5e94\u529b 5");
    model.result("pg14").set("edges", false);
    model.result("pg14").set("data", "mir10");
    model.result("pg14").run();
    model.result("pg14").run();
    model.result().create("pg15", "PlotGroup3D");
    model.result("pg15").set("data", "dset6");
    model.result("pg15").setIndex("looplevel", 10, 0);
    model.result("pg15").label("\u7ea4\u7ef4, \u7ebf\u5f39\u6027\u6750\u6599 1 (solid)");
    model.result("pg15").set("showlegends", true);
    model.result("pg15").set("titletype", "manual");
    model.result("pg15")
         .set("title", "\u7ea4\u7ef4\u5e94\u529b, \u7ea4\u7ef4, \u7ebf\u5f39\u6027\u6750\u6599 1 (solid)");
    model.result("pg15").set("showlegendsunit", true);
    model.result("pg15").create("str1", "Streamline");
    model.result("pg15").feature("str1")
         .set("expr", new String[]{"solid.lemm1.fib1.a0X", "solid.lemm1.fib1.a0Y", "solid.lemm1.fib1.a0Z"});
    model.result("pg15").feature("str1").set("linetype", "tube");
    model.result("pg15").feature("str1").set("posmethod", "uniform");
    model.result("pg15").feature("str1").set("revcoordsys", "cylindrical");
    model.result("pg15").feature("str1").label("\u7ea4\u7ef4 1");
    model.result("pg15").feature("str1").set("inheritplot", "none");
    model.result("pg15").feature("str1").create("col", "Color");
    model.result("pg15").feature("str1").feature("col").set("colortabletrans", "none");
    model.result("pg15").feature("str1").feature("col").set("colorscalemode", "linear");
    model.result("pg15").feature("str1").feature("col").set("expr", "solid.lemm1.fib1.sa");
    model.result("pg15").feature("str1").feature("col").set("colortable", "Rainbow");
    model.result("pg15").label("\u7ea4\u7ef4, \u7ebf\u5f39\u6027\u6750\u6599 1 (solid)");
    model.result("pg15").run();
    model.result("pg15").label("\u7ea4\u7ef4\u4e2d\u7684\u5e94\u529b");
    model.result("pg15").set("data", "mir10");
    model.result("pg15").set("edges", false);
    model.result("pg15").set("paramindicator", "para(10)=0.225");
    model.result("pg15").run();
    model.result("pg15").feature("str1").create("def1", "Deform");
    model.result("pg15").run();
    model.result("pg15").run();
    model.result("pg15").feature("str1").set("udist", 0.01);
    model.result("pg15").feature("str1").set("tuberadiusscaleactive", true);
    model.result("pg15").feature("str1").set("tuberadiusscale", "diam_bar/2");
    model.result("pg15").run();
    model.result("pg15").feature("str1").feature("col").set("colortable", "WaveLight");
    model.result("pg15").run();
    model.result("pg14").run();

    model.nodeGroup().create("grp5", "Results");
    model.nodeGroup("grp5").set("type", "plotgroup");
    model.nodeGroup().move("grp5", 4);
    model.nodeGroup("grp5").add("plotgroup", "pg14");
    model.nodeGroup("grp5").add("plotgroup", "pg15");
    model.nodeGroup("grp5").label("\u6709\u7ea4\u7ef4");

    model.result().create("pg16", "PlotGroup1D");
    model.result("pg16").run();
    model.result("pg16").label("\u6320\u5ea6");
    model.result("pg16").setIndex("looplevelinput", "last", 0);
    model.result("pg16").set("titletype", "manual");
    model.result("pg16").set("title", "\u6881\u6320\u5ea6");
    model.result("pg16").set("xlabelactive", true);
    model.result("pg16").set("xlabel", "X \u8f74\u4e0a\u7684\u4f4d\u7f6e");
    model.result("pg16").set("ylabelactive", true);
    model.result("pg16").set("ylabel", "\u6320\u5ea6 (mm)");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg16").set("legendpos", "lowerleft");
    model.result("pg16").create("lngr1", "LineGraph");
    model.result("pg16").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg16").feature("lngr1").set("linewidth", "preference");
    model.result("pg16").feature("lngr1").set("data", "dset1");
    model.result("pg16").feature("lngr1").setIndex("looplevelinput", "last", 0);
    model.result("pg16").feature("lngr1").selection().set(11);
    model.result("pg16").feature("lngr1").set("unit", "mm");
    model.result("pg16").feature("lngr1").set("expr", "w");
    model.result("pg16").feature("lngr1").set("descr", "\u4f4d\u79fb\u573a\uff0cZ \u5206\u91cf");
    model.result("pg16").feature("lngr1").set("xdata", "expr");
    model.result("pg16").feature("lngr1").set("xdataexpr", "X");
    model.result("pg16").feature("lngr1").set("linemarker", "cycle");
    model.result("pg16").feature("lngr1").set("markerpos", "interp");
    model.result("pg16").feature("lngr1").set("legend", true);
    model.result("pg16").feature("lngr1").set("legendmethod", "manual");
    model.result("pg16").feature("lngr1").setIndex("legends", "\u7ebf\u5f39\u6027\u6a21\u578b", 0);
    model.result("pg16").feature().duplicate("lngr2", "lngr1");
    model.result("pg16").run();
    model.result("pg16").feature("lngr2").set("data", "dset2");
    model.result("pg16").feature("lngr2")
         .setIndex("legends", "\u6709\u94a2\u7b4b\u7684\u7ebf\u5f39\u6027\u6a21\u578b", 0);
    model.result("pg16").run();
    model.result("pg16").feature().duplicate("lngr3", "lngr1");
    model.result("pg16").run();
    model.result("pg16").feature("lngr3").set("data", "dset3");
    model.result("pg16").feature("lngr3").setIndex("legends", "\u6709\u94a2\u7b4b\u7684 Ottosen \u6a21\u578b", 0);
    model.result("pg16").run();
    model.result("pg16").feature().duplicate("lngr4", "lngr1");
    model.result("pg16").run();
    model.result("pg16").feature("lngr4").set("data", "dset4");
    model.result("pg16").feature("lngr4").setIndex("legends", "\u6709\u94a2\u7b4b\u7684 Mazars \u6a21\u578b", 0);
    model.result("pg16").run();
    model.result("pg16").feature().duplicate("lngr5", "lngr1");
    model.result("pg16").run();
    model.result("pg16").feature("lngr5").set("data", "dset6");
    model.result("pg16").feature("lngr5")
         .setIndex("legends", "\u6709\u7ea4\u7ef4\u7684\u7ebf\u5f39\u6027\u6a21\u578b", 0);
    model.result("pg16").feature("lngr5").set("markers", 7);
    model.result("pg16").run();
    model.result().create("pg17", "PlotGroup1D");
    model.result("pg17").run();
    model.result("pg17").label("\u5851\u6027\u5e94\u53d8");
    model.result("pg17").setIndex("looplevelinput", "last", 0);
    model.result("pg17").set("titletype", "manual");
    model.result("pg17").set("title", "\u94a2\u7b4b\u4e2d\u7684\u5851\u6027\u5e94\u53d8");
    model.result("pg17").set("xlabelactive", true);
    model.result("pg17").set("xlabel", "X \u8f74\u4e0a\u7684\u4f4d\u7f6e");
    model.result("pg17").set("ylabelactive", true);
    model.result("pg17").set("ylabel", "\u5851\u6027\u5e94\u53d8");
    model.result("pg17").set("legendpos", "upperleft");
    model.result("pg17").create("lngr1", "LineGraph");
    model.result("pg17").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg17").feature("lngr1").set("linewidth", "preference");
    model.result("pg17").feature("lngr1").set("data", "dset2");
    model.result("pg17").feature("lngr1").setIndex("looplevelinput", "last", 0);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.result("pg17").feature("lngr1").selection().set(29);

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.result("pg17").feature("lngr1").set("expr", "truss.epnGp");
    model.result("pg17").feature("lngr1").set("descr", "\u5851\u6027\u8f74\u5411\u5e94\u53d8");
    model.result("pg17").feature("lngr1").set("xdata", "expr");
    model.result("pg17").feature("lngr1").set("xdataexpr", "X");
    model.result("pg17").feature("lngr1").set("xdataunit", "mm");
    model.result("pg17").feature("lngr1").set("legend", true);
    model.result("pg17").feature("lngr1").set("legendmethod", "manual");
    model.result("pg17").feature("lngr1")
         .setIndex("legends", "\u6709\u94a2\u7b4b\u7684\u7ebf\u5f39\u6027\u6a21\u578b", 0);
    model.result("pg17").feature().duplicate("lngr2", "lngr1");
    model.result("pg17").run();
    model.result("pg17").feature("lngr2").set("data", "dset3");
    model.result("pg17").feature("lngr2").setIndex("legends", "\u6709\u94a2\u7b4b\u7684 Ottosen \u6a21\u578b", 0);
    model.result("pg17").run();
    model.result("pg17").feature().duplicate("lngr3", "lngr1");
    model.result("pg17").run();
    model.result("pg17").feature("lngr3").set("data", "dset4");
    model.result("pg17").feature("lngr3").setIndex("legends", "\u6709\u94a2\u7b4b\u7684 Mazars \u6a21\u578b", 0);
    model.result("pg17").run();
    model.result().create("pg18", "PlotGroup1D");
    model.result("pg18").run();
    model.result("pg18").label("\u8f7d\u8377 vs. \u6320\u5ea6");
    model.result("pg18").set("titletype", "manual");
    model.result("pg18").set("title", "\u8f7d\u8377 vs. \u6320\u5ea6");
    model.result("pg18").set("xlabelactive", true);
    model.result("pg18").set("xlabel", "\u6320\u5ea6 (mm)");
    model.result("pg18").set("ylabelactive", true);
    model.result("pg18").set("ylabel", "\u8f7d\u8377 (kN/m<sup>2</sup>)");
    model.result("pg18").set("legendpos", "lowerright");
    model.result("pg18").create("glob1", "Global");
    model.result("pg18").feature("glob1").set("markerpos", "datapoints");
    model.result("pg18").feature("glob1").set("linewidth", "preference");
    model.result("pg18").feature("glob1").set("data", "dset1");
    model.result("pg18").feature("glob1").setIndex("expr", "-load", 0);
    model.result("pg18").feature("glob1").setIndex("unit", "kN/m^2", 0);
    model.result("pg18").feature("glob1").setIndex("descr", "\u7ebf\u5f39\u6027\u6a21\u578b", 0);
    model.result("pg18").feature("glob1").set("xdata", "expr");
    model.result("pg18").feature("glob1").set("xdataexpr", "-intop1(w)");
    model.result("pg18").feature("glob1").set("xdataunit", "mm");
    model.result("pg18").feature().duplicate("glob2", "glob1");
    model.result("pg18").run();
    model.result("pg18").feature("glob2").set("data", "dset2");
    model.result("pg18").feature("glob2")
         .setIndex("descr", "\u6709\u94a2\u7b4b\u7684\u7ebf\u5f39\u6027\u6a21\u578b", 0);
    model.result("pg18").run();
    model.result("pg18").feature().duplicate("glob3", "glob1");
    model.result("pg18").run();
    model.result("pg18").feature("glob3").set("data", "dset3");
    model.result("pg18").feature("glob3").setIndex("descr", "\u6709\u94a2\u7b4b\u7684 Ottosen \u6a21\u578b", 0);
    model.result("pg18").run();
    model.result("pg18").feature().duplicate("glob4", "glob1");
    model.result("pg18").run();
    model.result("pg18").feature("glob4").set("data", "dset4");
    model.result("pg18").feature("glob4").setIndex("descr", "\u6709\u94a2\u7b4b\u7684 Mazars \u6a21\u578b", 0);
    model.result("pg18").run();
    model.result("pg18").feature().duplicate("glob5", "glob1");
    model.result("pg18").run();
    model.result("pg18").feature("glob5").set("data", "dset6");
    model.result("pg18").feature("glob5")
         .setIndex("descr", "\u6709\u7ea4\u7ef4\u7684\u7ebf\u5f39\u6027\u6a21\u578b", 0);
    model.result("pg18").run();

    model.study("std1").feature("stat")
         .set("disabledphysics", new String[]{"solid/lemm1/cm1", "truss", "solid/lemm1/cm2", "solid/lemm1/fib1"});
    model.study("std2").feature("stat")
         .set("disabledphysics", new String[]{"solid/lemm1/cm1", "solid/lemm1/cm2", "solid/lemm1/fib1"});
    model.study("std3").feature("stat").set("useadvanceddisable", true);
    model.study("std3").feature("stat").set("disabledphysics", new String[]{"solid/lemm1/cm2", "solid/lemm1/fib1"});
    model.study("std4").feature("stat").set("disabledphysics", new String[]{"solid/lemm1/fib1"});

    model.result("pg5").run();

    model.title("\u94a2\u7b4b\u6df7\u51dd\u571f\u6881");

    model
         .description("\u6df7\u51dd\u571f\u7ed3\u6784\u51e0\u4e4e\u603b\u662f\u5305\u542b\u94a2\u7b6f\u5f62\u72b6\u7684\u52a0\u5f3a\u4ef6\u3002\u5728 COMSOL \u4e2d\uff0c\u60a8\u53ef\u4ee5\u901a\u8fc7\u5728\u201c\u56fa\u4f53\u201d\u63a5\u53e3\u4e2d\u6dfb\u52a0\u201c\u6841\u67b6\u201d\u63a5\u53e3\uff08\u7528\u4e8e\u6df7\u51dd\u571f\uff09\u5355\u72ec\u5bf9\u94a2\u7b6f\u8fdb\u884c\u5efa\u6a21\uff0c\u4e5f\u53ef\u4ee5\u4f7f\u7528\u201c\u7ea4\u7ef4\u201d\u5b50\u7279\u5f81\u6765\u6a21\u62df\u5b83\u4eec\u7684\u5168\u5c40\u6548\u5e94\u3002\u5728\u4f7f\u7528\u201c\u6841\u67b6\u201d\u63a5\u53e3\u7684\u60c5\u51b5\u4e0b\uff0c\u7531\u4e8e\u4f4d\u79fb\u4ece\u5b9e\u4f53\u5185\u90e8\u6620\u5c04\u5230\u94a2\u7b4b\u4e0a\u7684\u67d0\u4e2a\u4f4d\u7f6e\uff0c\u56e0\u6b64\u6df7\u51dd\u571f\u7684\u5b9e\u4f53\u7f51\u683c\u548c\u94a2\u7b4b\u7f51\u683c\u53ef\u4ee5\u76f8\u4e92\u72ec\u7acb\u3002\n\n\u672c\u4f8b\u4f7f\u7528\u591a\u79cd\u6750\u6599\u6a21\u578b\u5bf9\u6881\u8fdb\u884c\u5206\u6790\uff0c\u5305\u62ec Ottosen \u6df7\u51dd\u571f\u6a21\u578b\u548c Mazars \u635f\u4f24\u6a21\u578b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();

    model.label("concrete_beam.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
