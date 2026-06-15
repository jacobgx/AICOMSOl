/*
 * rotor_thermal_stress.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:48 by COMSOL 6.3.0.290. */
public class rotor_thermal_stress {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Rotordynamics_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("htlsh", "HeatTransferInFilmsLM", "geom1");
    model.component("comp1").physics().create("hdb", "HydrodynamicBearing", "geom1");
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.component("comp1").multiphysics().create("te1", "ThermalExpansion", 3);
    model.component("comp1").multiphysics("te1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("te1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("te1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/htlsh", true);
    model.study("std1").feature("stat").setSolveFor("/physics/hdb", true);
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/te1", true);

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "rotor_thermal_stress.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("imprint", true);
    model.component("comp1").geom("geom1").run("fin");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("dJ", "0.05[m]", "\u8f74\u9888\u76f4\u5f84");
    model.param().set("Lb", "0.04[m]", "\u886c\u5957\u957f\u5ea6");
    model.param().set("C", "1e-3*dJ", "\u8f74\u627f\u95f4\u9699");
    model.param().set("W", "2000[N]", "\u8f6c\u5b50\u4e0a\u7684\u9759\u8f7d\u8377");
    model.param().set("muO", "0.0028[Pa*s]", "\u6cb9\u7684\u9ecf\u5ea6");
    model.param().set("rhoO", "866[kg/m^3]", "\u6cb9\u7684\u5bc6\u5ea6");
    model.param().set("kO", "0.13[W/m/K]", "\u6cb9\u7684\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("CpO", "2000[J/kg/K]", "\u6cb9\u7684\u70ed\u5bb9");
    model.param().set("gammaO", "1.4", "\u6cb9\u7684\u70ed\u5bb9\u6bd4");
    model.param().set("fr", "3000[rpm]", "\u8f6c\u5b50\u89d2\u901f\u5ea6");
    model.param().set("nu_air", "17e-6[m^2/s]", "\u7a7a\u6c14\u8fd0\u52a8\u9ecf\u5ea6");
    model.param().set("k_air", "2.8e-2[W/m/K]", "\u7a7a\u6c14\u5bfc\u70ed\u7cfb\u6570");
    model.param().set("Cp_air", "1.006[kJ/kg/K]", "\u7a7a\u6c14\u70ed\u5bb9");
    model.param().set("rho_air", "1.225[kg/m^3]", "\u7a7a\u6c14\u5bc6\u5ea6");
    model.param().set("Rd", "287[J/kg/K]", "\u7a7a\u6c14\u7684\u6c14\u4f53\u5e38\u6570");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").set(28, 29);
    model.component("comp1").selection("sel1").label("\u8f74\u627f\u5957");
    model.component("comp1").selection().create("com1", "Complement");
    model.component("comp1").selection("com1").label("\u8f6c\u5b50");
    model.component("comp1").selection("com1").set("input", new String[]{"sel1"});
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").label("\u8f74\u9888 1");
    model.component("comp1").selection("sel2").set(149, 150, 184, 185);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").label("\u8f74\u627f 1");
    model.component("comp1").selection("sel3").set(388, 389, 393, 394);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").label("\u8f74\u9888 2");
    model.component("comp1").selection("sel4").set(159, 160, 336, 337);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").geom(2);
    model.component("comp1").selection("sel5").label("\u8f74\u627f 2");
    model.component("comp1").selection("sel5").set(451, 452, 456, 457);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("\u8f74\u627f\u5ea7");
    model.component("comp1").selection("sel6").geom(2);
    model.component("comp1").selection("sel6").set(367, 409, 430, 472);
    model.component("comp1").selection("sel6").set("groupcontang", true);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u8f74\u9888");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("input", new String[]{"sel2", "sel4"});
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("\u8f74\u627f");
    model.component("comp1").selection("uni2").set("entitydim", 2);
    model.component("comp1").selection("uni2").set("input", new String[]{"sel3", "sel5"});
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").set("input", new String[]{"com1"});
    model.component("comp1").selection("adj1").label("\u8f74\u5916\u90e8");
    model.component("comp1").selection().duplicate("adj2", "adj1");
    model.component("comp1").selection("adj2").set("input", new String[]{"sel1"});
    model.component("comp1").selection("adj2").label("\u8f74\u627f\u5957\u5916\u90e8");
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").set("entitydim", 2);
    model.component("comp1").selection("dif1").set("add", new String[]{"adj1"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"uni1"});
    model.component("comp1").selection("dif1").label("\u5bf9\u6d41\u8fb9\u754c\uff08\u8f74\uff09");
    model.component("comp1").selection().duplicate("dif2", "dif1");
    model.component("comp1").selection("dif2").label("\u5bf9\u6d41\u8fb9\u754c\uff08\u8f74\u627f\u5957\uff09");
    model.component("comp1").selection("dif2").set("add", new String[]{"adj2"});
    model.component("comp1").selection("dif2").set("subtract", new String[]{"uni2"});
    model.component("comp1").selection().create("adj3", "Adjacent");
    model.component("comp1").selection("adj3").set("entitydim", 2);
    model.component("comp1").selection("adj3").set("input", new String[]{"uni1"});
    model.component("comp1").selection("adj3").set("outputdim", 1);
    model.component("comp1").selection("adj3").label("\u5916\u90e8\u8fb9\uff08\u8f74\u9888\uff09");
    model.component("comp1").selection().duplicate("adj4", "adj3");
    model.component("comp1").selection("adj4").label("\u5185\u90e8\u8fb9\uff08\u8f74\u9888\uff09");
    model.component("comp1").selection("adj4").set("exterior", false);
    model.component("comp1").selection("adj4").set("interior", true);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").selection().geom("geom1", 2);
    model.component("comp1").variable("var1").selection().named("dif1");
    model.component("comp1").variable("var1").set("d_rot", "2*sqrt(X^2+Z^2)");
    model.component("comp1").variable("var1").descr("d_rot", "\u8f74\u7684\u76f4\u5f84");
    model.component("comp1").variable("var1").set("Re_air", "2*pi*fr*d_rot^2/nu_air");
    model.component("comp1").variable("var1").descr("Re_air", "\u6d41\u52a8\u7684\u96f7\u8bfa\u6570");
    model.component("comp1").variable("var1").set("Pr_air", "Cp_air*rho_air*nu_air/k_air");
    model.component("comp1").variable("var1").descr("Pr_air", "\u7a7a\u6c14\u7684\u666e\u6717\u7279\u6570");
    model.component("comp1").variable("var1").set("h_shaft", "k_air*Re_air^(2/3)*Pr_air^(1/3)/(15*d_rot/2)");
    model.component("comp1").variable("var1").descr("h_shaft", "\u8f74\u7684\u4f20\u70ed\u7cfb\u6570");

    model.component("comp1").cpl().create("genext1", "GeneralExtrusion");
    model.component("comp1").cpl("genext1").selection().geom("geom1", 2);
    model.component("comp1").cpl("genext1").selection().named("uni2");
    model.component("comp1").cpl("genext1").set("dstmap", new String[]{"X", "Y", "Z"});
    model.component("comp1").cpl("genext1").set("srcframe", "material");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.component("comp1").material("mat1").propertyGroup().create("Swift", "Swift", "Swift");
    model.component("comp1").material("mat1").propertyGroup().create("Voce", "Voce", "Voce");
    model.component("comp1").material("mat1").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Norton", "Norton", "Norton");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.component("comp1").material("mat1").label("Structural steel");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.9);
    model.component("comp1").material("mat1").set("roughness", 0.3);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").set("lossfactor", "0.02");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "E(T)");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "nu(T)");
    model.component("comp1").material("mat1").propertyGroup("Enu").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .label("Elastoplastic material model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.component("comp1").material("mat1").propertyGroup("Swift").set("e0_swi", "0.021");
    model.component("comp1").material("mat1").propertyGroup("Swift").set("n_swi", "0.2");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp1").material("mat1").propertyGroup("Voce").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("Norton").label("Norton");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("n_nor", "4.5");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").label("Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity")
         .label("Chaboche viscoplasticity");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().geom("geom1", 2);
    model.component("comp1").material("mat2").selection().named("uni1");

    model.component("comp1").physics("htlsh").selection().named("uni1");
    model.component("comp1").physics("hdb").selection().named("uni1");

    model.component("comp1").material("mat2").propertyGroup("def").set("thermalconductivity", new String[]{"kO"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"rhoO"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", new String[]{"CpO"});
    model.component("comp1").material("mat2").propertyGroup().create("shell", "shell", "Shell");
    model.component("comp1").material("mat2").propertyGroup("shell").set("lth", new String[]{"C"});
    model.component("comp1").material("mat2").propertyGroup("def").set("dynamicviscosity", new String[]{"muO"});
    model.component("comp1").material("mat2").label("\u6750\u6599\uff1a\u6cb9");

    model.component("comp1").physics("htlsh").feature("fls1").set("LayerType", "General");
    model.component("comp1").physics("htlsh").feature("fls1").set("u_src", "root.comp1.hdb.vavex");
    model.component("comp1").physics("htlsh").create("hs1", "HeatSource", 2);
    model.component("comp1").physics("htlsh").feature("hs1").selection().named("uni1");
    model.component("comp1").physics("htlsh").feature("hs1").set("Q0_src", "root.comp1.hdb.Qvd");
    model.component("comp1").physics("htlsh").create("tempi1", "TemperatureInterface", 2);
    model.component("comp1").physics("htlsh").feature("tempi1").set("applyTo", "top");
    model.component("comp1").physics("htlsh").feature("tempi1").set("T0", "T2");
    model.component("comp1").physics("htlsh").feature("tempi1").selection().named("uni1");
    model.component("comp1").physics("htlsh").feature().duplicate("tempi2", "tempi1");
    model.component("comp1").physics("htlsh").feature("tempi2").set("applyTo", "bottom");
    model.component("comp1").physics("htlsh").feature("tempi2").set("T0", "genext1(T2)");
    model.component("comp1").physics("hdb").prop("EquationType")
         .set("EquationType", "ReynoldsEquationWithCavitation");
    model.component("comp1").physics("hdb").feature("hjb1").set("C_plain", "C");
    model.component("comp1").physics("hdb").feature("hjb1").set("BearingCenterType", "fromGeom");
    model.component("comp1").physics("hdb").feature("hjb1").set("Specify", "Load");
    model.component("comp1").physics("hdb").feature("hjb1").set("W", new String[]{"0", "0", "-W/2"});
    model.component("comp1").physics("hdb").feature("hjb1").set("Ow", "2*pi[rad]*fr");
    model.component("comp1").physics("hdb").feature().duplicate("hjb2", "hjb1");
    model.component("comp1").physics("hdb").feature("hjb2").selection().named("sel4");
    model.component("comp1").physics("hdb").feature("bax1").set("bearingAxis", "yAxis");
    model.component("comp1").physics("hdb").feature("bax1").set("e_aux", new int[]{1, 0, 0});
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("sel6");
    model.component("comp1").physics("solid").create("disp1", "Displacement2", 2);
    model.component("comp1").physics("solid").feature("disp1").selection()
         .set(9, 10, 41, 42, 89, 90, 165, 168, 226, 231, 354, 355);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("solid").create("disp2", "Displacement1", 1);
    model.component("comp1").physics("solid").feature("disp2").selection().set(219, 479);
    model.component("comp1").physics("solid").feature("disp2").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("solid").feature("disp2").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").label("\u70ed\u901a\u91cf\uff1a\u8f74");
    model.component("comp1").physics("ht").feature("hf1").selection().named("dif1");
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", "h_shaft");
    model.component("comp1").physics("ht").feature().duplicate("hf2", "hf1");
    model.component("comp1").physics("ht").feature("hf2").label("\u70ed\u901a\u91cf\uff1a\u8f74\u627f\u5957");
    model.component("comp1").physics("ht").feature("hf2").selection().named("dif2");
    model.component("comp1").physics("ht").feature("hf2").set("h", 5);

    model.component("comp1").mesh("mesh1").create("id1", "IdenticalMesh");
    model.component("comp1").mesh("mesh1").feature("id1").selection("group1").named("uni1");
    model.component("comp1").mesh("mesh1").feature("id1").selection("group2").named("uni2");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("com1");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 40);
    model.component("comp1").mesh("mesh1").feature("swe1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").selection().set(26, 27);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hauto", 3);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(362, 404, 425, 467);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 4);
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").create("fc1", "FullyCoupled");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("lshl1", "LayeredMaterial");
    model.result().dataset("lshl1").set("data", "dset1");
    model.result().dataset("lshl1").selection().geom("geom1", 2);
    model.result().dataset("lshl1").selection().set(149, 150, 159, 160, 184, 185, 336, 337);
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u6e29\u5ea6\uff0c\u58f3 (htlsh)");
    model.result("pg1").feature().create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("data", "lshl1");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("solutionparams", "parent");
    model.result("pg1").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("vol1").set("smooth", "internal");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("data", "lshl1");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u6d41\u4f53\u538b\u529b (hdb)");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "pfilm");
    model.result("pg2").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").set("expr", "pfilm");
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("colorlegend", false);
    model.result("pg2").feature("con1").set("smooth", "internal");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg2").feature("con1").set("inherittubescale", false);
    model.result("pg2").feature("con1").set("data", "parent");
    model.result("pg2").feature("con1").set("inheritplot", "surf1");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").label("\u5e94\u529b (solid)");
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
    model.result("pg3").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u6e29\u5ea6 (ht)");
    model.result("pg4").feature().create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("showsolutionparams", "on");
    model.result("pg4").feature("vol1").set("solutionparams", "parent");
    model.result("pg4").feature("vol1").set("expr", "T2");
    model.result("pg4").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg4").feature("vol1").set("smooth", "internal");
    model.result("pg4").feature("vol1").set("showsolutionparams", "on");
    model.result("pg4").feature("vol1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("vol1").set("rangecoloractive", true);
    model.result("pg3").feature("vol1").set("rangecolormax", 400000000);
    model.result("pg3").run();
    model.result("pg3").feature("vol1").feature("def").set("scaleactive", true);
    model.result("pg3").feature("vol1").feature("def").set("scale", 50);
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("\u9ecf\u6027\u70ed");
    model.result("pg5").set("view", "new");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "hdb.Qvd");
    model.result("pg5").feature("surf1").set("descr", "\u9ecf\u6027\u8017\u6563");
    model.result("pg5").feature("surf1").set("colortable", "Prism");
    model.result("pg5").run();
    model.result("pg3").run();

    model.title("\u8f74\u627f\u70ed\u635f\u8017\u5728\u8f6c\u5b50\u4e2d\u4ea7\u751f\u7684\u70ed\u5e94\u529b");

    model
         .description("\u6db2\u4f53\u52a8\u538b\u8f74\u627f\u7531\u4e8e\u6da6\u6ed1\u6cb9\u7684\u9ecf\u6ede\u635f\u8017\u800c\u4ea7\u751f\u70ed\u91cf\uff0c\u7ed3\u679c\uff0c\u8f6c\u5b50\u7684\u6e29\u5ea6\u5347\u9ad8\uff0c\u4ece\u800c\u5728\u8f6c\u5b50\u548c\u8f74\u627f\u5957\u4e2d\u4ea7\u751f\u53d8\u5f62\u548c\u70ed\u5e94\u529b\u3002\n\n\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u6a21\u62df\u8f6c\u5b50\u8f74\u627f\u7cfb\u7edf\u7684\u4f20\u70ed\u6d89\u53ca\u7684\u4e0d\u540c\u7269\u7406\u73b0\u8c61\uff0c\u4ee5\u53ca\u5b83\u4eec\u5982\u4f55\u76f8\u4e92\u4f5c\u7528\u3002\n\n\u4eff\u771f\u7ed3\u679c\u5305\u62ec\u8f74\u627f\u7684\u538b\u529b\u5206\u5e03\u548c\u9ecf\u6ede\u70ed\u635f\u8017\uff1b\u6da6\u6ed1\u6cb9\u819c\u3001\u8f6c\u5b50\u548c\u8f74\u627f\u5957\u7684\u6e29\u5ea6\u5206\u5e03\uff1b\u4ee5\u53ca\u8f6c\u5b50\u548c\u8f74\u627f\u5957\u7684\u5e94\u529b\u548c\u53d8\u5f62\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("rotor_thermal_stress.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
