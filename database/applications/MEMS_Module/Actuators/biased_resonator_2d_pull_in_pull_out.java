/*
 * biased_resonator_2d_pull_in_pull_out.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:52 by COMSOL 6.3.0.290. */
public class biased_resonator_2d_pull_in_pull_out {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Actuators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics("es").prop("ShapeProperty").set("order_electricpotential", "2m");
    model.component("comp1").physics("es").create("ccns1", "ChargeConservationSolid");
    model.component("comp1").physics("es").feature("ccns1").selection().all();

    model.component("comp1").multiphysics().create("eme1", "ElectromechanicalForces", 2);
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

    model.param().set("Vdc", "35[V]");
    model.param().descr("Vdc", "\u76f4\u6d41\u504f\u538b");
    model.param().set("l_b", "22.5[um]");
    model.param().descr("l_b", "\u6881\u957f");
    model.param().set("t_b", "2[um]");
    model.param().descr("t_b", "\u6881\u539a");
    model.param().set("l_e", "10[um]");
    model.param().descr("l_e", "\u7535\u6781\u957f\u5ea6");
    model.param().set("gap", "0.1985[um]");
    model.param().descr("gap", "\u6c14\u9699");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"l_b", "t_b"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"-l_b", "gap"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"l_b", "gap"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"-l_b", "0"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"l_e", "gap+t_b"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"-l_e", "0"});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("r1", "r2", "r3");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").runPre("fin");

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

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material("mat1").selection().set(2, 4, 6, 8);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u7a7a\u6c14");
    model.component("comp1").selection("sel1").set(1, 3, 5, 7);

    model.component("comp1").physics("solid").selection().set(2, 4, 6, 8);

    model.component("comp1").common("free1").selection().named("sel1");

    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(3, 22);
    model.component("comp1").physics("es").create("term1", "DomainTerminal", 2);
    model.component("comp1").physics("es").feature("term1").selection().set(2, 4, 6, 8);
    model.component("comp1").physics("es").feature("term1").set("TerminalType", "Voltage");
    model.component("comp1").physics("es").feature("term1").set("V0", 0);
    model.component("comp1").physics("es").feature().remove("ccns1");
    model.component("comp1").physics("es").create("pot1", "ElectricPotential", 1);
    model.component("comp1").physics("es").feature("pot1").set("V0", "Vdc");
    model.component("comp1").physics("es").feature("pot1").selection().set(7, 12);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(5, 10, 15, 20);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 15);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(1, 3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 10);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7a33\u6001");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u4f4d\u79fb (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.disp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").set("colortable", "SpectrumLight");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u7535\u52bf (es)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("expr", "V");
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature().create("str1", "Streamline");
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("solutionparams", "parent");
    model.result("pg2").feature("str1").set("expr", new String[]{"es.Ex", "es.Ey"});
    model.result("pg2").feature("str1").set("titletype", "none");
    model.result("pg2").feature("str1").set("posmethod", "uniform");
    model.result("pg2").feature("str1").set("udist", 0.02);
    model.result("pg2").feature("str1").set("maxlen", 0.4);
    model.result("pg2").feature("str1").set("maxsteps", 5000);
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("inheritcolor", false);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("data", "parent");
    model.result("pg2").feature("str1").selection().geom("geom1", 1);
    model.result("pg2").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22);
    model.result("pg2").feature("str1").set("inheritplot", "surf1");
    model.result("pg2").feature("str1").feature().create("col1", "Color");
    model.result("pg2").feature("str1").feature("col1").set("expr", "V");
    model.result("pg2").feature("str1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg2").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("str1").feature().create("filt1", "Filter");
    model.result("pg2").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u7535\u573a (es)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "es.normE");
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature().create("str1", "Streamline");
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("solutionparams", "parent");
    model.result("pg3").feature("str1").set("expr", new String[]{"es.Ex", "es.Ey"});
    model.result("pg3").feature("str1").set("titletype", "none");
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("udist", 0.02);
    model.result("pg3").feature("str1").set("maxlen", 0.4);
    model.result("pg3").feature("str1").set("maxsteps", 5000);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("inheritcolor", false);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("data", "parent");
    model.result("pg3").feature("str1").selection().geom("geom1", 1);
    model.result("pg3").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22);
    model.result("pg3").feature("str1").set("inheritplot", "surf1");
    model.result("pg3").feature("str1").feature().create("col1", "Color");
    model.result("pg3").feature("str1").feature("col1").set("expr", "es.normE");
    model.result("pg3").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg3").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg3").feature("str1").feature().create("filt1", "Filter");
    model.result("pg3").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").label("\u52a8\u7f51\u683c");
    model.result("pg4").create("mesh1", "Mesh");
    model.result("pg4").feature("mesh1").set("meshdomain", "surface");
    model.result("pg4").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg4").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg4").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg4").feature("mesh1").feature("sel1").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
    model.result("pg4").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg4").feature("mesh1").set("qualexpr", "comp1.spatial.relVol");
    model.result("pg4").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg1").run();
    model.result("pg1").label("\u504f\u7f6e\u4f4d\u79fb");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().named("sel1");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("str1").set("udist", 0.005);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").feature("str1").set("udist", 0.005);
    model.result("pg3").run();
    model.result("pg4").run();
    model.result().remove("pg4");

    model.title("\u504f\u538b\u8c10\u632f\u5668\u7684\u7a33\u6001\u5206\u6790 - \u4e8c\u7ef4");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u4e00\u4e2a\u9759\u7535\u9a71\u52a8\u7684 MEMS \u8c10\u632f\u5668\u3002\u8be5\u88c5\u7f6e\u7531\u5e73\u884c\u677f\u7535\u5bb9\u5668\u4e0a\u65bd\u52a0\u7684\u4ea4\u6d41 + \u76f4\u6d41\u504f\u538b\u9a71\u52a8\u3002\u5176\u4e2d\u8ba1\u7b97\u8c10\u632f\u5668\u5728\u5916\u52a0\u76f4\u6d41\u504f\u538b\u4f5c\u7528\u4e0b\u7684\u53d8\u5f62\u3002");

    model.label("biased_resonator_2d_basic.mph");

    model.param().set("vrel", "-0.3");
    model.param().descr("vrel", "\u76f8\u5bf9\u5782\u76f4\u4f4d\u79fb");

    model.component("comp1").probe().create("point1", "Point");
    model.component("comp1").probe("point1").label("\u4e2d\u95f4\u70b9\u7684\u76f8\u5bf9\u5782\u76f4\u4f4d\u79fb");
    model.component("comp1").probe("point1").set("probename", "vmid");
    model.component("comp1").probe("point1").selection().set(8);
    model.component("comp1").probe("point1").set("expr", "v/gap");
    model.component("comp1").probe("point1").set("method", "summation");
    model.component("comp1").probe("point1").set("frame", "material");

    model.component("comp1").physics("es").prop("ShapeProperty").set("order_electricpotential", 2);
    model.component("comp1").physics("es").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("es").feature("ge1").setIndex("name", "Vdc", 0, 0);
    model.component("comp1").physics("es").feature("ge1").setIndex("equation", "vrel-vmid", 0, 0);
    model.component("comp1").physics("es").feature("ge1").setIndex("description", "\u76f4\u6d41\u504f\u538b", 0, 0);
    model.component("comp1").physics("es").feature("ge1").set("DependentVariableQuantity", "electricpotential");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").setSolveFor("/physics/es", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/eme1", true);
    model.study("std2").feature("stat").set("probesel", "none");
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "Vdc", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "V", 0);
    model.study("std2").feature("stat").setIndex("pname", "Vdc", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "V", 0);
    model.study("std2").feature("stat").setIndex("pname", "vrel", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(-0.3,-0.01,-0.45)", 0);
    model.study("std2").createAutoSequences("sol");
    model.study("std2").createAutoSequences("jobs");

    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").label("\u4f4d\u79fb (solid)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"solid.disp"});
    model.result("pg4").feature("surf1").set("threshold", "manual");
    model.result("pg4").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colortabletrans", "none");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").feature("surf1").set("resolution", "normal");
    model.result("pg4").feature("surf1").set("colortable", "SpectrumLight");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u7535\u52bf (es) 1");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("solutionparams", "parent");
    model.result("pg5").feature("surf1").set("expr", "V");
    model.result("pg5").feature("surf1").set("colortable", "Dipole");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg5").feature().create("str1", "Streamline");
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("solutionparams", "parent");
    model.result("pg5").feature("str1").set("expr", new String[]{"es.Ex", "es.Ey"});
    model.result("pg5").feature("str1").set("titletype", "none");
    model.result("pg5").feature("str1").set("posmethod", "uniform");
    model.result("pg5").feature("str1").set("udist", 0.02);
    model.result("pg5").feature("str1").set("maxlen", 0.4);
    model.result("pg5").feature("str1").set("maxsteps", 5000);
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("inheritcolor", false);
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("data", "parent");
    model.result("pg5").feature("str1").selection().geom("geom1", 1);
    model.result("pg5").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22);
    model.result("pg5").feature("str1").set("inheritplot", "surf1");
    model.result("pg5").feature("str1").feature().create("col1", "Color");
    model.result("pg5").feature("str1").feature("col1").set("expr", "V");
    model.result("pg5").feature("str1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg5").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg5").feature("str1").feature().create("filt1", "Filter");
    model.result("pg5").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("\u7535\u573a (es) 1");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("solutionparams", "parent");
    model.result("pg6").feature("surf1").set("expr", "es.normE");
    model.result("pg6").feature("surf1").set("colortable", "Prism");
    model.result("pg6").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg6").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result("pg6").feature().create("str1", "Streamline");
    model.result("pg6").feature("str1").set("showsolutionparams", "on");
    model.result("pg6").feature("str1").set("solutionparams", "parent");
    model.result("pg6").feature("str1").set("expr", new String[]{"es.Ex", "es.Ey"});
    model.result("pg6").feature("str1").set("titletype", "none");
    model.result("pg6").feature("str1").set("posmethod", "uniform");
    model.result("pg6").feature("str1").set("udist", 0.02);
    model.result("pg6").feature("str1").set("maxlen", 0.4);
    model.result("pg6").feature("str1").set("maxsteps", 5000);
    model.result("pg6").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("str1").set("inheritcolor", false);
    model.result("pg6").feature("str1").set("showsolutionparams", "on");
    model.result("pg6").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("str1").set("showsolutionparams", "on");
    model.result("pg6").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("str1").set("showsolutionparams", "on");
    model.result("pg6").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("str1").set("showsolutionparams", "on");
    model.result("pg6").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("str1").set("data", "parent");
    model.result("pg6").feature("str1").selection().geom("geom1", 1);
    model.result("pg6").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22);
    model.result("pg6").feature("str1").set("inheritplot", "surf1");
    model.result("pg6").feature("str1").feature().create("col1", "Color");
    model.result("pg6").feature("str1").feature("col1").set("expr", "es.normE");
    model.result("pg6").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg6").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg6").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg6").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg6").feature("str1").feature().create("filt1", "Filter");
    model.result("pg6").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").label("\u52a8\u7f51\u683c");
    model.result("pg7").create("mesh1", "Mesh");
    model.result("pg7").feature("mesh1").set("meshdomain", "surface");
    model.result("pg7").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg7").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg7").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg7").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg7").feature("mesh1").feature("sel1").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
    model.result("pg7").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg7").feature("mesh1").set("qualexpr", "comp1.spatial.relVol");
    model.result("pg7").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg4").run();

    model.sol("sol2").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol2").feature("s1").feature("fc1").set("dtech", "hnlin");

    model.study("std2").label("\u5438\u5408");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg4").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u5438\u5408\u56fe");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("ylabel", "v/gap (1)");
    model.result("pg8").set("showlegends", false);
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("linewidth", "preference");
    model.result("pg8").feature("glob1").set("expr", new String[]{"vmid"});
    model.result("pg8").feature("glob1")
         .set("descr", new String[]{"\u4e2d\u95f4\u70b9\u7684\u76f8\u5bf9\u5782\u76f4\u4f4d\u79fb"});
    model.result("pg8").feature("glob1").set("unit", new String[]{"1"});
    model.result("pg8").feature("glob1").set("xdata", "expr");
    model.result("pg8").feature("glob1").set("xdatadescr", " \u76f4\u6d41\u504f\u538b");
    model.result("pg8").feature("glob1").set("xdataexpr", "Vdc");
    model.result("pg8").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("\u5438\u5408\u7535\u538b");
    model.result().numerical("gev1").set("data", "dset2");
    model.result().numerical("gev1").set("expr", new String[]{"Vdc"});
    model.result().numerical("gev1").set("descr", new String[]{"\u76f4\u6d41\u504f\u538b"});
    model.result().numerical("gev1").set("unit", new String[]{"V"});
    model.result().numerical("gev1").set("dataseries", "maximum");
    model.result().numerical("gev1").set("includeparam", true);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5438\u5408\u7535\u538b");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result("pg4").run();
    model.result("pg4").label("\u5438\u5408\u4f4d\u79fb");
    model.result("pg4").setIndex("looplevel", 8, 0);
    model.result("pg4").set("frametype", "material");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("unit", "nm");
    model.result("pg4").run();

    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"es/ge1"});

    model.component("comp1").common().remove("sym1");

    model.result("pg4").run();

    model.title("\u504f\u538b\u8c10\u632f\u5668\u7684\u5438\u5408\u7535\u538b - \u4e8c\u7ef4");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u4e00\u4e2a\u9759\u7535\u9a71\u52a8\u7684 MEMS \u8c10\u632f\u5668\u3002\u8be5\u88c5\u7f6e\u7531\u5e73\u884c\u677f\u7535\u5bb9\u5668\u4e0a\u65bd\u52a0\u7684\u4ea4\u6d41 + \u76f4\u6d41\u504f\u538b\u9a71\u52a8\u3002\u5176\u4e2d\u8ba1\u7b97\u8c10\u632f\u5668\u7684\u5438\u5408\u7535\u538b\u3002");

    model.label("biased_resonator_2d_pull_in.mph");

    model.result("pg4").run();

    model.component("comp1").probe().duplicate("point2", "point1");
    model.component("comp1").probe("point2").label("\u8bbe\u5b9a\u70b9\u7684\u76f8\u5bf9\u5782\u76f4\u4f4d\u79fb");
    model.component("comp1").probe("point2").set("probename", "vset");
    model.component("comp1").probe("point2").selection().set(5);

    model.component("comp1").physics("es").feature("ge1").setIndex("equation", "vrel-vset", 0, 0);
    model.component("comp1").physics("solid").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("solid").feature("disp1").selection().set(9, 14);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "limited", 1);
    model.component("comp1").physics("solid").feature("disp1").setIndex("U0min", "-0.96*gap", 1);

    model.study("std2").label("\u5438\u5408 - \u5168\u8303\u56f4");
    model.study("std2").feature("stat").setIndex("plistarr", "range(-0.006,-0.014,-0.9)", 0);
    model.study("std2").createAutoSequences("all");

    return model;
  }

  public static Model run2(Model model) {

    model.sol("sol2").runAll();

    model.result("pg4").run();
    model.result("pg4").set("view", "new");
    model.result("pg4").run();

    model.view("view2").axis().set("viewscaletype", "automatic");

    model.result("pg4").run();
    model.result("pg4").stepLast(0);
    model.result("pg4").run();
    model.result("pg8").run();
    model.result("pg8").label("\u5438\u5408/\u91ca\u653e\u56fe");
    model.result("pg8").set("titletype", "manual");
    model.result("pg8").set("title", "\u76f8\u5bf9\u5782\u76f4\u4f4d\u79fb");
    model.result("pg8").set("showlegends", true);
    model.result("pg8").set("legendpos", "center");
    model.result("pg8").run();
    model.result("pg8").feature("glob1").set("legendmethod", "manual");
    model.result("pg8").feature("glob1").setIndex("legends", "at middle point", 0);
    model.result("pg8").feature().duplicate("glob2", "glob1");
    model.result("pg8").run();
    model.result("pg8").feature("glob2").set("expr", new String[]{"vset"});
    model.result("pg8").feature("glob2")
         .set("descr", new String[]{"\u8bbe\u5b9a\u70b9\u7684\u76f8\u5bf9\u5782\u76f4\u4f4d\u79fb"});
    model.result("pg8").feature("glob2").set("unit", new String[]{"1"});
    model.result("pg8").feature("glob2").setIndex("legends", "at set point", 0);
    model.result("pg8").run();
    model.result("pg8").create("ann1", "Annotation");
    model.result("pg8").feature("ann1").set("latexmarkup", true);
    model.result("pg8").feature("ann1").set("posxexpr", 20);
    model.result("pg8").feature("ann1").set("posyexpr", -0.15);
    model.result("pg8").feature("ann1").set("showpoint", false);
    model.result("pg8").feature("ann1").set("anchorpoint", "center");
    model.result("pg8").feature().duplicate("ann2", "ann1");
    model.result("pg8").run();
    model.result("pg8").feature("ann2").set("posyexpr", -0.75);
    model.result("pg8").feature("ann2").set("posxexpr", 55);
    model.result("pg8").run();
    model.result().table("tbl1").clearTableData();
    model.result().numerical("gev1").set("includeparam", false);
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().duplicate("gev2", "gev1");
    model.result().numerical("gev2").label("\u91ca\u653e\u7535\u538b");
    model.result().numerical("gev2").set("dataseries", "minimum");
    model.result().numerical("gev2").set("table", "tbl1");
    model.result().numerical("gev2").appendResult();
    model.result("pg4").run();

    model.title("\u504f\u538b\u8c10\u632f\u5668\u7684\u5438\u5408\u548c\u91ca\u653e\u5206\u6790 - \u4e8c\u7ef4");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u9759\u7535\u9a71\u52a8\u7684 MEMS \u8c10\u632f\u5668\uff0c\u8be5\u88c5\u7f6e\u7531\u5e73\u884c\u677f\u7535\u5bb9\u5668\u4e0a\u65bd\u52a0\u7684\u4ea4\u6d41 + \u76f4\u6d41\u504f\u538b\u9a71\u52a8\u3002\u5176\u4e2d\u8ba1\u7b97\u8c10\u632f\u5668\u7684\u5438\u5408\u548c\u91ca\u653e\u7535\u538b\uff0c\u8fd9\u662f\u901a\u8fc7\u5bf9\u6574\u4e2a\u8fd0\u52a8\u8303\u56f4\uff08\u4ece\u521d\u59cb\u677e\u5f1b\u72b6\u6001\u4e00\u76f4\u5230\u5438\u5408\u5e73\u5766\u72b6\u6001\uff09\u7684\u4f4d\u79fb-\u7535\u538b\u8f68\u8ff9\u8fdb\u884c\u51c6\u9759\u6001\u5206\u6790\u6765\u5b8c\u6210\u7684\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("biased_resonator_2d_pull_in_pull_out.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
