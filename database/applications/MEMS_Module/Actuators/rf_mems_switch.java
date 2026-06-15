/*
 * rf_mems_switch.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:56 by COMSOL 6.3.0.290. */
public class rf_mems_switch {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Actuators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics("es").prop("ShapeProperty").set("order_electricpotential", "2m");
    model.component("comp1").physics("es").create("ccns1", "ChargeConservationSolid");
    model.component("comp1").physics("es").feature("ccns1").selection().all();

    model.component("comp1").multiphysics().create("eme1", "ElectromechanicalForces", 3);
    model.component("comp1").multiphysics("eme1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("eme1").set("Electrostatics_physics", "es");

    model.component("comp1").common().create("free1", "DeformingDomain");
    model.component("comp1").common("free1").set("smoothingType", "hyperelastic");
    model.component("comp1").common("free1").selection().set();
    model.component("comp1").common().create("sym1", "Symmetry");
    model.component("comp1").common("sym1").selection().set();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/es", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/eme1", true);

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");

    model.param().set("V0", "1[mV]");
    model.param().descr("V0", "\u521d\u59cb\u7535\u538b");
    model.param().set("Vstep", "5[V]");
    model.param().descr("Vstep", "\u7535\u538b\u9636\u8dc3");
    model.param().set("insheight", "100[nm]");
    model.param().descr("insheight", "\u7edd\u7f18\u4f53\u9ad8\u5ea6");
    model.param().set("airheight", "900[nm]");
    model.param().descr("airheight", "\u7a7a\u6c14\u9ad8\u5ea6");
    model.param().set("en", "1e15[Pa/m]");
    model.param().descr("en", "\u5f39\u7c27\u521a\u5ea6");
    model.param().set("tn", "5e5[Pa]");
    model.param().descr("tn", "\u63a5\u89e6\u529b");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("gap", "airheight+w");
    model.component("comp1").variable("var1")
         .set("contactpressure", "(gap<=0)*(tn-en*gap)+(gap>0)*tn*exp(-gap*en/tn)");
    model.component("comp1").variable("var1").set("Va", "V0+Vstep*step2(t/1[s])");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new int[]{110, 5});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("pos", new int[]{-60, 0});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("size", new int[]{50, 60});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("pos", new int[]{0, -10});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("size", new int[]{110, 60});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("pos", new int[]{-60, -10});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").set("size", new int[]{110, 10});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").set("pos", new double[]{-60, -2.5});
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 2, 1);
    model.component("comp1").geom("geom1").feature("ext1").set("displ", new String[][]{{"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1").set("scale", new String[][]{{"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0"});
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 4, 2);
    model.component("comp1").geom("geom1").feature("ext1")
         .set("displ", new String[][]{{"0", "0"}, {"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1")
         .set("scale", new String[][]{{"1", "1"}, {"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0", "0"});
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").set("location", "1.05*insheight");
    model.component("comp1").func("step1").set("smooth", "0.05*insheight");
    model.component("comp1").func().create("step2", "Step");
    model.component("comp1").func("step2").set("location", "3e-5");
    model.component("comp1").func("step2").set("smooth", "1e-5");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u6865");
    model.component("comp1").selection("sel1").set(8, 23, 26, 29);
    model.component("comp1").selection().create("box1", "Box");
    model.component("comp1").selection("box1").label("\u95f4\u9699");
    model.component("comp1").selection("box1").set("zmax", 1.1);
    model.component("comp1").selection("box1").set("condition", "inside");
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("\u6865\u8868\u9762");
    model.component("comp1").selection("adj1").set("input", new String[]{"sel1"});
    model.component("comp1").selection().create("box2", "Box");
    model.component("comp1").selection("box2").label("\u57fa\u90e8");
    model.component("comp1").selection("box2").set("entitydim", 2);
    model.component("comp1").selection("box2").set("zmax", 0.1);
    model.component("comp1").selection("box2").set("condition", "inside");
    model.component("comp1").selection().create("box3", "Box");
    model.component("comp1").selection("box3").set("entitydim", 2);
    model.component("comp1").selection("box3").set("zmin", 0.9);
    model.component("comp1").selection("box3").set("zmax", 1.1);
    model.component("comp1").selection("box3").set("condition", "inside");
    model.component("comp1").selection().create("int1", "Intersection");
    model.component("comp1").selection("int1").label("\u6865\u4e0b\u4fa7");
    model.component("comp1").selection("int1").set("entitydim", 2);
    model.component("comp1").selection("int1").set("input", new String[]{"adj1", "box3"});
    model.component("comp1").selection().create("box4", "Box");
    model.component("comp1").selection("box4").label("\u5bf9\u79f0 x");
    model.component("comp1").selection("box4").set("entitydim", 2);
    model.component("comp1").selection("box4").set("xmin", 45);
    model.component("comp1").selection("box4").set("condition", "inside");
    model.component("comp1").selection().create("box5", "Box");
    model.component("comp1").selection("box5").label("\u5bf9\u79f0 y");
    model.component("comp1").selection("box5").set("entitydim", 2);
    model.component("comp1").selection("box5").set("ymin", 45);
    model.component("comp1").selection("box5").set("condition", "inside");
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("input", new String[]{"box4", "box5"});
    model.component("comp1").selection("uni1").label("\u5bf9\u79f0");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").all();
    model.component("comp1").selection("sel2").label("\u6240\u6709\u57df");
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").set("add", new String[]{"sel2"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"sel1"});
    model.component("comp1").selection("dif1").label("\u975e\u56fa\u4f53");

    model.component("comp1").physics("solid").selection().named("sel1");
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().named("uni1");
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().named("int1");
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "0", "contactpressure"});
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(24);
    model.component("comp1").physics("solid").feature("lemm1").create("dmp1", "Damping", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("beta_dK", "1e-6");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").selection().named("sel1");
    model.component("comp1").physics("es").feature().remove("ccns1");
    model.component("comp1").physics("es").create("ccnf1", "ChargeConservationFluid", 3);
    model.component("comp1").physics("es").feature("ccnf1").selection().named("box1");
    model.component("comp1").physics("es").create("term1", "DomainTerminal", 3);
    model.component("comp1").physics("es").feature("term1").selection().named("sel1");
    model.component("comp1").physics("es").feature("term1").set("TerminalType", "Voltage");
    model.component("comp1").physics("es").feature("term1").set("V0", "Va");
    model.component("comp1").physics("es").create("term2", "Terminal", 2);
    model.component("comp1").physics("es").feature("term2").selection().named("box2");
    model.component("comp1").physics("es").feature("term2").set("TerminalType", "Voltage");
    model.component("comp1").physics("es").feature("term2").set("V0", 0);
    model.component("comp1").physics("es").feature().duplicate("term3", "term1");
    model.component("comp1").physics("es").feature("term3").set("V0", "V0");

    model.component("comp1").common("free1").selection().named("dif1");
    model.component("comp1").common("sym1").selection().named("uni1");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("Si - Polycrystalline silicon");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.7);
    model.component("comp1").material("mat1").set("roughness", 0.5);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "678[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.5", "0", "0", "0", "4.5", "0", "0", "0", "4.5"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2320[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"34[W/(m*K)]", "0", "0", "0", "34[W/(m*K)]", "0", "0", "0", "34[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "160e9[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.22");
    model.component("comp1").material("mat1").selection().named("sel1");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().named("box1");
    model.component("comp1").material("mat2").materialType("nonSolid");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1+6.5*(1-step1(z/1[m]))"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(10, 20, 30, 40, 50, 63, 73, 83, 93, 103);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().named("box1");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 4);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("numelem", 2);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").selection()
         .set(2, 5, 8, 11, 14, 23, 26, 29);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("tlist", "range(0,5e-7,5e-5)");
    model.study("std1").feature("time").set("plot", true);
    model.study("std1").feature("time").set("plotfreq", "tsteps");
    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time").set("disabledphysics", new String[]{"es/term3"});
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("timemethod", "bdf");
    model.sol("sol1").feature("t1").set("tstepsbdf", "intermediate");
    model.sol("sol1").feature("t1").create("fc1", "FullyCoupled");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u4f4d\u79fb (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.disp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "SpectrumLight");

    model.sol("sol1").runAll();

    model.result().remove("pg1");

    model.study("std1").feature("time").set("plotgroup", "Default");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 101, 0);
    model.result("pg1").label("\u4f4d\u79fb (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.disp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "SpectrumLight");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u7535\u52bf (es)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("solutionparams", "parent");
    model.result("pg2").feature("mslc1").set("expr", "V");
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg2").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg2").feature("mslc1").set("colortable", "Dipole");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("data", "parent");
    model.result("pg2").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg2").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg2").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg2").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg2").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg2").feature("strmsl1").set("titletype", "none");
    model.result("pg2").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg2").feature("strmsl1").set("udist", 0.02);
    model.result("pg2").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg2").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("inheritcolor", false);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("data", "parent");
    model.result("pg2").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg2").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg2").feature("strmsl1").feature("col1").set("expr", "V");
    model.result("pg2").feature("strmsl1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg2").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u7535\u573a (es)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("mslc1", "Multislice");
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("solutionparams", "parent");
    model.result("pg3").feature("mslc1").set("expr", "es.normE");
    model.result("pg3").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg3").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg3").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg3").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg3").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg3").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg3").feature("mslc1").set("colortable", "Prism");
    model.result("pg3").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("data", "parent");
    model.result("pg3").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg3").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg3").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg3").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg3").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg3").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg3").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg3").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg3").feature("strmsl1").set("titletype", "none");
    model.result("pg3").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg3").feature("strmsl1").set("udist", 0.02);
    model.result("pg3").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg3").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("inheritcolor", false);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("data", "parent");
    model.result("pg3").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg3").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg3").feature("strmsl1").feature("col1").set("expr", "es.normE");
    model.result("pg3").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg3").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg3").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg3").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 101, 0);
    model.result("pg4").label("\u52a8\u7f51\u683c");
    model.result("pg4").create("mesh1", "Mesh");
    model.result("pg4").feature("mesh1").set("meshdomain", "volume");
    model.result("pg4").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg4").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg4").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg4").feature("mesh1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 24, 25, 27, 28, 30);
    model.result("pg4").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg4").feature("mesh1").set("qualexpr", "comp1.spatial.relVol");
    model.result("pg4").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg1").run();
    model.result().dataset("dset1").set("frametype", "material");
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").set("quickx", 50);
    model.result().dataset().duplicate("mir2", "mir1");
    model.result().dataset("mir2").set("data", "mir1");
    model.result().dataset("mir2").set("quickplane", "xz");
    model.result().dataset("mir2").set("quicky", 50);
    model.result("pg1").run();
    model.result("pg1").set("data", "mir2");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("data", "mir2");
    model.result("pg2").run();
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "number");
    model.result("pg2").feature("mslc1").set("xnumber", "5");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "number");
    model.result("pg2").feature("mslc1").set("ynumber", "0");
    model.result("pg2").feature("mslc1").set("multiplanezmethod", "number");
    model.result("pg2").feature("mslc1").set("znumber", "0");
    model.result("pg2").feature("mslc1").set("resolution", "norefine");
    model.result("pg2").run();
    model.result("pg2").feature("strmsl1").set("multiplanexmethod", "number");
    model.result("pg2").feature("strmsl1").set("xnumber", "5");
    model.result("pg2").feature("strmsl1").set("multiplaneymethod", "number");
    model.result("pg2").feature("strmsl1").set("ynumber", "0");
    model.result("pg2").feature("strmsl1").set("multiplanezmethod", "number");
    model.result("pg2").feature("strmsl1").set("znumber", "0");
    model.result("pg2").run();
    model.result("pg1").run();
    model.result().duplicate("pg5", "pg1");
    model.result("pg5").run();
    model.result("pg5").label("\u63a5\u89e6\u529b");
    model.result("pg5").run();
    model.result("pg5").feature("vol1").set("expr", "contactpressure");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u4f4d\u79fb");
    model.result("pg6").create("ptgr1", "PointGraph");
    model.result("pg6").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg6").feature("ptgr1").set("linewidth", "preference");
    model.result("pg6").feature("ptgr1").selection().set(70);
    model.result("pg6").feature("ptgr1").set("expr", "w");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u7535\u5bb9");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").setIndex("expr", "4*es.Q0_1/es.V0_1", 0);
    model.result("pg7").feature("glob1").setIndex("unit", "pF", 0);
    model.result("pg7").feature("glob1").setIndex("descr", "\u7535\u5bb9", 0);
    model.result("pg7").feature("glob1").set("legend", false);
    model.result("pg7").run();
    model.result("pg1").run();

    model.title("RF MEMS \u5f00\u5173\u7684\u5438\u5408");

    model
         .description("\u672c\u6a21\u578b\u5206\u6790\u4e00\u79cd RF MEMS \u5f00\u5173\uff0c\u5b83\u7531\u60ac\u6302\u5728\u4ecb\u7535\u5c42\u4e0a\u7684\u4e00\u4e2a\u8584\u5fae\u6865\u7ec4\u6210\u3002\u5176\u4e2d\u5728\u5f00\u5173\u4e0a\u65bd\u52a0\u4e00\u4e2a\u5927\u4e8e\u5438\u5408\u7535\u538b\u7684\u76f4\u6d41\u7535\u538b\uff0c\u4f7f\u6865\u574d\u584c\u5728\u4ecb\u7535\u5c42\u4e0a\uff0c\u4ece\u800c\u589e\u5927\u88c5\u7f6e\u7684\u7535\u5bb9\uff0c\u901a\u8fc7\u65bd\u52a0\u57fa\u4e8e\u7f5a\u51fd\u6570\u7684\u63a5\u89e6\u529b\u6765\u6a21\u62df\u6865\u4e0e\u7535\u4ecb\u8d28\u63a5\u89e6\u65f6\u7684\u63a5\u89e6\u529b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("rf_mems_switch.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
