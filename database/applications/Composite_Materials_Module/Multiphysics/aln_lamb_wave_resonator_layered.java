/*
 * aln_lamb_wave_resonator_layered.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:33 by COMSOL 6.3.0.290. */
public class aln_lamb_wave_resonator_layered {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Composite_Materials_Module\\Multiphysics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("lshell", "LayeredShell", "geom1");
    model.component("comp1").physics("lshell").create("pzm1", "PiezoelectricMaterialModel");
    model.component("comp1").physics("lshell").feature("pzm1").selection().all();
    model.component("comp1").physics().create("ecis", "ElectricCurrentsShell", "geom1");
    model.component("comp1").physics("ecis").prop("DefaultDescription")
         .set("DefaultDescription", "Electric_currents_in_layered_shells");
    model.component("comp1").physics("ecis").prop("LayerSelection").set("lth_mat", "from_mat");
    model.component("comp1").physics("ecis").create("epzml1", "PiezoelectricLayer");
    model.component("comp1").physics("ecis").feature("epzml1").selection().all();

    model.component("comp1").multiphysics().create("pzel1", "PiezoelectricEffectLS", 2);
    model.component("comp1").multiphysics("pzel1").set("Solid_physics", "lshell");
    model.component("comp1").multiphysics("pzel1").set("Electrostatics_physics", "ecis");

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").set("shift", "1[Hz]");
    model.study("std1").feature("eig").set("chkeigregion", true);
    model.study("std1").feature("eig").set("storefact", false);
    model.study("std1").feature("eig").set("linpsolnum", "auto");
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("outputmap", new String[]{});
    model.study("std1").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").setSolveFor("/physics/lshell", true);
    model.study("std1").feature("eig").setSolveFor("/physics/ecis", true);
    model.study("std1").feature("eig").setSolveFor("/multiphysics/pzel1", true);

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");

    model.param().set("tp", "0.4[um]");
    model.param().descr("tp", "Thickness of piezoelectric layer");
    model.param().set("l", "15[um]");
    model.param().descr("l", "Length of finger");
    model.param().set("wf", "0.2[um]");
    model.param().descr("wf", "Width of finger");
    model.param().set("op", "14.6[um]");
    model.param().descr("op", "Length of overlap, for op less than l");
    model.param().set("dy", "(l-op)/2");
    model.param().descr("dy", "Electrode separation");
    model.param().set("n", "11");
    model.param().descr("n", "Number of fingers, where (n+1)/4 = integer");
    model.param().set("we", "0.2[um]");
    model.param().descr("we", "Width of edge");
    model.param().set("la", "0.4[um]");
    model.param().descr("la", "Length of anchor");
    model.param().set("Vapp", "1[V]");
    model.param().descr("Vapp", "Applied voltage");
    model.param().set("eta0", "2.0e-3");
    model.param().descr("eta0", "Loss factor for electrode layer");
    model.param().set("eta1", "2.0e-3");
    model.param().descr("eta1", "Loss factor for piezoelectric layer");
    model.param().set("te", "80[nm]");
    model.param().descr("te", "Thickness of electrode layer");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat1").label("Pt - Platinum");
    model.material("mat1").set("family", "custom");
    model.material("mat1").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.material("mat1").set("diffuse", "custom");
    model.material("mat1")
         .set("customdiffuse", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.material("mat1").set("ambient", "custom");
    model.material("mat1")
         .set("customambient", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.material("mat1").set("noise", true);
    model.material("mat1").set("fresnel", 0.9);
    model.material("mat1").set("roughness", 0.1);
    model.material("mat1").set("diffusewrap", 0);
    model.material("mat1").set("reflectance", 0);
    model.material("mat1").propertyGroup("def").label("Basic");
    model.material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"8.9e6[S/m]", "0", "0", "0", "8.9e6[S/m]", "0", "0", "0", "8.9e6[S/m]"});
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"8.80e-6[1/K]", "0", "0", "0", "8.80e-6[1/K]", "0", "0", "0", "8.80e-6[1/K]"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", "133[J/(kg*K)]");
    model.material("mat1").propertyGroup("def").set("density", "21450[kg/m^3]");
    model.material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"71.6[W/(m*K)]", "0", "0", "0", "71.6[W/(m*K)]", "0", "0", "0", "71.6[W/(m*K)]"});
    model.material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.material("mat1").propertyGroup("Enu").set("E", "168e9[Pa]");
    model.material("mat1").propertyGroup("Enu").set("nu", "0.38");
    model.material().create("mat2", "Common", "");
    model.material("mat2").propertyGroup().create("StrainCharge", "StrainCharge", "Strain-charge form");
    model.material("mat2").propertyGroup().create("StressCharge", "StressCharge", "Stress-charge form");
    model.material("mat2").label("Aluminum Nitride");
    model.material("mat2").set("family", "custom");
    model.material("mat2").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.material("mat2").set("diffuse", "custom");
    model.material("mat2")
         .set("customdiffuse", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.material("mat2").set("ambient", "custom");
    model.material("mat2")
         .set("customambient", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.material("mat2").set("noise", true);
    model.material("mat2").set("fresnel", 0.9);
    model.material("mat2").set("roughness", 0.1);
    model.material("mat2").set("diffusewrap", 0);
    model.material("mat2").set("reflectance", 0);
    model.material("mat2").propertyGroup("def").label("Basic");
    model.material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"9", "0", "0", "0", "9", "0", "0", "0", "9"});
    model.material("mat2").propertyGroup("def").set("density", "3300[kg/m^3]");
    model.material("mat2").propertyGroup("StrainCharge").label("Strain-charge form");
    model.material("mat2").propertyGroup("StrainCharge")
         .set("sE", new String[]{"2.8987e-12[1/Pa]", "-9.326e-013[1/Pa]", "-5.0038e-013[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "-9.326e-013[1/Pa]", "2.8987e-12[1/Pa]", "-5.0038e-013[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "-5.0038e-013[1/Pa]", "-5.0038e-013[1/Pa]", "2.8253e-012[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "8E-12[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "8E-12[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "7.6628E-12[1/Pa]"});
    model.material("mat2").propertyGroup("StrainCharge")
         .set("dET", new String[]{"0[C/N]", "0[C/N]", "-1.9159e-012[C/N]", "0[C/N]", "0[C/N]", "-1.9159e-012[C/N]", "0[C/N]", "0[C/N]", "4.9597e-012[C/N]", "0[C/N]", 
         "-3.84e-012[C/N]", "0[C/N]", "-3.84e-012[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]"});
    model.material("mat2").propertyGroup("StrainCharge")
         .set("epsilonrT", new String[]{"9.2081", "0", "0", "0", "9.2081", "0", "0", "0", "10.1192"});
    model.material("mat2").propertyGroup("StressCharge").label("Stress-charge form");
    model.material("mat2").propertyGroup("StressCharge")
         .set("cE", new String[]{"4.1e+011[Pa]", "1.49e+011[Pa]", "0.99e+011[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "1.49e+011[Pa]", "4.1e+011[Pa]", "0.99e+011[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "0.99e+011[Pa]", "0.99e+011[Pa]", "3.89e+011[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", 
         "0[Pa]", "1.25e+011[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "1.25e+011[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "1.305e+011[Pa]"});
    model.material("mat2").propertyGroup("StressCharge")
         .set("eES", new String[]{"0[C/m^2]", "0[C/m^2]", "-0.58[C/m^2]", "0[C/m^2]", "0[C/m^2]", "-0.58[C/m^2]", "0[C/m^2]", "0[C/m^2]", "1.55[C/m^2]", "0[C/m^2]", 
         "-0.48[C/m^2]", "0[C/m^2]", "-0.48[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]"});
    model.material("mat2").propertyGroup("StressCharge")
         .set("epsilonrS", new String[]{"9", "0", "0", "0", "9", "0", "0", "0", "9"});
    model.material("mat1").propertyGroup("def").set("lossfactor", new String[]{"eta0"});
    model.material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.material().create("lmat1", "LayeredMaterial", "");
    model.material("lmat1").setIndex("thickness", "te", 0);
    model.material("lmat1").setIndex("meshPoints", 1, 0);
    model.material().create("lmat2", "LayeredMaterial", "");
    model.material("lmat2").setIndex("link", "mat2", 0);
    model.material("lmat2").setIndex("thickness", "tp", 0);
    model.material("lmat2").setIndex("meshPoints", 3, 0);
    model.material("mat2").propertyGroup("def").set("lossfactor", new String[]{"eta1"});

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new String[]{"wf", "l"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("pos", new String[]{"0", "dy/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("fullsize", new String[]{"(n+1)/4", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("displ", new String[]{"4*wf", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("arr1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mov1").selection("input").set("arr1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mov1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mov1").set("displx", "2*wf");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mov1").set("disply", "-dy");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("mov1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"(n-1/2)*wf+we", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").setIndex("size", "2*wf", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("pos", new String[]{"0", "(l+dy)/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("size", new String[]{"2*we", "la"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("pos", new String[]{"(n-1/2)*wf-we", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").setIndex("pos", "(l+dy)/2+2*wf", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").selection("input").set("r2", "r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("axis", new int[]{0, -1});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("mir1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4")
         .set("size", new String[]{"(n+1/2)*wf", "l+dy"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4")
         .set("pos", new String[]{"(n+1/2)*wf/2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r4");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("par1").selection("input").set("arr1(1,1)");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("par1").selection("tool").set("r4");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("par1").set("keeptool", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("par1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input").set("par1", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("del1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("box1", "Box");
    model.component("comp1").selection("box1").set("entitydim", 2);
    model.component("comp1").selection("box1").set("ymin", "-l/2");
    model.component("comp1").selection("box1").set("zmin", 0);
    model.component("comp1").selection("box1").set("condition", "inside");
    model.component("comp1").selection().duplicate("box2", "box1");
    model.component("comp1").selection("box2").set("ymin", Double.NEGATIVE_INFINITY);
    model.component("comp1").selection("box2").set("ymax", "l/2");
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("input", new String[]{"box1", "box2"});
    model.component("comp1").selection().create("box3", "Box");
    model.component("comp1").selection("box3").set("entitydim", 1);
    model.component("comp1").selection("box3").set("xmax", 0);
    model.component("comp1").selection("box3").set("condition", "inside");
    model.component("comp1").selection().create("box4", "Box");
    model.component("comp1").selection("box4").set("entitydim", 2);
    model.component("comp1").selection("box4").set("zmin", 0);
    model.component("comp1").selection("box4").set("condition", "inside");
    model.component("comp1").selection().create("box5", "Box");
    model.component("comp1").selection("box5").set("entitydim", 2);
    model.component("comp1").selection("box5").set("ymin", "-((l+dy)/2+2*wf+la)+0.01");
    model.component("comp1").selection("box5").set("ymax", "(l+dy)/2+2*wf+la-0.01");
    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(33, 40);

    model.component("comp1").material().create("stlmat1", "LayeredMaterialStack");
    model.component("comp1").material("stlmat1").set("middlePlane", "bottom");
    model.component("comp1").material("stlmat1").feature("stllmat1").set("link", "lmat2");
    model.component("comp1").material("stlmat1").feature().create("stllmat2", "LayeredMaterialStackLink", "comp1");
    model.component("comp1").material("stlmat1").feature("stllmat2").selection().named("uni1");

    model.view().create("view4", 2);
    model.view("view4").set("showgrid", false);
    model.view("view4").axis().set("viewscaletype", "manual");
    model.view("view4").axis().set("yscale", 416666.66666666674);

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("view", "view4");
    model.result("pg1").set("ispendingzoom", true);
    model.result("pg1").set("edges", false);
    model.result("pg1").label("\u5c42\u6a2a\u622a\u9762\u9884\u89c8");
    model.result("pg1").create("arrow1", "ArrowData");
    model.result("pg1").feature("arrow1").set("pointdata", new double[][]{{-0.02}, {0}});
    model.result("pg1").feature("arrow1").set("vectordata", new double[][]{{0}, {1.2E-7}});
    model.result("pg1").feature("arrow1").set("coloring", "uniform");
    model.result("pg1").feature("arrow1").set("color", "custom");
    model.result("pg1").feature("arrow1").set("customcolor", new double[]{1, 0, 0});
    model.result("pg1").feature("arrow1").set("autoscale", 2.3999999999999995E-6);
    model.result("pg1").create("surf1", "SurfaceData");
    model.result("pg1").feature("surf1")
         .set("pointdata", new double[][]{{0, 0.46499999999999997, 0, 0.46499999999999997}, {0, 0, 4.0E-7, 4.0E-7}});
    model.result("pg1").feature("surf1").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf1").set("colordata", new double[]{1, 1, 1, 1});
    model.result("pg1").feature("surf1").set("rangecoloractive", true);
    model.result("pg1").feature("surf1").set("rangecolormin", 1);
    model.result("pg1").feature("surf1").set("rangecolormax", 2);
    model.result("pg1").feature("surf1").set("rangedataactive", true);
    model.result("pg1").feature("surf1").set("rangedatamin", 1);
    model.result("pg1").feature("surf1").set("rangedatamax", 2);
    model.result("pg1").feature("surf1").set("coloring", "colortable");
    model.result("pg1").feature("surf1").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf1").set("colortablerev", true);
    model.result("pg1").feature("surf1").set("colorlegend", false);
    model.result("pg1").create("surf2", "SurfaceData");
    model.result("pg1").feature("surf2")
         .set("pointdata", new double[][]{{0.01, 0.475, 0.01, 0.475}, {4.0E-7, 4.0E-7, 4.8E-7, 4.8E-7}});
    model.result("pg1").feature("surf2").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf2").set("colordata", new double[]{2, 2, 2, 2});
    model.result("pg1").feature("surf2").set("rangecoloractive", true);
    model.result("pg1").feature("surf2").set("rangecolormin", 1);
    model.result("pg1").feature("surf2").set("rangecolormax", 2);
    model.result("pg1").feature("surf2").set("rangedataactive", true);
    model.result("pg1").feature("surf2").set("rangedatamin", 1);
    model.result("pg1").feature("surf2").set("rangedatamax", 2);
    model.result("pg1").feature("surf2").set("coloring", "colortable");
    model.result("pg1").feature("surf2").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf2").set("colortablerev", true);
    model.result("pg1").feature("surf2").set("colorlegend", false);
    model.result("pg1").create("line1", "LineData");
    model.result("pg1").feature("line1")
         .set("pointdata", new double[][]{{0, 0.01, 0.46499999999999997, 0.475, 0, 0.01, 0.46499999999999997, 0.475, 0, 0.01, 0.46499999999999997, 0.475}, {0, 0, 0, 0, 4.0E-7, 4.0E-7, 4.0E-7, 4.0E-7, 4.8E-7, 4.8E-7, 4.8E-7, 4.8E-7}});
    model.result("pg1").feature("line1")
         .set("elementdata", new int[][]{{0, 4, 4, 6, 9, 9, 11}, {2, 7, 0, 2, 11, 5, 7}});
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "custom");
    model.result("pg1").feature("line1").set("customcolor", new double[]{0, 0, 0});
    model.result("pg1").create("tann_uppermiddle", "TableAnnotation");
    model.result("pg1").feature("tann_uppermiddle").set("source", "localtable");
    model.result("pg1").feature("tann_uppermiddle").set("showpoint", false);
    model.result("pg1").feature("tann_uppermiddle").set("showframe", false);
    model.result("pg1").feature("tann_uppermiddle").set("anchorpoint", "uppermiddle");
    model.result("pg1").feature("tann_uppermiddle").setIndex("localtablematrix", 0.2375, 0, 0);
    model.result("pg1").feature("tann_uppermiddle").setIndex("localtablematrix", -2.3999999999999997E-8, 0, 1);
    model.result("pg1").feature("tann_uppermiddle")
         .setIndex("localtablematrix", "\\textbf{\\unicode{\u533a\u57df\u201c1\u201d:}}\\newline \\unicode{\u591a\u5c42\u6750\u6599\u94fe\u63a5 1}\\newline \\unicode{\u591a\u5c42\u6750\u6599\u94fe\u63a5 2}", 0, 2);
    model.result("pg1").feature("tann_uppermiddle").set("latexmarkup", true);
    model.result("pg1").create("surf3", "SurfaceData");
    model.result("pg1").feature("surf3")
         .set("pointdata", new double[][]{{0.5, 0.965, 0.5, 0.965}, {0, 0, 4.0E-7, 4.0E-7}});
    model.result("pg1").feature("surf3").set("elementdata", new int[][]{{0, 0}, {1, 3}, {3, 2}});
    model.result("pg1").feature("surf3").set("colordata", new double[]{1, 1, 1, 1});
    model.result("pg1").feature("surf3").set("rangecoloractive", true);
    model.result("pg1").feature("surf3").set("rangecolormin", 1);
    model.result("pg1").feature("surf3").set("rangecolormax", 2);
    model.result("pg1").feature("surf3").set("rangedataactive", true);
    model.result("pg1").feature("surf3").set("rangedatamin", 1);
    model.result("pg1").feature("surf3").set("rangedatamax", 2);
    model.result("pg1").feature("surf3").set("coloring", "colortable");
    model.result("pg1").feature("surf3").set("colortable", "GrayPrint");
    model.result("pg1").feature("surf3").set("colortablerev", true);
    model.result("pg1").feature("surf3").set("colorlegend", false);
    model.result("pg1").create("line2", "LineData");
    model.result("pg1").feature("line2")
         .set("pointdata", new double[][]{{0.5, 0.51, 0.965, 0.975, 0.5, 0.51, 0.965, 0.975}, {0, 0, 0, 0, 4.0E-7, 4.0E-7, 4.0E-7, 4.0E-7}});
    model.result("pg1").feature("line2").set("elementdata", new int[][]{{0, 4, 4, 6}, {2, 6, 0, 2}});
    model.result("pg1").feature("line2").set("coloring", "uniform");
    model.result("pg1").feature("line2").set("color", "custom");
    model.result("pg1").feature("line2").set("customcolor", new double[]{0, 0, 0});
    model.result("pg1").feature("tann_uppermiddle").setIndex("localtablematrix", 0.7375, 1, 0);
    model.result("pg1").feature("tann_uppermiddle").setIndex("localtablematrix", -2.3999999999999997E-8, 1, 1);
    model.result("pg1").feature("tann_uppermiddle")
         .setIndex("localtablematrix", "\\textbf{\\unicode{\u533a\u57df\u201c2\u201d:}}\\newline \\unicode{\u591a\u5c42\u6750\u6599\u94fe\u63a5 1}", 1, 2);
    model.result("pg1").feature("tann_uppermiddle").set("latexmarkup", true);
    model.result("pg1").create("line3", "LineData");
    model.result("pg1").feature("line3")
         .set("pointdata", new double[][]{{-0.01, 0.0298, -0.01, 0.0298, 0.03975, 0.07955000000000001, 0.03975, 0.07955000000000001, 0.08950000000000001, 0.12930000000000003, 0.08950000000000001, 0.12930000000000003, 0.13924999999999998, 0.17905, 0.13924999999999998, 0.17905, 0.189, 0.2288, 0.189, 0.2288, 0.23875000000000002, 0.27855, 0.23875000000000002, 0.27855, 0.2885, 0.3283, 0.2885, 0.3283, 0.33825, 0.37805, 0.33825, 0.37805, 0.388, 0.4278, 0.388, 0.4278, 0.43775000000000003, 0.47755000000000003, 0.43775000000000003, 0.47755000000000003, 0.48750000000000004, 0.5273000000000001, 0.48750000000000004, 0.5273000000000001, 0.53725, 0.5770500000000001, 0.53725, 0.5770500000000001, 0.587, 0.6268, 0.587, 0.6268, 0.63675, 0.67655, 0.63675, 0.67655, 0.6865, 0.7263, 0.6865, 0.7263, 0.7362500000000001, 0.7760500000000001, 0.7362500000000001, 0.7760500000000001, 0.786, 0.8258000000000001, 0.786, 0.8258000000000001, 0.83575, 0.87555, 0.83575, 0.87555, 0.8855000000000001, 0.9253, 0.8855000000000001, 0.9253, 0.93525, 0.97505, 0.93525, 0.97505}, {-2.3999999999999996E-9, -2.3999999999999996E-9, 2.3999999999999996E-9, 2.3999999999999996E-9, -2.3999999999999996E-9, -2.3999999999999996E-9, 2.3999999999999996E-9, 2.3999999999999996E-9, -2.3999999999999996E-9, -2.3999999999999996E-9, 2.3999999999999996E-9, 2.3999999999999996E-9, -2.3999999999999996E-9, -2.3999999999999996E-9, 2.3999999999999996E-9, 2.3999999999999996E-9, -2.3999999999999996E-9, -2.3999999999999996E-9, 2.3999999999999996E-9, 2.3999999999999996E-9, -2.3999999999999996E-9, -2.3999999999999996E-9, 2.3999999999999996E-9, 2.3999999999999996E-9, -2.3999999999999996E-9, -2.3999999999999996E-9, 2.3999999999999996E-9, 2.3999999999999996E-9, -2.3999999999999996E-9, -2.3999999999999996E-9, 2.3999999999999996E-9, 2.3999999999999996E-9, -2.3999999999999996E-9, -2.3999999999999996E-9, 2.3999999999999996E-9, 2.3999999999999996E-9, -2.3999999999999996E-9, -2.3999999999999996E-9, 2.3999999999999996E-9, 2.3999999999999996E-9, -2.3999999999999996E-9, -2.3999999999999996E-9, 2.3999999999999996E-9, 2.3999999999999996E-9, -2.3999999999999996E-9, -2.3999999999999996E-9, 2.3999999999999996E-9, 2.3999999999999996E-9, -2.3999999999999996E-9, -2.3999999999999996E-9, 2.3999999999999996E-9, 2.3999999999999996E-9, -2.3999999999999996E-9, -2.3999999999999996E-9, 2.3999999999999996E-9, 2.3999999999999996E-9, -2.3999999999999996E-9, -2.3999999999999996E-9, 2.3999999999999996E-9, 2.3999999999999996E-9, -2.3999999999999996E-9, -2.3999999999999996E-9, 2.3999999999999996E-9, 2.3999999999999996E-9, -2.3999999999999996E-9, -2.3999999999999996E-9, 2.3999999999999996E-9, 2.3999999999999996E-9, -2.3999999999999996E-9, -2.3999999999999996E-9, 2.3999999999999996E-9, 2.3999999999999996E-9, -2.3999999999999996E-9, -2.3999999999999996E-9, 2.3999999999999996E-9, 2.3999999999999996E-9, -2.3999999999999996E-9, -2.3999999999999996E-9, 2.3999999999999996E-9, 2.3999999999999996E-9}});
    model.result("pg1").feature("line3")
         .set("elementdata", new int[][]{{0, 1, 3, 2, 4, 5, 7, 6, 8, 9, 11, 10, 12, 13, 15, 14, 16, 17, 19, 18, 20, 21, 23, 22, 24, 25, 27, 26, 28, 29, 31, 30, 32, 33, 35, 34, 36, 37, 39, 38, 40, 41, 43, 42, 44, 45, 47, 46, 48, 49, 51, 50, 52, 53, 55, 54, 56, 57, 59, 58, 60, 61, 63, 62, 64, 65, 67, 66, 68, 69, 71, 70, 72, 73, 75, 74, 76, 77, 79, 78}, {1, 3, 2, 0, 5, 7, 6, 4, 9, 11, 10, 8, 13, 15, 14, 12, 17, 19, 18, 16, 21, 23, 22, 20, 25, 27, 26, 24, 29, 31, 30, 28, 33, 35, 34, 32, 37, 39, 38, 36, 41, 43, 42, 40, 45, 47, 46, 44, 49, 51, 50, 48, 53, 55, 54, 52, 57, 59, 58, 56, 61, 63, 62, 60, 65, 67, 66, 64, 69, 71, 70, 68, 73, 75, 74, 72, 77, 79, 78, 76}});
    model.result("pg1").feature("line3").label("\u5c42\u4e2d\u9762");
    model.result("pg1").feature("line3").set("coloring", "uniform");
    model.result("pg1").feature("line3").set("color", "custom");
    model.result("pg1").feature("line3").set("customcolor", new double[]{1, 0, 0});
    model.result("pg1").set("ylabel", "\u539a\u5ea6\u5750\u6807");
    model.result("pg1").set("defaultaxisunits", new String[]{"", "m"});
    model.result("pg1").run();
    model.result("pg1").run();

    model.component("comp1").physics("lshell").feature("lemm1").create("dmp1", "Damping", 2);
    model.component("comp1").physics("lshell").feature("lemm1").feature("dmp1")
         .set("DampingType", "IsotropicLossFactor");
    model.component("comp1").physics("lshell").feature("pzm1").set("allLayers", false);
    model.component("comp1").physics("lshell").feature("pzm1").setIndex("shelllist_lCheck", 0, 1, 0);
    model.component("comp1").physics("lshell").feature("pzm1").create("mdmp1", "MechanicalDamping", 2);
    model.component("comp1").physics("lshell").feature("pzm1").feature("mdmp1")
         .set("DampingType", "IsotropicLossFactor");
    model.component("comp1").physics("lshell").create("fix1", "Fixed", 1);
    model.component("comp1").physics("lshell").feature("fix1").selection().named("sel1");
    model.component("comp1").physics("lshell").create("sym1", "Symmetry", 1);
    model.component("comp1").physics("lshell").feature("sym1").selection().named("box3");
    model.component("comp1").physics("lshell").create("contls1", "ContinuityLayeredShell", 1);
    model.component("comp1").physics("lshell").feature("contls1").set("shelllist_src", "stlmat1_z1");
    model.component("comp1").physics("lshell").feature("contls1").set("shelllist", "stlmat1_z2");
    model.component("comp1").physics("lshell").prop("ShapeProperty").set("order_displacement", "21s");
    model.component("comp1").physics("ecis").feature("csh1").create("gnd1", "Ground", 2);
    model.component("comp1").physics("ecis").feature("csh1").feature("gnd1").selection().named("box2");
    model.component("comp1").physics("ecis").feature("csh1").feature("gnd1").set("applyTo", "all");
    model.component("comp1").physics("ecis").feature("csh1").create("term1", "Terminal", 2);
    model.component("comp1").physics("ecis").feature("csh1").feature("term1").selection().named("box1");
    model.component("comp1").physics("ecis").feature("csh1").feature("term1").set("applyTo", "all");
    model.component("comp1").physics("ecis").feature("csh1").feature("term1").set("TerminalType", "Voltage");
    model.component("comp1").physics("ecis").feature("csh1").feature("term1").set("V0", "Vapp");
    model.component("comp1").physics("ecis").feature("epzml1").set("allLayers", false);
    model.component("comp1").physics("ecis").feature("epzml1").setIndex("shelllist_lCheck", 0, 1, 0);
    model.component("comp1").physics("ecis").create("cls1", "ContinuityLayeredShell", 1);
    model.component("comp1").physics("ecis").feature("cls1").set("shelllist_src", "stlmat1");
    model.component("comp1").physics("ecis").feature("cls1").set("shelllist", "stlmat1_z2");
    model.component("comp1").physics("ecis").feature("cls1").set("shelllist_src", "stlmat1_z1");

    model.component("comp1").mesh("mesh1").create("fq1", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("fq1").selection().named("box4");
    model.component("comp1").mesh("mesh1").feature("fq1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hmax", 0.1);
    model.component("comp1").mesh("mesh1").run("fq1");

    model.study("std1").setGenPlots(false);
    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").feature("eig").set("neigs", 20);
    model.study("std1").feature("eig").set("eigunit", "GHz");

    return model;
  }

  public static Model run2(Model model) {
    model.study("std1").feature("eig").set("shift", "8");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("e1").set("eigvfunscale", "mass");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("lshl1", "LayeredMaterial");
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").set("data", "lshl1");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").set("data", "mir1");
    model.result("pg2").set("edges", false);
    model.result("pg2").set("view", "new");
    model.result("pg2").set("showlegends", false);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "w");
    model.result("pg2").feature("surf1").set("descractive", true);
    model.result("pg2").feature("surf1").set("rangecoloractive", true);
    model.result("pg2").feature("surf1").set("rangecolormin", "-1E10");
    model.result("pg2").feature("surf1").set("rangecolormax", "1E10");
    model.result("pg2").run();

    model.view("view7").set("showgrid", false);

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").set("data", "mir1");
    model.result("pg3").set("view", "new");
    model.result("pg3").set("showlegends", false);
    model.result("pg3").create("slc1", "Slice");
    model.result("pg3").feature("slc1").set("descractive", true);
    model.result("pg3").feature("slc1").set("quickplane", "zx");
    model.result("pg3").feature("slc1").set("quickynumber", 1);
    model.result("pg3").feature("slc1").set("colortabletype", "discrete");
    model.result("pg3").feature("slc1").set("bandcount", 15);
    model.result("pg3").run();
    model.result("pg3").set("edges", false);
    model.result("pg3").run();

    model.view("view8").set("showgrid", false);

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").set("plotgroup", "Default");
    model.study("std2").feature("freq").set("solnum", "auto");
    model.study("std2").feature("freq").set("notsolnum", "auto");
    model.study("std2").feature("freq").set("outputmap", new String[]{});
    model.study("std2").feature("freq").set("ngenAUX", "1");
    model.study("std2").feature("freq").set("goalngenAUX", "1");
    model.study("std2").feature("freq").set("ngenAUX", "1");
    model.study("std2").feature("freq").set("goalngenAUX", "1");
    model.study("std2").feature("freq").setSolveFor("/physics/lshell", true);
    model.study("std2").feature("freq").setSolveFor("/physics/ecis", true);
    model.study("std2").feature("freq").setSolveFor("/multiphysics/pzel1", true);
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("freq").set("punit", "GHz");
    model.study("std2").feature("freq").set("plist", "range(7.95,0.005,8.05)");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").set("data", "dset2");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("axislimits", true);
    model.result("pg4").set("xmin", 7.92);
    model.result("pg4").set("xmax", 8.08);
    model.result("pg4").set("ymin", -4.7);
    model.result("pg4").set("ymax", "-3.0");
    model.result("pg4").set("showlegends", false);
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "log10(abs(ecis.I0_1/ecis.V0_1))", 0);
    model.result("pg4").feature("glob1").create("gmrk1", "GraphMarker");
    model.result("pg4").feature("glob1").feature("gmrk1").set("linewidth", "preference");
    model.result("pg4").run();
    model.result("pg4").feature("glob1").feature("gmrk1").set("inclxcoord", true);
    model.result("pg4").run();
    model.result().dataset().create("dset2lshelllshlge", "LayeredMaterial");
    model.result().dataset("dset2lshelllshlge").label("\u591a\u5c42\u6750\u6599 2 (\u58f3\u51e0\u4f55\u7ed3\u6784)");
    model.result().dataset("dset2lshelllshlge").set("data", "dset2");
    model.result().dataset("dset2lshelllshlge").set("scale", "50*0.035326478305152995");
    model.result().dataset("dset2lshelllshlge")
         .set("defaultPlotIDs", new String[]{"shellGeometry|lshell", "plyAngle|lshell"});
    model.result().dataset("dset2lshelllshlge").label("\u591a\u5c42\u6750\u6599 2 (\u58f3\u51e0\u4f55\u7ed3\u6784)");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset2lshelllshlge");
    model.result("pg5").setIndex("looplevel", 21, 0);
    model.result("pg5").label("\u58f3\u51e0\u4f55\u7ed3\u6784 (lshell)");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("showlegends", false);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg5").feature("surf1").set("threshold", "manual");
    model.result("pg5").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg5").feature("surf1").set("colortable", "Rainbow");
    model.result("pg5").feature("surf1").set("colortabletrans", "none");
    model.result("pg5").feature("surf1").set("colorscalemode", "linear");
    model.result("pg5").feature("surf1").set("coloring", "uniform");
    model.result("pg5").feature("surf1").set("color", "gray");
    model.result("pg5").label("\u58f3\u51e0\u4f55\u7ed3\u6784 (lshell)");
    model.result("pg5").run();
    model.result("pg5").set("titletype", "none");

    model.component("comp1").view("view1").set("showgrid", false);

    model.result("pg5").set("titletype", "label");
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("expr", "stlmat1.zone");
    model.result("pg6").feature("surf1").set("coloring", "colortable");
    model.result("pg6").feature("surf1").set("colortable", "TrafficLight");
    model.result("pg6").run();
    model.result().move("pg6", 1);
    model.result("pg6").run();
    model.result("pg2").run();

    model.title("\u6c2e\u5316\u94dd\u5170\u59c6\u6ce2\u8c10\u632f\u5668 - \u591a\u5c42\u58f3\u7248\u672c");

    model
         .description("\u5170\u59c6\u6ce2\u8c10\u632f\u5668\u662f\u8bb8\u591a\u5c04\u9891\u5e94\u7528\u4e2d\u7684\u6709\u7528\u7ec4\u4ef6\u3002\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u5bf9\u6c2e\u5316\u94dd\u5170\u59c6\u6ce2\u8c10\u632f\u5668\u8fdb\u884c\u5efa\u6a21\uff0c\u5e76\u6267\u884c\u7279\u5f81\u9891\u7387\u548c\u9891\u7387\u54cd\u5e94\u5206\u6790\u4ee5\u63cf\u8ff0\u8be5\u5668\u4ef6\u7684\u7279\u6027\u3002\u5176\u4e2d\u5c06\u201c\u591a\u5c42\u58f3\u4e2d\u7684\u7535\u6d41\u201d\u4e0e\u201c\u591a\u5c42\u58f3\u201d\u63a5\u53e3\u76f8\u7ed3\u5408\uff0c\u4ee5\u6a21\u62df\u538b\u7535\u6548\u5e94\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("aln_lamb_wave_resonator_layered.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
