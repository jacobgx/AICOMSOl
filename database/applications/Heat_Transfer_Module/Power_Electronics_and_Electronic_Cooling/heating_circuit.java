/*
 * heating_circuit.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:27 by COMSOL 6.3.0.290. */
public class heating_circuit {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Power_Electronics_and_Electronic_Cooling");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");
    model.component("comp1").physics().create("ecis", "ElectricCurrentsShell", "geom1");
    model.component("comp1").physics("ecis").prop("DefaultDescription")
         .set("DefaultDescription", "Electric_currents_in_layered_shells");
    model.component("comp1").physics("ecis").prop("LayerSelection").set("lth_mat", "from_mat");

    model.component("comp1").multiphysics().create("te1", "ThermalExpansion", 3);
    model.component("comp1").multiphysics("te1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("te1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("te1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ecis", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/te1", true);

    model.param().set("V_in", "12[V]");
    model.param().descr("V_in", "\u8f93\u5165\u7535\u538b");
    model.param().set("d_layer", "10[um]");
    model.param().descr("d_layer", "\u5c42\u539a\u5ea6");
    model.param().set("sigma_silver", "6.3e7[S/m]");
    model.param().descr("sigma_silver", "\u94f6\u7684\u7535\u5bfc\u7387");
    model.param().set("sigma_nichrome", "9.3e5[S/m]");
    model.param().descr("sigma_nichrome", "\u954d\u94ec\u5408\u91d1\u7684\u7535\u5bfc\u7387");
    model.param().set("T_air", "20[degC]");
    model.param().descr("T_air", "\u7a7a\u6c14\u6e29\u5ea6");
    model.param().set("h_air", "5[W/(m^2*K)]");
    model.param().descr("h_air", "\u4f20\u70ed\u819c\u7cfb\u6570\uff0c\u7a7a\u6c14");
    model.param().set("T_fluid", "353[K]");
    model.param().descr("T_fluid", "\u6d41\u4f53\u6e29\u5ea6");
    model.param().set("h_fluid", "20[W/(m^2*K)]");
    model.param().descr("h_fluid", "\u4f20\u70ed\u819c\u7cfb\u6570\uff0c\u6d41\u4f53");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{80, 130, 2});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("size", 10);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("pos", new int[]{7, 10});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("sq2", "sq1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq2").set("pos", new int[]{30, 8});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "file");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .set("filename", "heating_circuit_polygon.txt");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("pointinsketch")
         .set("pol1", 2, 3, 4, 5, 6, 7, 8, 23, 24, 25, 26, 27, 28, 29, 34, 36, 37, 41, 42);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", 10);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil2", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil2").selection("pointinsketch")
         .set("fil1", 6, 7, 8, 9, 10, 11, 12, 26, 27, 28, 29, 30, 31, 37, 40, 43, 46, 49, 50);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil2").set("radius", 5);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil2");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u7535\u8def");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(6, 7, 8);

    model.component("comp1").physics("ht").create("sls1", "SolidLayeredShell", 2);
    model.component("comp1").physics("ht").feature("sls1").selection().named("sel1");
    model.component("comp1").physics("ht").feature("sls1").set("LayerType", "Conductive");
    model.component("comp1").physics("ecis").selection().named("sel1");
    model.component("comp1").physics("ecis").feature("csh1")
         .set("epsilonr_crel_DE_RelativePermittivity_mat", "userdef");
    model.component("comp1").physics("solid").create("tl1", "ThinLayer", 2);
    model.component("comp1").physics("solid").feature("tl1").selection().named("sel1");
    model.component("comp1").physics("solid").feature("tl1").set("lth_mat", "from_mat");

    model.component("comp1").multiphysics().create("tetl1", "ThermalExpansionTL", 2);
    model.component("comp1").multiphysics("tetl1").set("bndType", "allShell");
    model.component("comp1").multiphysics("tetl1").set("lth_mat", "from_mat");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat1").label("Silica glass");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.99);
    model.component("comp1").material("mat1").set("roughness", 0.02);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-14[S/m]", "0", "0", "0", "1e-14[S/m]", "0", "0", "0", "1e-14[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"0.55e-6[1/K]", "0", "0", "0", "0.55e-6[1/K]", "0", "0", "0", "0.55e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "703[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"3.75", "0", "0", "0", "3.75", "0", "0", "0", "3.75"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2203[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.38[W/(m*K)]", "0", "0", "0", "1.38[W/(m*K)]", "0", "0", "0", "1.38[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "73.1[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.17");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1.45", "0", "0", "0", "1.45", "0", "0", "0", "1.45"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().geom("geom1", 2);
    model.component("comp1").material("mat2").propertyGroup().create("shell", "shell", "Layered_material");
    model.component("comp1").material("mat2").propertyGroup("shell").set("lth", "1e-4[m]");
    model.component("comp1").material("mat2").selection().geom("geom1", 2);
    model.component("comp1").material("mat2").label("\u94f6\u5c42");
    model.component("comp1").material("mat2").selection().named("sel1");
    model.component("comp1").material("mat2").set("middlePlane", "bottom");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("heatcapacity", new String[]{"230[J/(kg*K)]"});
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", new String[]{"83e9[Pa]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", new String[]{"0.37"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"10500[kg/m^3]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"420[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"sigma_silver"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"18.9e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("shell").set("lth", new String[]{"d_layer"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").selection().geom("geom1", 2);
    model.component("comp1").material("mat3").propertyGroup().create("shell", "shell", "Layered_material");
    model.component("comp1").material("mat3").propertyGroup("shell").set("lth", "1e-4[m]");
    model.component("comp1").material("mat3").selection().geom("geom1", 2);
    model.component("comp1").material("mat3").selection().set(7);
    model.component("comp1").material("mat3").label("\u954d\u94ec\u5408\u91d1\u5c42");
    model.component("comp1").material("mat3").set("middlePlane", "bottom");
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", new String[]{"20[J/(kg*K)]"});
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", new String[]{"213e9[Pa]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", new String[]{"0.33"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"9000[kg/m^3]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"15[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"sigma_nichrome"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"10e-6[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("shell").set("lth", new String[]{"d_layer"});

    model.component("comp1").physics("ecis").create("bgnd1", "BoundaryGround", 1);
    model.component("comp1").physics("ecis").feature("bgnd1").selection().set(43);
    model.component("comp1").physics("ecis").create("bpot1", "BoundaryElectricPotential", 1);
    model.component("comp1").physics("ecis").feature("bpot1").selection().set(10);
    model.component("comp1").physics("ecis").feature("bpot1").set("V0", "V_in");
    model.component("comp1").physics("ecis").create("cls1", "ContinuityLayeredShell", 1);
    model.component("comp1").physics("ecis").feature("cls1").set("shelllist_src", "mat2");
    model.component("comp1").physics("ecis").feature("cls1").set("shelllist", "mat3");

    model.component("comp1").multiphysics().create("ehls1", "ElectromagneticHeatingLayeredShell", 2);
    model.component("comp1").multiphysics("ehls1").set("bndType", "allShell");

    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection().set(4, 6, 7, 8);
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "h_air");
    model.component("comp1").physics("ht").feature("hf1").set("Text", "T_air");
    model.component("comp1").physics("ht").create("hf2", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf2").selection().set(3);
    model.component("comp1").physics("ht").feature("hf2").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf2").set("h", "h_fluid");
    model.component("comp1").physics("ht").feature("hf2").set("Text", "T_fluid");
    model.component("comp1").physics("solid").create("rms1", "RigidMotionSuppression", 3);
    model.component("comp1").physics("solid").create("wrp1", "Warpage", 2);
    model.component("comp1").physics("solid").feature("wrp1").selection().set(3);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(4, 6, 7, 8);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", 2);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-3");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
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
    model.result().dataset().create("dset1solidlshl_tl", "LayeredMaterial");
    model.result().dataset("dset1solidlshl_tl").set("data", "dset1");
    model.result().dataset("dset1solidlshl_tl").label("\u8584\u5c42");
    model.result().dataset("dset1solidlshl_tl").set("data", "dset1");
    model.result().dataset("dset1solidlshl_tl").set("scale", 1);
    model.result().dataset("dset1solidlshl_tl").selection().geom(2);
    model.result().dataset("dset1solidlshl_tl").selection().set(6, 7, 8);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").label("\u5e94\u529b\uff0c\u8584\u5c42 (solid)");
    model.result("pg2").create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg2").feature("vol1").set("threshold", "manual");
    model.result("pg2").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("vol1").set("colortable", "Rainbow");
    model.result("pg2").feature("vol1").set("colortabletrans", "none");
    model.result("pg2").feature("vol1").set("colorscalemode", "linear");
    model.result("pg2").feature("vol1").set("data", "dset1solidlshl_tl");
    model.result("pg2").feature("vol1").set("solutionparams", "parent");
    model.result("pg2").feature("vol1").set("colortable", "Prism");
    model.result("pg2").feature("vol1").set("resolution", "custom");
    model.result("pg2").feature("vol1").set("refine", 2);
    model.result("pg2").feature("vol1").create("def", "Deform");
    model.result("pg2").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().dataset("dset1solidlshl_tl").set("data", "dset1");
    model.result().dataset("dset1solidlshl_tl").selection().geom("geom1", 2);
    model.result().dataset("dset1solidlshl_tl").selection().set(6, 7, 8);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u6e29\u5ea6 (ht)");
    model.result("pg3").selection().geom("geom1", 3);
    model.result("pg3").selection().set(1);
    model.result("pg3").feature().create("vol1", "Volume");
    model.result("pg3").feature("vol1").label("\u57df");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("solutionparams", "parent");
    model.result("pg3").feature("vol1").set("expr", "T");
    model.result("pg3").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("vol1").set("smooth", "internal");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("data", "parent");
    model.result("pg3").feature().create("vol2", "Volume");
    model.result("pg3").feature("vol2").label("\u591a\u5c42\u58f3");
    model.result("pg3").feature("vol2").set("data", "dset1solidlshl_tl");
    model.result("pg3").feature("vol2").set("showsolutionparams", "on");
    model.result("pg3").feature("vol2").set("solutionparams", "parent");
    model.result("pg3").feature("vol2").set("expr", "T");
    model.result("pg3").feature("vol2").set("titletype", "none");
    model.result("pg3").feature("vol2").set("smooth", "internal");
    model.result("pg3").feature("vol2").set("showsolutionparams", "on");
    model.result("pg3").feature("vol2").set("data", "dset1solidlshl_tl");
    model.result("pg3").feature("vol2").set("inheritplot", "vol1");
    model.result("pg3").feature().create("line1", "Line");
    model.result("pg3").feature("line1").label("\u591a\u5c42\u58f3\u8fb9");
    model.result("pg3").feature("line1").set("data", "dset1solidlshl_tl");
    model.result("pg3").feature("line1").set("showsolutionparams", "on");
    model.result("pg3").feature("line1").set("solutionparams", "parent");
    model.result("pg3").feature("line1").set("expr", "1");
    model.result("pg3").feature("line1").set("titletype", "none");
    model.result("pg3").feature("line1").set("coloring", "uniform");
    model.result("pg3").feature("line1").set("color", "fromtheme");
    model.result("pg3").feature("line1").set("smooth", "internal");
    model.result("pg3").feature("line1").set("showsolutionparams", "on");
    model.result("pg3").feature("line1").set("data", "dset1solidlshl_tl");
    model.result().dataset().create("lshl1", "LayeredMaterial");
    model.result().dataset("lshl1").set("data", "dset1");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u7535\u52bf (ecis)");
    model.result("pg4").set("data", "lshl1");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("expr", "V");
    model.result("pg4").feature("surf1").set("colortable", "Dipole");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("unit", "MPa");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").feature("vol1").set("unit", "MPa");
    model.result("pg2").run();
    model.result("pg3").run();
    model.result().dataset().duplicate("dset2", "dset1");
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().named("sel1");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("\u8868\u9762\u635f\u8017");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "ecis.Qsh");
    model.result("pg5").feature("surf1").set("descr", "\u8868\u9762\u635f\u8017\u5bc6\u5ea6\uff0c\u7535\u78c1");
    model.result("pg5").run();

    model.component("comp1").view("view1").set("scenelight", false);

    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").label("\u754c\u9762\u5e94\u529b");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "sqrt(solid.Tax^2+solid.Tay^2)");
    model.result("pg6").feature("surf1").set("unit", "MPa");
    model.result("pg6").feature("surf1").set("colortable", "Prism");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").label("\u7fd8\u66f2 (wrp1)");
    model.result("pg7").feature().create("surf1", "Surface");
    model.result("pg7").feature("surf1").label("\u7fd8\u66f2\u4f4d\u79fb");
    model.result("pg7").feature("surf1").set("expr", "solid.wrp1.dispn_warp");
    model.result("pg7").feature("surf1").set("colortable", "WaveLight");
    model.result("pg7").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg7").feature("surf1").set("smooth", "internal");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("data", "parent");
    model.result("pg7").feature("surf1").feature().create("def1", "Deform");
    model.result("pg7").feature("surf1").feature("def1")
         .set("expr", new String[]{"solid.wrp1.u", "solid.wrp1.v", "solid.wrp1.w"});
    model.result("pg7").feature("surf1").feature().create("mrkr1", "Marker");
    model.result("pg7").feature().create("surf2", "Surface");
    model.result("pg7").feature("surf2").label("\u5e73\u5747\u4f4d\u79fb");
    model.result("pg7").feature("surf2").set("expr", "solid.wrp1.disp_avg");
    model.result("pg7").feature("surf2").set("coloring", "uniform");
    model.result("pg7").feature("surf2").set("color", "gray");
    model.result("pg7").feature("surf2").set("smooth", "internal");
    model.result("pg7").feature("surf2").set("inheritcolor", false);
    model.result("pg7").feature("surf2").set("inheritrange", false);
    model.result("pg7").feature("surf2").set("showsolutionparams", "on");
    model.result("pg7").feature("surf2").set("data", "parent");
    model.result("pg7").feature("surf2").set("inheritplot", "surf1");
    model.result("pg7").feature("surf2").feature().create("def1", "Deform");
    model.result("pg7").feature("surf2").feature("def1")
         .set("expr", new String[]{"solid.wrp1.u_avg", "solid.wrp1.v_avg", "solid.wrp1.w_avg"});
    model.result("pg7").label("\u7fd8\u66f2 (wrp1)");
    model.result("pg7").run();
    model.result().numerical().create("int1", "IntSurface");
    model.result().numerical("int1").set("intvolume", true);
    model.result().numerical("int1").selection().set(3);
    model.result().numerical("int1").set("expr", new String[]{"ht.q0"});
    model.result().numerical("int1").set("descr", new String[]{"\u5411\u5185\u70ed\u901a\u91cf"});
    model.result().numerical("int1").set("unit", new String[]{"W"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u8868\u9762\u79ef\u5206 1");
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").setResult();
    model.result().numerical().create("int2", "IntSurface");
    model.result().numerical("int2").set("intvolume", true);
    model.result().numerical("int2").selection().named("sel1");
    model.result().numerical("int2").set("expr", new String[]{"ecis.Qsh"});
    model.result().numerical("int2")
         .set("descr", new String[]{"\u8868\u9762\u635f\u8017\u5bc6\u5ea6\uff0c\u7535\u78c1"});
    model.result().numerical("int2").set("unit", new String[]{"W"});
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u8868\u9762\u79ef\u5206 2");
    model.result().numerical("int2").set("table", "tbl2");
    model.result().numerical("int2").setResult();
    model.result("pg1").run();

    model.title("\u52a0\u70ed\u7535\u8def");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u52a0\u70ed\u7535\u8def\u7684\u7a33\u6001\u6a21\u578b\uff0c\u5bf9\u5b9e\u5fc3\u73bb\u7483\u677f\u4e0a\u7684\u8584\u5bfc\u7535\u5c42\u8fdb\u884c\u76f4\u6d41\u611f\u5e94\u7126\u8033\u70ed\u3001\u4f20\u70ed\u548c\u7ed3\u6784\u529b\u5b66\u5206\u6790\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("heating_circuit.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
