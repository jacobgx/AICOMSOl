/*
 * gearbox_vibration_noise_bearing.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:20 by COMSOL 6.3.0.290. */
public class gearbox_vibration_noise_bearing {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Multibody_Dynamics_Module\\Automotive_and_Aerospace");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mbd", "MultibodyDynamics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/mbd", true);

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "gearbox_vibration_noise.mphbin");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("createpairs", false);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").hideObjects().create("hide1");
    model.component("comp1").view("view1").hideObjects("hide1").init(3);
    model.component("comp1").view("view1").hideObjects("hide1").add("fin", 4);

    model.component("comp1").geom("geom1").run("fin");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("n1i", "20", "\u9f7f\u6570\uff0c\u7b2c\u4e00\u6863\uff08\u526f\u8f74\uff09");
    model.param().set("n2i", "20", "\u9f7f\u6570\uff0c\u7b2c\u4e8c\u6863\uff08\u526f\u8f74\uff09");
    model.param().set("n3i", "20", "\u9f7f\u6570\uff0c\u7b2c\u4e09\u6863\uff08\u526f\u8f74\uff09");
    model.param().set("n4i", "20", "\u9f7f\u6570\uff0c\u7b2c\u56db\u6863\uff08\u526f\u8f74\uff09");
    model.param().set("n5i", "28", "\u9f7f\u6570\uff0c\u7b2c\u4e94\u6863\uff08\u526f\u8f74\uff09");
    model.param().set("n1o", "76", "\u9f7f\u6570\uff0c\u7b2c\u4e00\u6863\uff08\u4e3b\u8f74\uff09");
    model.param().set("n2o", "44", "\u9f7f\u6570\uff0c\u7b2c\u4e8c\u6863\uff08\u4e3b\u8f74\uff09");
    model.param().set("n3o", "28", "\u9f7f\u6570\uff0c\u7b2c\u4e09\u6863\uff08\u4e3b\u8f74\uff09");
    model.param().set("n4o", "20", "\u9f7f\u6570\uff0c\u7b2c\u56db\u6863\uff08\u4e3b\u8f74\uff09");
    model.param().set("n5o", "20", "\u9f7f\u6570\uff0c\u7b2c\u4e94\u6863\uff08\u4e3b\u8f74\uff09");
    model.param().set("gear_ratio1", "n1o/n1i", "\u9f7f\u8f6e\u6bd4\uff0c\u7b2c\u4e00\u6863");
    model.param().set("gear_ratio2", "n2o/n2i", "\u9f7f\u8f6e\u6bd4\uff0c\u7b2c\u4e8c\u6863");
    model.param().set("gear_ratio3", "n3o/n3i", "\u9f7f\u8f6e\u6bd4\uff0c\u7b2c\u4e09\u6863");
    model.param().set("gear_ratio4", "n4o/n4i", "\u9f7f\u8f6e\u6bd4\uff0c\u7b2c\u56db\u6863");
    model.param().set("gear_ratio5", "n5o/n5i", "\u9f7f\u8f6e\u6bd4\uff0c\u7b2c\u4e94\u6863");
    model.param().set("cd", "100[mm]", "\u4e2d\u5fc3\u8ddd\u79bb");
    model.param()
         .set("d1i", "2*cd/(1+gear_ratio1)", "\u8282\u5706\u76f4\u5f84\uff0c\u7b2c\u4e00\u6863\uff08\u526f\u8f74\uff09");
    model.param().set("d1o", "2*cd-d1i", "\u8282\u5706\u76f4\u5f84\uff0c\u7b2c\u4e00\u6863\uff08\u4e3b\u8f74\uff09");
    model.param()
         .set("d2i", "2*cd/(1+gear_ratio2)", "\u8282\u5706\u76f4\u5f84\uff0c\u7b2c\u4e8c\u6863\uff08\u526f\u8f74\uff09");
    model.param().set("d2o", "2*cd-d2i", "\u8282\u5706\u76f4\u5f84\uff0c\u7b2c\u4e8c\u6863\uff08\u4e3b\u8f74\uff09");
    model.param()
         .set("d3i", "2*cd/(1+gear_ratio3)", "\u8282\u5706\u76f4\u5f84\uff0c\u7b2c\u4e09\u6863\uff08\u526f\u8f74\uff09");
    model.param().set("d3o", "2*cd-d3i", "\u8282\u5706\u76f4\u5f84\uff0c\u7b2c\u4e09\u6863\uff08\u4e3b\u8f74\uff09");
    model.param()
         .set("d4i", "2*cd/(1+gear_ratio4)", "\u8282\u5706\u76f4\u5f84\uff0c\u7b2c\u56db\u6863\uff08\u526f\u8f74\uff09");
    model.param().set("d4o", "2*cd-d4i", "\u8282\u5706\u76f4\u5f84\uff0c\u7b2c\u56db\u6863\uff08\u4e3b\u8f74\uff09");
    model.param()
         .set("d5i", "2*cd/(1+gear_ratio5)", "\u8282\u5706\u76f4\u5f84\uff0c\u7b2c\u4e94\u6863\uff08\u526f\u8f74\uff09");
    model.param().set("d5o", "2*cd-d5i", "\u8282\u5706\u76f4\u5f84\uff0c\u7b2c\u4e94\u6863\uff08\u4e3b\u8f74\uff09");
    model.param().set("alpha", "25[deg]", "\u538b\u529b\u89d2");
    model.param().set("beta", "30[deg]", "\u87ba\u65cb\u89d2");
    model.param().set("omega", "2*pi[rad]*800[rpm]", "\u53d1\u52a8\u673a\u6216\u4e3b\u8f74\u8f6c\u901f");
    model.param().set("T", "2*pi/omega", "\u4e3b\u8f74\u65cb\u8f6c\u7684\u65f6\u95f4\u5468\u671f");
    model.param().set("T_ext", "1e3[N*m]", "\u8f93\u51fa\u626d\u77e9");
    model.param().set("kt", "1e8[N/m]", "\u9f7f\u8f6e\u556e\u5408\u521a\u5ea6");
    model.param().set("R", "0.75", "\u7403\u4f53\u534a\u5f84");
    model.param().set("n", "5", "\u556e\u5408\u9f7f\u8f6e");
    model.param().create("par2");
    model.param("par2").label("\u8f74\u627f\u53c2\u6570");
    model.param("par2").set("N", "20");
    model.param("par2").descr("N", "\u7403\u6570");
    model.param("par2").set("ds", "0.0375[m]");
    model.param("par2").descr("ds", "\u8f74\u5f84");
    model.param("par2").set("db", "0.8*pi*ds/(N-pi)");
    model.param("par2").descr("db", "\u7403\u5f84");
    model.param("par2").set("dp", "ds+db");
    model.param("par2").descr("dp", "\u8282\u5706\u76f4\u5f84");
    model.param("par2").set("r_out", "0.53*db");
    model.param("par2").descr("r_out", "\u5916\u5708\u534a\u5f84");
    model.param("par2").set("r_in", "0.53*db");
    model.param("par2").descr("r_in", "\u5185\u5708\u534a\u5f84");
    model.param("par2").set("phi0", "25[deg]");
    model.param("par2").descr("phi0", "\u63a5\u89e6\u89d2");

    model.func().create("step1", "Step");
    model.func("step1").set("location", "T/40");
    model.func("step1").set("smooth", "T/20");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("an", "mbd.u_ttX*nX+mbd.u_ttY*nY+mbd.u_ttZ*nZ");
    model.component("comp1").variable("var1").descr("an", "\u6cd5\u5411\u52a0\u901f\u5ea6");
    model.component("comp1").variable("var1").set("th", "(omega*t)*step1(t)");
    model.component("comp1").variable("var1").descr("th", "\u4e3b\u8f74\u65cb\u8f6c");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u57fa\u7840 1\uff08\u526f\u8f74\uff09");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(367, 368, 369, 370);
    model.component("comp1").selection().duplicate("sel2", "sel1");
    model.component("comp1").selection("sel2").label("\u57fa\u7840 1\uff08\u4e3b\u8f74\uff09");
    model.component("comp1").selection("sel2").set(372, 373, 374, 375, 377, 379, 380, 381);
    model.component("comp1").selection().duplicate("sel3", "sel2");
    model.component("comp1").selection("sel3").label("\u57fa\u7840 2\uff08\u526f\u8f74\uff09");
    model.component("comp1").selection("sel3").set(818, 819, 820, 821);
    model.component("comp1").selection().duplicate("sel4", "sel3");
    model.component("comp1").selection("sel4").label("\u57fa\u7840 2\uff08\u4e3b\u8f74\uff09");
    model.component("comp1").selection("sel4").set(824, 825, 826, 828, 830, 832, 833, 834);
    model.component("comp1").selection().duplicate("sel5", "sel4");
    model.component("comp1").selection("sel5").label("\u87ba\u6813\u5b54");
    model.component("comp1").selection("sel5")
         .set(184, 185, 186, 187, 188, 189, 190, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 214, 215, 216, 217, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 242, 243, 244, 245, 246, 533, 534, 535, 536, 537, 538, 539, 540, 602, 603, 604, 605, 606, 607, 608, 609, 610, 611, 612, 613, 677, 678, 679, 680, 683, 684, 685, 686, 687, 688, 689, 690);
    model.component("comp1").selection().duplicate("sel6", "sel5");
    model.component("comp1").selection("sel6").label("\u8f74\u627f\u5957");
    model.component("comp1").selection("sel6").geom(3);
    model.component("comp1").selection("sel6").set(1, 2, 3, 4, 5, 6, 7, 8);
    model.component("comp1").selection("sel6").geom("geom1", 3, 2, new String[]{"exterior"});
    model.component("comp1").selection("sel6").set(1, 2, 3, 4, 5, 6, 7, 8);

    model.component("comp1").cpl().create("genext1", "GeneralExtrusion");
    model.component("comp1").cpl("genext1").selection().geom("geom1", 2);
    model.component("comp1").cpl("genext1").selection().named("sel6");

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

    model.component("comp1").physics("mbd").create("hlg1", "HelicalGear", 3);
    model.component("comp1").physics("mbd").feature("hlg1").selection().set(9);
    model.component("comp1").physics("mbd").feature("hlg1")
         .label("\u659c\u9f7f\u8f6e\uff1a\u7b2c\u56db\u4e2a\uff08\u526f\u8f74\uff09");
    model.component("comp1").physics("mbd").feature("hlg1").set("nt", "n4i");
    model.component("comp1").physics("mbd").feature("hlg1").set("dp", "d4i");
    model.component("comp1").physics("mbd").feature("hlg1").set("alpha", "alpha");
    model.component("comp1").physics("mbd").feature("hlg1").set("beta", "beta");
    model.component("comp1").physics("mbd").feature("hlg1").set("eg", new int[]{1, 0, 0});
    model.component("comp1").physics("mbd").feature().duplicate("hlg2", "hlg1");
    model.component("comp1").physics("mbd").feature("hlg2")
         .label("\u659c\u9f7f\u8f6e\uff1a\u7b2c\u4e00\u4e2a\uff08\u526f\u8f74\uff09");
    model.component("comp1").physics("mbd").feature("hlg2").selection().set(11);
    model.component("comp1").physics("mbd").feature("hlg2").set("nt", "n1i");
    model.component("comp1").physics("mbd").feature("hlg2").set("dp", "d1i");
    model.component("comp1").physics("mbd").feature().duplicate("hlg3", "hlg2");
    model.component("comp1").physics("mbd").feature("hlg3")
         .label("\u659c\u9f7f\u8f6e\uff1a\u7b2c\u4e8c\u4e2a\uff08\u526f\u8f74\uff09");
    model.component("comp1").physics("mbd").feature("hlg3").selection().set(13);
    model.component("comp1").physics("mbd").feature("hlg3").set("nt", "n2i");
    model.component("comp1").physics("mbd").feature("hlg3").set("dp", "d2i");
    model.component("comp1").physics("mbd").feature().duplicate("hlg4", "hlg3");
    model.component("comp1").physics("mbd").feature("hlg4")
         .label("\u659c\u9f7f\u8f6e\uff1a\u7b2c\u4e09\u4e2a\uff08\u526f\u8f74\uff09");
    model.component("comp1").physics("mbd").feature("hlg4").selection().set(15);
    model.component("comp1").physics("mbd").feature("hlg4").set("nt", "n3i");
    model.component("comp1").physics("mbd").feature("hlg4").set("dp", "d3i");
    model.component("comp1").physics("mbd").feature().duplicate("hlg5", "hlg4");
    model.component("comp1").physics("mbd").feature("hlg5")
         .label("\u659c\u9f7f\u8f6e\uff1a\u7b2c\u4e94\u4e2a\uff08\u526f\u8f74\uff09");
    model.component("comp1").physics("mbd").feature("hlg5").selection().set(17);
    model.component("comp1").physics("mbd").feature("hlg5").set("nt", "n5i");
    model.component("comp1").physics("mbd").feature("hlg5").set("dp", "d5i");
    model.component("comp1").physics("mbd").create("rd1", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd1").label("\u521a\u6027\u57df\uff1a\u526f\u8f74");
    model.component("comp1").physics("mbd").feature("rd1").selection().set(8);
    model.component("comp1").physics("mbd").create("fxj1", "FixedJoint", -1);
    model.component("comp1").physics("mbd").feature("fxj1").set("Source", "rd1");
    model.component("comp1").physics("mbd").feature("fxj1").set("Destination", "hlg1");
    model.component("comp1").physics("mbd").feature().duplicate("fxj2", "fxj1");
    model.component("comp1").physics("mbd").feature("fxj2").set("Destination", "hlg2");
    model.component("comp1").physics("mbd").feature().duplicate("fxj3", "fxj2");
    model.component("comp1").physics("mbd").feature("fxj3").set("Destination", "hlg3");
    model.component("comp1").physics("mbd").feature().duplicate("fxj4", "fxj3");
    model.component("comp1").physics("mbd").feature("fxj4").set("Destination", "hlg4");
    model.component("comp1").physics("mbd").feature().duplicate("fxj5", "fxj4");
    model.component("comp1").physics("mbd").feature("fxj5").set("Destination", "hlg5");
    model.component("comp1").physics("mbd").feature().duplicate("hlg6", "hlg1");
    model.component("comp1").physics("mbd").feature("hlg6")
         .label("\u659c\u9f7f\u8f6e\uff1a\u7b2c\u56db\u4e2a\uff08\u4e3b\u8f74\uff09");
    model.component("comp1").physics("mbd").feature("hlg6").selection().set(10);
    model.component("comp1").physics("mbd").feature("hlg6").set("nt", "n4o");
    model.component("comp1").physics("mbd").feature("hlg6").set("dp", "d4o");
    model.component("comp1").physics("mbd").feature("hlg6").set("beta", "-beta");
    model.component("comp1").physics("mbd").feature().duplicate("hlg7", "hlg6");
    model.component("comp1").physics("mbd").feature("hlg7")
         .label("\u659c\u9f7f\u8f6e\uff1a\u7b2c\u4e00\u4e2a\uff08\u4e3b\u8f74\uff09");
    model.component("comp1").physics("mbd").feature("hlg7").selection().set(12);
    model.component("comp1").physics("mbd").feature("hlg7").set("nt", "n1o");
    model.component("comp1").physics("mbd").feature("hlg7").set("dp", "d1o");
    model.component("comp1").physics("mbd").feature().duplicate("hlg8", "hlg7");
    model.component("comp1").physics("mbd").feature("hlg8")
         .label("\u659c\u9f7f\u8f6e\uff1a\u7b2c\u4e8c\u4e2a\uff08\u4e3b\u8f74\uff09");
    model.component("comp1").physics("mbd").feature("hlg8").selection().set(14);
    model.component("comp1").physics("mbd").feature("hlg8").set("nt", "n2o");
    model.component("comp1").physics("mbd").feature("hlg8").set("dp", "d2o");
    model.component("comp1").physics("mbd").feature().duplicate("hlg9", "hlg8");
    model.component("comp1").physics("mbd").feature("hlg9")
         .label("\u659c\u9f7f\u8f6e\uff1a\u7b2c\u4e09\u4e2a\uff08\u4e3b\u8f74\uff09");
    model.component("comp1").physics("mbd").feature("hlg9").selection().set(16);
    model.component("comp1").physics("mbd").feature("hlg9").set("nt", "n3o");
    model.component("comp1").physics("mbd").feature("hlg9").set("dp", "d3o");
    model.component("comp1").physics("mbd").feature().duplicate("hlg10", "hlg9");
    model.component("comp1").physics("mbd").feature("hlg10")
         .label("\u659c\u9f7f\u8f6e\uff1a\u7b2c\u4e94\u4e2a\uff08\u4e3b\u8f74\uff09");
    model.component("comp1").physics("mbd").feature("hlg10").selection().set(18);
    model.component("comp1").physics("mbd").feature("hlg10").set("nt", "n5o");
    model.component("comp1").physics("mbd").feature("hlg10").set("dp", "d5o");
    model.component("comp1").physics("mbd").create("rd2", "RigidDomain", 3);
    model.component("comp1").physics("mbd").feature("rd2")
         .label("\u521a\u6027\u6750\u6599\uff1a\u4e3b\u8f93\u5165\u8f74");
    model.component("comp1").physics("mbd").feature("rd2").selection().set(1);
    model.component("comp1").physics("mbd").feature().duplicate("rd3", "rd2");
    model.component("comp1").physics("mbd").feature("rd3")
         .label("\u521a\u6027\u6750\u6599\uff1a\u4e3b\u8f93\u51fa\u8f74");
    model.component("comp1").physics("mbd").feature("rd3").selection().set(2);
    model.component("comp1").physics("mbd").feature("rd2").create("pdr1", "PrescribedDispRot", -1);
    model.component("comp1").physics("mbd").feature("rd2").feature("pdr1")
         .set("RotationType", "PrescribedRotationGroup");
    model.component("comp1").physics("mbd").feature("rd2").feature("pdr1").set("Omega", new int[]{1, 0, 0});
    model.component("comp1").physics("mbd").feature("rd2").feature("pdr1").set("phi0", "th");
    model.component("comp1").physics("mbd").feature("rd3").create("am1", "AppliedMoment", -1);
    model.component("comp1").physics("mbd").feature("rd3").feature("am1")
         .set("Mt", new String[]{"-T_ext*step1(t)", "0", "0"});
    model.component("comp1").physics("mbd").feature().duplicate("fxj6", "fxj5");
    model.component("comp1").physics("mbd").feature("fxj6").set("Source", "rd2");
    model.component("comp1").physics("mbd").feature("fxj6").set("Destination", "hlg6");
    model.component("comp1").physics("mbd").create("hgj1", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj1").set("Source", "rd3");
    model.component("comp1").physics("mbd").feature("hgj1").set("Destination", "hlg7");
    model.component("comp1").physics("mbd").feature("hgj1").create("pm1", "PrescribedMotion", -1);
    model.component("comp1").physics("mbd").feature("hgj1").feature("pm1")
         .set("ActivationConditionRotational", "conditionallyActive");
    model.component("comp1").physics("mbd").feature("hgj1").feature("pm1").set("i_thp", "n!=1");
    model.component("comp1").physics("mbd").feature().duplicate("hgj2", "hgj1");
    model.component("comp1").physics("mbd").feature("hgj2").set("Destination", "hlg8");
    model.component("comp1").physics("mbd").feature("hgj2").feature("pm1").set("i_thp", "n!=2");
    model.component("comp1").physics("mbd").feature().duplicate("hgj3", "hgj2");
    model.component("comp1").physics("mbd").feature("hgj3").set("Destination", "hlg9");
    model.component("comp1").physics("mbd").feature("hgj3").feature("pm1").set("i_thp", "n!=3");
    model.component("comp1").physics("mbd").feature().duplicate("hgj4", "hgj3");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("mbd").feature("hgj4").set("Destination", "hlg10");
    model.component("comp1").physics("mbd").feature("hgj4").feature("pm1").set("i_thp", "n!=5");
    model.component("comp1").physics("mbd").feature().duplicate("hgj5", "hgj4");
    model.component("comp1").physics("mbd").feature("hgj5").set("Destination", "rd2");
    model.component("comp1").physics("mbd").feature("hgj5").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("hgj5").feature("cjp1").selection().set(151, 152);
    model.component("comp1").physics("mbd").feature("hgj5").feature("pm1").set("i_thp", "n!=4");
    model.component("comp1").physics("mbd").create("grp1", "GearPair", -1);
    model.component("comp1").physics("mbd").feature("grp1").label("\u9f7f\u8f6e\u526f\uff1a\u7b2c\u56db\u4e2a");
    model.component("comp1").physics("mbd").feature("grp1").set("Wheel", "hlg6");
    model.component("comp1").physics("mbd").feature("grp1").set("Pinion", "hlg1");
    model.component("comp1").physics("mbd").feature("grp1").set("IncludeGearElasticity", true);
    model.component("comp1").physics("mbd").feature("grp1").feature("gel1").set("kt_dr", "kt");
    model.component("comp1").physics("mbd").feature("grp1").feature("gel1").set("kt_dn", "kt");
    model.component("comp1").physics("mbd").feature("grp1").feature("gel1").set("ContactRatioType", "Varying");
    model.component("comp1").physics("mbd").feature("grp1").feature("gel1").set("zeta", 0.8);
    model.component("comp1").physics("mbd").feature().duplicate("grp2", "grp1");
    model.component("comp1").physics("mbd").feature("grp2").label("\u9f7f\u8f6e\u526f\uff1a\u7b2c\u4e00\u4e2a");
    model.component("comp1").physics("mbd").feature("grp2").set("Wheel", "hlg2");
    model.component("comp1").physics("mbd").feature("grp2").set("Pinion", "hlg7");
    model.component("comp1").physics("mbd").feature("grp2").set("ObtainedThrough", "CounterclockwiseDirection");
    model.component("comp1").physics("mbd").feature().duplicate("grp3", "grp2");
    model.component("comp1").physics("mbd").feature("grp3").label("\u9f7f\u8f6e\u526f\uff1a\u7b2c\u4e8c\u4e2a");
    model.component("comp1").physics("mbd").feature("grp3").set("Wheel", "hlg3");
    model.component("comp1").physics("mbd").feature("grp3").set("Pinion", "hlg8");
    model.component("comp1").physics("mbd").feature().duplicate("grp4", "grp3");
    model.component("comp1").physics("mbd").feature("grp4").label("\u9f7f\u8f6e\u526f\uff1a\u7b2c\u4e09\u4e2a");
    model.component("comp1").physics("mbd").feature("grp4").set("Wheel", "hlg4");
    model.component("comp1").physics("mbd").feature("grp4").set("Pinion", "hlg9");
    model.component("comp1").physics("mbd").feature().duplicate("grp5", "grp4");
    model.component("comp1").physics("mbd").feature("grp5").label("\u9f7f\u8f6e\u526f\uff1a\u7b2c\u4e94\u4e2a");
    model.component("comp1").physics("mbd").feature("grp5").set("Wheel", "hlg5");
    model.component("comp1").physics("mbd").feature("grp5").set("Pinion", "hlg10");
    model.component("comp1").physics("mbd").create("att1", "Attachment", 2);
    model.component("comp1").physics("mbd").feature("att1")
         .label("\u8fde\u63a5\u4ef6\uff1a\u8f74\u627f 1\uff08\u526f\u8f74\uff09");
    model.component("comp1").physics("mbd").feature("att1").selection().named("sel1");
    model.component("comp1").physics("mbd").feature().duplicate("att2", "att1");
    model.component("comp1").physics("mbd").feature("att2")
         .label("\u8fde\u63a5\u4ef6\uff1a\u8f74\u627f 1\uff08\u4e3b\u8f74\uff09");
    model.component("comp1").physics("mbd").feature("att2").selection().named("sel2");
    model.component("comp1").physics("mbd").feature().duplicate("att3", "att2");
    model.component("comp1").physics("mbd").feature("att3")
         .label("\u8fde\u63a5\u4ef6\uff1a\u8f74\u627f 2\uff08\u526f\u8f74\uff09");
    model.component("comp1").physics("mbd").feature("att3").selection().named("sel3");
    model.component("comp1").physics("mbd").feature().duplicate("att4", "att3");
    model.component("comp1").physics("mbd").feature("att4")
         .label("\u8fde\u63a5\u4ef6\uff1a\u8f74\u627f 2\uff08\u4e3b\u8f74\uff09");
    model.component("comp1").physics("mbd").feature("att4").selection().named("sel4");
    model.component("comp1").physics("mbd").create("rrb1", "RadialRollerBearing", 2);
    model.component("comp1").physics("mbd").feature("rrb1").label("\u8f74\u627f 1\uff08\u526f\u8f74\uff09");
    model.component("comp1").physics("mbd").feature("rrb1").selection().set(878, 879, 880, 881);
    model.component("comp1").physics("mbd").feature("rrb1").set("yDirection", "userDef");
    model.component("comp1").physics("mbd").feature("rrb1").set("BearingType", "AngularContactBall");
    model.component("comp1").physics("mbd").feature("rrb1").set("Nb", "N");
    model.component("comp1").physics("mbd").feature("rrb1").set("d_ball", "db");
    model.component("comp1").physics("mbd").feature("rrb1").set("d_pitch", "dp");
    model.component("comp1").physics("mbd").feature("rrb1").set("r_in", "r_in");
    model.component("comp1").physics("mbd").feature("rrb1").set("r_out", "r_out");
    model.component("comp1").physics("mbd").feature("rrb1").set("phi0", "phi0");
    model.component("comp1").physics("mbd").feature("rrb1").set("Foundation", "att1");
    model.component("comp1").physics("mbd").feature().duplicate("rrb2", "rrb1");
    model.component("comp1").physics("mbd").feature("rrb2").label("\u8f74\u627f 1\uff08\u4e3b\u8f74\uff09");
    model.component("comp1").physics("mbd").feature("rrb2").selection().set(76, 77, 78, 79);
    model.component("comp1").physics("mbd").feature("rrb2").set("phi0", "-phi0");
    model.component("comp1").physics("mbd").feature("rrb2").set("Foundation", "att2");
    model.component("comp1").physics("mbd").feature().duplicate("rrb3", "rrb1");
    model.component("comp1").physics("mbd").feature("rrb3").label("\u8f74\u627f 2\uff08\u4e3b\u8f74\uff09");
    model.component("comp1").physics("mbd").feature("rrb3").selection().set(100, 101, 102, 103);
    model.component("comp1").physics("mbd").feature("rrb3").set("Foundation", "att4");
    model.component("comp1").physics("mbd").feature().duplicate("rrb4", "rrb2");
    model.component("comp1").physics("mbd").feature("rrb4").label("\u8f74\u627f 2\uff08\u526f\u8f74\uff09");
    model.component("comp1").physics("mbd").feature("rrb4").selection().set(896, 897, 898, 899);
    model.component("comp1").physics("mbd").feature("rrb4").set("Foundation", "att3");
    model.component("comp1").physics("mbd").create("fix1", "Fixed", 2);
    model.component("comp1").physics("mbd").feature("fix1").selection().named("sel5");

    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76\uff1a\u591a\u4f53\u5206\u6790");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("time").set("tlist", "range(0,T/2000,T)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb1_th").set("scaleval", 1);
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb1_alphay").set("scaleval", "1e-2");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb1_alphaz").set("scaleval", "1e-2");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb2_th").set("scaleval", 1);
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb2_alphay").set("scaleval", "1e-2");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb2_alphaz").set("scaleval", "1e-2");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb3_th").set("scaleval", 1);
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb3_alphay").set("scaleval", "1e-2");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb3_alphaz").set("scaleval", "1e-2");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb4_th").set("scaleval", 1);
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb4_alphay").set("scaleval", "1e-2");
    model.sol("sol1").feature("v1").feature("comp1_mbd_rrb4_alphaz").set("scaleval", "1e-2");
    model.sol("sol1").feature("t1").set("tstepsbdf", "intermediate");
    model.sol("sol1").feature("t1").feature("fc1").set("maxiter", 10);
    model.sol("sol1").feature("t1").feature("fc1").set("jtech", "once");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().duplicate("dset2", "dset1");
    model.result().dataset("dset2").selection().geom("geom1", 3);
    model.result().dataset("dset2").selection().geom("geom1", 3);
    model.result().dataset("dset2").selection().set(1, 2, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18);
    model.result().dataset().duplicate("dset3", "dset2");
    model.result().dataset("dset3").selection().geom("geom1", 3);
    model.result().dataset("dset3").selection().set();
    model.result().dataset("dset3").selection().geom("geom1", 3);
    model.result().dataset("dset3").selection().set(3, 5, 6, 7);
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u901f\u5ea6 - \u5e94\u529b");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("data", "dset2");
    model.result("pg1").feature("surf1").set("expr", "mbd.vel");
    model.result("pg1").feature("surf1").set("colortable", "AuroraAustralis");
    model.result("pg1").feature("surf1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("surf2", "surf1");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("data", "dset3");
    model.result("pg1").feature("surf2").set("expr", "mbd.mises");
    model.result("pg1").feature("surf2").set("colortable", "TrafficLight");
    model.result("pg1").feature("surf2").set("rangecoloractive", true);
    model.result("pg1").feature("surf2").set("rangecolormin", 0);
    model.result("pg1").feature("surf2").set("rangecolormax", "2e8");
    model.result("pg1").run();
    model.result("pg1").set("legendpos", "rightdouble");
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u901f\u5ea6 - \u6cd5\u5411\u52a0\u901f\u5ea6");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").set("expr", "an");
    model.result("pg2").feature("surf2").set("rangecoloractive", false);
    model.result("pg2").feature("surf2").set("colortable", "PrismDark");
    model.result("pg2").run();
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("expr", new String[]{"mbd.rrb1.fbx", "mbd.rrb1.fby", "mbd.rrb1.fbz"});
    model.result("pg2").feature("arws1")
         .set("descr", "\u8f74\u627f\u529b\u5206\u5e03 \uff08\u7a7a\u95f4\u5750\u6807\u7cfb\uff09");
    model.result("pg2").feature("arws1").set("placement", "elements");
    model.result("pg2").feature("arws1").set("scaleactive", true);
    model.result("pg2").feature("arws1").set("scale", "6E-6");
    model.result("pg2").feature("arws1").create("def1", "Deform");
    model.result("pg2").run();
    model.result("pg2").feature("arws1").feature("def1").set("expr", new String[]{"mbd.rrb1.u_cage", "v", "w"});
    model.result("pg2").feature("arws1").feature("def1").setIndex("expr", "mbd.rrb1.v_cage", 1);
    model.result("pg2").feature("arws1").feature("def1").setIndex("expr", "mbd.rrb1.w_cage", 2);
    model.result("pg2").feature("arws1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature("arws1").feature("def1").set("scale", 1);
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("arws2", "arws1");
    model.result("pg2").run();
    model.result("pg2").feature("arws2").set("inheritplot", "arws1");
    model.result("pg2").feature("arws2").set("inheritcolor", false);
    model.result("pg2").feature("arws2").set("color", "blue");
    model.result("pg2").feature("arws2").set("titletype", "none");
    model.result("pg2").feature("arws2").setIndex("expr", "mbd.rrb2.fbx", 0);
    model.result("pg2").feature("arws2").setIndex("expr", "mbd.rrb2.fby", 1);
    model.result("pg2").feature("arws2").setIndex("expr", "mbd.rrb2.fbz", 2);
    model.result("pg2").run();
    model.result("pg2").feature("arws2").feature("def1").setIndex("expr", "mbd.rrb2.u_cage", 0);
    model.result("pg2").feature("arws2").feature("def1").setIndex("expr", "mbd.rrb2.v_cage", 1);
    model.result("pg2").feature("arws2").feature("def1").setIndex("expr", "mbd.rrb2.w_cage", 2);
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("arws3", "arws2");
    model.result("pg2").run();
    model.result("pg2").feature("arws3").setIndex("expr", "mbd.rrb3.fbx", 0);
    model.result("pg2").feature("arws3").setIndex("expr", "mbd.rrb3.fby", 1);
    model.result("pg2").feature("arws3").setIndex("expr", "mbd.rrb3.fbz", 2);
    model.result("pg2").run();
    model.result("pg2").feature("arws3").feature("def1").setIndex("expr", "mbd.rrb3.u_cage", 0);
    model.result("pg2").feature("arws3").feature("def1").setIndex("expr", "mbd.rrb3.v_cage", 1);
    model.result("pg2").feature("arws3").feature("def1").setIndex("expr", "mbd.rrb3.w_cage", 2);
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("arws4", "arws3");
    model.result("pg2").run();
    model.result("pg2").feature("arws4").set("color", "red");
    model.result("pg2").feature("arws4").setIndex("expr", "mbd.rrb4.fbx", 0);
    model.result("pg2").feature("arws4").setIndex("expr", "mbd.rrb4.fby", 1);
    model.result("pg2").feature("arws4").setIndex("expr", "mbd.rrb4.fbz", 2);
    model.result("pg2").run();
    model.result("pg2").feature("arws4").feature("def1").setIndex("expr", "mbd.rrb4.u_cage", 0);
    model.result("pg2").feature("arws4").feature("def1").setIndex("expr", "mbd.rrb4.v_cage", 1);
    model.result("pg2").feature("arws4").feature("def1").setIndex("expr", "mbd.rrb4.w_cage", 2);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u6cd5\u5411\u52a0\u901f\u5ea6");
    model.result("pg3").create("ptgr1", "PointGraph");
    model.result("pg3").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr1").set("linewidth", "preference");
    model.result("pg3").feature("ptgr1").selection().set(879);
    model.result("pg3").feature("ptgr1").set("expr", "an");
    model.result("pg3").feature("ptgr1").set("xdata", "expr");
    model.result("pg3").feature("ptgr1").set("xdataexpr", "th");
    model.result("pg3").feature("ptgr1").set("xdataunit", "\u00b0");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u6cd5\u5411\u52a0\u901f\u5ea6\uff1a\u9891\u7387");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr1").set("xdata", "fourier");
    model.result("pg4").feature("ptgr1").set("fouriershow", "spectrum");
    model.result("pg4").feature("ptgr1").set("freqrangeactive", true);
    model.result("pg4").feature("ptgr1").set("freqmax", 6000);
    model.result("pg4").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").geom("geom2").geomRep("cadps");
    model.component("comp2").geom("geom2").create("imp1", "Import");
    model.component("comp2").geom("geom2").feature("imp1").set("filename", "gearbox_vibration_noise.mphbin");
    model.component("comp2").geom("geom2").run("imp1");
    model.component("comp2").geom("geom2").create("csol1", "ConvertToSolid");
    model.component("comp2").geom("geom2").feature("csol1").selection("input").set("imp1");
    model.component("comp2").geom("geom2").run("csol1");
    model.component("comp2").geom("geom2").create("sph1", "Sphere");
    model.component("comp2").geom("geom2").feature("sph1").set("r", "R");
    model.component("comp2").geom("geom2").run("sph1");
    model.component("comp2").geom("geom2").create("dif1", "Difference");
    model.component("comp2").geom("geom2").feature("dif1").selection("input").set("sph1");
    model.component("comp2").geom("geom2").feature("dif1").selection("input2").set("csol1");
    model.component("comp2").geom("geom2").run("fin");

    model.component("comp2").view("view2").hideObjects().create("hide1");
    model.component("comp2").view("view2").hideObjects("hide1").init(2);
    model.component("comp2").view("view2").hideObjects("hide1").add("fin", 2);

    model.component("comp2").selection().create("sel7", "Explicit");
    model.component("comp2").selection("sel7").geom(2);
    model.component("comp2").selection("sel7").set(1, 2, 3, 4, 349, 350, 357, 360);
    model.component("comp2").selection().create("sel8", "Explicit");
    model.component("comp2").selection("sel8").geom(2);
    model.component("comp2").selection("sel8")
         .set(12, 13, 14, 15, 16, 17, 18, 19, 20, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 43, 44, 45, 46, 47, 49, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 127, 132, 133, 134, 139, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 196, 197, 198, 199, 200, 201, 202, 205, 206, 207, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 223, 224, 225, 226, 227, 228, 229, 232, 233, 234, 237, 238, 239, 240, 241, 242, 243, 244, 280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 299, 300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339, 340, 341, 342, 343, 344, 345, 346, 347, 348, 351, 352, 353, 354, 355, 356, 358, 359, 361, 362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379, 380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 396, 397, 398, 399, 400, 401, 402, 403, 404, 405, 406, 407, 408, 409, 410, 411, 412, 413, 414, 415, 416, 417, 418, 419, 420, 421, 422, 423, 424, 425, 426, 427, 428, 429, 430, 431, 432, 433, 434, 435, 436, 437, 438, 439, 440, 441, 442, 443, 444, 445, 446, 447, 448, 449, 450, 451, 452, 453, 454, 455, 456, 457, 458, 459, 460, 461, 462, 463, 464, 465, 466, 467, 468, 469, 470, 471, 472, 473, 474, 475, 476, 477, 478, 479, 480, 481, 482, 483, 484, 485, 486, 487, 488, 489, 490, 491, 492, 493, 494, 495, 496, 497, 498, 499, 500, 501, 502, 503, 504, 505, 506, 507, 508, 509, 510, 511, 512, 513, 514, 515, 516, 517, 518, 519, 520, 521, 522, 523, 524, 525, 526, 527, 528, 529, 530, 531, 532, 533, 534, 535, 536, 537, 538, 539, 540, 541, 542, 543, 544, 545, 546, 547, 548, 549, 550, 551, 552, 553, 554, 555, 556, 557, 558, 559, 560, 561, 562, 563, 564, 565, 566, 567, 568, 569, 570, 571, 572, 573, 574, 575, 576, 577, 578, 579, 580, 581, 582, 583, 584, 585, 586, 587, 588, 589, 590, 591, 592, 593, 594, 595, 596, 597, 598, 599, 600, 601, 602, 603, 604, 605, 606, 607, 608, 609, 610, 611, 612, 613, 614, 615, 616, 617, 618, 619, 620, 621, 622, 623, 624, 625, 626, 627, 628, 629, 630, 631, 632, 633, 634, 635, 636, 637, 638, 639, 640, 641, 642, 643, 644, 645, 646, 647, 648, 649, 650, 651, 652, 653, 654, 655, 656, 657, 658, 659, 660, 661, 662, 663, 664, 665, 666, 667, 668, 669, 670, 671, 672, 673, 674, 675, 676, 677, 678, 679, 680, 681, 711, 712, 715, 716, 717, 718, 719, 720, 723, 724, 727, 728, 729, 730, 731, 732, 733, 734);

    model.component("comp2").material().create("mat2", "Common");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp2").material("mat2").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp2").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp2").material("mat2").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp2").material("mat2").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp2").material("mat2").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp2").material("mat2").label("Air");
    model.component("comp2").material("mat2").set("family", "air");
    model.component("comp2").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp2").material("mat2").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp2").material("mat2").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp2").material("mat2").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp2").material("mat2").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp2").material("mat2").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp2").material("mat2").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp2").material("mat2").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp2").material("mat2").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp2").material("mat2").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp2").material("mat2").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp2").material("mat2").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp2").material("mat2").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp2").material("mat2").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp2").material("mat2").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp2").material("mat2").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp2").material("mat2").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp2").material("mat2").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp2").material("mat2").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp2").material("mat2").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp2").material("mat2").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp2").material("mat2").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp2").material("mat2").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp2").material("mat2").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp2").material("mat2").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp2").material("mat2").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp2").material("mat2").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp2").material("mat2").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp2").material("mat2").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp2").material("mat2").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp2").material("mat2").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp2").material("mat2").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp2").material("mat2").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp2").material("mat2").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp2").material("mat2").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp2").material("mat2").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp2").material("mat2").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp2").material("mat2").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp2").material("mat2").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp2").material("mat2").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp2").material("mat2").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp2").material("mat2").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp2").material("mat2").propertyGroup("def").set("molarmass", "");
    model.component("comp2").material("mat2").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp2").material("mat2").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp2").material("mat2").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat2").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp2").material("mat2").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp2").material("mat2").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp2").material("mat2").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp2").material("mat2").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp2").material("mat2").propertyGroup("def").addInput("temperature");
    model.component("comp2").material("mat2").propertyGroup("def").addInput("pressure");
    model.component("comp2").material("mat2").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp2").material("mat2").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp2").material("mat2").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp2").material("mat2").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp2").material("mat2").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp2").material("mat2").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp2").material("mat2").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp2").material("mat2").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp2").material("mat2").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp2").material("mat2").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp2").material("mat2").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp2").material("mat2").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp2").material("mat2").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp2").material("mat2").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp2").material("mat2").propertyGroup("idealGas").addInput("temperature");
    model.component("comp2").material("mat2").propertyGroup("idealGas").addInput("pressure");
    model.component("comp2").material("mat2").materialType("nonSolid");

    model.component("comp2").physics().create("acpr", "PressureAcoustics", "geom2");

    model.study("std1").feature("time").setSolveFor("/physics/acpr", false);

    model.component("comp2").physics("acpr").prop("ShapeProperty").set("order_pressure", 1);
    model.component("comp2").physics("acpr").create("swr1", "SphericalWaveRadiation", 2);
    model.component("comp2").physics("acpr").feature("swr1").selection().named("sel7");
    model.component("comp2").physics("acpr").create("efc1", "ExteriorFieldCalculation", 2);
    model.component("comp2").physics("acpr").feature("efc1").selection().named("sel7");

    return model;
  }

  public static Model run3(Model model) {
    model.component("comp2").physics("acpr").feature("efc1").set("UsePPR", false);
    model.component("comp2").physics("acpr").create("nacc1", "NormalAcceleration", 2);
    model.component("comp2").physics("acpr").feature("nacc1").selection().named("sel8");
    model.component("comp2").physics("acpr").feature("nacc1").set("Type", "acc");
    model.component("comp2").physics("acpr").feature("nacc1")
         .set("acc", new String[]{"comp1.genext1(mbd.u_ttX)/T[1/s]", "comp1.genext1(mbd.u_ttY)/T[1/s]", "comp1.genext1(mbd.u_ttZ)/T[1/s]"});

    model.component("comp2").mesh("mesh2").create("ftet1", "FreeTet");
    model.component("comp2").mesh("mesh2").feature("size").set("custom", true);
    model.component("comp2").mesh("mesh2").feature("size").set("hmax", "343/3000/4");
    model.component("comp2").mesh("mesh2").create("bl1", "BndLayer");
    model.component("comp2").mesh("mesh2").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp2").mesh("mesh2").feature("bl1").selection().geom(3);
    model.component("comp2").mesh("mesh2").feature("bl1").selection().set();
    model.component("comp2").mesh("mesh2").feature("bl1").selection().allGeom();
    model.component("comp2").mesh("mesh2").feature("bl1").feature("blp").selection().named("sel7");
    model.component("comp2").mesh("mesh2").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp2").mesh("mesh2").feature("bl1").feature("blp").set("blhmin", "343/3000/4/10");

    model.study().create("std2");
    model.study("std2").setGenPlots(false);
    model.study("std2").label("\u7814\u7a76\uff1a\u58f0\u5b66\uff08\u9891\u57df\uff09");
    model.study("std2").create("tffft", "TimeToFreqFFT");
    model.study("std2").feature("tffft").set("fftinputstudy", "std1");
    model.study("std2").feature("tffft").set("fftendtime", "T");
    model.study("std2").feature("tffft").set("fftmaxfreq", "250/T");
    model.study("std2").feature("tffft").setSolveFor("/physics/acpr", false);
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").set("plist", "range(1000,20,3000)");
    model.study("std2").feature("freq").setSolveFor("/physics/mbd", false);
    model.study("std2").feature("freq").set("usesol", true);
    model.study("std2").feature("freq").set("notsolmethod", "sol");
    model.study("std2").feature("freq").set("notstudy", "std2");
    model.study("std2").feature("freq").set("notsolnum", "all");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("v2").feature("comp2_p").set("scalemethod", "manual");
    model.sol("sol2").feature("v2").feature("comp2_p").set("scaleval", "1e5");
    model.sol("sol2").feature("s1").set("nonlin", false);

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().duplicate("dset6", "dset5");
    model.result().dataset("dset6").selection().geom("geom2", 2);
    model.result().dataset("dset6").selection().named("sel8");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").set("data", "dset5");
    model.result("pg5").setIndex("looplevel", 51, 0);
    model.result("pg5").label("\u8fd1\u573a\u58f0\u538b\u7ea7");
    model.result("pg5").create("slc1", "Slice");
    model.result("pg5").feature("slc1").set("expr", "acpr.Lp_t");
    model.result("pg5").feature("slc1").set("quickxnumber", 1);
    model.result("pg5").feature("slc1").set("colortable", "Rainbow");
    model.result("pg5").feature("slc1").set("colorscalemode", "linear");
    model.result("pg5").feature().duplicate("slc2", "slc1");
    model.result("pg5").run();
    model.result("pg5").feature("slc2").set("titletype", "none");
    model.result("pg5").feature("slc2").set("quickplane", "zx");
    model.result("pg5").feature("slc2").set("quickynumber", 1);
    model.result("pg5").feature("slc2").set("inheritplot", "slc1");
    model.result("pg5").feature().duplicate("slc3", "slc2");
    model.result("pg5").run();
    model.result("pg5").feature("slc3").set("quickplane", "xy");
    model.result("pg5").feature("slc3").set("quickznumber", 1);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").set("data", "dset6");
    model.result("pg6").setIndex("looplevel", 51, 0);
    model.result("pg6").label("\u53d8\u901f\u7bb1\u4f53\u8868\u9762\u7684\u58f0\u538b\u7ea7");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "acpr.Lp_t");
    model.result("pg6").feature("surf1").set("colortable", "Rainbow");
    model.result("pg6").feature("surf1").set("colorscalemode", "linear");
    model.result("pg6").run();
    model.result().create("pg7", "PolarGroup");
    model.result("pg7").run();
    model.result("pg7").label("\u6781\u5750\u6807\u58f0\u538b\u7ea7 xy \u5e73\u9762");
    model.result("pg7").set("data", "dset5");
    model.result("pg7").setIndex("looplevelinput", "manual", 0);
    model.result("pg7").setIndex("looplevel", new int[]{51}, 0);
    model.result("pg7").set("titletype", "manual");
    model.result("pg7").set("title", "\u58f0\u538b\u7ea7 - \u5916\u573a\uff08xy \u5e73\u9762\uff09(dB)");
    model.result("pg7").create("rp1", "RadiationPattern");
    model.result("pg7").feature("rp1").set("markerpos", "datapoints");
    model.result("pg7").feature("rp1").set("linewidth", "preference");
    model.result("pg7").feature("rp1").set("phidisc", 360);
    model.result("pg7").feature("rp1").set("linewidth", 2);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u6781\u5750\u6807\u58f0\u538b\u7ea7 xz \u5e73\u9762");
    model.result("pg8").set("title", "\u58f0\u538b\u7ea7 - \u5916\u573a\uff08xz \u5e73\u9762\uff09(dB)");
    model.result("pg8").run();
    model.result("pg8").feature("rp1").set("normal", new int[]{0, 1, 0});
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").run();
    model.result("pg9").label("\u6781\u5750\u6807\u58f0\u538b\u7ea7 yz \u5e73\u9762");
    model.result("pg9").set("title", "\u58f0\u538b\u7ea7 - \u5916\u573a\uff08yz \u5e73\u9762\uff09(dB)");
    model.result("pg9").run();
    model.result("pg9").feature("rp1").set("normal", new int[]{1, 0, 0});
    model.result("pg9").run();

//    To import content from file, use:
//    model.result().param().loadFile("FILENAME");
    model.result().param().set("xm1", "0", "\u9ea6\u514b\u98ce 1 \u4f4d\u7f6e\uff0cx \u5750\u6807");
    model.result().param().set("ym1", "-0.5", "\u9ea6\u514b\u98ce 1 \u4f4d\u7f6e\uff0cy \u5750\u6807");
    model.result().param().set("zm1", "0", "\u9ea6\u514b\u98ce 1 \u4f4d\u7f6e\uff0cz \u5750\u6807");
    model.result().param().set("xm2", "0", "\u9ea6\u514b\u98ce 2 \u4f4d\u7f6e\uff0cx \u5750\u6807");
    model.result().param().set("ym2", "0", "\u9ea6\u514b\u98ce 2 \u4f4d\u7f6e\uff0cy \u5750\u6807");
    model.result().param().set("zm2", "0.75", "\u9ea6\u514b\u98ce 2 \u4f4d\u7f6e\uff0cz \u5750\u6807");
    model.result().param().set("dm1", "sqrt(xm1^2+ym1^2+zm1^2)", "\u9ea6\u514b\u98ce 1 \u5f84\u5411\u8ddd\u79bb");
    model.result().param().set("dm2", "sqrt(xm2^2+ym2^2+zm2^2)", "\u9ea6\u514b\u98ce 2 \u5f84\u5411\u8ddd\u79bb");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("\u5168\u5c40\u8ba1\u7b97\uff1a\u9ea6\u514b\u98ce 1");
    model.result().numerical("gev1").set("data", "dset5");
    model.result().numerical("gev1").setIndex("expr", "abs(if(dm1<=R,at3(xm1,ym1,zm1,p),pext(xm1,ym1,zm1)))", 0);
    model.result().numerical("gev1").setIndex("descr", "\u538b\u529b\uff08\u7edd\u5bf9\u503c\uff09", 0);
    model.result().numerical("gev1").setIndex("expr", "arg(if(dm1<=R,at3(xm1,ym1,zm1,p),pext(xm1,ym1,zm1)))", 1);
    model.result().numerical("gev1").setIndex("descr", "\u538b\u529b\uff08\u76f8\u4f4d\uff09", 1);
    model.result().numerical("gev1").setIndex("expr", "freq", 2);
    model.result().numerical("gev1").setIndex("unit", "1/s", 2);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97\uff1a\u9ea6\u514b\u98ce 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().duplicate("gev2", "gev1");
    model.result().numerical("gev2").label("\u5168\u5c40\u8ba1\u7b97\uff1a\u9ea6\u514b\u98ce 2");
    model.result().numerical("gev2").setIndex("expr", "abs(if(dm2<=R,at3(xm2,ym2,zm2,p),pext(xm2,ym2,zm2)))", 0);
    model.result().numerical("gev2").setIndex("expr", "arg(if(dm2<=R,at3(xm2,ym2,zm2,p),pext(xm2,ym2,zm2)))", 1);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5168\u5c40\u8ba1\u7b97\uff1a\u9ea6\u514b\u98ce 2");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").label("\u9ea6\u514b\u98ce\u538b\u529b");
    model.result("pg10").set("data", "dset5");
    model.result("pg10").set("titletype", "manual");
    model.result("pg10").set("title", "\u9ea6\u514b\u98ce\u538b\u529b (Pa)");
    model.result("pg10").set("ylabelactive", true);
    model.result("pg10").set("ylabel", "\u538b\u529b (Pa)");
    model.result("pg10").create("glob1", "Global");
    model.result("pg10").feature("glob1").set("markerpos", "datapoints");
    model.result("pg10").feature("glob1").set("linewidth", "preference");
    model.result("pg10").feature("glob1")
         .setIndex("expr", "abs(if(dm1<=R,at3(xm1,ym1,zm1,p),pext(xm1,ym1,zm1)))", 0);
    model.result("pg10").feature("glob1").setIndex("descr", "\u9ea6\u514b\u98ce 1", 0);
    model.result("pg10").feature("glob1")
         .setIndex("expr", "abs(if(dm2<=R,at3(xm2,ym2,zm2,p),pext(xm2,ym2,zm2)))", 1);
    model.result("pg10").feature("glob1").setIndex("descr", "\u9ea6\u514b\u98ce 2", 1);
    model.result("pg10").feature("glob1").set("linewidth", 2);
    model.result("pg10").run();

    model.study().create("std3");
    model.study("std3").label("\u7814\u7a76\uff1a\u58f0\u5b66\uff08\u65f6\u57df\uff09");
    model.study("std3").setGenPlots(false);
    model.study("std3").create("ftfft", "FreqToTimeFFT");
    model.study("std3").feature("ftfft").set("fftouttrange", "range(0,T/2000,T/20)");
    model.study("std3").feature("ftfft").set("fftinputstudy", "std2");
    model.study("std3").feature("ftfft").setSolveFor("/physics/mbd", false);
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").run();
    model.result("pg11").label("\u8fd1\u573a\u538b\u529b\uff1a\u65f6\u95f4");
    model.result("pg11").set("data", "dset8");
    model.result("pg11").create("slc1", "Slice");
    model.result("pg11").feature("slc1").set("quickxnumber", 1);
    model.result("pg11").feature().duplicate("slc2", "slc1");
    model.result("pg11").run();
    model.result("pg11").feature("slc2").set("titletype", "none");
    model.result("pg11").feature("slc2").set("quickplane", "zx");
    model.result("pg11").feature("slc2").set("quickynumber", 1);
    model.result("pg11").feature("slc2").set("inheritplot", "slc1");
    model.result("pg11").feature().duplicate("slc3", "slc2");
    model.result("pg11").run();
    model.result("pg11").feature("slc3").set("quickplane", "xy");
    model.result("pg11").feature("slc3").set("quickznumber", 1);
    model.result("pg11").run();
    model.result("pg11").feature("slc1").create("filt1", "Filter");
    model.result("pg11").run();
    model.result("pg11").feature("slc1").feature("filt1").set("expr", "y>0");
    model.result("pg11").run();
    model.result("pg11").feature("slc2").create("filt1", "Filter");
    model.result("pg11").run();
    model.result("pg11").feature("slc2").feature("filt1").set("expr", "x>0");
    model.result("pg11").run();
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
    model.result().export("anim1").set("maxframes", 50);
    model.result().export().duplicate("anim2", "anim1");
    model.result().export("anim2").showFrame();
    model.result().export("anim2").set("plotgroup", "pg2");
    model.result().export().duplicate("anim3", "anim2");
    model.result().export("anim3").showFrame();
    model.result().export("anim3").set("plotgroup", "pg11");
    model.result("pg1").run();
    model.result().duplicate("pg12", "pg1");
    model.result("pg12").run();
    model.result("pg12").label("\u7f29\u7565\u56fe");
    model.result("pg12").create("iso1", "Isosurface");
    model.result("pg12").feature("iso1").set("data", "dset5");
    model.result("pg12").feature("iso1").setIndex("looplevel", 16, 0);
    model.result("pg12").feature("iso1").set("expr", "acpr.Lp_t");
    model.result("pg12").feature("iso1").set("number", 10);
    model.result("pg12").feature("iso1").create("filt1", "Filter");
    model.result("pg12").run();
    model.result("pg12").feature("iso1").feature("filt1").set("expr", "y>0.1");
    model.result("pg12").run();
    model.result("pg12").run();

    model.title("\u53d8\u901f\u7bb1\u7684\u632f\u52a8\u548c\u566a\u58f0\u5efa\u6a21\uff1a\u8f74\u627f\u7248\u672c");

    model
         .description("\u672c\u4f8b\u4ee5\u7814\u7a76\u624b\u52a8\u53d8\u901f\u7bb1\u8f66\u8f86 5 \u901f\u540c\u6b65\u556e\u5408\u53d8\u901f\u7bb1\u7684\u632f\u52a8\u548c\u566a\u58f0\u7684\u6a21\u578b\u4e3a\u57fa\u7840\uff0c\u5e76\u505a\u4e86\u8fdb\u4e00\u6b65\u6269\u5c55\u3002\u8be5\u6a21\u578b\u7248\u672c\u4f7f\u7528\u6eda\u5b50\u8f74\u627f\u7684\u8be6\u7ec6\u8868\u793a\uff0c\u800c\u4e0d\u662f\u94f0\u94fe\u5173\u8282\u3002\u9996\u5148\uff0c\u5bf9\u4e8e\u6307\u5b9a\u7684\u53d1\u52a8\u673a\u8f6c\u901f\u548c\u5916\u90e8\u8f7d\u8377\uff0c\u4f7f\u7528\u77ac\u6001\u5206\u6790\u6765\u8ba1\u7b97\u53d8\u901f\u7bb1\u7684\u632f\u52a8\u3002\u7136\u540e\uff0c\u8ba1\u7b97\u53d8\u901f\u7bb1\u4f53\u6cd5\u5411\u52a0\u901f\u5ea6\u7684\u9891\u57df\u8868\u793a\uff0c\u4ee5\u7528\u4f5c\u566a\u58f0\u6e90\u3002\u6700\u540e\uff0c\u6267\u884c\u58f0\u5b66\u5206\u6790\u4ee5\u5f97\u5230\u8fd1\u573a\u3001\u8fdc\u573a\u548c\u5916\u573a\u7684\u58f0\u538b\u7ea7\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("gearbox_vibration_noise_bearing.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
