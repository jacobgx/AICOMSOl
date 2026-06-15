/*
 * li_battery_1d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:24 by COMSOL 6.3.0.290. */
public class li_battery_1d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Lithium-Ion_Batteries,_Performance");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("liion", "LithiumIonBatteryMPH", "geom1");

    model.study().create("std1");
    model.study("std1").create("cdi", "CurrentDistributionInitialization");
    model.study("std1").feature("cdi").set("ftplistmethod", "manual");
    model.study("std1").feature("cdi").set("solnum", "auto");
    model.study("std1").feature("cdi").set("notsolnum", "auto");
    model.study("std1").feature("cdi").set("outputmap", new String[]{});
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").setSolveFor("/physics/liion", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/liion", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("i_1C", "17.5[A/m^2]", "1C \u653e\u7535\u7535\u6d41");
    model.param().set("Ds_neg", "3.9e-14[m^2/s]", "\u8d1f\u6781\u7684\u56fa\u76f8\u9502\u6269\u6563\u7cfb\u6570");
    model.param().set("Ds_pos", "1e-13[m^2/s]", "\u6b63\u6781\u7684\u56fa\u76f8\u9502\u6269\u6563\u7cfb\u6570");
    model.param().set("rp_neg", "12.5e-6[m]", "\u8d1f\u6781\u9897\u7c92\u534a\u5f84");
    model.param().set("rp_pos", "8e-6[m]", "\u6b63\u6781\u9897\u7c92\u534a\u5f84");
    model.param().set("T", "298[K]", "\u6e29\u5ea6");
    model.param().set("epsl_pos", "0.63", "\u7535\u89e3\u8d28\u76f8\u4f53\u79ef\u5206\u6570\uff0c\u6b63\u6781");
    model.param()
         .set("epss_pos", "1-epsl_pos-epss_filler_pos", "\u7535\u6781\u76f8\u4f53\u79ef\u5206\u6570\uff0c\u6b63\u6781");
    model.param()
         .set("epss_filler_pos", "0.073", "\u5bfc\u7535\u586b\u6599\u76f8\u4f53\u79ef\u5206\u6570\uff0c\u6b63\u6781");
    model.param().set("cl_0", "2000[mol/m^3]", "\u521d\u59cb\u7535\u89e3\u8d28\u76d0\u6d53\u5ea6");
    model.param().set("epsl_neg", "0.503", "\u7535\u89e3\u8d28\u76f8\u4f53\u79ef\u5206\u6570\uff0c\u8d1f\u6781");
    model.param()
         .set("epss_filler_neg", "0.026", "\u5bfc\u7535\u586b\u6599\u76f8\u4f53\u79ef\u5206\u6570\uff0c\u8d1f\u6781");
    model.param()
         .set("epss_neg", "1-epsl_neg-epss_filler_neg", "\u8d1f\u6781\u7684\u56fa\u76f8\u4f53\u79ef\u5206\u6570");
    model.param().set("csmax_neg", "26390[mol/m^3]", "\u6700\u5927\u56fa\u76f8\u6d53\u5ea6\uff0c\u8d1f\u6781");
    model.param().set("csmax_pos", "22860[mol/m^3]", "\u6700\u5927\u56fa\u76f8\u6d53\u5ea6\uff0c\u6b63\u6781");
    model.param()
         .set("cs0_neg", "14870[mol/m^3]", "\u8d1f\u6781\u6d3b\u6027\u6750\u6599\u7684\u521d\u59cb\u6d53\u5ea6");
    model.param()
         .set("cs0_pos", "3900[mol/m^3]", "\u6b63\u6781\u6d3b\u6027\u6750\u6599\u7684\u521d\u59cb\u6d53\u5ea6");
    model.param().set("Ks_neg", "100[S/m]", "\u8d1f\u6781\u7684\u56fa\u76f8\u7535\u5bfc\u7387");
    model.param().set("Ks_pos", "100[S/m]", "\u6b63\u6781\u7684\u56fa\u76f8\u7535\u5bfc\u7387");
    model.param()
         .set("i0_neg_ref", "0.11[mA/cm^2]", "\u53c2\u8003\u6761\u4ef6\u4e0b\u7684\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u8d1f\u6781");
    model.param()
         .set("i0_pos_ref", "0.08[mA/cm^2]", "\u53c2\u8003\u6761\u4ef6\u4e0b\u7684\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6b63\u6781");
    model.param().set("cl_ref", "2000[mol/m^3]", "\u53c2\u8003\u7535\u89e3\u8d28\u76d0\u6d53\u5ea6");
    model.param()
         .set("cs_neg_ref", "14870[mol/m^3]", "\u8d1f\u6781\u6d3b\u6027\u6750\u6599\u7684\u53c2\u8003\u6d53\u5ea6");
    model.param()
         .set("cs_pos_ref", "3900[mol/m^3]", "\u8d1f\u6781\u6d3b\u6027\u6750\u6599\u7684\u53c2\u8003\u6d53\u5ea6");
    model.param()
         .set("k_neg", "i0_neg_ref/(F_const*(cs_neg_ref[m^3/mol])^0.5*((csmax_neg-cs_neg_ref)[m^3/mol])^0.5)[mol/m^3]", "\u901f\u7387\u5e38\u6570\uff0c\u8d1f\u6781");
    model.param()
         .set("k_pos", "i0_pos_ref/(F_const*(cs_pos_ref[m^3/mol])^0.5*((csmax_pos-cs_pos_ref)[m^3/mol])^0.5)[mol/m^3]", "\u901f\u7387\u5e38\u6570\uff0c\u6b63\u6781");
    model.param().set("brugg", "3.3", "Bruggeman \u7cfb\u6570");
    model.param().set("t_disch_stop", "2000[s]", "\u653e\u7535\u6301\u7eed\u65f6\u95f4");
    model.param().set("t_ocp", "300[s]", "\u5f00\u8def\u6301\u7eed\u65f6\u95f4");
    model.param().set("t_charge_stop", "2000[s]", "\u7535\u8377\u6301\u7eed\u65f6\u95f4");
    model.param().set("L_neg", "100e-6[m]", "\u8d1f\u6781\u957f\u5ea6");
    model.param().set("L_sep", "52e-6[m]", "\u9694\u819c\u957f\u5ea6");
    model.param().set("L_pos", "174e-6[m]", "\u6b63\u6781\u957f\u5ea6");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").set("specify", "len");
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_neg", 0);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_sep", 1);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_pos", 2);
    model.component("comp1").geom("geom1").run("i1");

    model.func().create("int1", "Interpolation");
    model.func("int1").set("source", "file");
    model.func("int1").set("filename", "li_battery_1d_Eeq_neg.txt");
    model.func("int1").importData();
    model.func("int1").set("funcname", "Eeq_neg");
    model.func("int1").set("interp", "cubicspline");
    model.func("int1").set("extrap", "interior");
    model.func("int1").setIndex("fununit", "V", 0);
    model.func().create("pw1", "Piecewise");
    model.func("pw1").set("arg", "t");
    model.func("pw1").set("smooth", "contd2");
    model.func("pw1").set("zonelengthtype", "absolute");
    model.func("pw1").set("smoothzone", 10);
    model.func("pw1")
         .set("pieces", new String[][]{{"0", "t_disch_stop", "1"}, 
         {"t_disch_stop", "t_disch_stop+t_ocp", "0"}, 
         {"t_disch_stop+t_ocp", "t_charge_stop+t_disch_stop+t_ocp", "-1"}, 
         {"t_charge_stop+t_disch_stop+t_ocp", "8000", "0"}});
    model.func("pw1").set("argunit", "s");
    model.func("pw1").set("fununit", "1");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").label("\u53d8\u91cf\uff08\u6b63\u6781\u96c6\u6d41\u4f53\uff09");
    model.component("comp1").variable("var1").selection().geom("geom1", 0);
    model.component("comp1").variable("var1").selection().set(4);
    model.component("comp1").variable("var1").set("i_app", "pw1(t)*i_1C");
    model.component("comp1").variable("var1")
         .descr("i_app", "\u65bd\u52a0\u7684\u7535\u6c60\u5355\u5143\u7535\u6d41\u5bc6\u5ea6");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int2", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElectrodePotential", "ElectrodePotential", "Equilibrium potential");
    model.component("comp1").material("mat1").propertyGroup()
         .create("OperationalSOC", "OperationalSOC", "Operational electrode state of charge");
    model.component("comp1").material("mat1").propertyGroup()
         .create("EquilibriumConcentration", "EquilibriumConcentration", "Equilibrium concentration");
    model.component("comp1").material("mat1").propertyGroup()
         .create("EquilibriumPotentialWithDOCInput", "EquilibriumPotentialWithDOCInput", "Equilibrium potential (using degree of conversion as model input)");
    model.component("comp1").material("mat1").propertyGroup()
         .create("EquilibriumDegreeOfConversion", "EquilibriumDegreeOfConversion", "Equilibrium degree of conversion");
    model.component("comp1").material("mat1").label("LMO, LiMn2O4 Spinel (Positive, Li-ion Battery)");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("funcname", "Eeq");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0.1750", "4.2763"}, 
         {"0.1950", "4.1898"}, 
         {"0.2150", "4.1507"}, 
         {"0.2350", "4.133"}, 
         {"0.2550", "4.1248"}, 
         {"0.2750", "4.1209"}, 
         {"0.2950", "4.119"}, 
         {"0.3150", "4.1179"}, 
         {"0.3350", "4.1171"}, 
         {"0.3550", "4.1165"}, 
         {"0.3750", "4.116"}, 
         {"0.3950", "4.1153"}, 
         {"0.4150", "4.1145"}, 
         {"0.4350", "4.1135"}, 
         {"0.4550", "4.1121"}, 
         {"0.4750", "4.1099"}, 
         {"0.4950", "4.1066"}, 
         {"0.5150", "4.1014"}, 
         {"0.5350", "4.0934"}, 
         {"0.5550", "4.082"}, 
         {"0.5750", "4.067"}, 
         {"0.5950", "4.05"}, 
         {"0.6150", "4.0333"}, 
         {"0.6350", "4.0192"}, 
         {"0.6550", "4.0087"}, 
         {"0.6750", "4.0012"}, 
         {"0.6950", "3.996"}, 
         {"0.7150", "3.9923"}, 
         {"0.7350", "3.9893"}, 
         {"0.7550", "3.9867"}, 
         {"0.7750", "3.9841"}, 
         {"0.7950", "3.9813"}, 
         {"0.8150", "3.9783"}, 
         {"0.8350", "3.9747"}, 
         {"0.8550", "3.9705"}, 
         {"0.8750", "3.9652"}, 
         {"0.8950", "3.9585"}, 
         {"0.9150", "3.9493"}, 
         {"0.9350", "3.9361"}, 
         {"0.9550", "3.9144"}, 
         {"0.9750", "3.869"}, 
         {"0.9950", "3.5944"}, 
         {"", ""}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("fununit", new String[]{"V"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("argunit", new String[]{""});
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("defineinv", true);
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("funcinvname", "Eeq_inv");
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").set("funcname", "dEeqdT");
    model.component("comp1").material("mat1").propertyGroup("def").func("int2")
         .set("table", new String[][]{{"0.15", "0.15e-3"}, 
         {"0.18", "0.25e-3"}, 
         {"0.20", "0.21e-3"}, 
         {"0.209", "0.19e-3"}, 
         {"0.26", "0.175e-3"}, 
         {"0.28", "0.166e-3"}, 
         {"0.30", "0.155e-3"}, 
         {"0.35", "0.11e-3"}, 
         {"0.394", "0.095e-3"}, 
         {"0.41", "0.05e-3"}, 
         {"0.437", "0.02e-3"}, 
         {"0.445", "0"}, 
         {"0.46", "-0.048e-3"}, 
         {"0.48", "-0.15e-3"}, 
         {"0.50", "-0.255e-3"}, 
         {"0.515", "-0.3e-3"}, 
         {"0.545", "-0.3e-3"}, 
         {"0.553", "-0.22e-3"}, 
         {"0.58", "-0.145e-3"}, 
         {"0.592", "-0.10e-3"}, 
         {"0.60", "0"}, 
         {"0.62", "0.08e-3"}, 
         {"0.64", "0.12e-3"}, 
         {"0.70", "0.124e-3"}, 
         {"0.72", "0.10e-3"}, 
         {"0.73", "0.05e-3"}, 
         {"0.76", "0"}, 
         {"0.78", "-0.057e-3"}, 
         {"0.81", "-0.08e-3"}, 
         {"0.86", "-0.10e-3"}, 
         {"0.91", "-0.16e-3"}, 
         {"0.96", "-0.22e-3"}, 
         {"0.98", "-0.30e-3"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").set("interp", "piecewisecubic");
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").set("fununit", new String[]{"V/K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").set("argunit", new String[]{""});
    model.component("comp1").material("mat1").propertyGroup("def").set("youngsmodulus", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("poissonsratio", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("youngsmodulus", "194[GPa]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("youngsmodulus", "Yue Qi et al 2014 J. Electrochem. Soc. 161 F3010");
    model.component("comp1").material("mat1").propertyGroup("def").set("poissonsratio", "0.26");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("poissonsratio", "Yue Qi et al 2014 J. Electrochem. Soc. 161 F3010");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"1.3e-3[S/cm]", "0", "0", "0", "1.3e-3[S/cm]", "0", "0", "0", "1.3e-3[S/cm]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("electricconductivity", "Electrochemical and Electronic Charge Transport Properties of Ni-Doped LiMn2O4 Spinel Obtained from Polyol-Mediated Synthesis, Shuo Yang et al, Materials 2018, 11(5), 806");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("diffusion", new String[]{"1e-14*exp(20000/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]", "0", "0", "0", "1e-14*exp(20000/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]", "0", "0", "0", "1e-14*exp(20000/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("diffusion", "V. Srinivasan and C.Y. Wang, \"Analysis of Electrochemical and Thermal Behavior of Li-Ion Cells,\" J. Electrochem. Soc., vol. 150, p. A98, 2003");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "4140[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("density", "N. Nitta, F. Wu, J. Tae Lee, and G. Yushin, Materials Today, Volume 18, Number 5, June 2015");
    model.component("comp1").material("mat1").propertyGroup("def").set("T_ref", "298[K]");
    model.component("comp1").material("mat1").propertyGroup("def").descr("T_ref", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("T2", "min(393.15,max(T,223.15))");
    model.component("comp1").material("mat1").propertyGroup("def").descr("T2", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("csmax", "22860[mol/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def").descr("csmax", "");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").identifier("eeq");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .set("Eeq", "def.Eeq(doc)+def.dEeqdT(doc)*(T-298[K])");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .setPropertyInfo("Eeq", "V. Srinivasan and C.Y. Wang, \"Analysis of Electrochemical and Thermal Behavior of Li-Ion Cells,\" J. Electrochem. Soc., vol. 150, p. A98, 2003");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("dEeqdT", "def.dEeqdT(doc)");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .setPropertyInfo("dEeqdT", "V. Srinivasan and C.Y. Wang, \"Analysis of Electrochemical and Thermal Behavior of Li-Ion Cells,\" J. Electrochem. Soc., vol. 150, p. A98, 2003");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("cEeqref", "def.csmax");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .setPropertyInfo("cEeqref", "V. Srinivasan and C.Y. Wang, \"Analysis of Electrochemical and Thermal Behavior of Li-Ion Cells,\" J. Electrochem. Soc., vol. 150, p. A98, 2003");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("doc", "c/cEeqref");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .descr("doc", "Degree of conversion (state of lithiation)");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").addInput("concentration");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("OperationalSOC")
         .label("Operational electrode state of charge");
    model.component("comp1").material("mat1").propertyGroup("OperationalSOC").identifier("opsoc");
    model.component("comp1").material("mat1").propertyGroup("OperationalSOC").set("socmax", "def.Eeq_inv(E_min)");
    model.component("comp1").material("mat1").propertyGroup("OperationalSOC").set("socmin", "def.Eeq_inv(E_max)");
    model.component("comp1").material("mat1").propertyGroup("OperationalSOC").set("E_max", "4.2[V]");
    model.component("comp1").material("mat1").propertyGroup("OperationalSOC").set("E_min", "3.9[V]");
    model.component("comp1").material("mat1").propertyGroup("EquilibriumConcentration")
         .label("Equilibrium concentration");
    model.component("comp1").material("mat1").propertyGroup("EquilibriumConcentration")
         .set("csEq", "def.csmax*def.Eeq_inv(V)");
    model.component("comp1").material("mat1").propertyGroup("EquilibriumConcentration")
         .addInput("electricpotential");
    model.component("comp1").material("mat1").propertyGroup("EquilibriumPotentialWithDOCInput")
         .label("Equilibrium potential (using degree of conversion as model input)");
    model.component("comp1").material("mat1").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("Eeq", "def.Eeq(doc)+def.dEeqdT(doc)*(T-298[K])");
    model.component("comp1").material("mat1").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("dEeqdT", "def.dEeqdT(doc)");
    model.component("comp1").material("mat1").propertyGroup("EquilibriumPotentialWithDOCInput")
         .addInput("degreeofconversion");
    model.component("comp1").material("mat1").propertyGroup("EquilibriumPotentialWithDOCInput")
         .addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("EquilibriumDegreeOfConversion")
         .label("Equilibrium degree of conversion");
    model.component("comp1").material("mat1").propertyGroup("EquilibriumDegreeOfConversion")
         .set("docEq", "def.Eeq_inv(V)");
    model.component("comp1").material("mat1").propertyGroup("EquilibriumDegreeOfConversion")
         .addInput("electricpotential");
    model.component("comp1").material("mat1").selection().set(3);
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ElectrolyteConductivity", "ElectrolyteConductivity", "Electrolyte conductivity");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("SpeciesProperties", "SpeciesProperties", "Species properties");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ElectrolyteSaltConcentration", "ElectrolyteSaltConcentration", "Electrolyte salt concentration");
    model.component("comp1").material("mat2").label("LiPF6 in 1:2 EC:DMC and p(VdF-HFP) (Polymer, Li-ion Battery)");
    model.component("comp1").material("mat2").comments("\n");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("diffusion", new String[]{"7.5e-11[m^2/s]", "0", "0", "0", "7.5e-11[m^2/s]", "0", "0", "0", "7.5e-11[m^2/s]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("diffusion", "M. Doyle, J. Newman, A.S. Gozdz, C.N. Schmutz, and J.M. Tarascon, \u201cComparison of Modeling Predictions with Experimental Data from Plastic Lithium Ion Cells,\u201d J. Electrochem. Soc., vol. 143, p. 1890, 1996.\n");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity")
         .label("Electrolyte conductivity");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("funcname", "sigmal_int1");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("table", new String[][]{{"0", "0.0108"}, 
         {"0.2000", "0.1259"}, 
         {"0.4000", "0.2055"}, 
         {"0.6000", "0.2553"}, 
         {"0.8000", "0.2810"}, 
         {"1.0000", "0.2873"}, 
         {"1.2000", "0.2788"}, 
         {"1.4000", "0.2595"}, 
         {"1.6000", "0.2331"}, 
         {"1.8000", "0.2027"}, 
         {"2.0000", "0.1710"}, 
         {"2.200", "0.1403"}, 
         {"2.4000", "0.1123"}, 
         {"2.6000", "0.0885"}, 
         {"2.8000", "0.0696"}, 
         {"3.0000", "0.0563"}});
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("fununit", new String[]{""});
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("argunit", new String[]{""});
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity")
         .set("sigmal", new String[]{"sigmal_int1(c/c_ref)", "0", "0", "0", "sigmal_int1(c/c_ref)", "0", "0", "0", "sigmal_int1(c/c_ref)"});
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity")
         .setPropertyInfo("sigmal", "M. Doyle, J. Newman, A.S. Gozdz, C.N. Schmutz, and J.M. Tarascon, \u201cComparison of Modeling Predictions with Experimental Data from Plastic Lithium Ion Cells,\u201d J. Electrochem. Soc., vol. 143, p. 1890, 1996.\n");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").set("c_ref", "1000[mol/m^3]");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").descr("c_ref", "");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").addInput("concentration");
    model.component("comp1").material("mat2").propertyGroup("SpeciesProperties").label("Species properties");
    model.component("comp1").material("mat2").propertyGroup("SpeciesProperties").set("transpNum", "0.363");
    model.component("comp1").material("mat2").propertyGroup("SpeciesProperties")
         .setPropertyInfo("transpNum", "M. Doyle, J. Newman, A.S. Gozdz, C.N. Schmutz, and J.M. Tarascon, \u201cComparison of Modeling Predictions with Experimental Data from Plastic Lithium Ion Cells,\u201d J. Electrochem. Soc., vol. 143, p. 1890, 1996.\n");
    model.component("comp1").material("mat2").propertyGroup("SpeciesProperties").set("fcl", "0");
    model.component("comp1").material("mat2").propertyGroup("SpeciesProperties")
         .setPropertyInfo("fcl", "M. Doyle, J. Newman, A.S. Gozdz, C.N. Schmutz, and J.M. Tarascon, \u201cComparison of Modeling Predictions with Experimental Data from Plastic Lithium Ion Cells,\u201d J. Electrochem. Soc., vol. 143, p. 1890, 1996.\n");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteSaltConcentration")
         .label("Electrolyte salt concentration");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteSaltConcentration").identifier("cElsalt");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteSaltConcentration")
         .set("cElsalt", "1000[mol/m^3]");
    model.component("comp1").material("mat2").propertyGroup("ElectrolyteSaltConcentration")
         .setPropertyInfo("cElsalt", "M. Doyle, J. Newman, A.S. Gozdz, C.N. Schmutz, and J.M. Tarascon, \u201cComparison of Modeling Predictions with Experimental Data from Plastic Lithium Ion Cells,\u201d J. Electrochem. Soc., vol. 143, p. 1890, 1996.\n");
    model.component("comp1").material("mat2").selection().set(1, 2, 3);

    model.component("comp1").physics("liion").feature("sep1").set("epsl", 1);
    model.component("comp1").physics("liion").create("pce1", "PorousElectrode", 1);
    model.component("comp1").physics("liion").feature("pce1").selection().set(1);
    model.component("comp1").physics("liion").feature("pce1")
         .set("sigma", new String[]{"Ks_neg", "0", "0", "0", "Ks_neg", "0", "0", "0", "Ks_neg"});
    model.component("comp1").physics("liion").feature("pce1").set("epsl", "epsl_neg");
    model.component("comp1").physics("liion").feature("pce1").set("epss", "epss_neg");
    model.component("comp1").physics("liion").feature("pce1").set("IonicCorrModel", "userdef");
    model.component("comp1").physics("liion").feature("pce1").set("fl", "epsl_neg^brugg");
    model.component("comp1").physics("liion").feature("pce1").set("ElectricCorrModel", "NoCorr");
    model.component("comp1").physics("liion").feature("pce1").set("DiffusionCorrModel", "userdef");
    model.component("comp1").physics("liion").feature("pce1").set("fDl", "epsl_neg^brugg");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("csinit", "cs0_neg");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("cEeqref_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("cEeqref", "csmax_neg");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("Ds_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("Ds", "Ds_neg");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("rp", "rp_neg");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("socmin_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("socmax_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("Eeq_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce1").feature("per1")
         .set("Eeq", "Eeq_neg(liion.cs_surface/csmax_neg)");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("i0refType", "FromRateConstant");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("k", "k_neg");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("cl_ref", "cl_ref");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("dEeqdT_mat", "userdef");
    model.component("comp1").physics("liion").create("pce2", "PorousElectrode", 1);
    model.component("comp1").physics("liion").feature("pce2").selection().set(3);
    model.component("comp1").physics("liion").feature("pce2")
         .set("sigma", new String[]{"Ks_pos", "0", "0", "0", "Ks_pos", "0", "0", "0", "Ks_pos"});
    model.component("comp1").physics("liion").feature("pce2").set("epsl", "epsl_pos");
    model.component("comp1").physics("liion").feature("pce2").set("epss", "epss_pos");
    model.component("comp1").physics("liion").feature("pce2").set("IonicCorrModel", "userdef");
    model.component("comp1").physics("liion").feature("pce2").set("fl", "epsl_pos^brugg");
    model.component("comp1").physics("liion").feature("pce2").set("ElectricCorrModel", "NoCorr");
    model.component("comp1").physics("liion").feature("pce2").set("DiffusionCorrModel", "userdef");
    model.component("comp1").physics("liion").feature("pce2").set("fDl", "epsl_pos^brugg");
    model.component("comp1").physics("liion").feature("pce2").feature("pin1").set("ParticleMaterial", "mat1");
    model.component("comp1").physics("liion").feature("pce2").feature("pin1").set("csinit", "cs0_pos");
    model.component("comp1").physics("liion").feature("pce2").feature("pin1").set("Ds_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce2").feature("pin1").set("Ds", "Ds_pos");
    model.component("comp1").physics("liion").feature("pce2").feature("pin1").set("rp", "rp_pos");
    model.component("comp1").physics("liion").feature("pce2").feature("pin1").set("Nord", 2);
    model.component("comp1").physics("liion").feature("pce2").feature("per1").set("MaterialOption", "mat1");
    model.component("comp1").physics("liion").feature("pce2").feature("per1").set("i0refType", "FromRateConstant");
    model.component("comp1").physics("liion").feature("pce2").feature("per1").set("k", "k_pos");
    model.component("comp1").physics("liion").feature("pce2").feature("per1").set("cl_ref", "cl_ref");
    model.component("comp1").physics("liion").create("egnd1", "ElectricGround", 0);
    model.component("comp1").physics("liion").feature("egnd1").selection().set(1);
    model.component("comp1").physics("liion").create("ecd1", "ElectrodeNormalCurrentDensity", 0);
    model.component("comp1").physics("liion").feature("ecd1").selection().set(4);
    model.component("comp1").physics("liion").feature("ecd1").set("nis", "-i_app");
    model.component("comp1").physics("liion").feature("init1").set("cl", "cl_0");

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.study("std1").feature("time").set("tlist", "range(0,10,8000)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("ptgr1", "PointGraph");
    model.result("pg1").feature("ptgr1").selection().all();
    model.result("pg1").feature("ptgr1").set("expr", new String[]{"phis"});
    model.result("pg1").feature("ptgr1").selection().set(4);
    model.result("pg1").label("\u5bf9\u5730\u8fb9\u754c\u7535\u6781\u7535\u4f4d (liion)");
    model.result("pg1").feature("ptgr1").set("xdatasolnumtype", "level1");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{"", ""});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"liion.soc_average_pce1", "liion.soc_average_pce2"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u5e73\u5747 SOC\uff0c\u591a\u5b54\u7535\u6781 1", "\u5e73\u5747 SOC\uff0c\u591a\u5b54\u7535\u6781 2"});
    model.result("pg2").label("\u7535\u6781\u5e73\u5747\u8377\u7535\u72b6\u6001 (liion)");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").label("\u7535\u89e3\u8d28\u7535\u4f4d (liion)");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg3").feature("lngr1").selection().set(1, 2, 3);
    model.result("pg3").feature("lngr1").set("expr", new String[]{"phil"});
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (liion)");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x");
    model.result("pg4").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg4").feature("lngr1").selection().set(1, 2, 3);
    model.result("pg4").feature("lngr1").set("expr", new String[]{"phis"});
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "x");
    model.result("pg5").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr1").selection().set(1, 2, 3);
    model.result("pg5").feature("lngr1").set("expr", new String[]{"cl"});
    model.result("pg5").label("\u7535\u89e3\u8d28\u76d0\u6d53\u5ea6 (liion)");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u7535\u6c60\u7535\u538b (V)");
    model.result("pg1").set("twoyaxes", true);
    model.result("pg1").set("legendpos", "lowerright");
    model.result("pg1").run();
    model.result("pg1").feature("ptgr1").set("legend", true);
    model.result("pg1").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg1").feature("ptgr1").setIndex("legends", "\u7535\u538b", 0);
    model.result("pg1").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg1").run();
    model.result("pg1").feature("ptgr2").set("expr", "i_app");
    model.result("pg1").feature("ptgr2")
         .set("descr", "\u65bd\u52a0\u7684\u7535\u6c60\u5355\u5143\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg1").feature("ptgr2").set("plotonsecyaxis", true);
    model.result("pg1").feature("ptgr2").setIndex("legends", "\u7535\u6d41", 0);
    model.result("pg1").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").set("data", "none");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").set("data", "dset1");
    model.result("pg6").feature("lngr1").setIndex("looplevelinput", "manual", 0);
    model.result("pg6").feature("lngr1").setIndex("looplevel", new int[]{2}, 0);
    model.result("pg6").feature("lngr1").selection().all();
    model.result("pg6").feature("lngr1").set("expr", "phil+0.148");
    model.result("pg6").feature("lngr1").set("legend", true);
    model.result("pg6").feature("lngr1").set("legendmethod", "manual");
    model.result("pg6").feature("lngr1").setIndex("legends", "phil+0.148", 0);
    model.result("pg6").feature().duplicate("lngr2", "lngr1");
    model.result("pg6").run();
    model.result("pg6").feature("lngr2").set("expr", "liion.eta_per1");
    model.result("pg6").feature("lngr2").setIndex("legends", "eta", 0);
    model.result("pg6").feature().duplicate("lngr3", "lngr2");
    model.result("pg6").run();
    model.result("pg6").feature("lngr3").setIndex("looplevel", new int[]{200}, 0);
    model.result("pg6").feature("lngr3").set("expr", "phil+0.558");
    model.result("pg6").feature("lngr3").setIndex("legends", "phil+0.558", 0);
    model.result("pg6").run();
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "x");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u7535\u538b (V)");
    model.result("pg6").run();
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevelinput", "manual", 0);
    model.result("pg5").setIndex("looplevel", new int[]{121, 181, 221, 241, 301}, 0);
    model.result("pg5").run();
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").run();
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "x");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u7535\u89e3\u8d28\u6d53\u5ea6\uff0ccl");
    model.result("pg5").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").setIndex("looplevelinput", "manual", 0);
    model.result("pg7").setIndex("looplevel", new int[]{2, 121, 181}, 0);
    model.result("pg7").set("titletype", "manual");
    model.result("pg7")
         .set("title", "\u7535\u6781\u9897\u7c92\u9502\u6d53\u5ea6\uff0c\u8868\u9762\uff08\u5b9e\u7ebf\uff09\u548c\u4e2d\u5fc3\uff08\u865a\u7ebf\uff09");
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("xlabel", "x");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "cs (mol//m<sup>3</sup>)");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1").selection().all();
    model.result("pg7").feature("lngr1").set("expr", "liion.cs_surface");
    model.result("pg7").feature("lngr1").set("descr", "\u5d4c\u5165\u9897\u7c92\u6d53\u5ea6\uff0c\u8868\u9762");
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").run();
    model.result("pg7").create("lngr2", "LineGraph");
    model.result("pg7").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr2").set("linewidth", "preference");
    model.result("pg7").feature("lngr2").selection().all();
    model.result("pg7").feature("lngr2").set("expr", "liion.cs_center");
    model.result("pg7").feature("lngr2").set("descr", "\u5d4c\u5165\u9897\u7c92\u6d53\u5ea6\uff0c\u4e2d\u5fc3");
    model.result("pg7").feature("lngr2").set("linestyle", "dashed");
    model.result("pg7").feature("lngr2").set("linecolor", "cyclereset");
    model.result("pg7").run();

    model.param().set("C", "1");
    model.param().descr("C", "\u53c2\u6570\u5316\u7814\u7a76\u7684\u500d\u7387\u56e0\u5b50");

    model.component("comp1").variable("var1").set("i_app_p", "C*i_1C");
    model.component("comp1").variable("var1")
         .descr("i_app_p", "\u53c2\u6570\u5316\u7814\u7a76\u7684\u653e\u7535\u7535\u6d41");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(4);
    model.component("comp1").cpl("intop1").set("opname", "PositiveCC");

    model.component("comp1").physics("liion").feature().duplicate("ecd2", "ecd1");
    model.component("comp1").physics("liion").feature("ecd2")
         .label("\u7535\u6781\u7535\u6d41\u5bc6\u5ea6 2 - \u7814\u7a76 2");
    model.component("comp1").physics("liion").feature("ecd2").set("nis", "-i_app_p");
    model.component("comp1").physics("liion").feature("ecd1")
         .label("\u7535\u6781\u7535\u6d41\u5bc6\u5ea6 1 - \u7814\u7a76 1");

    model.study().create("std2");
    model.study("std2").create("cdi", "CurrentDistributionInitialization");
    model.study("std2").feature("cdi").set("ftplistmethod", "manual");
    model.study("std2").feature("cdi").set("solnum", "auto");
    model.study("std2").feature("cdi").set("notsolnum", "auto");
    model.study("std2").feature("cdi").set("outputmap", new String[]{});
    model.study("std2").feature("cdi").set("ngenAUX", "1");
    model.study("std2").feature("cdi").set("goalngenAUX", "1");
    model.study("std2").feature("cdi").set("ngenAUX", "1");
    model.study("std2").feature("cdi").set("goalngenAUX", "1");
    model.study("std2").feature("cdi").setSolveFor("/physics/liion", true);
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").set("plotgroup", "Default");
    model.study("std2").feature("time").set("ftplistmethod", "manual");
    model.study("std2").feature("time").set("initialtime", "0");
    model.study("std2").feature("time").set("solnum", "auto");
    model.study("std2").feature("time").set("notsolnum", "auto");
    model.study("std2").feature("time").set("outputmap", new String[]{});
    model.study("std2").feature("time").setSolveFor("/physics/liion", true);
    model.study("std2").feature("time").set("tlist", "0 40000");
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").set("disabledphysics", new String[]{"liion/ecd1"});
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "i_1C", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "A/m^2", 0);
    model.study("std2").feature("param").setIndex("pname", "i_1C", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "A/m^2", 0);
    model.study("std2").feature("param").setIndex("pname", "C", 0);
    model.study("std2").feature("param").setIndex("plistarr", "0.1 1 2 4", 0);
    model.study("std2").showAutoSequences("all");

    model.sol("sol3").feature("t1").create("st1", "StopCondition");
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopcondarr", "comp1.PositiveCC(comp1.phis)<2.0", 0);
    model.sol("sol3").feature("t1").feature("st1").set("storestopcondsol", "stepbefore_stepafter");
    model.sol("sol3").feature("t1").feature("st1").set("stopcondwarn", false);
    model.sol("sol3").feature("t1").set("tout", "tsteps");
    model.sol("sol3").feature("t1").set("tstepsstore", 3);

    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol5");
    model.sol("sol5").study("std2");
    model.sol("sol5").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol5");
    model.batch("p1").run("compute");

    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").set("data", "dset5");
    model.result("pg8").create("ptgr1", "PointGraph");
    model.result("pg8").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg8").feature("ptgr1").set("linewidth", "preference");
    model.result("pg8").feature("ptgr1").selection().set(4);
    model.result("pg8").feature("ptgr1").set("expr", "phis");
    model.result("pg8").feature("ptgr1").set("descr", "\u7535\u52bf");
    model.result("pg8").feature("ptgr1").set("xdata", "expr");
    model.result("pg8").feature("ptgr1").set("xdataexpr", "(t[s]/1[h])*i_app_p");
    model.result("pg8").feature("ptgr1").set("legend", true);
    model.result("pg8").feature("ptgr1").set("legendmethod", "evaluated");
    model.result("pg8").feature("ptgr1").set("legendpattern", "eval(C) C");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").set("titletype", "manual");
    model.result("pg8").set("title", "\u653e\u7535\u66f2\u7ebf");
    model.result("pg8").set("xlabelactive", true);
    model.result("pg8").set("xlabel", "\u5bb9\u91cf (Ah/m<sup>2</sup>)");
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("ylabel", "\u7535\u538b (V)");
    model.result("pg8").set("axislimits", true);
    model.result("pg8").set("xmin", 0);
    model.result("pg8").set("xmax", 19);
    model.result("pg8").set("ymin", "2.0");
    model.result("pg8").set("ymax", 4.4);
    model.result("pg8").run();
    model.result("pg1").run();
    model.result("pg1").label("\u8d1f\u8f7d\u5faa\u73af\u7535\u538b\u548c\u7535\u6d41");
    model.result("pg6").run();
    model.result("pg6").label("\u7535\u538b\u635f\u5931\u6bd4\u8f83");
    model.result("pg7").run();
    model.result("pg7").label("\u56fa\u4f53\u9502\u6d53\u5ea6");
    model.result("pg8").run();
    model.result("pg8").label("\u653e\u7535\u66f2\u7ebf\u6bd4\u8f83");

    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time").set("disabledphysics", new String[]{"liion/ecd2"});

    model.func("int1").createPlot("pg9");

    model.result("pg9").run();
    model.result("pg9").set("titletype", "none");
    model.result("pg9").set("xlabelactive", true);
    model.result("pg9").set("xlabel", "c<sub>s</sub>/c<sub>s, max</sub> (1)");
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("ylabel", "E<sub>eq, neg</sub> (V)");
    model.result("pg9").run();

    model.component("comp1").material("mat1").propertyGroup("def").func("int1").createPlot("pg10");

    model.result("pg10").run();
    model.result("pg10").set("titletype", "none");
    model.result("pg10").set("xlabelactive", true);
    model.result("pg10").set("xlabel", "c<sub>s</sub>/c<sub>s, max</sub> (1)");
    model.result("pg10").set("ylabelactive", true);
    model.result("pg10").set("ylabel", "E<sub>eq, pos</sub> (V)");
    model.result("pg10").run();

    model.component("comp1").material("mat2").propertyGroup("ElectrolyteConductivity").func("int1")
         .createPlot("pg11");

    model.result("pg11").run();
    model.result("pg11").set("titletype", "none");
    model.result("pg11").set("xlabelactive", true);
    model.result("pg11").set("xlabel", "c<sub>l</sub> (mol/m<sup>3</sup>)");
    model.result("pg11").set("ylabelactive", true);
    model.result("pg11").set("ylabel", "\\sigma<sub>l</sub> (S/m)");
    model.result("pg11").run();
    model.result("pg9").run();
    model.result().remove("pg9");
    model.result().remove("pg10");
    model.result().remove("pg11");
    model.result().dataset().remove("int1_ds1");
    model.result().dataset().remove("int1_ds2");
    model.result().dataset().remove("int1_ds3");

    model.title("\u9502\u79bb\u5b50\u7535\u6c60\u7b49\u6e29\u6a21\u578b - \u4e00\u7ef4");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u9502\u79bb\u5b50\u7535\u6c60\u201d\u63a5\u53e3\u6765\u7814\u7a76\u7535\u6c60\u7684\u653e\u7535\u548c\u5145\u7535\uff0c\u5176\u4e2d\u91c7\u7528\u4e00\u7ef4\u51e0\u4f55\u7ed3\u6784\u548c\u7b49\u6e29\u6a21\u578b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();

    model.label("li_battery_1d.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
