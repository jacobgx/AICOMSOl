/*
 * tube_connection.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:04 by COMSOL 6.3.0.290. */
public class tube_connection {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Contact_and_Friction");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("pipeDiameter", "360[mm]", "\u7ba1\u5185\u5f84");
    model.param().set("pipeThickness", "20[mm]", "\u7ba1\u58c1\u539a\u5ea6");
    model.param().set("pipeOuterDiameter", "pipeDiameter+2*pipeThickness", "\u7ba1\u5916\u5f84");
    model.param()
         .set("pipeLength", "5*sqrt(pipeDiameter/2*pipeThickness)", "\u7ba1\u957f\u5ea6\uff0c\u907f\u514d\u7aef\u90e8\u6548\u5e94");
    model.param().set("flangeThickness", "26[mm]", "\u6cd5\u5170\u539a\u5ea6");
    model.param().set("flangeDiameter", "520[mm]", "\u6cd5\u5170\u5916\u5f84");
    model.param().set("filletRadius", "12[mm]", "\u5706\u89d2\u534a\u5f84\uff0c\u6cd5\u5170\u5230\u7ba1");
    model.param().set("numBolts", "8", "\u87ba\u6813\u6570");
    model.param().set("boltHeadDiameter", "1.5*boltDiameter", "\u87ba\u6813\u5934\u76f4\u5f84");
    model.param().set("boltHeadThickness", "0.6*boltDiameter", "\u87ba\u6813\u5934\u539a\u5ea6");
    model.param().set("boltDiameter", "24[mm]", "\u87ba\u6813\u76f4\u5f84");
    model.param()
         .set("boltHoleClearance", "2[mm]", "\u87ba\u6813\u5b54\u4e0e\u87ba\u6813\u7684\u5f84\u5411\u95f4\u9699");
    model.param().set("boltHoleDiameter", "boltDiameter+boltHoleClearance", "\u87ba\u6813\u5b54\u5f84");
    model.param().set("boltCircle", "470[mm]", "\u87ba\u6813\u5706\u76f4\u5f84");
    model.param().set("washerDiameter", "boltHeadDiameter*1.15", "\u57ab\u5708\u76f4\u5f84");
    model.param().set("washerThickness", "1.5[mm]", "\u57ab\u5708\u539a\u5ea6");
    model.param().set("axialForce", "500[kN]", "\u8f74\u5411\u529b");
    model.param().set("bendingMoment", "40[kN*m]", "\u5f2f\u77e9");
    model.param().set("pressure", "30[bar]", "\u5185\u90e8\u7ba1\u538b\u529b");
    model.param().set("boltPrestressForce", "150[kN]", "\u87ba\u6813\u9884\u5e94\u529b");
    model.param().set("ps", "1", "\u5f39\u7c27\u677e\u5f1b\u53c2\u6570");
    model.param().set("lp", "0", "\u8377\u8f7d\u53c2\u6570");

    model.component("comp1").geom("geom1").insertFile("tube_connection_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("pairtype", "contact");
    model.component("comp1").geom("geom1").feature("fin").set("frame", "material");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").pair("ap1").mapping("initial");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").label("\u87ba\u6813\u5bf9\u79f0");
    model.component("comp1").selection("sel1")
         .set(162, 173, 180, 206, 207, 217, 228, 236, 237, 272, 273, 283, 294, 302, 303, 338, 339, 349, 360, 368, 369, 396, 407, 414);

    model.component("comp1").pair().remove("ap2");
    model.component("comp1").pair("ap3").mapping("initial");
    model.component("comp1").pair("ap3").swap();
    model.component("comp1").pair("ap4").manualSelection(true);
    model.component("comp1").pair("ap4").type("Identity");

    model.component("comp1").coordSystem("sys1").set("mastercoordsystcomp", "1");

    model.component("comp1").selection().create("box1", "Box");
    model.component("comp1").selection("box1").label("\u5bf9\u79f0\u8fb9\u754c\uff08xz \u5e73\u9762\uff09");
    model.component("comp1").selection("box1").set("entitydim", 2);
    model.component("comp1").selection("box1").set("ymax", 0);
    model.component("comp1").selection("box1").set("zmin", 0);
    model.component("comp1").selection("box1").set("condition", "inside");
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("input", new String[]{"sel1", "box1"});
    model.component("comp1").selection("uni1").label("\u5bf9\u79f0");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u87ba\u6813\u9884\u7d27\uff0c\u5207\u9762 1");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(165);
    model.component("comp1").selection("sel2").set("groupcontang", true);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u87ba\u6813\u9884\u7d27\uff0c\u5207\u9762 2");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(211);
    model.component("comp1").selection("sel3").set("groupcontang", true);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u87ba\u6813\u9884\u7d27\uff0c\u5207\u9762 3");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").set(277);
    model.component("comp1").selection("sel4").set("groupcontang", true);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u87ba\u6813\u9884\u7d27\uff0c\u5207\u9762 4");
    model.component("comp1").selection("sel5").geom(2);
    model.component("comp1").selection("sel5").set(343);
    model.component("comp1").selection("sel5").set("groupcontang", true);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("\u87ba\u6813\u9884\u7d27\uff0c\u5207\u9762 5");
    model.component("comp1").selection("sel6").geom(2);
    model.component("comp1").selection("sel6").set(399);
    model.component("comp1").selection("sel6").set("groupcontang", true);

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

    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").label("\u538b\u529b");
    model.component("comp1").physics("solid").feature("bndl1").selection().set(21, 22, 30, 33);
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "FollowerPressure");
    model.component("comp1").physics("solid").feature("bndl1").set("pressure", "pressure");
    model.component("comp1").physics("solid").create("bndl2", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl2").label("\u5f2f\u77e9\u548c\u8f74\u5411\u529b");
    model.component("comp1").physics("solid").feature("bndl2").selection().set(20, 35);
    model.component("comp1").physics("solid").feature("bndl2").set("forceType", "Resultant");
    model.component("comp1").physics("solid").feature("bndl2").set("F", new String[]{"0", "0", "axialForce"});
    model.component("comp1").physics("solid").feature("bndl2").set("M", new String[]{"0", "-lp*bendingMoment", "0"});
    model.component("comp1").physics("solid").feature("bndl2").set("symmetryType", "Symmetry");
    model.component("comp1").physics("solid").feature("bndl2").set("nsym", new int[]{0, 1, 0});
    model.component("comp1").physics("solid").create("pblt1", "BoltPrestress", -1);
    model.component("comp1").physics("solid").feature("pblt1").set("F_pre", "boltPrestressForce");
    model.component("comp1").physics("solid").feature("pblt1").feature("sblt1").selection().named("sel2");
    model.component("comp1").physics("solid").feature("pblt1").create("sblt2", "BoltSelection", 2);
    model.component("comp1").physics("solid").feature("pblt1").feature("sblt2").selection().named("sel3");
    model.component("comp1").physics("solid").feature("pblt1").create("sblt3", "BoltSelection", 2);
    model.component("comp1").physics("solid").feature("pblt1").feature("sblt3").selection().named("sel4");
    model.component("comp1").physics("solid").feature("pblt1").create("sblt4", "BoltSelection", 2);
    model.component("comp1").physics("solid").feature("pblt1").feature("sblt4").selection().named("sel5");
    model.component("comp1").physics("solid").feature("pblt1").create("sblt5", "BoltSelection", 2);
    model.component("comp1").physics("solid").feature("pblt1").feature("sblt5").selection().named("sel6");
    model.component("comp1").physics("solid").create("cnt1", "SolidContact", 2);
    model.component("comp1").physics("solid").feature("cnt1").set("pairs", new String[]{"ap3"});
    model.component("comp1").physics("solid").feature("cnt1").set("ContactMethodCtrl", "AugmentedLagrange");
    model.component("comp1").physics("solid").feature("cnt1").set("tunedFor", "Speed");
    model.component("comp1").physics("solid").feature("cnt1").create("fric1", "Friction", 2);
    model.component("comp1").physics("solid").feature("cnt1").feature("fric1").set("mu_fric", 0.15);
    model.component("comp1").physics("solid").feature("cnt1").create("stb1", "ContactStabilization", 2);
    model.component("comp1").physics("solid").feature("cnt1").feature("stb1").set("method", "manual");
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().named("uni1");
    model.component("comp1").physics("solid").create("disp1", "Displacement0", 0);
    model.component("comp1").physics("solid").feature("disp1").selection().set(35);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("solid").create("sl1", "StressLinearization", -1);
    model.component("comp1").physics("solid").feature("sl1").selection("EdgeSelection").set(22);
    model.component("comp1").physics("solid").feature("sl1").selection("RefPointSelection").set(1);
    model.component("comp1").physics("solid").create("sl2", "StressLinearization", -1);
    model.component("comp1").physics("solid").feature("sl2").selection("EdgeSelection").set(28);
    model.component("comp1").physics("solid").feature("sl2").selection("RefPointSelection").set(1);
    model.component("comp1").physics("solid").create("sl3", "StressLinearization", -1);
    model.component("comp1").physics("solid").feature("sl3").selection("EdgeSelection").set(31);
    model.component("comp1").physics("solid").feature("sl3").selection("RefPointSelection").set(1);
    model.component("comp1").physics("solid").create("sl4", "StressLinearization", -1);
    model.component("comp1").physics("solid").feature("sl4").selection("EdgeSelection").set(114);
    model.component("comp1").physics("solid").feature("sl4").selection("RefPointSelection").set(1);
    model.component("comp1").physics("solid").create("sl5", "StressLinearization", -1);
    model.component("comp1").physics("solid").feature("sl5").selection("EdgeSelection").set(106);
    model.component("comp1").physics("solid").feature("sl5").selection("RefPointSelection").set(1);
    model.component("comp1").physics("solid").create("sl6", "StressLinearization", -1);
    model.component("comp1").physics("solid").feature("sl6").selection("EdgeSelection").set(109);
    model.component("comp1").physics("solid").feature("sl6").selection("RefPointSelection").set(1);
    model.component("comp1").physics("solid").create("secf1", "SectionForces", -1);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").physics("solid").feature("secf1").selection("BoundarySelection").set(19, 34);

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.component("comp1").physics("solid").feature("secf1").set("SymmetryPlane", true);
    model.component("comp1").physics("solid").feature("secf1").selection("SymmetrySelection").set(17);

    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("geom1_rot3_dom");
    model.component("comp1").mesh("mesh1").feature("swe1").selection("sourceface").set(59, 63, 67, 145, 149, 153);
    model.component("comp1").mesh("mesh1").feature("swe1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hauto", 3);
    model.component("comp1").mesh("mesh1").run("swe1");
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe2").selection().named("geom1_rot2_dom");
    model.component("comp1").mesh("mesh1").feature("swe2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("size1").set("hauto", 4);
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").run("swe2");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(10, 14, 17);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(25, 27, 33);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(30);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 20);
    model.component("comp1").mesh("mesh1").run("map1");
    model.component("comp1").mesh("mesh1").create("swe3", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe3").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe3").selection().set(2, 3, 4);
    model.component("comp1").mesh("mesh1").feature("swe3").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe3").feature("dis1").set("numelem", 30);
    model.component("comp1").mesh("mesh1").run("swe3");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(5, 41);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("swe4", "Sweep");
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("map2", "Map");
    model.component("comp1").mesh("mesh1").feature("map2").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("map2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").selection().set(1, 2);
    model.component("comp1").mesh("mesh1").feature("map2").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("bolt", "BoltPretension");
    model.study("std1").feature().move("bolt", 0);
    model.study("std1").feature("bolt").set("useadvanceddisable", true);
    model.study("std1").feature("bolt").set("disabledphysics", new String[]{"solid/bndl1", "solid/bndl2"});
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "pipeDiameter", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "pipeDiameter", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "lp", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0.2,0.2,1)", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-4");
    model.sol("sol1").feature("v2").feature("comp1_u").set("scaleval", "1e-4");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 5, 0);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.misesGp"});

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").create("def", "Deform");
    model.result("pg1").feature("vol1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("vol1").feature("def").set("scale", "1");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("expr", new String[]{"if(isdefined(solid.SImb),solid.SImb,NaN)"});
    model.result("pg1").feature("line1").set("threshold", "manual");
    model.result("pg1").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("line1").set("colortable", "Rainbow");
    model.result("pg1").feature("line1").set("colortabletrans", "none");
    model.result("pg1").feature("line1").set("colorscalemode", "linear");
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("tuberadiusscale", 1);
    model.result("pg1").feature("line1").set("radiusexpr", "2e-3*0.6833457388717638");
    model.result("pg1").feature("line1").create("def", "Deform");
    model.result("pg1").feature("line1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("line1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").feature("line1").set("inheritplot", "vol1");
    model.result("pg1").feature("line1").set("inheritcolor", false);
    model.result("pg1").feature("line1").set("inheritrange", false);
    model.result("pg1").feature("line1").set("colortable", "Traffic");
    model.result("pg1").feature("line1").set("descractive", true);
    model.result("pg1").feature("line1").set("descr", "\u5e94\u529b\u5f3a\u5ea6\uff0c\u819c\u52a0\u5f2f\u66f2");
    model.result("pg1").feature("line1").label("\u5e94\u529b\u5f3a\u5ea6");
    model.result("pg1").set("legendpos", "rightdouble");
    model.result().dataset().create("edg1", "Edge3D");
    model.result().dataset("edg1").set("data", "dset1");
    model.result().dataset("edg1").selection().geom("geom1", 1);
    model.result().dataset("edg1").selection().set(22);
    model.result().dataset("edg1").selection().inherit(false);
    model.result().dataset("edg1").selection().embedded(false);
    model.result().dataset("edg1").label("\u7ebf\u6027\u5316\u7ebf (\u5e94\u529b\u7ebf\u6027\u5316 1)");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "edg1");
    model.result("pg2").label("\u5e94\u529b\u7ebf\u6027\u5316 1 (solid)");
    model.result("pg2").set("legendpos", "middleright");
    model.result("pg2").set("data", "edg1");
    model.result("pg2").setIndex("looplevelinput", "last", 0);
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("expr", "solid.sl1.Slls22");
    model.result("pg2").feature("lngr1").label("\u5df2\u8ba1\u7b97");
    model.result("pg2").feature("lngr1").set("linestyle", "cyclereset");
    model.result("pg2").feature("lngr1").set("linecolor", "custom");
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").feature("lngr1").set("autoplotlabel", true);
    model.result("pg2").feature("lngr1").set("autosolution", false);
    model.result("pg2").feature("lngr1")
         .set("customlinecolor", new double[]{0.07058823853731155, 0.2235294133424759, 0.9529411792755127});
    model.result("pg2").create("lnsg1", "LineSegments");
    model.result("pg2").feature("lnsg1").label("\u819c\u5e94\u529b");
    model.result("pg2").feature("lnsg1").setIndex("xexpr", "solid.sl1.xs", 0);
    model.result("pg2").feature("lnsg1").setIndex("xexpr", "solid.sl1.xe", 1);
    model.result("pg2").feature("lnsg1").setIndex("yexpr", "solid.sl1.ySm22", 0);
    model.result("pg2").feature("lnsg1").setIndex("yexpr", "solid.sl1.ySm22", 1);
    model.result("pg2").feature("lnsg1").set("linestyle", "cyclereset");
    model.result("pg2").feature("lnsg1").set("linecolor", "custom");
    model.result("pg2").feature("lnsg1").set("legend", true);
    model.result("pg2").feature("lnsg1").set("autoplotlabel", true);
    model.result("pg2").feature("lnsg1").set("autosolution", false);
    model.result("pg2").feature("lnsg1")
         .set("customlinecolor", new double[]{0.1568627506494522, 0.7254902124404907, 0.24313725531101227});
    model.result("pg2").create("lnsg2", "LineSegments");
    model.result("pg2").feature("lnsg2").label("\u819c\u5e94\u529b\u52a0\u5f2f\u66f2\u5e94\u529b");
    model.result("pg2").feature("lnsg2").setIndex("xexpr", "solid.sl1.xs", 0);
    model.result("pg2").feature("lnsg2").setIndex("xexpr", "solid.sl1.xe", 1);
    model.result("pg2").feature("lnsg2").setIndex("yexpr", "solid.sl1.ySmbs22", 0);
    model.result("pg2").feature("lnsg2").setIndex("yexpr", "solid.sl1.ySmbe22", 1);
    model.result("pg2").feature("lnsg2").set("linestyle", "cyclereset");
    model.result("pg2").feature("lnsg2").set("linecolor", "custom");
    model.result("pg2").feature("lnsg2").set("legend", true);
    model.result("pg2").feature("lnsg2").set("autoplotlabel", true);
    model.result("pg2").feature("lnsg2").set("autosolution", false);
    model.result("pg2").feature("lnsg2")
         .set("customlinecolor", new double[]{0.8235294222831726, 0.019607843831181526, 0.019607843831181526});
    model.result().dataset().create("edg2", "Edge3D");
    model.result().dataset("edg2").set("data", "dset1");
    model.result().dataset("edg2").selection().geom("geom1", 1);
    model.result().dataset("edg2").selection().set(28);
    model.result().dataset("edg2").selection().inherit(false);
    model.result().dataset("edg2").selection().embedded(false);
    model.result().dataset("edg2").label("\u7ebf\u6027\u5316\u7ebf (\u5e94\u529b\u7ebf\u6027\u5316 2)");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "edg2");
    model.result("pg3").label("\u5e94\u529b\u7ebf\u6027\u5316 2 (solid)");
    model.result("pg3").set("legendpos", "middleright");
    model.result("pg3").set("data", "edg2");
    model.result("pg3").setIndex("looplevelinput", "last", 0);
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("expr", "solid.sl2.Slls22");
    model.result("pg3").feature("lngr1").label("\u5df2\u8ba1\u7b97");
    model.result("pg3").feature("lngr1").set("linestyle", "cyclereset");
    model.result("pg3").feature("lngr1").set("linecolor", "custom");
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").feature("lngr1").set("autoplotlabel", true);
    model.result("pg3").feature("lngr1").set("autosolution", false);
    model.result("pg3").feature("lngr1")
         .set("customlinecolor", new double[]{0.07058823853731155, 0.2235294133424759, 0.9529411792755127});
    model.result("pg3").create("lnsg1", "LineSegments");
    model.result("pg3").feature("lnsg1").label("\u819c\u5e94\u529b");
    model.result("pg3").feature("lnsg1").setIndex("xexpr", "solid.sl2.xs", 0);
    model.result("pg3").feature("lnsg1").setIndex("xexpr", "solid.sl2.xe", 1);
    model.result("pg3").feature("lnsg1").setIndex("yexpr", "solid.sl2.ySm22", 0);
    model.result("pg3").feature("lnsg1").setIndex("yexpr", "solid.sl2.ySm22", 1);
    model.result("pg3").feature("lnsg1").set("linestyle", "cyclereset");
    model.result("pg3").feature("lnsg1").set("linecolor", "custom");
    model.result("pg3").feature("lnsg1").set("legend", true);
    model.result("pg3").feature("lnsg1").set("autoplotlabel", true);
    model.result("pg3").feature("lnsg1").set("autosolution", false);
    model.result("pg3").feature("lnsg1")
         .set("customlinecolor", new double[]{0.1568627506494522, 0.7254902124404907, 0.24313725531101227});
    model.result("pg3").create("lnsg2", "LineSegments");
    model.result("pg3").feature("lnsg2").label("\u819c\u5e94\u529b\u52a0\u5f2f\u66f2\u5e94\u529b");
    model.result("pg3").feature("lnsg2").setIndex("xexpr", "solid.sl2.xs", 0);
    model.result("pg3").feature("lnsg2").setIndex("xexpr", "solid.sl2.xe", 1);
    model.result("pg3").feature("lnsg2").setIndex("yexpr", "solid.sl2.ySmbs22", 0);
    model.result("pg3").feature("lnsg2").setIndex("yexpr", "solid.sl2.ySmbe22", 1);
    model.result("pg3").feature("lnsg2").set("linestyle", "cyclereset");
    model.result("pg3").feature("lnsg2").set("linecolor", "custom");
    model.result("pg3").feature("lnsg2").set("legend", true);
    model.result("pg3").feature("lnsg2").set("autoplotlabel", true);
    model.result("pg3").feature("lnsg2").set("autosolution", false);
    model.result("pg3").feature("lnsg2")
         .set("customlinecolor", new double[]{0.8235294222831726, 0.019607843831181526, 0.019607843831181526});
    model.result().dataset().create("edg3", "Edge3D");
    model.result().dataset("edg3").set("data", "dset1");
    model.result().dataset("edg3").selection().geom("geom1", 1);
    model.result().dataset("edg3").selection().set(31);
    model.result().dataset("edg3").selection().inherit(false);
    model.result().dataset("edg3").selection().embedded(false);
    model.result().dataset("edg3").label("\u7ebf\u6027\u5316\u7ebf (\u5e94\u529b\u7ebf\u6027\u5316 3)");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "edg3");
    model.result("pg4").label("\u5e94\u529b\u7ebf\u6027\u5316 3 (solid)");
    model.result("pg4").set("legendpos", "middleright");
    model.result("pg4").set("data", "edg3");
    model.result("pg4").setIndex("looplevelinput", "last", 0);
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("expr", "solid.sl3.Slls22");
    model.result("pg4").feature("lngr1").label("\u5df2\u8ba1\u7b97");
    model.result("pg4").feature("lngr1").set("linestyle", "cyclereset");
    model.result("pg4").feature("lngr1").set("linecolor", "custom");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("autoplotlabel", true);
    model.result("pg4").feature("lngr1").set("autosolution", false);
    model.result("pg4").feature("lngr1")
         .set("customlinecolor", new double[]{0.07058823853731155, 0.2235294133424759, 0.9529411792755127});
    model.result("pg4").create("lnsg1", "LineSegments");
    model.result("pg4").feature("lnsg1").label("\u819c\u5e94\u529b");
    model.result("pg4").feature("lnsg1").setIndex("xexpr", "solid.sl3.xs", 0);
    model.result("pg4").feature("lnsg1").setIndex("xexpr", "solid.sl3.xe", 1);
    model.result("pg4").feature("lnsg1").setIndex("yexpr", "solid.sl3.ySm22", 0);
    model.result("pg4").feature("lnsg1").setIndex("yexpr", "solid.sl3.ySm22", 1);
    model.result("pg4").feature("lnsg1").set("linestyle", "cyclereset");
    model.result("pg4").feature("lnsg1").set("linecolor", "custom");
    model.result("pg4").feature("lnsg1").set("legend", true);
    model.result("pg4").feature("lnsg1").set("autoplotlabel", true);
    model.result("pg4").feature("lnsg1").set("autosolution", false);
    model.result("pg4").feature("lnsg1")
         .set("customlinecolor", new double[]{0.1568627506494522, 0.7254902124404907, 0.24313725531101227});
    model.result("pg4").create("lnsg2", "LineSegments");
    model.result("pg4").feature("lnsg2").label("\u819c\u5e94\u529b\u52a0\u5f2f\u66f2\u5e94\u529b");
    model.result("pg4").feature("lnsg2").setIndex("xexpr", "solid.sl3.xs", 0);
    model.result("pg4").feature("lnsg2").setIndex("xexpr", "solid.sl3.xe", 1);
    model.result("pg4").feature("lnsg2").setIndex("yexpr", "solid.sl3.ySmbs22", 0);
    model.result("pg4").feature("lnsg2").setIndex("yexpr", "solid.sl3.ySmbe22", 1);
    model.result("pg4").feature("lnsg2").set("linestyle", "cyclereset");
    model.result("pg4").feature("lnsg2").set("linecolor", "custom");
    model.result("pg4").feature("lnsg2").set("legend", true);
    model.result("pg4").feature("lnsg2").set("autoplotlabel", true);
    model.result("pg4").feature("lnsg2").set("autosolution", false);
    model.result("pg4").feature("lnsg2")
         .set("customlinecolor", new double[]{0.8235294222831726, 0.019607843831181526, 0.019607843831181526});
    model.result().dataset().create("edg4", "Edge3D");
    model.result().dataset("edg4").set("data", "dset1");
    model.result().dataset("edg4").selection().geom("geom1", 1);
    model.result().dataset("edg4").selection().set(114);
    model.result().dataset("edg4").selection().inherit(false);
    model.result().dataset("edg4").selection().embedded(false);
    model.result().dataset("edg4").label("\u7ebf\u6027\u5316\u7ebf (\u5e94\u529b\u7ebf\u6027\u5316 4)");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "edg4");
    model.result("pg5").label("\u5e94\u529b\u7ebf\u6027\u5316 4 (solid)");
    model.result("pg5").set("legendpos", "middleright");
    model.result("pg5").set("data", "edg4");
    model.result("pg5").setIndex("looplevelinput", "last", 0);
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("expr", "solid.sl4.Slls22");
    model.result("pg5").feature("lngr1").label("\u5df2\u8ba1\u7b97");
    model.result("pg5").feature("lngr1").set("linestyle", "cyclereset");
    model.result("pg5").feature("lngr1").set("linecolor", "custom");
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("autoplotlabel", true);
    model.result("pg5").feature("lngr1").set("autosolution", false);
    model.result("pg5").feature("lngr1")
         .set("customlinecolor", new double[]{0.07058823853731155, 0.2235294133424759, 0.9529411792755127});
    model.result("pg5").create("lnsg1", "LineSegments");
    model.result("pg5").feature("lnsg1").label("\u819c\u5e94\u529b");
    model.result("pg5").feature("lnsg1").setIndex("xexpr", "solid.sl4.xs", 0);
    model.result("pg5").feature("lnsg1").setIndex("xexpr", "solid.sl4.xe", 1);
    model.result("pg5").feature("lnsg1").setIndex("yexpr", "solid.sl4.ySm22", 0);
    model.result("pg5").feature("lnsg1").setIndex("yexpr", "solid.sl4.ySm22", 1);
    model.result("pg5").feature("lnsg1").set("linestyle", "cyclereset");
    model.result("pg5").feature("lnsg1").set("linecolor", "custom");
    model.result("pg5").feature("lnsg1").set("legend", true);
    model.result("pg5").feature("lnsg1").set("autoplotlabel", true);
    model.result("pg5").feature("lnsg1").set("autosolution", false);
    model.result("pg5").feature("lnsg1")
         .set("customlinecolor", new double[]{0.1568627506494522, 0.7254902124404907, 0.24313725531101227});
    model.result("pg5").create("lnsg2", "LineSegments");
    model.result("pg5").feature("lnsg2").label("\u819c\u5e94\u529b\u52a0\u5f2f\u66f2\u5e94\u529b");
    model.result("pg5").feature("lnsg2").setIndex("xexpr", "solid.sl4.xs", 0);
    model.result("pg5").feature("lnsg2").setIndex("xexpr", "solid.sl4.xe", 1);
    model.result("pg5").feature("lnsg2").setIndex("yexpr", "solid.sl4.ySmbs22", 0);
    model.result("pg5").feature("lnsg2").setIndex("yexpr", "solid.sl4.ySmbe22", 1);
    model.result("pg5").feature("lnsg2").set("linestyle", "cyclereset");
    model.result("pg5").feature("lnsg2").set("linecolor", "custom");
    model.result("pg5").feature("lnsg2").set("legend", true);
    model.result("pg5").feature("lnsg2").set("autoplotlabel", true);
    model.result("pg5").feature("lnsg2").set("autosolution", false);
    model.result("pg5").feature("lnsg2")
         .set("customlinecolor", new double[]{0.8235294222831726, 0.019607843831181526, 0.019607843831181526});
    model.result().dataset().create("edg5", "Edge3D");
    model.result().dataset("edg5").set("data", "dset1");
    model.result().dataset("edg5").selection().geom("geom1", 1);
    model.result().dataset("edg5").selection().set(106);
    model.result().dataset("edg5").selection().inherit(false);
    model.result().dataset("edg5").selection().embedded(false);
    model.result().dataset("edg5").label("\u7ebf\u6027\u5316\u7ebf (\u5e94\u529b\u7ebf\u6027\u5316 5)");
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").set("data", "edg5");
    model.result("pg6").label("\u5e94\u529b\u7ebf\u6027\u5316 5 (solid)");
    model.result("pg6").set("legendpos", "middleright");
    model.result("pg6").set("data", "edg5");
    model.result("pg6").setIndex("looplevelinput", "last", 0);
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("expr", "solid.sl5.Slls22");
    model.result("pg6").feature("lngr1").label("\u5df2\u8ba1\u7b97");
    model.result("pg6").feature("lngr1").set("linestyle", "cyclereset");
    model.result("pg6").feature("lngr1").set("linecolor", "custom");
    model.result("pg6").feature("lngr1").set("legend", true);
    model.result("pg6").feature("lngr1").set("autoplotlabel", true);
    model.result("pg6").feature("lngr1").set("autosolution", false);
    model.result("pg6").feature("lngr1")
         .set("customlinecolor", new double[]{0.07058823853731155, 0.2235294133424759, 0.9529411792755127});
    model.result("pg6").create("lnsg1", "LineSegments");
    model.result("pg6").feature("lnsg1").label("\u819c\u5e94\u529b");
    model.result("pg6").feature("lnsg1").setIndex("xexpr", "solid.sl5.xs", 0);
    model.result("pg6").feature("lnsg1").setIndex("xexpr", "solid.sl5.xe", 1);
    model.result("pg6").feature("lnsg1").setIndex("yexpr", "solid.sl5.ySm22", 0);
    model.result("pg6").feature("lnsg1").setIndex("yexpr", "solid.sl5.ySm22", 1);
    model.result("pg6").feature("lnsg1").set("linestyle", "cyclereset");
    model.result("pg6").feature("lnsg1").set("linecolor", "custom");
    model.result("pg6").feature("lnsg1").set("legend", true);
    model.result("pg6").feature("lnsg1").set("autoplotlabel", true);
    model.result("pg6").feature("lnsg1").set("autosolution", false);
    model.result("pg6").feature("lnsg1")
         .set("customlinecolor", new double[]{0.1568627506494522, 0.7254902124404907, 0.24313725531101227});
    model.result("pg6").create("lnsg2", "LineSegments");
    model.result("pg6").feature("lnsg2").label("\u819c\u5e94\u529b\u52a0\u5f2f\u66f2\u5e94\u529b");
    model.result("pg6").feature("lnsg2").setIndex("xexpr", "solid.sl5.xs", 0);
    model.result("pg6").feature("lnsg2").setIndex("xexpr", "solid.sl5.xe", 1);
    model.result("pg6").feature("lnsg2").setIndex("yexpr", "solid.sl5.ySmbs22", 0);
    model.result("pg6").feature("lnsg2").setIndex("yexpr", "solid.sl5.ySmbe22", 1);
    model.result("pg6").feature("lnsg2").set("linestyle", "cyclereset");
    model.result("pg6").feature("lnsg2").set("linecolor", "custom");
    model.result("pg6").feature("lnsg2").set("legend", true);
    model.result("pg6").feature("lnsg2").set("autoplotlabel", true);
    model.result("pg6").feature("lnsg2").set("autosolution", false);
    model.result("pg6").feature("lnsg2")
         .set("customlinecolor", new double[]{0.8235294222831726, 0.019607843831181526, 0.019607843831181526});
    model.result().dataset().create("edg6", "Edge3D");
    model.result().dataset("edg6").set("data", "dset1");
    model.result().dataset("edg6").selection().geom("geom1", 1);
    model.result().dataset("edg6").selection().set(109);
    model.result().dataset("edg6").selection().inherit(false);
    model.result().dataset("edg6").selection().embedded(false);
    model.result().dataset("edg6").label("\u7ebf\u6027\u5316\u7ebf (\u5e94\u529b\u7ebf\u6027\u5316 6)");
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").set("data", "edg6");
    model.result("pg7").label("\u5e94\u529b\u7ebf\u6027\u5316 6 (solid)");
    model.result("pg7").set("legendpos", "middleright");
    model.result("pg7").set("data", "edg6");
    model.result("pg7").setIndex("looplevelinput", "last", 0);
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("expr", "solid.sl6.Slls22");
    model.result("pg7").feature("lngr1").label("\u5df2\u8ba1\u7b97");
    model.result("pg7").feature("lngr1").set("linestyle", "cyclereset");
    model.result("pg7").feature("lngr1").set("linecolor", "custom");
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").feature("lngr1").set("autoplotlabel", true);
    model.result("pg7").feature("lngr1").set("autosolution", false);
    model.result("pg7").feature("lngr1")
         .set("customlinecolor", new double[]{0.07058823853731155, 0.2235294133424759, 0.9529411792755127});
    model.result("pg7").create("lnsg1", "LineSegments");
    model.result("pg7").feature("lnsg1").label("\u819c\u5e94\u529b");
    model.result("pg7").feature("lnsg1").setIndex("xexpr", "solid.sl6.xs", 0);
    model.result("pg7").feature("lnsg1").setIndex("xexpr", "solid.sl6.xe", 1);
    model.result("pg7").feature("lnsg1").setIndex("yexpr", "solid.sl6.ySm22", 0);
    model.result("pg7").feature("lnsg1").setIndex("yexpr", "solid.sl6.ySm22", 1);
    model.result("pg7").feature("lnsg1").set("linestyle", "cyclereset");
    model.result("pg7").feature("lnsg1").set("linecolor", "custom");
    model.result("pg7").feature("lnsg1").set("legend", true);
    model.result("pg7").feature("lnsg1").set("autoplotlabel", true);
    model.result("pg7").feature("lnsg1").set("autosolution", false);
    model.result("pg7").feature("lnsg1")
         .set("customlinecolor", new double[]{0.1568627506494522, 0.7254902124404907, 0.24313725531101227});
    model.result("pg7").create("lnsg2", "LineSegments");
    model.result("pg7").feature("lnsg2").label("\u819c\u5e94\u529b\u52a0\u5f2f\u66f2\u5e94\u529b");
    model.result("pg7").feature("lnsg2").setIndex("xexpr", "solid.sl6.xs", 0);
    model.result("pg7").feature("lnsg2").setIndex("xexpr", "solid.sl6.xe", 1);
    model.result("pg7").feature("lnsg2").setIndex("yexpr", "solid.sl6.ySmbs22", 0);
    model.result("pg7").feature("lnsg2").setIndex("yexpr", "solid.sl6.ySmbe22", 1);
    model.result("pg7").feature("lnsg2").set("linestyle", "cyclereset");
    model.result("pg7").feature("lnsg2").set("linecolor", "custom");
    model.result("pg7").feature("lnsg2").set("legend", true);
    model.result("pg7").feature("lnsg2").set("autoplotlabel", true);
    model.result("pg7").feature("lnsg2").set("autosolution", false);
    model.result("pg7").feature("lnsg2")
         .set("customlinecolor", new double[]{0.8235294222831726, 0.019607843831181526, 0.019607843831181526});
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").set("data", "dset1");
    model.result().evaluationGroup("eg1")
         .label("\u87ba\u6813\u529b: \u87ba\u6813\u9884\u7d27\u529b 1 (\u7814\u7a76 1) (solid)");
    model.result().evaluationGroup("eg1").set("transpose", true);
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").label("Bolt_1");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp1.solid.pblt1.sblt1.F_bolt", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u87ba\u6813\u529b", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "comp1.solid.pblt1.sblt1.F_shear", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u87ba\u6813\u526a\u529b", 1);
    model.result().evaluationGroup("eg1").create("gev2", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev2").label("Bolt_2");
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("expr", "comp1.solid.pblt1.sblt2.F_bolt", 0);
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("descr", "\u87ba\u6813\u529b", 0);
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("expr", "comp1.solid.pblt1.sblt2.F_shear", 1);
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("descr", "\u87ba\u6813\u526a\u529b", 1);
    model.result().evaluationGroup("eg1").create("gev3", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev3").label("Bolt_3");
    model.result().evaluationGroup("eg1").feature("gev3").setIndex("expr", "comp1.solid.pblt1.sblt3.F_bolt", 0);
    model.result().evaluationGroup("eg1").feature("gev3").setIndex("descr", "\u87ba\u6813\u529b", 0);
    model.result().evaluationGroup("eg1").feature("gev3").setIndex("expr", "comp1.solid.pblt1.sblt3.F_shear", 1);
    model.result().evaluationGroup("eg1").feature("gev3").setIndex("descr", "\u87ba\u6813\u526a\u529b", 1);
    model.result().evaluationGroup("eg1").create("gev4", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev4").label("Bolt_4");
    model.result().evaluationGroup("eg1").feature("gev4").setIndex("expr", "comp1.solid.pblt1.sblt4.F_bolt", 0);
    model.result().evaluationGroup("eg1").feature("gev4").setIndex("descr", "\u87ba\u6813\u529b", 0);
    model.result().evaluationGroup("eg1").feature("gev4").setIndex("expr", "comp1.solid.pblt1.sblt4.F_shear", 1);
    model.result().evaluationGroup("eg1").feature("gev4").setIndex("descr", "\u87ba\u6813\u526a\u529b", 1);
    model.result().evaluationGroup("eg1").create("gev5", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev5").label("Bolt_5");
    model.result().evaluationGroup("eg1").feature("gev5").setIndex("expr", "comp1.solid.pblt1.sblt5.F_bolt", 0);
    model.result().evaluationGroup("eg1").feature("gev5").setIndex("descr", "\u87ba\u6813\u529b", 0);
    model.result().evaluationGroup("eg1").feature("gev5").setIndex("expr", "comp1.solid.pblt1.sblt5.F_shear", 1);
    model.result().evaluationGroup("eg1").feature("gev5").setIndex("descr", "\u87ba\u6813\u526a\u529b", 1);
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup().create("eg2", "EvaluationGroup");
    model.result().evaluationGroup("eg2").label("\u622a\u9762\u529b (secf1)");
    model.result().evaluationGroup("eg2").set("showlooplevelinput", new String[]{"off", "off", "off"});
    model.result().evaluationGroup("eg2").set("transpose", true);
    model.result().evaluationGroup("eg2").set("data", "dset1");
    model.result().evaluationGroup("eg2").feature().create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg2").feature("gev1").label("\u622a\u9762\u529b");
    model.result().evaluationGroup("eg2").feature("gev1").set("showsolutionparams", "on");
    model.result().evaluationGroup("eg2").feature("gev1")
         .set("expr", new String[]{"solid.secf1.N", "solid.secf1.T1", "solid.secf1.T2", "solid.secf1.Mt", "solid.secf1.M1", "solid.secf1.M2"});
    model.result().evaluationGroup("eg2").feature("gev1").set("showsolutionparams", "on");
    model.result().evaluationGroup("eg2").feature("gev1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").label("\u5e94\u529b\uff0c\u87ba\u6813\u9884\u7d27");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("expr", "solid.sGpzz");
    model.result("pg1").feature("vol1").set("descr", "\u5e94\u529b\u5f20\u91cf\uff0czz \u5206\u91cf");
    model.result("pg1").feature("vol1").set("rangecoloractive", true);
    model.result("pg1").feature("vol1").set("rangecolormin", "-3e8");
    model.result("pg1").feature("vol1").set("rangecolormax", "5e8");
    model.result("pg1").run();
    model.result("pg1").feature("vol1").feature("def").set("scale", 20);
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").set("applytosamedims", true);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().configuration().create("gsty1", "GraphStyle");
    model.result().configuration("gsty1").set("linewidth", 2);
    model.result().configuration("gsty1").set("autosolution", false);
    model.result().configuration("gsty1").set("autoplotlabel", true);
    model.result().configuration("gsty1").set("autopoint", false);
    model.result("pg2").run();
    model.result("pg2").set("titletype", "manual");
    model.result("pg2")
         .set("title", "\u7ebf\u7ed3\u679c\u56fe\uff1a\u5e94\u529b\u5f20\u91cf\uff0c\u7ebf\u6027\u5316\u7ebf\u5750\u6807\u7cfb\uff0c33 \u5206\u91cf (MPa)");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u5e94\u529b (MPa)");
    model.result("pg2").set("styleconfig", "gsty1");
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").feature("lngr1").set("expr", "solid.sl1.Slls33");
    model.result("pg3").feature("lngr1").set("expr", "solid.sl2.Slls33");
    model.result("pg4").feature("lngr1").set("expr", "solid.sl3.Slls33");
    model.result("pg5").feature("lngr1").set("expr", "solid.sl4.Slls33");
    model.result("pg6").feature("lngr1").set("expr", "solid.sl5.Slls33");
    model.result("pg7").feature("lngr1").set("expr", "solid.sl6.Slls33");
    model.result("pg2").run();
    model.result("pg2").feature("lnsg1").setIndex("yexpr", "solid.sl1.ySm33", 0);
    model.result("pg2").feature("lnsg1").setIndex("yexpr", "solid.sl1.ySm33", 1);
    model.result("pg3").feature("lnsg1").setIndex("yexpr", "solid.sl2.ySm33", 0);
    model.result("pg3").feature("lnsg1").setIndex("yexpr", "solid.sl2.ySm33", 1);
    model.result("pg4").feature("lnsg1").setIndex("yexpr", "solid.sl3.ySm33", 0);
    model.result("pg4").feature("lnsg1").setIndex("yexpr", "solid.sl3.ySm33", 1);
    model.result("pg5").feature("lnsg1").setIndex("yexpr", "solid.sl4.ySm33", 0);
    model.result("pg5").feature("lnsg1").setIndex("yexpr", "solid.sl4.ySm33", 1);
    model.result("pg6").feature("lnsg1").setIndex("yexpr", "solid.sl5.ySm33", 0);
    model.result("pg6").feature("lnsg1").setIndex("yexpr", "solid.sl5.ySm33", 1);
    model.result("pg7").feature("lnsg1").setIndex("yexpr", "solid.sl6.ySm33", 0);
    model.result("pg7").feature("lnsg1").setIndex("yexpr", "solid.sl6.ySm33", 1);
    model.result("pg2").run();
    model.result("pg2").feature("lnsg2").setIndex("yexpr", "solid.sl1.ySmbs33", 0);
    model.result("pg3").feature("lnsg2").setIndex("yexpr", "solid.sl2.ySmbs33", 0);
    model.result("pg4").feature("lnsg2").setIndex("yexpr", "solid.sl3.ySmbs33", 0);
    model.result("pg5").feature("lnsg2").setIndex("yexpr", "solid.sl4.ySmbs33", 0);
    model.result("pg6").feature("lnsg2").setIndex("yexpr", "solid.sl5.ySmbs33", 0);
    model.result("pg7").feature("lnsg2").setIndex("yexpr", "solid.sl6.ySmbs33", 0);
    model.result("pg2").run();
    model.result("pg2").feature("lnsg2").setIndex("yexpr", "solid.sl1.ySmbe33", 1);
    model.result("pg3").feature("lnsg2").setIndex("yexpr", "solid.sl2.ySmbe33", 1);
    model.result("pg4").feature("lnsg2").setIndex("yexpr", "solid.sl3.ySmbe33", 1);
    model.result("pg5").feature("lnsg2").setIndex("yexpr", "solid.sl4.ySmbe33", 1);
    model.result("pg6").feature("lnsg2").setIndex("yexpr", "solid.sl5.ySmbe33", 1);
    model.result("pg7").feature("lnsg2").setIndex("yexpr", "solid.sl6.ySmbe33", 1);

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").set("styleconfig", "gsty1");
    model.result("pg3").set("legendpos", "upperleft");
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").set("styleconfig", "gsty1");
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").set("styleconfig", "gsty1");
    model.result("pg5").set("legendpos", "upperleft");
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").set("styleconfig", "gsty1");
    model.result("pg6").set("legendpos", "upperleft");
    model.result("pg6").run();
    model.result("pg7").run();
    model.result("pg7").set("styleconfig", "gsty1");
    model.result("pg7").set("legendpos", "upperleft");
    model.result("pg7").run();
    model.result().evaluationGroup("eg2").run();
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").label("\u622a\u9762\u529b (secf1)");
    model.result("pg8").set("titletype", "label");
    model.result("pg8").feature().create("pttraj1", "PointTrajectories");
    model.result("pg8").feature("pttraj1").label("\u8f74\u5411\u529b");
    model.result("pg8").feature("pttraj1")
         .set("expr", new String[]{"solid.secf1.xcX", "solid.secf1.xcY", "solid.secf1.xcZ"});
    model.result("pg8").feature("pttraj1").set("titletype", "none");
    model.result("pg8").feature("pttraj1").set("linetype", "none");
    model.result("pg8").feature("pttraj1").set("pointtype", "arrow");
    model.result("pg8").feature("pttraj1")
         .set("arrowexpr", new String[]{"solid.secf1.N*solid.secf1.nX", "solid.secf1.N*solid.secf1.nY", "solid.secf1.N*solid.secf1.nZ"});
    model.result("pg8").feature("pttraj1").set("showsolutionparams", "on");
    model.result("pg8").feature("pttraj1").set("data", "parent");
    model.result("pg8").feature().create("pttraj2", "PointTrajectories");
    model.result("pg8").feature("pttraj2").label("\u6cbf\u7b2c 1 \u8f74\u7684\u526a\u529b");
    model.result("pg8").feature("pttraj2")
         .set("expr", new String[]{"solid.secf1.xcX", "solid.secf1.xcY", "solid.secf1.xcZ"});
    model.result("pg8").feature("pttraj2").set("titletype", "none");
    model.result("pg8").feature("pttraj2").set("linetype", "none");
    model.result("pg8").feature("pttraj2").set("pointtype", "arrow");
    model.result("pg8").feature("pttraj2").set("pointcolor", "green");
    model.result("pg8").feature("pttraj2")
         .set("arrowexpr", new String[]{"solid.secf1.T1*solid.secf1.t1X", "solid.secf1.T1*solid.secf1.t1Y", "solid.secf1.T1*solid.secf1.t1Z"});
    model.result("pg8").feature("pttraj2").set("inheritcolor", false);
    model.result("pg8").feature("pttraj2").set("inheritrange", false);
    model.result("pg8").feature("pttraj2").set("showsolutionparams", "on");
    model.result("pg8").feature("pttraj2").set("data", "parent");
    model.result("pg8").feature("pttraj2").set("inheritplot", "pttraj1");
    model.result("pg8").feature().create("pttraj3", "PointTrajectories");
    model.result("pg8").feature("pttraj3").label("\u6cbf\u7b2c 2 \u8f74\u7684\u526a\u529b");
    model.result("pg8").feature("pttraj3")
         .set("expr", new String[]{"solid.secf1.xcX", "solid.secf1.xcY", "solid.secf1.xcZ"});
    model.result("pg8").feature("pttraj3").set("titletype", "none");
    model.result("pg8").feature("pttraj3").set("linetype", "none");
    model.result("pg8").feature("pttraj3").set("pointtype", "arrow");
    model.result("pg8").feature("pttraj3").set("pointcolor", "blue");
    model.result("pg8").feature("pttraj3")
         .set("arrowexpr", new String[]{"solid.secf1.T2*solid.secf1.t2X", "solid.secf1.T2*solid.secf1.t2Y", "solid.secf1.T2*solid.secf1.t2Z"});
    model.result("pg8").feature("pttraj3").set("inheritcolor", false);
    model.result("pg8").feature("pttraj3").set("inheritrange", false);
    model.result("pg8").feature("pttraj3").set("showsolutionparams", "on");
    model.result("pg8").feature("pttraj3").set("data", "parent");
    model.result("pg8").feature("pttraj3").set("inheritplot", "pttraj1");
    model.result("pg8").feature().create("ann1", "Annotation");
    model.result("pg8").feature("ann1")
         .set("text", "N = eval(solid.secf1.N) \\\\ T1 = eval(solid.secf1.T1) \\\\ T2 =  eval(solid.secf1.T2)");
    model.result("pg8").feature("ann1").set("latexmarkup", true);
    model.result("pg8").feature("ann1").set("level", "global");
    model.result("pg8").feature("ann1").set("posxexpr", "solid.secf1.xcX");
    model.result("pg8").feature("ann1").set("posyexpr", "solid.secf1.xcY");
    model.result("pg8").feature("ann1").set("poszexpr", "solid.secf1.xcZ");
    model.result("pg8").feature("ann1").set("showpoint", false);
    model.result("pg8").feature("ann1").set("backgroundcolor", "fromtheme");
    model.result("pg8").feature("ann1").set("showframe", true);
    model.result("pg8").feature("ann1").set("showsolutionparams", "on");
    model.result("pg8").feature("ann1").set("data", "parent");
    model.result("pg8").label("\u622a\u9762\u529b (secf1)");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").label("\u622a\u9762\u529b\u77e9 (secf1)");
    model.result("pg9").set("titletype", "label");
    model.result("pg9").feature().create("pttraj1", "PointTrajectories");
    model.result("pg9").feature("pttraj1").label("\u626d\u77e9");
    model.result("pg9").feature("pttraj1")
         .set("expr", new String[]{"solid.secf1.xcX", "solid.secf1.xcY", "solid.secf1.xcZ"});
    model.result("pg9").feature("pttraj1").set("titletype", "none");
    model.result("pg9").feature("pttraj1").set("linetype", "none");
    model.result("pg9").feature("pttraj1").set("pointtype", "arrow");
    model.result("pg9").feature("pttraj1")
         .set("arrowexpr", new String[]{"solid.secf1.Mt*solid.secf1.nX", "solid.secf1.Mt*solid.secf1.nY", "solid.secf1.Mt*solid.secf1.nZ"});
    model.result("pg9").feature("pttraj1").set("arrowtype", "double");
    model.result("pg9").feature("pttraj1").set("showsolutionparams", "on");
    model.result("pg9").feature("pttraj1").set("data", "parent");
    model.result("pg9").feature().create("pttraj2", "PointTrajectories");
    model.result("pg9").feature("pttraj2").label("\u7ed5\u7b2c 1 \u8f74\u7684\u5f2f\u77e9");
    model.result("pg9").feature("pttraj2")
         .set("expr", new String[]{"solid.secf1.xcX", "solid.secf1.xcY", "solid.secf1.xcZ"});
    model.result("pg9").feature("pttraj2").set("titletype", "none");
    model.result("pg9").feature("pttraj2").set("linetype", "none");
    model.result("pg9").feature("pttraj2").set("pointtype", "arrow");
    model.result("pg9").feature("pttraj2").set("pointcolor", "green");
    model.result("pg9").feature("pttraj2")
         .set("arrowexpr", new String[]{"solid.secf1.M1*solid.secf1.t1X", "solid.secf1.M1*solid.secf1.t1Y", "solid.secf1.M1*solid.secf1.t1Z"});
    model.result("pg9").feature("pttraj2").set("arrowtype", "double");
    model.result("pg9").feature("pttraj2").set("inheritcolor", false);
    model.result("pg9").feature("pttraj2").set("inheritrange", false);
    model.result("pg9").feature("pttraj2").set("showsolutionparams", "on");
    model.result("pg9").feature("pttraj2").set("data", "parent");
    model.result("pg9").feature("pttraj2").set("inheritplot", "pttraj1");
    model.result("pg9").feature().create("pttraj3", "PointTrajectories");
    model.result("pg9").feature("pttraj3").label("\u7ed5\u7b2c 2 \u8f74\u7684\u5f2f\u77e9");
    model.result("pg9").feature("pttraj3")
         .set("expr", new String[]{"solid.secf1.xcX", "solid.secf1.xcY", "solid.secf1.xcZ"});
    model.result("pg9").feature("pttraj3").set("titletype", "none");
    model.result("pg9").feature("pttraj3").set("linetype", "none");
    model.result("pg9").feature("pttraj3").set("pointtype", "arrow");
    model.result("pg9").feature("pttraj3").set("pointcolor", "blue");
    model.result("pg9").feature("pttraj3")
         .set("arrowexpr", new String[]{"solid.secf1.M2*solid.secf1.t2X", "solid.secf1.M2*solid.secf1.t2Y", "solid.secf1.M2*solid.secf1.t2Z"});
    model.result("pg9").feature("pttraj3").set("arrowtype", "double");
    model.result("pg9").feature("pttraj3").set("inheritcolor", false);
    model.result("pg9").feature("pttraj3").set("inheritrange", false);
    model.result("pg9").feature("pttraj3").set("showsolutionparams", "on");
    model.result("pg9").feature("pttraj3").set("data", "parent");
    model.result("pg9").feature("pttraj3").set("inheritplot", "pttraj1");
    model.result("pg9").feature().create("ann1", "Annotation");
    model.result("pg9").feature("ann1")
         .set("text", "Mt = eval(solid.secf1.Mt) \\\\ M1 = eval(solid.secf1.M1) \\\\ M2 =  eval(solid.secf1.M2)");
    model.result("pg9").feature("ann1").set("latexmarkup", true);
    model.result("pg9").feature("ann1").set("level", "global");
    model.result("pg9").feature("ann1").set("posxexpr", "solid.secf1.xcX");
    model.result("pg9").feature("ann1").set("posyexpr", "solid.secf1.xcY");
    model.result("pg9").feature("ann1").set("poszexpr", "solid.secf1.xcZ");
    model.result("pg9").feature("ann1").set("showpoint", false);
    model.result("pg9").feature("ann1").set("backgroundcolor", "fromtheme");
    model.result("pg9").feature("ann1").set("showframe", true);
    model.result("pg9").feature("ann1").set("showsolutionparams", "on");
    model.result("pg9").feature("ann1").set("data", "parent");
    model.result("pg9").label("\u622a\u9762\u529b\u77e9 (secf1)");
    model.result("pg9").run();
    model.result("pg8").run();
    model.result("pg9").run();
    model.result("pg9").feature("ann1").set("anchorpoint", "lowerright");
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").run();
    model.result("pg10").set("edges", false);
    model.result("pg10").label("\u5e94\u529b");
    model.result("pg10").set("legendpos", "rightdouble");
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", "solid.misesGp");
    model.result("pg10").feature("surf1").set("rangecoloractive", true);
    model.result("pg10").feature("surf1").set("rangecolormax", 400);
    model.result("pg10").feature("surf1").set("colortable", "Prism");
    model.result("pg10").feature("surf1").create("def1", "Deform");
    model.result("pg10").run();
    model.result("pg10").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg10").feature("surf1").feature("def1").set("scale", 30);
    model.result("pg10").run();
    model.result("pg10").create("line1", "Line");
    model.result("pg10").feature("line1").set("inheritplot", "surf1");
    model.result("pg10").feature("line1").set("inheritcolor", false);
    model.result("pg10").feature("line1").set("inheritrange", false);
    model.result("pg10").feature("line1").set("expr", "solid.SImb");
    model.result("pg10").feature("line1").set("descr", "\u5e94\u529b\u5f3a\u5ea6\uff0c\u819c\u52a0\u5f2f\u66f2");
    model.result("pg10").feature("line1").set("linetype", "tube");
    model.result("pg10").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg10").feature("line1").set("tuberadiusscale", 0.001);
    model.result("pg10").feature("line1").set("colortable", "Traffic");
    model.result("pg10").feature("line1").create("def1", "Deform");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").create("surf2", "Surface");
    model.result("pg10").feature("surf2").set("titletype", "none");
    model.result("pg10").feature("surf2").set("coloring", "uniform");
    model.result("pg10").feature("surf2").set("color", "black");
    model.result("pg10").feature("surf2").set("wireframe", true);
    model.result("pg10").feature("surf2").set("inheritplot", "surf1");
    model.result("pg10").feature("surf2").set("inheritcolor", false);
    model.result("pg10").feature("surf2").set("inheritrange", false);
    model.result("pg10").feature("surf2").create("def1", "Deform");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").label("\u87ba\u6813\u529b");
    model.result("pg11").create("glob1", "Global");
    model.result("pg11").feature("glob1").set("markerpos", "datapoints");
    model.result("pg11").feature("glob1").set("linewidth", "preference");
    model.result("pg11").feature("glob1").set("expr", new String[]{"solid.pblt1.sblt1.F_bolt"});
    model.result("pg11").feature("glob1").set("descr", new String[]{"\u87ba\u6813\u529b"});
    model.result("pg11").feature("glob1").set("unit", new String[]{"N"});
    model.result("pg11").feature("glob1").setIndex("unit", "kN", 0);
    model.result("pg11").feature("glob1").setIndex("descr", "\u87ba\u6813 1 \u4e2d\u7684\u529b", 0);
    model.result("pg11").feature("glob1").setIndex("expr", "solid.pblt1.sblt2.F_bolt", 1);
    model.result("pg11").feature("glob1").setIndex("unit", "kN", 1);
    model.result("pg11").feature("glob1").setIndex("descr", "\u87ba\u6813 2 \u4e2d\u7684\u529b", 1);
    model.result("pg11").feature("glob1").setIndex("expr", "solid.pblt1.sblt3.F_bolt", 2);
    model.result("pg11").feature("glob1").setIndex("unit", "kN", 2);
    model.result("pg11").feature("glob1").setIndex("descr", "\u87ba\u6813 3 \u4e2d\u7684\u529b", 2);
    model.result("pg11").feature("glob1").setIndex("expr", "solid.pblt1.sblt4.F_bolt", 3);
    model.result("pg11").feature("glob1").setIndex("unit", "kN", 3);
    model.result("pg11").feature("glob1").setIndex("descr", "\u87ba\u6813 4 \u4e2d\u7684\u529b", 3);
    model.result("pg11").feature("glob1").setIndex("expr", "solid.pblt1.sblt5.F_bolt", 4);
    model.result("pg11").feature("glob1").setIndex("unit", "kN", 4);
    model.result("pg11").feature("glob1").setIndex("descr", "\u87ba\u6813 5 \u4e2d\u7684\u529b", 4);
    model.result("pg11").feature("glob1").set("legend", false);
    model.result("pg11").feature("glob1").set("linewidth", 2);
    model.result("pg11").feature("glob1").set("xdata", "expr");
    model.result("pg11").feature("glob1").set("xdataexpr", "bendingMoment*lp/1000");
    model.result("pg11").run();
    model.result("pg11").set("xlabelactive", true);
    model.result("pg11").set("xlabel", "\u7ba1\u9053\u4e2d\u7684\u5f2f\u77e9 (kNm)");
    model.result("pg11").set("ylabelactive", true);
    model.result("pg11").set("ylabel", "\u87ba\u6813\u4e2d\u7684\u8f74\u5411\u529b (kN)");
    model.result("pg11").run();
    model.result("pg11").feature("glob1").set("legend", true);
    model.result("pg11").run();
    model.result("pg11").set("titletype", "none");
    model.result("pg11").set("legendpos", "upperleft");
    model.result().create("pg12", "PlotGroup1D");
    model.result("pg12").run();
    model.result("pg12").label("\u87ba\u6813\u5e94\u529b");
    model.result("pg12").create("ptgr1", "PointGraph");
    model.result("pg12").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg12").feature("ptgr1").set("linewidth", "preference");
    model.result("pg12").feature("ptgr1").selection().set(311, 328);
    model.result("pg12").feature("ptgr1").set("expr", "solid.sGpzz");
    model.result("pg12").feature("ptgr1").set("descr", "\u5e94\u529b\u5f20\u91cf\uff0czz \u5206\u91cf");
    model.result("pg12").feature("ptgr1").set("descractive", true);
    model.result("pg12").feature("ptgr1").set("descr", "\u87ba\u6813\u5e94\u529b");
    model.result("pg12").feature("ptgr1").set("xdata", "expr");
    model.result("pg12").feature("ptgr1").set("xdataexpr", "bendingMoment*lp");
    model.result("pg12").feature("ptgr1").set("xdataunit", "kN*m");
    model.result("pg12").feature("ptgr1").set("linestyle", "cycle");
    model.result("pg12").feature("ptgr1").set("linewidth", 2);
    model.result("pg12").feature("ptgr1").set("legend", true);
    model.result("pg12").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg12").feature("ptgr1").setIndex("legends", "\u5185\u90e8", 0);
    model.result("pg12").feature("ptgr1").setIndex("legends", "\u5916\u90e8", 1);
    model.result("pg12").run();
    model.result("pg12").set("xlabelactive", true);
    model.result("pg12").set("xlabel", "\u7ba1\u9053\u4e2d\u7684\u5f2f\u77e9 (kNm)");
    model.result("pg12").set("legendpos", "upperleft");
    model.result().create("pg13", "PlotGroup3D");
    model.result("pg13").run();
    model.result("pg13").label("\u63a5\u89e6\u538b\u529b");
    model.result("pg13").set("data", "dset2");
    model.result("pg13").create("surf1", "Surface");
    model.result("pg13").feature("surf1").set("expr", "solid.dcnt1.Tn");
    model.result("pg13").feature("surf1").set("descr", "\u63a5\u89e6\u538b\u529b");
    model.result("pg13").feature("surf1").set("rangecoloractive", true);
    model.result("pg13").feature("surf1").set("rangecolormax", 100);
    model.result("pg13").run();
    model.result("pg13").run();
    model.result("pg13").set("data", "dset1");
    model.result("pg13").run();
    model.result().create("pg14", "PlotGroup3D");
    model.result("pg14").set("data", "dset1");
    model.result("pg14").setIndex("looplevel", 5, 0);
    model.result("pg14").label("\u8fb9\u754c\u8f7d\u8377 (solid)");
    model.result("pg14").set("showlegends", true);
    model.result("pg14").set("titletype", "label");
    model.result("pg14").set("frametype", "spatial");
    model.result("pg14").set("showlegendsunit", true);
    model.result("pg14").create("surf1", "Surface");
    model.result("pg14").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg14").feature("surf1").label("\u7070\u8272\u8868\u9762");
    model.result("pg14").feature("surf1").set("coloring", "uniform");
    model.result("pg14").feature("surf1").set("color", "gray");
    model.result("pg14").feature("surf1").set("inheritcolor", false);
    model.result("pg14").feature("surf1").set("inheritrange", false);
    model.result("pg14").feature("surf1").set("inherittransparency", false);
    model.result("pg14").feature("surf1").create("def", "Deform");
    model.result("pg14").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg14").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg14").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg14").feature("surf1").feature("def").set("scale", 1);
    model.result("pg14").feature("surf1").create("sel1", "Selection");
    model.result("pg14").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg14").feature("surf1").feature("sel1").selection()
         .set(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259, 260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359, 360, 361, 362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422, 423, 424, 425);
    model.result("pg14").feature("surf1").create("tran1", "Transparency");
    model.result("pg14").feature("surf1").feature("tran1").set("transparency", 0.8);
    model.result("pg14").create("arws1", "ArrowSurface");
    model.result("pg14").feature("arws1")
         .set("expr", new String[]{"solid.bndl1.fax", "solid.bndl1.fay", "solid.bndl1.faz"});
    model.result("pg14").feature("arws1").set("placement", "gausspoints");
    model.result("pg14").feature("arws1").set("arrowbase", "head");
    model.result("pg14").feature("arws1").label("\u538b\u529b");
    model.result("pg14").feature("arws1").set("inheritplot", "none");
    model.result("pg14").feature("arws1").create("col", "Color");
    model.result("pg14").feature("arws1").feature("col").set("colortable", "Rainbow");
    model.result("pg14").feature("arws1").feature("col").set("colortabletrans", "none");
    model.result("pg14").feature("arws1").feature("col").set("colorscalemode", "linear");
    model.result("pg14").feature("arws1").feature("col").set("colordata", "arrowlength");
    model.result("pg14").feature("arws1").feature("col").set("coloring", "gradient");
    model.result("pg14").feature("arws1").feature("col").set("topcolor", "red");
    model.result("pg14").feature("arws1").feature("col").set("bottomcolor", "custom");
    model.result("pg14").feature("arws1").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg14").feature("arws1").set("color", "red");
    model.result("pg14").feature("arws1").create("def", "Deform");
    model.result("pg14").feature("arws1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg14").feature("arws1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg14").feature("arws1").feature("def").set("scaleactive", true);
    model.result("pg14").feature("arws1").feature("def").set("scale", 1);
    model.result("pg14").feature().move("surf1", 1);
    model.result("pg14").create("arws2", "ArrowSurface");
    model.result("pg14").feature("arws2")
         .set("expr", new String[]{"solid.bndl2.fax", "solid.bndl2.fay", "solid.bndl2.faz"});
    model.result("pg14").feature("arws2").set("placement", "gausspoints");
    model.result("pg14").feature("arws2").set("arrowbase", "tail");
    model.result("pg14").feature("arws2").label("\u5f2f\u77e9\u548c\u8f74\u5411\u529b");
    model.result("pg14").feature("arws2").set("inheritplot", "arws1");
    model.result("pg14").feature("arws2").create("col", "Color");
    model.result("pg14").feature("arws2").feature("col").set("colortable", "Rainbow");
    model.result("pg14").feature("arws2").feature("col").set("colortabletrans", "none");
    model.result("pg14").feature("arws2").feature("col").set("colorscalemode", "linear");
    model.result("pg14").feature("arws2").feature("col").set("colordata", "arrowlength");
    model.result("pg14").feature("arws2").feature("col").set("coloring", "gradient");
    model.result("pg14").feature("arws2").feature("col").set("topcolor", "red");
    model.result("pg14").feature("arws2").feature("col").set("bottomcolor", "custom");
    model.result("pg14").feature("arws2").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg14").feature("arws2").set("color", "red");
    model.result("pg14").feature("arws2").create("def", "Deform");
    model.result("pg14").feature("arws2").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg14").feature("arws2").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg14").feature("arws2").feature("def").set("scaleactive", true);
    model.result("pg14").feature("arws2").feature("def").set("scale", 1);
    model.result("pg14").feature().move("surf1", 2);
    model.result("pg14").label("\u8fb9\u754c\u8f7d\u8377 (solid)");
    model.result("pg14").run();
    model.result("pg14").run();
    model.result("pg10").run();

    model.title("\u7ba1\u8def\u8fde\u63a5\u5904\u7684\u9884\u5e94\u529b\u87ba\u6813");

    model
         .description("\u672c\u4f8b\u5206\u6790\u901a\u8fc7\u9884\u5e94\u529b\u87ba\u6813\u8fdb\u884c\u7684\u7ba1\u8fde\u63a5\uff0c\u8be5\u7ba1\u53d7\u5230\u5185\u538b\u548c\u5916\u90e8\u5f2f\u77e9\u4f5c\u7528\u3002\u8ba1\u7b97\u4e86\u87ba\u6813\u4e2d\u7684\u5e94\u529b\u968f\u5916\u8f7d\u8377\u7684\u53d8\u5316\u60c5\u51b5\u3002\u6cbf\u7740\u8bb8\u591a\u5e94\u529b\u5206\u7c7b\u7ebf\u8ba1\u7b97\u819c\u548c\u5f2f\u66f2\u5e94\u529b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("tube_connection.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
