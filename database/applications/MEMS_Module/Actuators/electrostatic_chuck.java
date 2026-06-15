/*
 * electrostatic_chuck.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:53 by COMSOL 6.3.0.290. */
public class electrostatic_chuck {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Actuators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("Compressibility", "Incompressible");
    model.component("comp1").physics("spf").field("velocity").field("u_fluid");
    model.component("comp1").physics("spf").field("velocity")
         .component(new String[]{"u_fluid", "v_fluid", "w_fluid"});
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").field("displacement").field("u_solid");
    model.component("comp1").physics("solid").field("displacement")
         .component(new String[]{"u_solid", "v_solid", "w_solid"});
    model.component("comp1").physics("solid").prop("ShapeProperty").set("order_displacement", "2");
    model.component("comp1").physics().create("ht", "HeatTransferInSolidsAndFluids", "geom1");
    model.component("comp1").physics().create("es", "Electrostatics", "geom1");

    model.component("comp1").multiphysics().create("fsi1", "FluidStructureInteractionBC", 1);
    model.component("comp1").multiphysics("fsi1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("fsi1").set("Structure_physics", "solid");
    model.component("comp1").multiphysics("fsi1").selection().all();
    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 2);
    model.component("comp1").multiphysics("nitf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nitf1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics().create("te1", "ThermalExpansion", 2);
    model.component("comp1").multiphysics("te1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("te1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("te1").selection().all();
    model.component("comp1").multiphysics("te1").set("IncludeTed", "0");

    model.component("comp1").common().create("free1", "DeformingDomain");
    model.component("comp1").common("free1").set("smoothingType", "yeoh");
    model.component("comp1").common("free1").selection().set();

    model.component("comp1").geom("geom1").run();

    model.component("comp1").multiphysics().create("eme1", "ElectromechanicalForces", 2);

    model.param().label("\u51e0\u4f55\u5f62\u72b6");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("d_wafer", "100[mm]", "\u6676\u5706\u76f4\u5f84");
    model.param().set("t_wafer", "0.5[mm]", "\u6676\u5706\u539a\u5ea6");
    model.param().set("r_electrode", "45[mm]", "\u7535\u6781\u534a\u5f84");
    model.param().set("t_insulator", "0.6[mm]", "\u7edd\u7f18\u4f53\u539a\u5ea6");
    model.param().set("gap", "40[um]", "\u6676\u5706\u4e0e\u5438\u76d8\u4e4b\u95f4\u7684\u95f4\u9699");
    model.param().set("d_pin", "1[mm]", "\u9876\u9488\u5bbd\u5ea6");
    model.param().set("x_pin", "49[mm]", "\u9876\u9488\u4f4d\u7f6e");
    model.param().set("d_inlet", "4[mm]", "\u5165\u53e3\u76f4\u5f84");
    model.param().set("w_outlet", "2[mm]", "\u51fa\u53e3\u5bbd\u5ea6");
    model.param().set("x_outlet", "30[mm]", "\u51fa\u53e3\u4f4d\u7f6e");
    model.param().set("L_inlet", "1[mm]", "\u5165\u53e3\u957f\u5ea6");
    model.param().set("L_outlet", "1[mm]", "\u51fa\u53e3\u957f\u5ea6");
    model.param().set("x_gap3", "48[mm]", "gap_3 \u4f4d\u7f6e");
    model.param().set("w_gap3", "0.25[mm]", "gap_3 \u5bbd\u5ea6");
    model.param().create("par2");
    model.param("par2").label("\u7f51\u683c");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("mx_inlet", "20[1]", "\u5165\u53e3\u7f51\u683c\u56e0\u5b50\uff0cx \u65b9\u5411");
    model.param("par2").set("my_channel", "5[1]", "\u901a\u9053\u7f51\u683c\u56e0\u5b50\uff0cx \u65b9\u5411");
    model.param("par2").set("mx_outlet", "20[1]", "\u51fa\u53e3\u7f51\u683c\u56e0\u5b50\uff0cx \u65b9\u5411");
    model.param("par2").set("my_inlet", "10[1]", "\u5165\u53e3\u7f51\u683c\u56e0\u5b50\uff0cy \u65b9\u5411");
    model.param("par2").set("my_outlet", "10[1]", "\u51fa\u53e3\u7f51\u683c\u56e0\u5b50\uff0cy \u65b9\u5411");
    model.param("par2")
         .set("my_insulator", "5[1]", "\u7edd\u7f18\u4f53\u7f51\u683c\u56e0\u5b50\uff0cy \u65b9\u5411");
    model.param("par2").set("mx_pin", "2[1]", "\u9876\u9488\u7f51\u683c\u56e0\u5b50\uff0cx \u65b9\u5411");
    model.param("par2").set("my_wafer", "8[1]", "\u6676\u5706\u7f51\u683c\u56e0\u5b50\uff0cy \u65b9\u5411");
    model.param("par2").set("mfx", "8[1]", "x \u65b9\u5411\u7684\u7f51\u683c\u56e0\u5b50");
    model.param().create("par3");
    model.param("par3").label("\u5de5\u827a");

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3").set("mass_He", "0.004[kg/mol]", "\u6c26\u7684\u6469\u5c14\u8d28\u91cf");
    model.param("par3").set("Q_plasma", "0[W/m^2]", "\u7b49\u79bb\u5b50\u4f53\u70ed\u901a\u91cf");
    model.param("par3").set("flow", "0[1]", "SCCM \u4e2d\u7684\u8d28\u91cf\u6d41");
    model.param("par3").set("zsp", "1[um]", "\u8bbe\u5b9a\u70b9\u7684 Z \u5750\u6807");
    model.param("par3").set("T_chuck", "273[K]", "\u5438\u76d8\u6e29\u5ea6");
    model.param("par3").set("T_wafer_init", "273[K]", "\u521d\u59cb\u6676\u5706\u6e29\u5ea6");
    model.param("par3").set("v_app", "0[V]", "\u5916\u52a0\u7535\u538b");
    model.param("par3").set("v_inlet", "0[m/s]", "\u5165\u53e3\u5904\u7684\u901f\u5ea6");
    model.param("par3").set("t_start", "1[s]", "\u8109\u51b2\u542f\u52a8\u65f6\u95f4");
    model.param("par3").set("t_pulse", "4[s]", "\u8109\u51b2\u6301\u7eed\u65f6\u95f4");

    model.component("comp1").func().create("rect1", "Rectangle");
    model.component("comp1").func("rect1").set("lower", "t_start");
    model.component("comp1").func("rect1").set("upper", "t_start+t_pulse");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("Qt", "Q_plasma*rect1(t)");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").label("\u6676\u5706");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"d_wafer/2", "t_wafer"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "gap"});
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u9009\u62e9\uff1a\u6c34");
    model.component("comp1").geom("geom1").feature("r1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").label("\u95f4\u9699 1");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"x_outlet", "gap"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").label("\u95f4\u9699 2");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"x_gap3-x_outlet", "1"});
    model.component("comp1").geom("geom1").feature("r3").setIndex("size", "gap", 1);
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"x_outlet", "0"});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").label("\u95f4\u9699 3");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"w_gap3", "gap"});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"x_gap3", "0"});
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").label("\u9500");
    model.component("comp1").geom("geom1").feature("r5").set("size", new String[]{"d_pin", "gap"});
    model.component("comp1").geom("geom1").feature("r5").set("pos", new String[]{"x_gap3+w_gap3", "0"});
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u7edd\u7f18\u4f53");
    model.component("comp1").geom("geom1").feature("r5").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("r5");
    model.component("comp1").geom("geom1").create("r6", "Rectangle");
    model.component("comp1").geom("geom1").feature("r6").label("\u7edd\u7f18\u4f53 1");
    model.component("comp1").geom("geom1").feature("r6").set("size", new String[]{"x_outlet-d_inlet/2", "1"});
    model.component("comp1").geom("geom1").feature("r6").setIndex("size", "t_insulator", 1);
    model.component("comp1").geom("geom1").feature("r6").set("pos", new String[]{"d_inlet/2", "-t_insulator"});
    model.component("comp1").geom("geom1").feature("r6").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("r6");
    model.component("comp1").geom("geom1").create("r7", "Rectangle");
    model.component("comp1").geom("geom1").feature("r7").label("\u7edd\u7f18\u4f53 2");
    model.component("comp1").geom("geom1").feature("r7")
         .set("size", new String[]{"x_gap3+w_gap3+d_pin-x_outlet-w_outlet", "1"});
    model.component("comp1").geom("geom1").feature("r7").setIndex("size", "t_insulator", 1);
    model.component("comp1").geom("geom1").feature("r7").set("pos", new String[]{"x_outlet+w_outlet", "0"});
    model.component("comp1").geom("geom1").feature("r7").setIndex("pos", "-t_insulator", 1);
    model.component("comp1").geom("geom1").feature("r7").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("r7");
    model.component("comp1").geom("geom1").create("r8", "Rectangle");
    model.component("comp1").geom("geom1").feature("r8").label("\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("r8")
         .set("size", new String[]{"d_inlet/2", "L_inlet+t_insulator"});
    model.component("comp1").geom("geom1").feature("r8").set("pos", new String[]{"0", "-L_inlet-t_insulator+gap"});
    model.component("comp1").geom("geom1").run("r8");
    model.component("comp1").geom("geom1").create("r9", "Rectangle");
    model.component("comp1").geom("geom1").feature("r9").label("\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("r9")
         .set("size", new String[]{"w_outlet", "L_outlet+t_insulator"});
    model.component("comp1").geom("geom1").feature("r9")
         .set("pos", new String[]{"x_outlet", "-L_inlet-t_insulator+gap"});
    model.component("comp1").geom("geom1").run("r9");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input")
         .set("r1", "r2", "r3", "r4", "r8", "r9");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").set("r5", "r6", "r7");
    model.component("comp1").geom("geom1").run("uni2");
    model.component("comp1").geom("geom1").create("pard1", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("pard1").set("partitionwith", "extendededges");
    model.component("comp1").geom("geom1").feature("pard1").selection("extendededge").set("uni2", 2);
    model.component("comp1").geom("geom1").feature("pard1").selection("domain").set("uni1", 1);
    model.component("comp1").geom("geom1").run("pard1");
    model.component("comp1").geom("geom1").create("pard2", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("pard2").selection("domain").set("pard1", 6);
    model.component("comp1").geom("geom1").feature("pard2").set("partitionwith", "extendededges");
    model.component("comp1").geom("geom1").feature("pard2").selection("extendededge").set("uni2", 2);
    model.component("comp1").geom("geom1").run("pard2");
    model.component("comp1").geom("geom1").create("pard3", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("pard3").selection("domain").set("uni2", 2);
    model.component("comp1").geom("geom1").feature("pard3").set("partitionwith", "extendededges");
    model.component("comp1").geom("geom1").feature("pard3").selection("extendededge").set("pard2", 27);
    model.component("comp1").geom("geom1").run("pard3");
    model.component("comp1").geom("geom1").create("pard4", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("pard4").selection("domain").set("pard2", 4);
    model.component("comp1").geom("geom1").feature("pard4").set("partitionwith", "extendededges");
    model.component("comp1").geom("geom1").feature("pard4").selection("extendededge").set("pard3", 8, 5);
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("createpairs", false);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").axis().set("viewscaletype", "manual");
    model.component("comp1").view("view1").axis().set("yscale", 20);
    model.component("comp1").view("view1").set("showlabels", true);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(17);
    model.component("comp1").cpl("intop1").set("axisym", false);

    model.component("comp1").pair().create("p1", "Contact");
    model.component("comp1").pair("p1").source().set(47, 49, 51);
    model.component("comp1").pair("p1").destination().set(32, 35);
    model.component("comp1").pair("p1").mapping("initial");
    model.component("comp1").pair().create("p2", "Contact");
    model.component("comp1").pair("p2").source().set(37, 39, 40);
    model.component("comp1").pair("p2").destination().set(8, 14, 21);
    model.component("comp1").pair("p2").mapping("initial");
    model.component("comp1").pair().create("p3", "Identity");
    model.component("comp1").pair("p3").source().set(37, 39, 40, 41, 43, 46, 47, 49);
    model.component("comp1").pair("p3").destination().set(13, 25, 30, 34, 35);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u6d41\u4f53");
    model.component("comp1").selection("sel1").set(1, 2, 3, 5, 6, 7, 8, 9, 11);
    model.component("comp1").selection().create("com1", "Complement");
    model.component("comp1").selection("com1").label("\u56fa\u4f53");
    model.component("comp1").selection("com1").set("input", new String[]{"sel1"});

    model.component("comp1").common("free1").selection().set(3, 5, 8, 9, 11);
    model.component("comp1").common("free1").set("smoothingType", "hyperelastic");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("Al2O3 - Aluminum oxide");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"6.5e-6[1/K]", "0", "0", "0", "6.5e-6[1/K]", "0", "0", "0", "6.5e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "730[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"5.7", "0", "0", "0", "5.7", "0", "0", "0", "5.7"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "3965[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"35[W/(m*K)]", "0", "0", "0", "35[W/(m*K)]", "0", "0", "0", "35[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "400e9[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.22");
    model.component("comp1").material("mat1").selection().named("geom1_csel2_dom");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").label("Si - Silicon (single-crystal, isotropic)");
    model.component("comp1").material("mat2").set("family", "custom");
    model.component("comp1").material("mat2").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat2").set("diffuse", "custom");
    model.component("comp1").material("mat2")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat2").set("ambient", "custom");
    model.component("comp1").material("mat2")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat2").set("noise", true);
    model.component("comp1").material("mat2").set("fresnel", 0.7);
    model.component("comp1").material("mat2").set("roughness", 0.5);
    model.component("comp1").material("mat2").set("diffusewrap", 0);
    model.component("comp1").material("mat2").set("reflectance", 0);
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "700[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"11.7", "0", "0", "0", "11.7", "0", "0", "0", "11.7"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "2329[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]", "0", "0", "0", "130[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "170e9[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material("mat2").selection().named("geom1_csel1_dom");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat3").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat3").label("Helium");
    model.component("comp1").material("mat3").set("family", "air");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"144.0", "900.0", "3.7251756E-6+6.83450863E-8*T^1-5.07299333E-11*T^2+2.36688744E-14*T^3"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"144.0", "900.0", "5200.0"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("expr", "pA*0.004003/R_const[K*mol/J]/T");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").set("dermethod", "manual");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("argders", new String[][]{{"pA", "d(pA*0.004003/R_const/T,pA)"}, {"T", "d(pA*0.004003/R_const/T,T)"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat3").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"144.0", "900.0", "0.0294900023+5.07655059E-4*T^1-4.22501605E-7*T^2+2.1209438E-10*T^3"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat3").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat3").propertyGroup("def").set("ratioofspecificheat", "1.66");
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat3").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat3").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"144.0", "900.0", "5200.0"}});
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat3").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("ratioofspecificheat", "1.6667");
    model.component("comp1").material("mat3").propertyGroup("idealGas").set("molarmass", "0.004[kg/mol]");
    model.component("comp1").material("mat3").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat3").selection().named("sel1");
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("arg", "pA");
    model.component("comp1").material("mat3").propertyGroup("def").func("k").setIndex("pieces", 1, 0, 0);
    model.component("comp1").material("mat3").propertyGroup("def").func("k").setIndex("pieces", 100, 0, 1);
    model.component("comp1").material("mat3").propertyGroup("def").func("k")
         .setIndex("pieces", "0.045809*log10(pA)+0.0063167", 0, 2);
    model.component("comp1").material("mat3").propertyGroup("def").func("k").set("argunit", "Torr");

    model.component("comp1").physics("spf").selection().named("sel1");
    model.component("comp1").physics("spf").feature("init1").set("p_init", 0.1);
    model.component("comp1").physics("spf").feature("dcont1").set("pairDisconnect", true);
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(2);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "MassFlow");
    model.component("comp1").physics("spf").feature("inl1").set("MassFlowType", "StandardFlowRateSCCM");
    model.component("comp1").physics("spf").feature("inl1").set("sccmmfr", "flow");
    model.component("comp1").physics("spf").feature("inl1").set("Mn", "mass_He");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(16);
    model.component("comp1").physics("spf").feature("out1").set("p0", "0.01[Torr]");
    model.component("comp1").physics("spf").feature("out1").set("SuppressBackflow", false);
    model.component("comp1").physics("solid").selection().named("geom1_csel1_dom");
    model.component("comp1").physics("solid").create("gacc1", "GravityAcceleration", -1);
    model.component("comp1").physics("solid").create("cnt1", "SolidContact", 1);
    model.component("comp1").physics("solid").feature("cnt1").set("penaltyCtrlPenalty", "ManualTuning");
    model.component("comp1").physics("solid").feature("cnt1").set("fp_penalty", "1/10");
    model.component("comp1").physics("solid").feature("cnt1").set("destination_offset", "0.5[um]");
    model.component("comp1").physics("solid").feature("cnt1").set("pairs", new String[]{"p2"});
    model.component("comp1").physics("solid").feature("dcnt1").set("penaltyCtrlPenalty", "ManualTuning");
    model.component("comp1").physics("solid").feature("dcnt1").set("fp_penalty", "1/10");
    model.component("comp1").physics("solid").feature("dcnt1").set("zeroInitGap", true);
    model.component("comp1").physics("ht").selection().set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
    model.component("comp1").physics("ht").prop("PhysicalModelProperty").set("Tref", "T_chuck");
    model.component("comp1").physics("ht").feature("solid1").set("minput_strainreferencetemperature_src", "userdef");
    model.component("comp1").physics("ht").feature("solid1").set("minput_strainreferencetemperature", "T_chuck");
    model.component("comp1").physics("ht").feature("solid1").set("minput_pressure_src", "fromCommonDef");
    model.component("comp1").physics("ht").feature("fluid1").selection().named("sel1");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T_chuck");
    model.component("comp1").physics("ht").create("solid2", "SolidHeatTransferModel", 2);
    model.component("comp1").physics("ht").feature("solid2").selection().named("geom1_csel1_dom");
    model.component("comp1").physics("ht").feature("solid2").label("\u56fa\u4f53 2 - \u6676\u5706");
    model.component("comp1").physics("ht").feature("solid2").set("minput_strainreferencetemperature_src", "userdef");
    model.component("comp1").physics("ht").feature("solid2").set("minput_strainreferencetemperature", "T_chuck");
    model.component("comp1").physics("ht").feature("solid2").set("minput_pressure_src", "root.comp1.spf.pA");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().set(10, 15, 22, 30, 38, 42, 45, 46);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "T_chuck");
    model.component("comp1").physics("ht").create("ins2", "ThermalInsulation", 1);
    model.component("comp1").physics("ht").feature("ins2").selection().set(46, 48);
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").selection().set(9, 28, 33);
    model.component("comp1").physics("ht").feature("hf1").set("q0_input", "Qt");
    model.component("comp1").physics("ht").create("init2", "init", 2);
    model.component("comp1").physics("ht").feature("init2").selection().named("geom1_csel1_dom");
    model.component("comp1").physics("ht").feature("init2").set("Tinit", "T_wafer_init");
    model.component("comp1").physics("es").create("ccns1", "ChargeConservationSolid", 2);
    model.component("comp1").physics("es").feature("ccns1").selection().named("com1");
    model.component("comp1").physics("es").create("cont1", "Continuity", 1);
    model.component("comp1").physics("es").feature("cont1").set("pairs", new String[]{"p3"});
    model.component("comp1").physics("es").create("term1", "Terminal", 1);
    model.component("comp1").physics("es").feature("term1").selection().set(8, 14, 21, 27, 32);
    model.component("comp1").physics("es").feature("term1").set("TerminalType", "Voltage");
    model.component("comp1").physics("es").feature("term1").set("V0", 0);
    model.component("comp1").physics("es").create("term2", "Terminal", 1);
    model.component("comp1").physics("es").feature("term2").selection().set(38, 42);
    model.component("comp1").physics("es").feature("term2").set("TerminalType", "Voltage");
    model.component("comp1").physics("es").feature("term2").set("V0", "VdcSP[V]");
    model.component("comp1").physics("es").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("es").feature("ge1").setIndex("name", "VdcSP", 0, 0);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("es").feature("ge1").setIndex("equation", "(intop1(z)-zsp)/zsp", 0, 0);

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().set(2, 4, 6, 8);
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", "10*(d_inlet/2)");
    model.component("comp1").mesh("mesh1").create("edg2", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg2").selection().set(16, 18, 20, 21);
    model.component("comp1").mesh("mesh1").feature("edg2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg2").feature("dis1").set("numelem", "20*(w_outlet/2)");
    model.component("comp1").mesh("mesh1").create("edg3", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg3").selection().set(1, 3, 10, 11, 15, 17, 22, 23);
    model.component("comp1").mesh("mesh1").feature("edg3").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg3").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg3").feature("dis1").set("elemcount", "my_inlet");
    model.component("comp1").mesh("mesh1").feature("edg3").feature("dis1").set("elemratio", 50);
    model.component("comp1").mesh("mesh1").feature("edg3").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").create("edg4", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg4").selection().set(37, 40, 41, 44, 50);
    model.component("comp1").mesh("mesh1").feature("edg4").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg4").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg4").feature("dis1").set("elemcount", "my_insulator");
    model.component("comp1").mesh("mesh1").feature("edg4").feature("dis1").set("elemratio", 2);
    model.component("comp1").mesh("mesh1").feature("edg4").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").label("\u6620\u5c04 1 - \u5165\u53e3\u548c\u51fa\u53e3");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(1, 2, 6, 7);
    model.component("comp1").mesh("mesh1").create("edg5", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg5").selection().set(5, 12, 19, 24, 29, 34, 47, 51);
    model.component("comp1").mesh("mesh1").feature("edg5").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg5").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg5").feature("dis1").set("elemcount", "my_channel");
    model.component("comp1").mesh("mesh1").feature("edg5").feature("dis1").set("elemratio", 5);
    model.component("comp1").mesh("mesh1").feature("edg5").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").create("map2", "Map");
    model.component("comp1").mesh("mesh1").feature("map2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map2").selection().set(3, 8);
    model.component("comp1").mesh("mesh1").feature("map2").label("\u6620\u5c04 2 - \u89d2");
    model.component("comp1").mesh("mesh1").create("edg6", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg6").selection().set(13, 25, 38, 39, 42, 43);
    model.component("comp1").mesh("mesh1").feature("edg6").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg6").feature("dis1").selection().set(13);
    model.component("comp1").mesh("mesh1").feature("edg6").feature("dis1").set("numelem", "mfx*x_outlet");
    model.component("comp1").mesh("mesh1").feature("edg6").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg6").feature("dis2").selection().set(25);
    model.component("comp1").mesh("mesh1").feature("edg6").feature("dis2")
         .set("numelem", "mfx*(d_wafer/2-x_outlet)");
    model.component("comp1").mesh("mesh1").feature("edg6").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg6").feature("dis3").selection().set(38, 39);
    model.component("comp1").mesh("mesh1").feature("edg6").feature("dis3").set("numelem", "2*x_outlet");
    model.component("comp1").mesh("mesh1").feature("edg6").create("dis4", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg6").feature("dis4").selection().set(42, 43);
    model.component("comp1").mesh("mesh1").feature("edg6").feature("dis4").set("numelem", "2*(d_wafer/2-x_outlet)");
    model.component("comp1").mesh("mesh1").create("map3", "Map");
    model.component("comp1").mesh("mesh1").feature("map3").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map3").selection().set(5, 9);
    model.component("comp1").mesh("mesh1").feature("map3").label("\u6620\u5c04 - \u901a\u9053");
    model.component("comp1").mesh("mesh1").create("map4", "Map");
    model.component("comp1").mesh("mesh1").feature("map4").label("\u6620\u5c04 - \u7edd\u7f18\u4f53");
    model.component("comp1").mesh("mesh1").feature("map4").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map4").selection().set(13, 14);
    model.component("comp1").mesh("mesh1").create("edg7", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg7").selection().set(30, 32, 49);
    model.component("comp1").mesh("mesh1").feature("edg7").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg7").feature("dis1").selection().set(49);
    model.component("comp1").mesh("mesh1").feature("edg7").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg7").feature("dis1").set("elemcount", "my_channel");
    model.component("comp1").mesh("mesh1").feature("edg7").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg7").feature("dis2").selection().set(30, 32);
    model.component("comp1").mesh("mesh1").feature("edg7").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg7").feature("dis2").set("elemcount", "3*mx_pin");
    model.component("comp1").mesh("mesh1").feature("edg7").feature("dis2").set("elemratio", 2);
    model.component("comp1").mesh("mesh1").create("map5", "Map");
    model.component("comp1").mesh("mesh1").feature("map5").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map5").selection().set(11);
    model.component("comp1").mesh("mesh1").create("edg8", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg8").selection().set(46);
    model.component("comp1").mesh("mesh1").feature("edg8").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg8").feature("dis1").selection().set(46);
    model.component("comp1").mesh("mesh1").feature("edg8").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg8").feature("dis1").set("elemcount", 2);
    model.component("comp1").mesh("mesh1").feature("edg8").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").create("map6", "Map");
    model.component("comp1").mesh("mesh1").feature("map6").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map6").selection().set(16);
    model.component("comp1").mesh("mesh1").create("edg9", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg9").selection().set(7, 36);
    model.component("comp1").mesh("mesh1").feature("edg9").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg9").feature("dis1").set("numelem", "my_wafer");
    model.component("comp1").mesh("mesh1").create("cpe1", "CopyEdge");
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("source").geom(1);
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("destination").geom(1);
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("source").set(46, 48);
    model.component("comp1").mesh("mesh1").feature("cpe1").selection("destination").set(45);
    model.component("comp1").mesh("mesh1").create("map7", "Map");
    model.component("comp1").mesh("mesh1").feature("map7").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map7").selection().set(15);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(4, 10, 12);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 3);

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/physics/es", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/fsi1", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf1", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/te1", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/eme1", true);
    model.study("std1").label("\u7814\u7a76 1 - \u65e0\u6c14\u6d41");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("stat").setSolveFor("/physics/spf", false);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/fsi1", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf1", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/te1", false);
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "d_inlet", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "d_inlet", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "zsp", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "35[um] 25[um] 15[um]", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "hnlin");
    model.sol("sol1").feature("s1").feature("fc1").set("maxiter", 200);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std2").feature("stat").setSolveFor("/physics/es", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/fsi1", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/nitf1", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/te1", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/eme1", true);
    model.study("std2").label("\u7814\u7a76 2 - \u6709\u6c14\u6d41");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/te1", false);
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").set("sweeptype", "filled");
    model.study("std2").feature("stat").setIndex("pname", "d_inlet", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "d_inlet", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "flow", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(0,100,400)", 0);
    model.study("std2").feature("stat").setIndex("pname", "d_inlet", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "", 1);
    model.study("std2").feature("stat").setIndex("punit", "m", 1);
    model.study("std2").feature("stat").setIndex("pname", "d_inlet", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "", 1);
    model.study("std2").feature("stat").setIndex("punit", "m", 1);
    model.study("std2").feature("stat").setIndex("pname", "zsp", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "30[um]", 1);
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol2").feature("s1").feature("fc1").set("linsolver", "dDef");
    model.sol("sol2").feature("s1").feature("fc1").set("dtech", "hnlin");
    model.sol("sol2").feature("s1").feature("fc1").set("maxiter", 500);

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/spf", true);
    model.study("std3").feature("time").setSolveFor("/physics/solid", true);
    model.study("std3").feature("time").setSolveFor("/physics/ht", true);
    model.study("std3").feature("time").setSolveFor("/physics/es", true);
    model.study("std3").feature("time").setSolveFor("/multiphysics/fsi1", true);
    model.study("std3").feature("time").setSolveFor("/multiphysics/nitf1", true);
    model.study("std3").feature("time").setSolveFor("/multiphysics/te1", true);
    model.study("std3").feature("time").setSolveFor("/multiphysics/eme1", true);
    model.study("std3").label("\u7814\u7a76 3 - \u6676\u5706\u6e29\u5ea6 vs. \u65f6\u95f4");
    model.study("std3").setGenPlots(false);
    model.study("std3").feature("time").setSolveFor("/multiphysics/te1", false);
    model.study("std3").feature("time").set("tunit", "ms");
    model.study("std3").feature("time").set("tlist", "range(0,5,7000)");
    model.study("std3").feature("time").set("useinitsol", true);
    model.study("std3").feature("time").set("initmethod", "sol");
    model.study("std3").feature("time").set("initstudy", "std2");
    model.study("std3").feature("time").set("solnum", 2);
    model.study("std3").feature("time").set("useparam", true);
    model.study("std3").feature("time").setIndex("pname", "d_inlet", 0);
    model.study("std3").feature("time").setIndex("plistarr", "", 0);
    model.study("std3").feature("time").setIndex("punit", "m", 0);
    model.study("std3").feature("time").setIndex("pname", "d_inlet", 0);
    model.study("std3").feature("time").setIndex("plistarr", "", 0);
    model.study("std3").feature("time").setIndex("punit", "m", 0);
    model.study("std3").feature("time").setIndex("pname", "flow", 0);
    model.study("std3").feature("time").setIndex("plistarr", 100, 0);
    model.study("std3").feature("time").setIndex("pname", "d_inlet", 1);
    model.study("std3").feature("time").setIndex("plistarr", "", 1);
    model.study("std3").feature("time").setIndex("punit", "m", 1);
    model.study("std3").feature("time").setIndex("pname", "d_inlet", 1);
    model.study("std3").feature("time").setIndex("plistarr", "", 1);
    model.study("std3").feature("time").setIndex("punit", "m", 1);
    model.study("std3").feature("time").setIndex("pname", "zsp", 1);
    model.study("std3").feature("time").setIndex("plistarr", "30[um]", 1);
    model.study("std3").feature("time").setIndex("pname", "d_inlet", 2);
    model.study("std3").feature("time").setIndex("plistarr", "", 2);
    model.study("std3").feature("time").setIndex("punit", "m", 2);
    model.study("std3").feature("time").setIndex("pname", "d_inlet", 2);
    model.study("std3").feature("time").setIndex("plistarr", "", 2);
    model.study("std3").feature("time").setIndex("punit", "m", 2);
    model.study("std3").feature("time").setIndex("pname", "Q_plasma", 2);
    model.study("std3").feature("time").setIndex("plistarr", "1E5", 2);
    model.study("std3").showAutoSequences("all");

    model.sol("sol3").feature("t1").create("fc1", "FullyCoupled");
    model.sol("sol3").feature("t1").feature("fc1").set("linsolver", "d2");
    model.sol("sol3").feature("t1").feature("fc1").set("dtech", "hnlin");
    model.sol("sol3").feature("t1").feature("fc1").set("maxiter", 200);

    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u6676\u5706\u5256\u9762\uff0c\u65e0\u6c14\u6d41");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg1").feature("lngr1").set("linewidth", "preference");
    model.result("pg1").feature("lngr1").selection().set(8, 14, 21, 27, 32, 35);
    model.result("pg1").feature("lngr1").set("expr", "z");
    model.result("pg1").feature("lngr1").set("unit", "\u00b5m");
    model.result("pg1").feature("lngr1").set("descractive", true);
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "r");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").run();
    model.result("pg1").set("legendpos", "lowerright");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("VdcSP vs. zsp");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", "VdcSP", 0);
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "zsp");
    model.result("pg2").feature("glob1").set("xdataunit", "\u00b5m");
    model.result("pg2").feature("glob1").set("xdatadescractive", true);
    model.result("pg2").run();
    model.result("pg1").run();
    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result("pg3").label("\u6676\u5706\u5256\u9762\uff0c\u6709\u6c14\u6d41");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg2").run();
    model.result("pg2").set("legendpos", "lowerright");
    model.result("pg2").run();
    model.result().duplicate("pg4", "pg2");
    model.result("pg4").run();
    model.result("pg4").label("VdcSP vs. \u6c14\u4f53\u6d41\u7387");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").run();
    model.result("pg4").feature("glob1").set("xdatasolnumtype", "level2");
    model.result("pg4").feature("glob1").set("xdata", "solution");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").set("legendpos", "upperright");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").label("\u4f4d\u79fb\uff0c\u65e0\u6c14\u6d41");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "solid.disp");
    model.result("pg5").feature("surf1").set("unit", "\u00b5m");
    model.result("pg5").feature("surf1").set("descractive", true);
    model.result("pg5").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg5").feature("surf1").create("def1", "Deform");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg5").feature("surf1").feature("def1").set("scale", 0);
    model.result("pg5").feature("surf1").feature("def1").set("descractive", true);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").run();
    model.result("pg6").label("\u4f4d\u79fb\uff0c\u6709\u6c14\u6d41");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "solid.disp");
    model.result("pg6").feature("surf1").set("unit", "\u00b5m");
    model.result("pg6").feature("surf1").set("descractive", true);
    model.result("pg6").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg6").feature("surf1").create("def1", "Deform");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").feature("def1").set("descractive", true);
    model.result("pg6").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg6").feature("surf1").feature("def1").set("scale", 0);
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u6676\u5706\u6e29\u5ea6 vs. \u65f6\u95f4");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").create("ptgr1", "PointGraph");
    model.result("pg7").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg7").feature("ptgr1").set("linewidth", "preference");
    model.result("pg7").feature("ptgr1").selection().set(5);
    model.result("pg7").feature("ptgr1").set("expr", "T");
    model.result("pg7").feature("ptgr1").set("descractive", true);
    model.result("pg7").feature("ptgr1").set("xdataparamunit", "s");
    model.result("pg7").run();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg7").run();

    model.title("\u9759\u7535\u5438\u76d8");

    model
         .description("\u9759\u7535\u5438\u76d8 (e-chuck) \u662f\u6676\u5706\u52a0\u5de5\u8bbe\u5907\u4e2d\u7684\u4e00\u4e2a\u91cd\u8981\u88c5\u7f6e\uff0c\u7528\u4e8e\u5c06\u6676\u5706\u56fa\u5b9a\u5728\u6e29\u63a7\u5e73\u53f0\u4e0a\uff0c\u4ee5\u4fbf\u5728\u52a0\u5de5\u8fc7\u7a0b\u4e2d\u5bf9\u6676\u5706\u8fdb\u884c\u51b7\u5374\u548c\u52a0\u70ed\u3002\u9759\u7535\u5438\u76d8\u7684\u64cd\u4f5c\u6d89\u53ca\u591a\u79cd\u7269\u7406\u73b0\u8c61\uff1a\u9759\u7535\u529b\u3001\u6c14\u6d41\u3001\u4f20\u70ed\u548c\u56fa\u4f53\u529b\u5b66\u3002\u672c\u4f8b\u6a21\u62df\u4e86\u6e29\u5ea6\u63a7\u5236\uff0c\u5176\u4e2d\u901a\u8fc7\u9759\u7535\u529b\u62b5\u6d88\u5728\u4f4e\u538b\u73af\u5883\u4e2d\u4e3a\u6676\u5706\u548c\u5438\u76d8\u8868\u9762\u63d0\u4f9b\u70ed\u4f20\u5bfc\u7684\u95f4\u9699\u4e2d\u7684\u6c26\u6c14\u538b\u529b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("electrostatic_chuck.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
