/*
 * viscoplastic_solder_joints.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:39 by COMSOL 6.3.0.290. */
public class viscoplastic_solder_joints {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Viscoplasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.component("comp1").multiphysics().create("te1", "ThermalExpansion", 3);
    model.component("comp1").multiphysics("te1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("te1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("te1").selection().all();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/te1", true);

    model.param().set("T0", "20[degC]");
    model.param().descr("T0", "\u521d\u59cb\u6e29\u5ea6");

    model.func().create("an1", "Analytic");
    model.func("an1").set("funcname", "power");
    model.func("an1").set("expr", "(flc2hs(x-0.1,0.1)*50)-flc2hs(x-(4.1),0.1)*40");
    model.func("an1").setIndex("argunit", "h", 0);
    model.func("an1").set("fununit", "MW/m^3");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "viscoplastic_solder_joints.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("FR4");
    model.component("comp1").selection("sel1").set(1);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u94dc");
    model.component("comp1").selection("sel2").set(2);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u7845");
    model.component("comp1").selection("sel3").set(3, 24);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u710a\u6599");
    model.component("comp1").selection("sel4").all();
    model.component("comp1").selection("sel4")
         .set(4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40);
    model.component("comp1").selection().duplicate("sel5", "sel4");
    model.component("comp1").selection("sel5").label("\u710a\u9762");
    model.component("comp1").selection("sel5").geom("geom1", 3, 2, new String[]{"exterior"});
    model.component("comp1").selection("sel5")
         .set(4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("\u5bf9\u79f0\u8fb9\u754c");
    model.component("comp1").selection("sel6").geom(2);
    model.component("comp1").selection("sel6").set(17, 19, 182, 183);
    model.component("comp1").selection().create("com1", "Complement");
    model.component("comp1").selection("com1").label("\u5bf9\u79f0\u8865\u96c6");
    model.component("comp1").selection("com1").set("entitydim", 2);
    model.component("comp1").selection("com1").set("input", new String[]{"sel6"});

    model.common("cminpt").set("modified", new String[][]{{"strainreferencetemperature", "T0"}});

    model.component("comp1").physics("solid").feature("lemm1").create("vpl1", "Viscoplasticity", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("vpl1").selection().named("sel4");
    model.component("comp1").physics("solid").feature("lemm1").set("CalculateDissipatedEnergy", true);
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().named("sel6");
    model.component("comp1").physics("solid").create("disp1", "Displacement0", 0);
    model.component("comp1").physics("solid").feature("disp1").selection().set(193);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T0");
    model.component("comp1").physics("ht").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("ht").feature("sym1").selection().named("sel6");
    model.component("comp1").physics("ht").create("hs1", "HeatSource", 3);
    model.component("comp1").physics("ht").feature("hs1").selection().named("sel3");
    model.component("comp1").physics("ht").feature("hs1").set("Q0", "power(t)");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection().named("com1");
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", 10);
    model.component("comp1").physics("ht").feature("hf1").set("Text", "T0");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat1").label("FR4 (Circuit Board)");
    model.material("mat1").set("family", "pcb");
    model.material("mat1").set("color", "custom");
    model.material("mat1").set("customcolor", "0 0.5019607843137255 0.25098039215686274");
    model.material("mat1").propertyGroup("def").label("Basic");
    model.material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0.004[S/m]", "0", "0", "0", "0.004[S/m]", "0", "0", "0", "0.004[S/m]"});
    model.material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.5", "0", "0", "0", "4.5", "0", "0", "0", "4.5"});
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"18e-6[1/K]", "0", "0", "0", "18e-6[1/K]", "0", "0", "0", "18e-6[1/K]"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", "1369[J/(kg*K)]");
    model.material("mat1").propertyGroup("def").set("density", "1900[kg/m^3]");
    model.material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.3[W/(m*K)]", "0", "0", "0", "0.3[W/(m*K)]", "0", "0", "0", "0.3[W/(m*K)]"});
    model.material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.material("mat1").propertyGroup("Enu").set("E", "22[GPa]");
    model.material("mat1").propertyGroup("Enu").set("nu", "0.15");
    model.material().create("mat2", "Common", "");
    model.material("mat2").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat2").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.material("mat2").label("Copper");
    model.material("mat2").set("family", "copper");
    model.material("mat2").propertyGroup("def").label("Basic");
    model.material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.material("mat2").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat2").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.material("mat2").propertyGroup("Enu").set("E", "110[GPa]");
    model.material("mat2").propertyGroup("Enu").set("nu", "0.35");
    model.material("mat2").propertyGroup("linzRes").label("Linearized resistivity");
    model.material("mat2").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.material("mat2").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.material("mat2").propertyGroup("linzRes").set("Tref", "298[K]");
    model.material("mat2").propertyGroup("linzRes").addInput("temperature");
    model.material().create("mat3", "Common", "");
    model.material("mat3").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat3").propertyGroup().create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.material("mat3").label("Silicon");
    model.material("mat3").set("family", "custom");
    model.material("mat3").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.material("mat3").set("diffuse", "custom");
    model.material("mat3")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.material("mat3").set("ambient", "custom");
    model.material("mat3")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.material("mat3").set("noise", true);
    model.material("mat3").set("fresnel", 0.7);
    model.material("mat3").set("roughness", 0.5);
    model.material("mat3").set("diffusewrap", 0);
    model.material("mat3").set("reflectance", 0);
    model.material("mat3").propertyGroup("def").label("Basic");
    model.material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-12[S/m]", "0", "0", "0", "1e-12[S/m]", "0", "0", "0", "1e-12[S/m]"});
    model.material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]"});
    model.material("mat3").propertyGroup("def").set("heatcapacity", "700[J/(kg*K)]");
    model.material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"11.7", "0", "0", "0", "11.7", "0", "0", "0", "11.7"});
    model.material("mat3").propertyGroup("def").set("density", "2329[kg/m^3]");
    model.material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]"});
    model.material("mat3").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.material("mat3").propertyGroup("Enu").set("E", "170[GPa]");
    model.material("mat3").propertyGroup("Enu").set("nu", "0.28");
    model.material("mat3").propertyGroup("RefractiveIndex").label("Refractive index");
    model.material("mat3").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"3.48", "0", "0", "0", "3.48", "0", "0", "0", "3.48"});
    model.material().create("mat4", "Common", "");
    model.material("mat4").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat4").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.material("mat4").propertyGroup().create("Anand", "Anand", "Anand viscoplasticity");
    model.material("mat4").label("Solder, 60Sn-40Pb");
    model.material("mat4").set("family", "custom");
    model.material("mat4").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.material("mat4").set("diffuse", "custom");
    model.material("mat4")
         .set("customdiffuse", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.material("mat4").set("ambient", "custom");
    model.material("mat4")
         .set("customambient", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.material("mat4").set("noise", true);
    model.material("mat4").set("fresnel", 0.9);
    model.material("mat4").set("roughness", 0.1);
    model.material("mat4").set("diffusewrap", 0);
    model.material("mat4").set("reflectance", 0);
    model.material("mat4").propertyGroup("def").label("Basic");
    model.material("mat4").propertyGroup("def")
         .set("electricconductivity", new String[]{"6.67e6[S/m]", "0", "0", "0", "6.67e6[S/m]", "0", "0", "0", "6.67e6[S/m]"});
    model.material("mat4").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"21e-6[1/K]", "0", "0", "0", "21e-6[1/K]", "0", "0", "0", "21e-6[1/K]"});
    model.material("mat4").propertyGroup("def").set("heatcapacity", "150[J/(kg*K)]");
    model.material("mat4").propertyGroup("def").set("density", "9000[kg/m^3]");
    model.material("mat4").propertyGroup("def")
         .set("thermalconductivity", new String[]{"50[W/(m*K)]", "0", "0", "0", "50[W/(m*K)]", "0", "0", "0", "50[W/(m*K)]"});
    model.material("mat4").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.material("mat4").propertyGroup("Enu").set("E", "10[GPa]");
    model.material("mat4").propertyGroup("Enu").set("nu", "0.4");
    model.material("mat4").propertyGroup("linzRes").label("Linearized resistivity");
    model.material("mat4").propertyGroup("linzRes").set("rho0", "4.99e-7[ohm*m]");
    model.material("mat4").propertyGroup("linzRes").addInput("temperature");
    model.material("mat4").propertyGroup("Anand").label("Anand viscoplasticity");
    model.material("mat4").propertyGroup("Anand").set("A_ana", "1.49e7[1/s]");
    model.material("mat4").propertyGroup("Anand").set("Q_ana", "90046[J/mol]");
    model.material("mat4").propertyGroup("Anand").set("xi_ana", "11");
    model.material("mat4").propertyGroup("Anand").set("m_ana", "0.303");
    model.material("mat4").propertyGroup("Anand").set("ssat_ana", "80.42[MPa]");
    model.material("mat4").propertyGroup("Anand").set("sa_init", "56.33[MPa]");
    model.material("mat4").propertyGroup("Anand").set("h0_ana", "2640.75[MPa]");
    model.material("mat4").propertyGroup("Anand").set("a_ana", "1.34");
    model.material("mat4").propertyGroup("Anand").set("n_ana", "0.0231");
    model.component("comp1").material().create("matlnk1", "Link");
    model.component("comp1").material("matlnk1").selection().named("sel1");
    model.component("comp1").material("matlnk1").set("family", "pcb");

    model.component("comp1").view("view1").set("showmaterial", true);

    model.component("comp1").material().create("matlnk2", "Link");
    model.component("comp1").material("matlnk2").selection().named("sel2");
    model.component("comp1").material("matlnk2").set("link", "mat2");
    model.component("comp1").material("matlnk2").set("family", "copper");
    model.component("comp1").material().create("matlnk3", "Link");
    model.component("comp1").material("matlnk3").selection().named("sel3");
    model.component("comp1").material("matlnk3").set("link", "mat3");
    model.component("comp1").material("matlnk3").set("color", "black");
    model.component("comp1").material().create("matlnk4", "Link");
    model.component("comp1").material("matlnk4").selection().named("sel4");
    model.component("comp1").material("matlnk4").set("link", "mat4");
    model.component("comp1").material("matlnk4").set("family", "steel");

    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().named("sel4");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 4);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(7);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("sel2");
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("ftet2", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.study("std1").feature("time").set("tunit", "h");
    model.study("std1").feature("time")
         .set("tlist", "0 0.005 range(0.025,0.025,0.5) range(0.75,0.25,3.75) 3.975 4+{range(0,0.025,0.5) range(0.75,0.25,2)}");
    model.study("std1").feature("time").setSolveFor("/physics/solid", false);
    model.study("std1").create("time2", "Transient");
    model.study("std1").feature("time2").set("tunit", "h");
    model.study("std1").feature("time2")
         .set("tlist", "0 0.005 range(0.025,0.025,0.5) range(0.75,0.25,3.75) 3.975 4+{range(0,0.025,0.5) range(0.75,0.25,2)}");
    model.study("std1").feature("time2").setSolveFor("/physics/ht", false);
    model.study("std1").feature("time2").set("usesol", true);
    model.study("std1").feature("time2").set("notsolmethod", "sol");
    model.study("std1").feature("time2").set("notstudy", "std1");
    model.study("std1").feature("time2").set("notsolnum", "all");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("storeudot", false);
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").feature("v2").feature("comp1_solid_Wvp").set("scalemethod", "manual");
    model.sol("sol1").feature("v2").feature("comp1_solid_Wvp").set("scaleval", "1e5");
    model.sol("sol1").feature("t2").set("storeudot", false);
    model.sol("sol1").feature("t2").set("tstepsbdf", "strict");
    model.sol("sol1").feature("t2").set("estrat", "exclude");
    model.sol("sol1").feature("t2").create("se1", "Segregated");
    model.sol("sol1").feature("t2").feature("se1").set("segterm", "iter");
    model.sol("sol1").feature("t2").feature("se1").feature("ssDef").label("\u4f4d\u79fb\u573a");
    model.sol("sol1").feature("t2").feature("se1").feature("ssDef")
         .set("segvar", new String[]{"comp1_T", "comp1_u"});
    model.sol("sol1").feature("t2").feature("se1").feature("ssDef").set("subtermconst", "tol");
    model.sol("sol1").feature("t2").feature("se1").feature("ssDef").set("subntolfact", 1);
    model.sol("sol1").feature("t2").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol1").feature("t2").feature("se1").feature("ss1").label("\u80fd\u8017");
    model.sol("sol1").feature("t2").feature("se1").feature("ss1").set("subtermconst", "tol");
    model.sol("sol1").feature("t2").feature("se1").feature("ss1").set("subntolfact", 1);
    model.sol("sol1").feature("t2").feature("se1").feature("ss1").set("segvar", new String[]{"comp1_solid_Wvp"});

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 63, 0);
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
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u6e29\u5ea6 (ht)");
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("solutionparams", "parent");
    model.result("pg2").feature("vol1").set("expr", "T");
    model.result("pg2").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("vol1").set("smooth", "internal");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 24, 0);
    model.result("pg2").run();
    model.result("pg2").feature("vol1").set("unit", "degC");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u53d8\u5f62\u963b\u529b\u5386\u53f2");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").create("ptgr1", "PointGraph");
    model.result("pg3").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr1").set("linewidth", "preference");
    model.result("pg3").feature("ptgr1").selection().set(36);
    model.result("pg3").feature("ptgr1").set("expr", "solid.saGp");
    model.result("pg3").feature("ptgr1").set("unit", "MPa");
    model.result("pg3").feature("ptgr1").set("linewidth", 2);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u5e94\u53d8\u5386\u53f2");
    model.result("pg4").create("ptgr1", "PointGraph");
    model.result("pg4").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr1").set("linewidth", "preference");
    model.result("pg4").feature("ptgr1").selection().set(36);
    model.result("pg4").feature("ptgr1").set("expr", "solid.gpeval(solid.el13)");
    model.result("pg4").feature("ptgr1").set("linewidth", 2);
    model.result("pg4").feature("ptgr1").set("legend", true);
    model.result("pg4").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg4").feature("ptgr1").setIndex("legends", "\u603b\u5e94\u53d8", 0);
    model.result("pg4").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr2").set("expr", "solid.gpeval(solid.evpl13)");
    model.result("pg4").feature("ptgr2").setIndex("legends", "\u9ecf\u5851\u6027\u5e94\u53d8", 0);
    model.result("pg4").feature().duplicate("ptgr3", "ptgr2");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr3").set("expr", "solid.gpeval(solid.el13-solid.evpl13)");
    model.result("pg4").feature("ptgr3").setIndex("legends", "\u5f39\u6027\u5e94\u53d8", 0);
    model.result("pg4").run();
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u526a\u5207\u5e94\u53d8\uff0cxz \u5206\u91cf");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("legendlayout", "outside");
    model.result("pg4").set("legendposoutside", "bottom");
    model.result("pg4").run();
    model.result("pg3").run();
    model.result().duplicate("pg5", "pg3");
    model.result("pg5").run();
    model.result("pg5").label("\u8017\u6563\u5386\u53f2");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr1").set("expr", "solid.WvpGp");
    model.result("pg5").feature("ptgr1").set("descr", "\u9ecf\u5851\u6027\u8017\u6563\u80fd\u5bc6\u5ea6");
    model.result("pg5").feature("ptgr1").set("unit", "kJ/m^3");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u6e29\u5ea6\u5386\u53f2");
    model.result("pg6").run();
    model.result("pg6").feature("ptgr1").set("expr", "T");
    model.result("pg6").feature("ptgr1").set("descr", "\u6e29\u5ea6");
    model.result("pg6").feature("ptgr1").set("unit", "degC");
    model.result("pg6").run();
    model.result("pg5").run();

    model.title("\u710a\u70b9\u7684\u9ecf\u5851\u6027\u8815\u53d8");

    model
         .description("\u672c\u4f8b\u901a\u8fc7 Anand \u9ecf\u5851\u6027\u6a21\u578b\u7814\u7a76\u4e86\u710a\u70b9\u53d7\u70ed\u8f7d\u8377\u4f5c\u7528\u65f6\u7684\u9ecf\u5851\u6027\u8815\u53d8\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("viscoplastic_solder_joints.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
