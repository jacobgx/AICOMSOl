/*
 * angle_beam_ndt.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:40 by COMSOL 6.3.0.290. */
public class angle_beam_ndt {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Ultrasound");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").insertFile("angle_beam_ndt_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.param().label("\u51e0\u4f55\u5f62\u72b6");
    model.param().create("par2");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("f0", "1.5[MHz]", "\u4fe1\u53f7\u4e2d\u5fc3\u9891\u7387");
    model.param("par2").set("T0", "1/f0", "\u4fe1\u53f7\u5468\u671f");
    model.param("par2").set("cp_plast", "2080[m/s]", "\u538b\u529b\u6ce2\u901f\uff0c\u5851\u6599");
    model.param("par2").set("cs_plast", "1000[m/s]", "\u526a\u5207\u6ce2\u901f\uff0c\u5851\u6599");
    model.param("par2").set("cp_al", "6200[m/s]", "\u538b\u529b\u6ce2\u901f\uff0c\u94dd");
    model.param("par2").set("cs_al", "3120[m/s]", "\u526a\u5207\u6ce2\u901f\uff0c\u94dd");
    model.param("par2").set("cp_pzt", "4620[m/s]", "\u538b\u529b\u6ce2\u901f\uff0cPZT");
    model.param("par2").set("cs_pzt", "1750[m/s]", "\u526a\u5207\u6ce2\u901f\uff0cPZT");
    model.param("par2").set("cp_damp", "1500[m/s]", "\u538b\u529b\u6ce2\u901f\uff0c\u5438\u97f3\u6750\u6599");
    model.param("par2").set("cs_damp", "775[m/s]", "\u526a\u5207\u6ce2\u901f\uff0c\u5438\u97f3\u6750\u6599");
    model.param("par2").set("rho_damp", "6580[kg/m^3]", "\u5bc6\u5ea6\uff0c\u5438\u97f3\u6750\u6599");
    model.param("par2").set("cp_match", "3400[m/s]", "\u538b\u529b\u6ce2\u901f\uff0c\u5339\u914d\u6750\u6599");
    model.param("par2").set("cs_match", "1920[m/s]", "\u526a\u5207\u6ce2\u901f\uff0c\u5339\u914d\u6750\u6599");
    model.param("par2").set("rho_match", "2280[kg/m^3]", "\u5bc6\u5ea6\uff0c\u5339\u914d\u6750\u6599");
    model.param("par2").label("\u6a21\u578b\u53c2\u6570");

    model.func().create("an1", "Analytic");
    model.func("an1").label("\u7535\u538b\u6e90");
    model.func("an1").set("funcname", "V0");
    model.func("an1").set("expr", "100*exp(-((t - 2*T0)/(T0/2))^2)*sin(2*pi*f0*t)");
    model.func("an1").set("args", "t");
    model.func("an1").set("fununit", "V");
    model.func("an1").setIndex("argunit", "s", 0);
    model.func("an1").setIndex("plotargs", "10*T0", 0, 2);
    model.func("an1").createPlot("pg1");

    model.result("pg1").run();
    model.result("pg1").label("\u7535\u538b\u6e90");

    model.func().create("int1", "Interpolation");
    model.func("int1").set("source", "file");
    model.func("int1").set("filename", "angle_beam_ndt_no_defect_signal.txt");
    model.func("int1").setIndex("argunit", "s", 0);
    model.func("int1").setEntry("funcnames", "col2", "V_no_defect");
    model.func("int1").setIndex("fununit", "V", 0);
    model.func("int1").set("extrap", "none");
    model.func("int1").createPlot("pg2");

    model.result("pg2").run();
    model.result("pg2").label("\u7ec8\u7aef\u7535\u538b");
    model.result("pg2").run();
    model.result("pg2").feature("plot1").set("legend", true);
    model.result("pg2").feature("plot1").set("legendmethod", "manual");
    model.result("pg2").feature("plot1").setIndex("legends", "\u65e0\u7f3a\u9677", 0);
    model.result("pg2").run();

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").set(6);
    model.component("comp1").selection("sel1").label("\u6362\u80fd\u5668");
    model.component("comp1").selection("sel1").set("color", "5");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").set(7);
    model.component("comp1").selection("sel2").label("\u5339\u914d\u5c42");
    model.component("comp1").selection("sel2").set("color", "10");
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").set(4);
    model.component("comp1").selection("sel3").label("\u963b\u5c3c\u5757");
    model.component("comp1").selection("sel3").set("color", "9");
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").set(5);
    model.component("comp1").selection("sel4").label("\u6954\u5757");
    model.component("comp1").selection("sel4").set("color", "18");
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").set(1, 2, 3);
    model.component("comp1").selection("sel5").label("\u6d4b\u8bd5\u6837\u54c1");
    model.component("comp1").selection("sel5").set("color", "4");

    model.component("comp1").coordSystem().create("sys2", "VectorBase");
    model.component("comp1").coordSystem("sys2").set("outofplane", "1");
    model.component("comp1").coordSystem("sys2")
         .set("base", new String[][]{{"cos(alpha)", "sin(alpha)", "0"}, {"0", "0", "1"}, {"-sin(alpha)", "cos(alpha)", "0"}});
    model.component("comp1").coordSystem("sys2").set("orthonormal", true);
    model.component("comp1").coordSystem("sys2").label("\u6362\u80fd\u5668\u5750\u6807\u7cfb");

    model.component("comp1").view().create("view2", "geom1");
    model.component("comp1").view("view2").set("locked", true);
    model.component("comp1").view("view2").axis().set("xmin", -0.005);
    model.component("comp1").view("view2").axis().set("xmax", 0.04);
    model.component("comp1").view("view2").axis().set("ymin", -0.012);
    model.component("comp1").view("view2").axis().set("ymax", 0.012);

    model.component("comp1").physics().create("elte", "ElasticWavesTimeExplicit", "geom1");
    model.component("comp1").physics("elte").feature("eltem1").set("IsotropicOption", "CpCs");
    model.component("comp1").physics("elte").create("pzm1", "PiezoelectricMaterialModel", 2);
    model.component("comp1").physics("elte").feature("pzm1").selection().named("sel1");
    model.component("comp1").physics("elte").create("lrb1", "LowReflectingBoundary", 1);
    model.component("comp1").physics("elte").feature("lrb1").selection().set(1, 13, 15);
    model.component("comp1").physics("elte").create("frac1", "Fracture", 1);
    model.component("comp1").physics("elte").feature("frac1").selection().set(8);
    model.component("comp1").physics("elte").feature("eltem1").create("dmp1", "Damping", 2);
    model.component("comp1").physics("elte").feature("eltem1").feature("dmp1").selection().named("sel2");
    model.component("comp1").physics("elte").feature("eltem1").feature("dmp1")
         .set("InputParameters", "DampingRatios");
    model.component("comp1").physics("elte").feature("eltem1").feature("dmp1").set("f1", "0.99*f0");
    model.component("comp1").physics("elte").feature("eltem1").feature("dmp1").set("zeta1", "5e-2");
    model.component("comp1").physics("elte").feature("eltem1").feature("dmp1").set("f2", "1.01*f0");
    model.component("comp1").physics("elte").feature("eltem1").feature("dmp1").set("zeta2", "5e-2");
    model.component("comp1").physics("elte").feature("eltem1").feature().duplicate("dmp2", "dmp1");
    model.component("comp1").physics("elte").feature("eltem1").feature("dmp2").selection().named("sel3");
    model.component("comp1").physics("elte").feature("eltem1").feature().duplicate("dmp3", "dmp2");
    model.component("comp1").physics("elte").feature("eltem1").feature("dmp3").selection().named("sel4");
    model.component("comp1").physics("elte").feature("eltem1").feature("dmp3").set("zeta1", "1e-2");
    model.component("comp1").physics("elte").feature("eltem1").feature("dmp3").set("zeta2", "1e-2");
    model.component("comp1").physics("elte").feature("eltem1").feature().duplicate("dmp4", "dmp3");
    model.component("comp1").physics("elte").feature("eltem1").feature("dmp4").selection().named("sel5");
    model.component("comp1").physics("elte").feature("eltem1").feature("dmp4").set("zeta1", "0.5e-2");
    model.component("comp1").physics("elte").feature("eltem1").feature("dmp4").set("zeta2", "0.5e-2");
    model.component("comp1").physics("elte").feature("pzm1").set("coordinateSystem", "sys2");
    model.component("comp1").physics("elte").feature("pzm1").create("mdmp1", "MechanicalDamping", 2);
    model.component("comp1").physics("elte").feature("pzm1").feature("mdmp1")
         .set("InputParameters", "DampingRatios");
    model.component("comp1").physics("elte").feature("pzm1").feature("mdmp1").set("f1", "0.99*f0");
    model.component("comp1").physics("elte").feature("pzm1").feature("mdmp1").set("zeta1", "0.5e-2");
    model.component("comp1").physics("elte").feature("pzm1").feature("mdmp1").set("f2", "1.01*f0");
    model.component("comp1").physics("elte").feature("pzm1").feature("mdmp1").set("zeta2", "0.5e-2");
    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics("es").selection().set();
    model.component("comp1").physics("es").selection().named("sel1");
    model.component("comp1").physics("es").create("ccnp1", "ChargeConservationPiezo", 2);
    model.component("comp1").physics("es").feature("ccnp1").selection().named("sel1");
    model.component("comp1").physics("es").create("gnd1", "Ground", 1);
    model.component("comp1").physics("es").feature("gnd1").selection().set(33);
    model.component("comp1").physics("es").create("term1", "Terminal", 1);
    model.component("comp1").physics("es").feature("term1").selection().set(32);
    model.component("comp1").physics("es").feature("term1").set("TerminalType", "Circuit");
    model.component("comp1").physics().create("cir", "Circuit", "geom1");
    model.component("comp1").physics("cir").create("V1", "VoltageSource", -1);
    model.component("comp1").physics("cir").feature("V1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("V1").set("value", "V0(t)");
    model.component("comp1").physics("cir").create("R1", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 1, 0, 0);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 2, 1, 0);
    model.component("comp1").physics("cir").feature("R1").set("R", "2[ohm]");
    model.component("comp1").physics("cir").create("termI1", "ModelTerminalIV", -1);
    model.component("comp1").physics("cir").feature("termI1").set("Connections", 2);
    model.component("comp1").physics("cir").feature("termI1").set("V_src", "root.comp1.es.V0_1");

    model.component("comp1").multiphysics().create("pzete1", "PiezoelectricEffectTimeExplicit", 2);

    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").set("probename", "V_with_defect");
    model.component("comp1").probe("var1").set("expr", "es.V0_1");
    model.component("comp1").probe("var1").set("descr", "\u7ec8\u7aef\u7535\u538b");
    model.component("comp1").probe("var1").set("descractive", true);

    model.component("comp1").coordSystem().create("ab1", "AbsorbingLayer");
    model.component("comp1").coordSystem("ab1").selection().set(1, 3);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").label("Aluminum");
    model.component("comp1").material("mat1").set("family", "aluminum");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").label("Acrylic plastic");
    model.component("comp1").material("mat2").set("family", "custom");
    model.component("comp1").material("mat2")
         .set("customspecular", new double[]{0.9803921568627451, 0.9803921568627451, 0.9803921568627451});
    model.component("comp1").material("mat2").set("diffuse", "custom");
    model.component("comp1").material("mat2")
         .set("customdiffuse", new double[]{0.39215686274509803, 0.7843137254901961, 0.39215686274509803});
    model.component("comp1").material("mat2").set("ambient", "custom");
    model.component("comp1").material("mat2")
         .set("customambient", new double[]{0.39215686274509803, 0.7843137254901961, 0.39215686274509803});
    model.component("comp1").material("mat2").set("noise", true);
    model.component("comp1").material("mat2").set("lighting", "phong");
    model.component("comp1").material("mat2").set("shininess", 1000);
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"7.0e-5[1/K]", "0", "0", "0", "7.0e-5[1/K]", "0", "0", "0", "7.0e-5[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "1470[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "1190[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"0.18[W/(m*K)]", "0", "0", "0", "0.18[W/(m*K)]", "0", "0", "0", "0.18[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "3.2[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat2").selection().named("sel4");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("StrainCharge", "StrainCharge", "Strain-charge form");
    model.component("comp1").material("mat3").propertyGroup()
         .create("StressCharge", "StressCharge", "Stress-charge form");
    model.component("comp1").material("mat3").label("Lead Zirconate Titanate (PZT-5H)");
    model.component("comp1").material("mat3").set("family", "lead");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "440[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.3[W/(m*K)]", "0", "0", "0", "1.3[W/(m*K)]", "0", "0", "0", "1.3[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1704.4", "0", "0", "0", "1704.4", "0", "0", "0", "1433.6"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "7500[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("StrainCharge").label("Strain-charge form");
    model.component("comp1").material("mat3").propertyGroup("StrainCharge")
         .set("sE", new String[]{"1.65e-011[1/Pa]", "-4.78e-012[1/Pa]", "-8.45e-012[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "-4.78e-012[1/Pa]", "1.65e-011[1/Pa]", "-8.45e-012[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "-8.45e-012[1/Pa]", "-8.45e-012[1/Pa]", "2.07e-011[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "4.35e-011[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "4.35e-011[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "4.26e-011[1/Pa]"});
    model.component("comp1").material("mat3").propertyGroup("StrainCharge")
         .set("dET", new String[]{"0[C/N]", "0[C/N]", "-2.74e-010[C/N]", "0[C/N]", "0[C/N]", "-2.74e-010[C/N]", "0[C/N]", "0[C/N]", "5.93e-010[C/N]", "0[C/N]", 
         "7.41e-010[C/N]", "0[C/N]", "7.41e-010[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]"});
    model.component("comp1").material("mat3").propertyGroup("StrainCharge")
         .set("epsilonrT", new String[]{"3130", "0", "0", "0", "3130", "0", "0", "0", "3400"});
    model.component("comp1").material("mat3").propertyGroup("StressCharge").label("Stress-charge form");
    model.component("comp1").material("mat3").propertyGroup("StressCharge")
         .set("cE", new String[]{"1.27205e+011[Pa]", "8.02122e+010[Pa]", "8.46702e+010[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "8.02122e+010[Pa]", "1.27205e+011[Pa]", "8.46702e+010[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "8.46702e+010[Pa]", "8.46702e+010[Pa]", "1.17436e+011[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", 
         "0[Pa]", "2.29885e+010[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "2.29885e+010[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "2.34742e+010[Pa]"});
    model.component("comp1").material("mat3").propertyGroup("StressCharge")
         .set("eES", new String[]{"0[C/m^2]", "0[C/m^2]", "-6.62281[C/m^2]", "0[C/m^2]", "0[C/m^2]", "-6.62281[C/m^2]", "0[C/m^2]", "0[C/m^2]", "23.2403[C/m^2]", "0[C/m^2]", 
         "17.0345[C/m^2]", "0[C/m^2]", "17.0345[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]"});
    model.component("comp1").material("mat3").propertyGroup("StressCharge")
         .set("epsilonrS", new String[]{"1704.4", "0", "0", "0", "1704.4", "0", "0", "0", "1433.6"});
    model.component("comp1").material("mat3").selection().named("sel1");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").selection().named("sel2");
    model.component("comp1").material("mat4").propertyGroup()
         .create("CpCs", "CpCs", "Pressure_wave_and_shear_wave_speeds");
    model.component("comp1").material("mat4").propertyGroup("CpCs").set("cp", new String[]{"cp_match"});
    model.component("comp1").material("mat4").propertyGroup("CpCs").set("cs", new String[]{"cs_match"});
    model.component("comp1").material("mat4").propertyGroup("def").set("density", new String[]{"rho_match"});
    model.component("comp1").material("mat4").label("\u5339\u914d\u6750\u6599");
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").selection().named("sel3");
    model.component("comp1").material("mat5").propertyGroup()
         .create("CpCs", "CpCs", "Pressure_wave_and_shear_wave_speeds");
    model.component("comp1").material("mat5").propertyGroup("CpCs").set("cp", new String[]{"cp_damp"});
    model.component("comp1").material("mat5").propertyGroup("CpCs").set("cs", new String[]{"cs_damp"});
    model.component("comp1").material("mat5").propertyGroup("def").set("density", new String[]{"rho_damp"});
    model.component("comp1").material("mat5").label("\u5438\u97f3\u6750\u6599");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(6, 7);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(34);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").feature("map1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").selection().named("sel1");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmax", "cs_pzt/f0/1.5");
    model.component("comp1").mesh("mesh1").feature("map1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size2").selection().named("sel2");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size2").set("hmax", "cs_match/f0/1.5");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().named("sel3");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "cs_damp/f0/1.5");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().named("sel4");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmax", "cs_plast/f0/1.5");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size3", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").selection().named("sel5");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").set("hmax", "cs_al/f0/1.5");
    model.component("comp1").mesh("mesh1").run();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/elte", true);
    model.study("std1").feature("time").setSolveFor("/physics/es", true);
    model.study("std1").feature("time").setSolveFor("/physics/cir", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/pzete1", true);
    model.study("std1").feature("time").set("tlist", "range(0, T0/5, 30*T0)");
    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");

    model.sol("sol1").runAll();

    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u901f\u5ea6\u5927\u5c0f (elte)");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").set("defaultPlotID", "ElasticWavesTimeExplicit/phys1/pdef1/pcond2/pg1");
    model.result("pg4").set("weight", 0);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("resolution", "custom");
    model.result("pg4").feature("surf1").set("refine", 6);
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u538b\u529b (elte)");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").set("defaultPlotID", "ElasticWavesTimeExplicit/phys1/pdef1/pcond2/pg2");
    model.result("pg5").set("weight", 0);
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "elte.p");
    model.result("pg5").feature("surf1").set("colortable", "Wave");
    model.result("pg5").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg5").feature("surf1").set("resolution", "custom");
    model.result("pg5").feature("surf1").set("refine", 6);
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("\u7535\u52bf (es)");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").set("defaultPlotID", "InterfaceComponents/PlotDefaults/icom2/pdef1/pcond2/pcond2/pg1");
    model.result("pg6").set("weight", 0);
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("solutionparams", "parent");
    model.result("pg6").feature("surf1").set("expr", "V");
    model.result("pg6").feature("surf1").set("colortable", "Dipole");
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
    model.result("pg6").feature("str1").selection().set(31, 32, 33, 34);
    model.result("pg6").feature("str1").set("inheritplot", "surf1");
    model.result("pg6").feature("str1").feature().create("col1", "Color");
    model.result("pg6").feature("str1").feature("col1").set("expr", "V");
    model.result("pg6").feature("str1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg6").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg6").feature("str1").feature().create("filt1", "Filter");
    model.result("pg6").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").label("\u7535\u573a (es)");
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").set("showlegendsmaxmin", true);
    model.result("pg7").set("defaultPlotID", "InterfaceComponents/PlotDefaults/icom3/pdef1/pcond2/pcond2/pg1");
    model.result("pg7").set("weight", 0);
    model.result("pg7").feature().create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("solutionparams", "parent");
    model.result("pg7").feature("surf1").set("expr", "es.normE");
    model.result("pg7").feature("surf1").set("colortable", "Prism");
    model.result("pg7").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg7").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("data", "parent");
    model.result("pg7").feature().create("str1", "Streamline");
    model.result("pg7").feature("str1").set("showsolutionparams", "on");
    model.result("pg7").feature("str1").set("solutionparams", "parent");
    model.result("pg7").feature("str1").set("expr", new String[]{"es.Ex", "es.Ey"});
    model.result("pg7").feature("str1").set("titletype", "none");
    model.result("pg7").feature("str1").set("posmethod", "uniform");
    model.result("pg7").feature("str1").set("udist", 0.02);
    model.result("pg7").feature("str1").set("maxlen", 0.4);
    model.result("pg7").feature("str1").set("maxsteps", 5000);
    model.result("pg7").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg7").feature("str1").set("inheritcolor", false);
    model.result("pg7").feature("str1").set("showsolutionparams", "on");
    model.result("pg7").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg7").feature("str1").set("showsolutionparams", "on");
    model.result("pg7").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg7").feature("str1").set("showsolutionparams", "on");
    model.result("pg7").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg7").feature("str1").set("showsolutionparams", "on");
    model.result("pg7").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg7").feature("str1").set("data", "parent");
    model.result("pg7").feature("str1").selection().geom("geom1", 1);
    model.result("pg7").feature("str1").selection().set(31, 32, 33, 34);
    model.result("pg7").feature("str1").set("inheritplot", "surf1");
    model.result("pg7").feature("str1").feature().create("col1", "Color");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg7").feature("str1").feature("col1").set("expr", "es.normE");
    model.result("pg7").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg7").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg7").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg7").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg7").feature("str1").feature().create("filt1", "Filter");
    model.result("pg7").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg4").run();
    model.result("pg2").run();
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u7ec8\u7aef\u7535\u538b (V)");
    model.result("pg2").create("tblp1", "Table");
    model.result("pg2").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg2").feature("tblp1").set("linewidth", "preference");
    model.result("pg2").feature("tblp1").set("legend", true);
    model.result("pg2").feature("tblp1").set("legendmethod", "manual");
    model.result("pg2").feature("tblp1").setIndex("legends", "\u6709\u7f3a\u9677", 0);
    model.result("pg2").run();
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").run();
    model.result("pg8").label("\u538b\u529b\u6ce2\u548c\u526a\u5207\u6ce2");
    model.result("pg8").setIndex("looplevel", 91, 0);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", "sqrt(abs(elte.I2s))*sign(elte.I2s)");
    model.result("pg8").feature("surf1").set("colortable", "Twilight");
    model.result("pg8").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").setIndex("looplevel", 31, 0);
    model.result("pg8").run();
    model.result("pg8").setIndex("looplevel", 46, 0);
    model.result("pg8").run();
    model.result("pg8").setIndex("looplevel", 61, 0);
    model.result("pg8").run();
    model.result("pg8").setIndex("looplevel", 91, 0);
    model.result("pg8").run();
    model.result("pg2").run();
    model.result().duplicate("pg9", "pg2");
    model.result("pg9").run();
    model.result("pg9").label("\u7ec8\u7aef\u7535\u538b\uff08\u653e\u5927\uff09");
    model.result("pg9").set("legendpos", "upperleft");
    model.result("pg9").run();
    model.result("pg9").feature("plot1").set("lowerbound", 1.2E-5);
    model.result("pg9").run();
    model.result("pg9").feature().remove("tblp1");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").create("glob1", "Global");
    model.result("pg9").feature("glob1").set("markerpos", "datapoints");
    model.result("pg9").feature("glob1").set("linewidth", "preference");
    model.result("pg9").feature("glob1").set("data", "dset2");
    model.result("pg9").feature("glob1").set("expr", new String[]{"V_with_defect"});
    model.result("pg9").feature("glob1").set("descr", new String[]{"\u5168\u5c40\u53d8\u91cf\u63a2\u9488 1"});
    model.result("pg9").feature("glob1").set("unit", new String[]{"V"});
    model.result("pg9").feature("glob1").set("legendmethod", "manual");
    model.result("pg9").feature("glob1").setIndex("legends", "\u6709\u7f3a\u9677", 0);
    model.result("pg9").feature("glob1").setIndex("looplevelinput", "interp", 0);
    model.result("pg9").feature("glob1").setIndex("interp", "range(1.2E-5, 1E-8, 2E-5)", 0);
    model.result("pg9").run();
    model.result("pg8").run();

    model.title("\u89d2\u5ea6\u675f\u65e0\u635f\u68c0\u6d4b");

    model
         .description("\u89d2\u5ea6\u675f\u8d85\u58f0\u88c5\u7f6e\u7528\u4e8e\u5bf9\u56fa\u4f53\u7269\u4f53\uff08\u5982\u91d1\u5c5e\u7ba1\uff09\u8fdb\u884c\u65e0\u635f\u68c0\u6d4b (NDT)\u3002\u6b64\u7c7b\u88c5\u7f6e\u5c24\u5176\u9002\u7528\u4e8e\u68c0\u6d4b\u710a\u63a5\u533a\u57df\u53ca\u5176\u5468\u56f4\u7684\u7f3a\u9677\uff0c\u4f8b\u5982\u5b54\u9699\u3001\u5c0f\u88c2\u7eb9\u3001\u672a\u7194\u5408\u7b49\u95ee\u9898\u3002\u5728\u76f4\u5c04\u68c0\u6d4b\u96be\u4ee5\u53d1\u73b0\u7f3a\u9677\u7684\u533a\u57df\uff0c\u4f8b\u5982\uff0c\u5782\u76f4\u4e14\u7ec6\u5c0f\u7684\u88c2\u7eb9\u7531\u4e8e\u53cd\u5c04\u91cf\u5c0f\u800c\u96be\u4ee5\u88ab\u68c0\u6d4b\u65f6\uff0c\u89d2\u5ea6\u675f\u65e0\u635f\u68c0\u6d4b\u4fbf\u663e\u793a\u51fa\u5176\u72ec\u7279\u7684\u4f18\u52bf\u3002\u89d2\u5ea6\u675f\u65e0\u635f\u68c0\u6d4b\u7684\u5de5\u4f5c\u539f\u7406\u5728\u4e8e\uff0c\u5c06\u6362\u80fd\u5668\u53d1\u51fa\u7684\u7eb5\u6ce2\uff08\u538b\u7f29\u6ce2\uff09\u8f6c\u6362\u4e3a\u5728\u6d4b\u8bd5\u6837\u54c1\u4e2d\u6298\u5c04\u7684\u526a\u5207\u6ce2\uff08\u6a2a\u6ce2\uff09\u3002\u8fd9\u79cd\u526a\u5207\u6ce2\u968f\u540e\u4f1a\u88ab\u6d4b\u8bd5\u7269\u4f53\u4e2d\u7684\u7f3a\u9677\u53cd\u5c04\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("angle_beam_ndt.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
