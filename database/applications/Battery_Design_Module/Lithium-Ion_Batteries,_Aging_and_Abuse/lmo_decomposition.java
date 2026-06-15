/*
 * lmo_decomposition.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:24 by COMSOL 6.3.0.290. */
public class lmo_decomposition {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Lithium-Ion_Batteries,_Aging_and_Abuse");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("liion", "LithiumIonBatteryMPH", "geom1");
    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");

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
    model.study("std1").feature("cdi").setSolveFor("/physics/tds", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/liion", true);
    model.study("std1").feature("time").setSolveFor("/physics/tds", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param()
         .set("Aa_oxid", "R_const*T/(0.5*F_const)*log(10)", "\u9633\u6781\u6eb6\u5242\u6c27\u5316 Tafel \u659c\u7387");
    model.param()
         .set("Ac_Hred", "-R_const*T/(0.5*F_const)*log(10)", "\u9634\u6781\u8d28\u5b50\u8fd8\u539f Tafel \u659c\u7387");
    model.param()
         .set("Ac_Mndep", "-R_const*T/(0.5*F_const)*log(10)", "\u9634\u6781\u9530\u6c89\u79ef Tafel \u659c\u7387");
    model.param()
         .set("av_oxid", "1e6[1/m]", "\u53d1\u751f\u6eb6\u5242\u6c27\u5316\u7684\u5bfc\u7535\u70ad\u9ed1\u7684\u6bd4\u8868\u9762\u79ef");
    model.param().set("cH_ref", "100[mol/m^3]", "\u53c2\u8003\u8d28\u5b50\u6d53\u5ea6");
    model.param().set("cMn_ref", "10[mol/m^3]", "\u53c2\u8003\u9530\u6d53\u5ea6");
    model.param().set("cl_ref", "1000[mol/m^3]", "\u53c2\u8003\u7535\u89e3\u8d28\u76d0\u6d53\u5ea6");
    model.param().set("Crate", "1/3", "\u500d\u7387");
    model.param().set("D_H", "5e-9[m^2/s]", "\u8d28\u5b50\u6269\u6563\u7cfb\u6570");
    model.param().set("D_H2O", "3e-9[m^2/s]", "\u6c34\u6269\u6563\u7cfb\u6570");
    model.param().set("D_Mn", "7.2e-10[m^2/s]", "\u9530\u79bb\u5b50\u6269\u6563\u7cfb\u6570");
    model.param()
         .set("Eeq_H", "1.5[V]", "\u8d28\u5b50\u8fd8\u539f\u7684\u53c2\u8003\u5e73\u8861\u7535\u4f4d vs Li(s)/Li+");
    model.param().set("Eeq_Mn", "2.5[V]", "\u9530\u7684\u53c2\u8003\u5e73\u8861\u7535\u4f4d vs Li(s)/Li+");
    model.param().set("Eeq_oxid", "4.2[V]", "\u6eb6\u5242\u6c27\u5316\u7684\u5e73\u8861\u7535\u4f4d vs Li(s)/Li+");
    model.param().set("epsl_pos", "0.4", "\u6b63\u6781\u4e2d\u7684\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570");
    model.param().set("epsl_sep", "0.4", "\u9694\u819c\u4e2d\u7684\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570");
    model.param()
         .set("epss_pos0", "0.4", "\u6b63\u6781\u7684\u521d\u59cb\u7535\u6781\uff08LMO\uff09\u4f53\u79ef\u5206\u6570");
    model.param().set("i0_oxid", "10[uA/m^2]", "\u6eb6\u5242\u6c27\u5316\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param()
         .set("i0_ref_H", "1e-18[A*m/mol]*cH_ref", "cH_ref \u7684\u8d28\u5b50\u8fd8\u539f\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param()
         .set("i0_ref_Mn", "1.0e-11[A*m/mol]*cMn_ref", "cMn_ref \u7684\u9530\u8fd8\u539f\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param()
         .set("i0_ref_lmo", "10[A/m^2]", "LMO \u7535\u6781\u6750\u6599 50% \u9502\u5316\u5ea6\u7684\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("kproton", "2e-11[m/s]", "\u8d28\u5b50\u6eb6\u89e3 LMO \u7684\u901f\u7387\u5e38\u6570");
    model.param()
         .set("kwater", "7e-10[m^6/mol^2/s]", "\u6c34\u4e2d\u7535\u89e3\u8d28\u76d0\u6d53\u5ea6\u7684\u901f\u7387\u5e38\u6570");
    model.param().set("L_pos", "50[um]", "\u6b63\u6781\u539a\u5ea6");
    model.param().set("L_sep", "50[um]", "\u9694\u819c\u539a\u5ea6");
    model.param().set("ns", "0.10", "LMO \u4e2d\u6269\u6563\u7cfb\u6570\u7684\u8c03\u6574\u7cfb\u6570");
    model.param().set("rp_lmo", "5[um]", "LMO \u7535\u6781\u9897\u7c92\u7684\u534a\u5f84");
    model.param().set("sigmas_pos", "10[S/m]", "\u7b49\u6548\u6b63\u6781\u7535\u5bfc\u7387");
    model.param().set("T", "55[degC]", "\u6e29\u5ea6");
    model.param().set("Vhigh_over", "4.5[V]", "\u8fc7\u5145\u7684\u9ad8\u622a\u6b62\u7535\u538b");
    model.param().set("Vhigh", "4.5[V]", "\u9ad8\u622a\u6b62\u7535\u538b");
    model.param().set("Vlow", "3.6[V]", "\u4f4e\u622a\u6b62\u7535\u538b");
    model.param().set("Vm", "4.1e-5[m^3/mol]", "LMO \u6469\u5c14\u4f53\u79ef");
    model.param().set("zH", "1", "\u8d28\u5b50\u7535\u8377\u6570");
    model.param().set("zMn", "2", "\u9530\u79bb\u5b50\u7535\u8377\u6570");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "L_sep", 1);
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "L_sep+L_pos", 2);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").set(1);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").set(2);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(0);
    model.component("comp1").selection("sel3").set(1);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElectrolyteConductivity", "ElectrolyteConductivity", "Electrolyte conductivity");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func()
         .create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup()
         .create("SpeciesProperties", "SpeciesProperties", "Species properties");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").label("LiPF6 in 1:1 EC:DMC (Liquid, Li-ion Battery)");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "D");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("expr", "1.47e3*exp(1.33*c)*exp(-1.69e3/T)*exp(-5.63e2*c/T)*1e-6");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"c", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("argunit", new String[]{"", ""});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"on", "off"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"0", "293.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"c", "0", "3"}, {"T", "293.15", "293.15"}});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("diffusion", new String[]{"D(c/1[M],T/1[K])[cm^2/s]", "0", "0", "0", "D(c/1[M],T/1[K])[cm^2/s]", "0", "0", "0", "D(c/1[M],T/1[K])[cm^2/s]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("diffusion", "Johannes Landesfeind and Hubert A. Gasteiger, Temperature and Concentration Dependence of the Ionic Transport Properties of Lithium-Ion Battery Electrolytes, Journal of The Electrochemical Society, 166 (14) A3079-A3097 (2019)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("concentration");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .label("Electrolyte conductivity");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("an1")
         .label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("an1")
         .set("funcname", "sigmal");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("an1")
         .set("expr", "7.98e-1*(1+(T-2.28e2))*c*(1-1.22*sqrt(max(c,eps))+5.09e-1*(1-4e-3*exp(1000/T))*c)/(1+c^4*(3.79e-3*exp(1000/T)))");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("an1")
         .set("args", new String[]{"c", "T"});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("an1")
         .set("argunit", new String[]{"", ""});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("an1")
         .set("plotaxis", new String[]{"on", "off"});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("an1")
         .set("plotfixedvalue", new String[]{"0", "298.15"});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").func("an1")
         .set("plotargs", new String[][]{{"c", "0", "3"}, {"T", "298.15", "298.15"}});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .set("sigmal", new String[]{"max(sigmal(c/1[M],T/1[K])[mS/cm],eps)", "0", "0", "0", "max(sigmal(c/1[M],T/1[K])[mS/cm],eps)", "0", "0", "0", "max(sigmal(c/1[M],T/1[K])[mS/cm],eps)"});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .setPropertyInfo("sigmal", "Johannes Landesfeind and Hubert A. Gasteiger, Temperature and Concentration Dependence of the Ionic Transport Properties of Lithium-Ion Battery Electrolytes, Journal of The Electrochemical Society, 166 (14) A3079-A3097 (2019)");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").addInput("concentration");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").label("Species properties");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("an1").set("funcname", "TDF");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("an1")
         .set("expr", "-5.58+7.17*c+3.8e-2*T+1.91*c^2-6.65e-2*c*T-5.08e-5*T^2+1.1e-1*c^3-6.1e-3*c^2*T+1.51e-4*c*T^2");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("an1")
         .set("args", new String[]{"c", "T"});
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("an1")
         .set("argunit", new String[]{"", ""});
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("an1")
         .set("plotaxis", new String[]{"on", "off"});
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("an1")
         .set("plotfixedvalue", new String[]{"0", "293.15"});
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("an1")
         .set("plotargs", new String[][]{{"c", "0", "3"}, {"T", "293.15", "293.15"}});
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("an2").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("an2")
         .set("funcname", "t_plus");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("an2")
         .set("expr", "-7.91+2.45e-1*c+5.28e-2*T+6.98e-1*c^2-1.08e-2*c*T-8.21e-5*T^2+7.43e-4*c^3-2.22e-3*c^2*T+3.07e-5*c*T^2");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("an2")
         .set("args", new String[]{"c", "T"});
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("an2")
         .set("argunit", new String[]{"", ""});
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("an2")
         .set("plotaxis", new String[]{"on", "off"});
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("an2")
         .set("plotfixedvalue", new String[]{"0", "293.15"});
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").func("an2")
         .set("plotargs", new String[][]{{"c", "0", "3"}, {"T", "293.15", "293.15"}});
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties")
         .set("transpNum", "t_plus(c/1[M],T/1[K])");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties")
         .setPropertyInfo("transpNum", "Johannes Landesfeind and Hubert A. Gasteiger, Temperature and Concentration Dependence of the Ionic Transport Properties of Lithium-Ion Battery Electrolytes, Journal of The Electrochemical Society, 166 (14) A3079-A3097 (2019)");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").set("fcl", "TDF(c/1[M],T/1[K])-1");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties")
         .setPropertyInfo("fcl", "Johannes Landesfeind and Hubert A. Gasteiger, Temperature and Concentration Dependence of the Ionic Transport Properties of Lithium-Ion Battery Electrolytes, Journal of The Electrochemical Society, 166 (14) A3079-A3097 (2019)");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").addInput("concentration");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").addInput("temperature");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("int2", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ElectrodePotential", "ElectrodePotential", "Equilibrium potential");
    model.component("comp1").material("mat2").propertyGroup()
         .create("OperationalSOC", "OperationalSOC", "Operational electrode state of charge");
    model.component("comp1").material("mat2").propertyGroup()
         .create("EquilibriumConcentration", "EquilibriumConcentration", "Equilibrium concentration");
    model.component("comp1").material("mat2").propertyGroup()
         .create("EquilibriumPotentialWithDOCInput", "EquilibriumPotentialWithDOCInput", "Equilibrium potential (using degree of conversion as model input)");
    model.component("comp1").material("mat2").propertyGroup()
         .create("EquilibriumDegreeOfConversion", "EquilibriumDegreeOfConversion", "Equilibrium degree of conversion");
    model.component("comp1").material("mat2").label("LMO, LiMn2O4 Spinel (Positive, Li-ion Battery)");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("funcname", "Eeq");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1")
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
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("fununit", new String[]{"V"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("argunit", new String[]{""});
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("defineinv", true);
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("funcinvname", "Eeq_inv");
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").set("funcname", "dEeqdT");
    model.component("comp1").material("mat2").propertyGroup("def").func("int2")
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
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").set("interp", "piecewisecubic");
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").set("fununit", new String[]{"V/K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").set("argunit", new String[]{""});
    model.component("comp1").material("mat2").propertyGroup("def").set("youngsmodulus", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("poissonsratio", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("youngsmodulus", "194[GPa]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("youngsmodulus", "Yue Qi et al 2014 J. Electrochem. Soc. 161 F3010");
    model.component("comp1").material("mat2").propertyGroup("def").set("poissonsratio", "0.26");
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("poissonsratio", "Yue Qi et al 2014 J. Electrochem. Soc. 161 F3010");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"1.3e-3[S/cm]", "0", "0", "0", "1.3e-3[S/cm]", "0", "0", "0", "1.3e-3[S/cm]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("electricconductivity", "Electrochemical and Electronic Charge Transport Properties of Ni-Doped LiMn2O4 Spinel Obtained from Polyol-Mediated Synthesis, Shuo Yang et al, Materials 2018, 11(5), 806");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("diffusion", new String[]{"1e-14*exp(20000/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]", "0", "0", "0", "1e-14*exp(20000/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]", "0", "0", "0", "1e-14*exp(20000/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("diffusion", "V. Srinivasan and C.Y. Wang, \"Analysis of Electrochemical and Thermal Behavior of Li-Ion Cells,\" J. Electrochem. Soc., vol. 150, p. A98, 2003");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "4140[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("density", "N. Nitta, F. Wu, J. Tae Lee, and G. Yushin, Materials Today, Volume 18, Number 5, June 2015");
    model.component("comp1").material("mat2").propertyGroup("def").set("T_ref", "298[K]");
    model.component("comp1").material("mat2").propertyGroup("def").descr("T_ref", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("T2", "min(393.15,max(T,223.15))");
    model.component("comp1").material("mat2").propertyGroup("def").descr("T2", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("csmax", "22860[mol/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def").descr("csmax", "");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").identifier("eeq");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .set("Eeq", "def.Eeq(doc)+def.dEeqdT(doc)*(T-298[K])");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .setPropertyInfo("Eeq", "V. Srinivasan and C.Y. Wang, \"Analysis of Electrochemical and Thermal Behavior of Li-Ion Cells,\" J. Electrochem. Soc., vol. 150, p. A98, 2003");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").set("dEeqdT", "def.dEeqdT(doc)");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .setPropertyInfo("dEeqdT", "V. Srinivasan and C.Y. Wang, \"Analysis of Electrochemical and Thermal Behavior of Li-Ion Cells,\" J. Electrochem. Soc., vol. 150, p. A98, 2003");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").set("cEeqref", "def.csmax");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .setPropertyInfo("cEeqref", "V. Srinivasan and C.Y. Wang, \"Analysis of Electrochemical and Thermal Behavior of Li-Ion Cells,\" J. Electrochem. Soc., vol. 150, p. A98, 2003");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").set("doc", "c/cEeqref");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .descr("doc", "Degree of conversion (state of lithiation)");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").addInput("concentration");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC")
         .label("Operational electrode state of charge");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").identifier("opsoc");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").set("socmax", "def.Eeq_inv(E_min)");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").set("socmin", "def.Eeq_inv(E_max)");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").set("E_max", "4.2[V]");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").set("E_min", "3.9[V]");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumConcentration")
         .label("Equilibrium concentration");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumConcentration")
         .set("csEq", "def.csmax*def.Eeq_inv(V)");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumConcentration")
         .addInput("electricpotential");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumPotentialWithDOCInput")
         .label("Equilibrium potential (using degree of conversion as model input)");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("Eeq", "def.Eeq(doc)+def.dEeqdT(doc)*(T-298[K])");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("dEeqdT", "def.dEeqdT(doc)");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumPotentialWithDOCInput")
         .addInput("degreeofconversion");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumPotentialWithDOCInput")
         .addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumDegreeOfConversion")
         .label("Equilibrium degree of conversion");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumDegreeOfConversion")
         .set("docEq", "def.Eeq_inv(V)");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumDegreeOfConversion")
         .addInput("electricpotential");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("ElectrodePotential", "ElectrodePotential", "Equilibrium potential");
    model.component("comp1").material("mat3").label("Lithium Metal, Li (Negative, Li-ion Battery)");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def").set("youngsmodulus", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("poissonsratio", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("thermalconductivity", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("electricconductivity", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("youngsmodulus", "2[GPa]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("youngsmodulus", "Yue Qi et al 2010 J. Electrochem. Soc. 157 A558");
    model.component("comp1").material("mat3").propertyGroup("def").set("poissonsratio", "0.34");
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("poissonsratio", "Yue Qi et al 2010 J. Electrochem. Soc. 157 A558");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "0.534[g/cm^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("density", "https://en.wikipedia.org/wiki/Lithium");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"84.8[W/(m*K)]", "0", "0", "0", "84.8[W/(m*K)]", "0", "0", "0", "84.8[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("thermalconductivity", "https://en.wikipedia.org/wiki/Lithium");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/(92.8[n\u03a9*m])", "0", "0", "0", "1/(92.8[n\u03a9*m])", "0", "0", "0", "1/(92.8[n\u03a9*m])"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("electricconductivity", "https://en.wikipedia.org/wiki/Lithium");
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "3.58[kJ/kg/K]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("heatcapacity", "https://en.wikipedia.org/wiki/Lithium");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").identifier("eeq");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").set("Eeq", "0[V]");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").set("dEeqdT", "0[V/K]");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").set("cEeqref", "0[M]");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").addInput("concentration");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").addInput("temperature");
    model.component("comp1").material("mat1").selection().named("sel1");
    model.component("comp1").material("mat2").selection().named("sel2");
    model.component("comp1").material("mat3").selection().geom("geom1", 0);
    model.component("comp1").material("mat3").selection().named("sel3");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("delta_phil", "-R_const*T/F_const*log(cl/1[M])", "\u6d89\u53ca\u5916\u6765\u7269\u8d28\u7684\u7535\u5316\u5b66\u53cd\u5e94\u548c\u4f20\u8f93\u7684\u7535\u89e3\u8d28\u76f8\u7535\u4f4d\u6821\u6b63");
    model.component("comp1").variable("var1")
         .set("phil_ps", "phil+delta_phil", "\u4f2a\u6052\u7535\u89e3\u8d28\u7535\u4f4d");
    model.component("comp1").variable("var1")
         .set("Rwater", "kwater*cH2O^2*cl_net", "\u7531\u6c34\u4ea7\u751f\u7684\u7535\u89e3\u8d28\u76d0\u635f\u5931\u901f\u7387");
    model.component("comp1").variable("var1")
         .set("cl_net", "cl-cMn*2-cH", "\u7535\u89e3\u6db2\u4e2d\u51c0\u9502\u79bb\u5b50\u6d53\u5ea6");
    model.component("comp1").variable("var1")
         .set("cl_battery", "(intop_lmo(cl_net*epsl_pos)+intop_sep(cl_net*epsl_sep))/(L_pos*epsl_pos+L_sep*epsl_sep)", "\u7535\u6c60\u4e2d\u51c0\u9502\u79bb\u5b50\u6d53\u5ea6");
    model.component("comp1").variable("var1")
         .set("epss_LMO_pos", "intop_lmo(liion.epss)/L_pos", "\u6b63\u6781\u7535\u6781\u4f53\u79ef\u5206\u6570");
    model.component("comp1").variable("var1")
         .set("D_LMO_pos", "intop_lmo(D_LMO)/L_pos", "\u6b63\u6781 LMO \u9897\u7c92\u4e2d\u9502\u6269\u6563\u7cfb\u6570");
    model.component("comp1").variable("var1")
         .set("cycle_no", "comp1.liion.cdc1.cycle_counter", "\u5faa\u73af\u6570");
    model.component("comp1").variable("var1")
         .set("t_cycle_first", "withsol('sol3',t)-withsol('sol3',comp1.liion.cdc1.t_dch_start)", "\u7b2c\u4e00\u4e2a\u653e\u7535\u5468\u671f\u6301\u7eed\u65f6\u95f4");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").variable("var1")
         .set("t_cycle_dch_first", "t-comp1.liion.cdc1.t_dch_start", "\u7b2c\u4e00\u6b21\u653e\u7535\u5468\u671f\u65f6\u95f4");
    model.component("comp1").variable("var1")
         .set("t_cycle_dch_last", "t-withsol('sol1',comp1.liion.cdc1.t_dch_start)", "\u6700\u540e\u4e00\u6b21\u653e\u7535\u5468\u671f\u65f6\u95f4");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").selection().geom("geom1", 1);
    model.component("comp1").variable("var2").selection().named("sel2");

//    To import content from file, use:
//    model.component("comp1").variable("var2").loadFile("FILENAME");
    model.component("comp1").variable("var2")
         .set("Rproton", "liion.Av_pce1_per1*kproton*cH", "LMO \u7684\u8d28\u5b50\u6eb6\u89e3\u901f\u7387");
    model.component("comp1").variable("var2")
         .set("D_LMO", "mat2.def.D_iso*(1-(max((epss_pos0-liion.epss)/epss_pos0,eps)^ns))", "LMO \u6309\u7167\u7535\u6781\u6750\u6599\u635f\u5931\u8c03\u6574\u7684\u6269\u6563\u7cfb\u6570");
    model.component("comp1").variable("var2").set("rho_LMO", "mat2.def.rho", "LMO \u5bc6\u5ea6");
    model.component("comp1").variable("var2").set("M_LMO", "rho_LMO*Vm", "LMO \u6469\u5c14\u8d28\u91cf");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "intop_lmo");
    model.component("comp1").cpl("intop1").selection().named("sel2");
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").set("opname", "intop_sep");
    model.component("comp1").cpl("intop2").selection().named("sel1");

    model.component("comp1").physics("liion").prop("CellSettings").set("CellSOCandInitialChargeInventory", true);
    model.component("comp1").physics("liion").feature("socicd1").set("CellType", "HalfCell");
    model.component("comp1").physics("liion").feature("socicd1").set("CellVoltageInputType", "userdef");
    model.component("comp1").physics("liion").feature("socicd1").set("Ecell_0SOC", "Vlow");
    model.component("comp1").physics("liion").feature("socicd1").set("Ecell_100SOC", "Vhigh");
    model.component("comp1").physics("liion").feature("socicd1").set("SOC_init", 0);
    model.component("comp1").physics("liion").feature("socicd1").feature("poses1").selection().named("sel2");
    model.component("comp1").physics("liion").feature("sep1").set("epsl", "epsl_sep");
    model.component("comp1").physics("liion").create("pce1", "PorousElectrode", 1);
    model.component("comp1").physics("liion").feature("pce1").selection().set(2);
    model.component("comp1").physics("liion").feature("pce1").set("ElectrolyteMaterial", "mat1");
    model.component("comp1").physics("liion").feature("pce1")
         .set("sigma", new String[]{"sigmas_pos", "0", "0", "0", "sigmas_pos", "0", "0", "0", "sigmas_pos"});
    model.component("comp1").physics("liion").feature("pce1").set("epss", "epss_pos0");
    model.component("comp1").physics("liion").feature("pce1").set("epsl", "epsl_pos");
    model.component("comp1").physics("liion").feature("pce1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("Species", "LMO_dead", 0, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("rhos", "rho_LMO", 0, 0);
    model.component("comp1").physics("liion").feature("pce1").setIndex("Ms", "M_LMO", 0, 0);
    model.component("comp1").physics("liion").feature("pce1")
         .set("SubtractVolumeChangeFromElectrolyteVolumeFraction", false);
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("Ds_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("Ds", "D_LMO");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("rp", "rp_lmo");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("i0_ref", "i0_ref_lmo");
    model.component("comp1").physics("liion").feature("pce1").create("per2", "PorousElectrodeReaction", 1);
    model.component("comp1").physics("liion").feature("pce1").feature("per2").set("Eeq_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce1").feature("per2").set("Eeq", "Eeq_oxid+delta_phil");
    model.component("comp1").physics("liion").feature("pce1").feature("per2")
         .set("ElectrodeKinetics", "AnodicTafelEquation");
    model.component("comp1").physics("liion").feature("pce1").feature("per2").set("i0", "i0_oxid*(cl/cl_ref)^0.5");
    model.component("comp1").physics("liion").feature("pce1").feature("per2").set("Aa", "Aa_oxid");
    model.component("comp1").physics("liion").feature("pce1").feature("per2")
         .set("ActiveSpecificSurfaceAreaType", "userdef");
    model.component("comp1").physics("liion").feature("pce1").feature("per2").set("Av", "av_oxid");
    model.component("comp1").physics("liion").feature("pce1").feature("per2").set("VLiTheta", 0);
    model.component("comp1").physics("liion").feature("pce1").create("nfr1", "NonFaradaicReactions", 1);
    model.component("comp1").physics("liion").feature("pce1").feature("nfr1").setIndex("R", "-Rproton", 0, 0);
    model.component("comp1").physics("liion").create("es1", "ElectrodeSurface", 0);
    model.component("comp1").physics("liion").feature("es1").selection().set(1);
    model.component("comp1").physics("liion").feature("es1").create("er2", "ElectrodeReaction", 0);
    model.component("comp1").physics("liion").feature("es1").feature("er2").set("Eeq_mat", "userdef");
    model.component("comp1").physics("liion").feature("es1").feature("er2").set("Eeq", "Eeq_Mn+delta_phil");
    model.component("comp1").physics("liion").feature("es1").feature("er2")
         .set("ElectrodeKinetics", "CathodicTafelEquation");
    model.component("comp1").physics("liion").feature("es1").feature("er2").set("i0", "i0_ref_Mn*cMn/cMn_ref");
    model.component("comp1").physics("liion").feature("es1").feature("er2").set("Ac", "Ac_Mndep");
    model.component("comp1").physics("liion").feature("es1").feature("er2").set("nm", 2);
    model.component("comp1").physics("liion").feature("es1").feature("er2").set("VLiPlus", -2);
    model.component("comp1").physics("liion").feature("es1").create("er3", "ElectrodeReaction", 0);
    model.component("comp1").physics("liion").feature("es1").feature("er3").set("Eeq_mat", "userdef");
    model.component("comp1").physics("liion").feature("es1").feature("er3").set("Eeq", "Eeq_H+delta_phil");
    model.component("comp1").physics("liion").feature("es1").feature("er3")
         .set("ElectrodeKinetics", "CathodicTafelEquation");
    model.component("comp1").physics("liion").feature("es1").feature("er3").set("i0", "i0_ref_H*cH/cH_ref");
    model.component("comp1").physics("liion").feature("es1").feature("er3").set("Ac", "Ac_Hred");
    model.component("comp1").physics("liion").create("rs1", "ReactionSource", 1);
    model.component("comp1").physics("liion").feature("rs1").selection().all();
    model.component("comp1").physics("liion").feature("rs1").set("Rlsrc", "Rwater");
    model.component("comp1").physics("liion").create("cdc1", "ChargeDischargeCycling", 0);
    model.component("comp1").physics("liion").feature("cdc1").selection().set(3);
    model.component("comp1").physics("liion").feature("cdc1").set("DischargingCurrentType", "CrateMultiple");
    model.component("comp1").physics("liion").feature("cdc1").set("Crate_dch", "-Crate");
    model.component("comp1").physics("liion").feature("cdc1").set("Vmin", "Vlow");
    model.component("comp1").physics("liion").feature("cdc1").set("ChargingCurrentType", "CrateMultiple");
    model.component("comp1").physics("liion").feature("cdc1").set("Crate_ch", "Crate");
    model.component("comp1").physics("liion").feature("cdc1").set("Vmax", "Vhigh");
    model.component("comp1").physics("liion").feature("cdc1").set("StartWith", "Charge_first");
    model.component("comp1").physics("tds").prop("TransportMechanism").set("Convection", false);
    model.component("comp1").physics("tds").prop("TransportMechanism").set("Migration", true);
    model.component("comp1").physics("tds").prop("TransportMechanism").set("MassTransferInPorousMedia", true);
    model.component("comp1").physics("tds").field("concentration").component(1, "cH");
    model.component("comp1").physics("tds").field("concentration").component(new String[]{"cH", "cH2"});
    model.component("comp1").physics("tds").field("concentration").component(2, "cMn");
    model.component("comp1").physics("tds").field("concentration").component(new String[]{"cH", "cMn", "cH3"});
    model.component("comp1").physics("tds").field("concentration").component(3, "cH2O");
    model.component("comp1").physics("tds").feature("sp1").setIndex("z", "zH", 0);
    model.component("comp1").physics("tds").feature("sp1").setIndex("z", "zMn", 1);
    model.component("comp1").physics("tds").create("porous1", "PorousMedium", 1);
    model.component("comp1").physics("tds").feature("porous1").selection().named("sel2");
    model.component("comp1").physics("tds").feature("porous1").feature("fluid1")
         .set("DF_cH", new String[]{"D_H", "0", "0", "0", "D_H", "0", "0", "0", "D_H"});
    model.component("comp1").physics("tds").feature("porous1").feature("fluid1")
         .set("DF_cMn", new String[]{"D_Mn", "0", "0", "0", "D_Mn", "0", "0", "0", "D_Mn"});
    model.component("comp1").physics("tds").feature("porous1").feature("fluid1")
         .set("DF_cH2O", new String[]{"D_H2O", "0", "0", "0", "D_H2O", "0", "0", "0", "D_H2O"});
    model.component("comp1").physics("tds").feature("porous1").feature("fluid1")
         .set("FluidDiffusivityModelType", "BruggemanModel");
    model.component("comp1").physics("tds").feature("porous1").feature("fluid1").set("V", "phil_ps");
    model.component("comp1").physics("tds").feature("porous1").feature("pm1").set("poro_mat", "userdef");
    model.component("comp1").physics("tds").feature("porous1").feature("pm1").set("poro", "epsl_pos");
    model.component("comp1").physics("tds").create("pec1", "PorousElectrodeCoupling", 1);
    model.component("comp1").physics("tds").feature("pec1").selection().named("sel2");
    model.component("comp1").physics("tds").feature("pec1").feature("rc1")
         .set("iv_src", "root.comp1.liion.pce1.per2.iv");
    model.component("comp1").physics("tds").feature("pec1").feature("rc1").setIndex("Vi", -1, 0);
    model.component("comp1").physics("tds").create("porous2", "PorousMedium", 1);
    model.component("comp1").physics("tds").feature("porous2").selection().named("sel1");
    model.component("comp1").physics("tds").feature("porous2").feature("fluid1")
         .set("DF_cH", new String[]{"D_H", "0", "0", "0", "D_H", "0", "0", "0", "D_H"});
    model.component("comp1").physics("tds").feature("porous2").feature("fluid1")
         .set("DF_cMn", new String[]{"D_Mn", "0", "0", "0", "D_Mn", "0", "0", "0", "D_Mn"});
    model.component("comp1").physics("tds").feature("porous2").feature("fluid1")
         .set("DF_cH2O", new String[]{"D_H2O", "0", "0", "0", "D_H2O", "0", "0", "0", "D_H2O"});
    model.component("comp1").physics("tds").feature("porous2").feature("fluid1")
         .set("FluidDiffusivityModelType", "BruggemanModel");
    model.component("comp1").physics("tds").feature("porous2").feature("fluid1").set("V", "phil_ps");
    model.component("comp1").physics("tds").feature("porous2").feature("pm1").set("poro_mat", "userdef");
    model.component("comp1").physics("tds").feature("porous2").feature("pm1").set("poro", "epsl_sep");
    model.component("comp1").physics("tds").create("eeic1", "ElectrodeElectrolyteInterfaceCoupling", 0);
    model.component("comp1").physics("tds").feature("eeic1").selection().named("sel3");
    model.component("comp1").physics("tds").feature("eeic1").feature("rc1")
         .set("iloc_src", "root.comp1.liion.es1.er2.iloc");
    model.component("comp1").physics("tds").feature("eeic1").feature("rc1").set("nm", 2);
    model.component("comp1").physics("tds").feature("eeic1").feature("rc1").setIndex("Vib", -1, 1);
    model.component("comp1").physics("tds").feature("eeic1").create("rc2", "BoundaryReactionCoefficients", 0);
    model.component("comp1").physics("tds").feature("eeic1").feature("rc2")
         .set("iloc_src", "root.comp1.liion.es1.er3.iloc");
    model.component("comp1").physics("tds").feature("eeic1").feature("rc2").setIndex("Vib", -1, 0);
    model.component("comp1").physics("tds").create("reac1", "Reactions", 1);
    model.component("comp1").physics("tds").feature("reac1").selection().named("sel2");
    model.component("comp1").physics("tds").feature("reac1").setIndex("R_cH", "-4*Rproton+2*Rwater", 0);
    model.component("comp1").physics("tds").feature("reac1").setIndex("R_cMn", "Rproton", 0);
    model.component("comp1").physics("tds").feature("reac1").setIndex("R_cH2O", "2*Rproton-Rwater", 0);
    model.component("comp1").physics("tds").create("reac2", "Reactions", 1);
    model.component("comp1").physics("tds").feature("reac2").selection().named("sel1");
    model.component("comp1").physics("tds").feature("reac2").setIndex("R_cH", "2*Rwater", 0);
    model.component("comp1").physics("tds").feature("reac2").setIndex("R_cH2O", "-Rwater", 0);

    model.study("std1").feature().duplicate("time1", "time");
    model.study("std1").feature().duplicate("time2", "time1");
    model.study("std1").feature("time").set("tunit", "h");
    model.study("std1").feature("time").set("tlist", "0 7");
    model.study("std1").feature("time1").set("tunit", "h");
    model.study("std1").feature("time1").set("tlist", "range(0,0.5,300)");
    model.study("std1").feature("time2").set("tunit", "h");
    model.study("std1").feature("time2").set("tlist", "0 7");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tout", "tsteps");
    model.sol("sol1").feature("t1").create("st1", "StopCondition");
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "comp1.cycle_no>0", 0);
    model.sol("sol1").feature("t1").feature("st1").set("stopcondwarn", false);
    model.sol("sol1").feature("t2").create("st1", "StopCondition");
    model.sol("sol1").feature("t2").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t2").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t2").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t2").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t2").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t2").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t2").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t2").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t2").feature("st1").setIndex("stopcondarr", "comp1.cycle_no>48", 0);
    model.sol("sol1").feature("t2").feature("st1").set("stopcondwarn", false);
    model.sol("sol1").feature("t3").set("tout", "tsteps");
    model.sol("sol1").feature("t3").create("st1", "StopCondition");
    model.sol("sol1").feature("t3").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t3").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t3").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t3").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t3").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t3").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t3").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t3").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t3").feature("st1").setIndex("stopcondarr", "comp1.cycle_no>49", 0);
    model.sol("sol1").feature("t3").feature("st1").set("stopcondwarn", false);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("unit", new String[]{""});
    model.result("pg1").feature("glob1").set("expr", new String[]{"liion.cdc1.phis0"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u7535\u6c60\u7535\u4f4d"});
    model.result("pg1").label("\u5bf9\u5730\u8fb9\u754c\u7535\u6781\u7535\u4f4d (liion)");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{""});
    model.result("pg2").feature("glob1").set("expr", new String[]{"liion.cdc1.Icell"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u7535\u6c60\u7535\u6d41"});
    model.result("pg2").label("\u7535\u6c60\u7535\u6d41 (liion)");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("unit", new String[]{""});
    model.result("pg3").feature("glob1").set("expr", new String[]{"liion.SOC_cell"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u7535\u6c60\u8377\u7535\u72b6\u6001"});
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg3").create("glob2", "Global");
    model.result("pg3").feature("glob2").set("unit", new String[]{""});
    model.result("pg3").feature("glob2").set("expr", new String[]{"liion.soc_average_pce1"});
    model.result("pg3").feature("glob2")
         .set("descr", new String[]{"\u5e73\u5747 SOC\uff0c\u591a\u5b54\u7535\u6781 1"});
    model.result("pg3").feature("glob2").set("linestyle", "dashed");
    model.result("pg3").feature("glob2").set("xdatasolnumtype", "level1");
    model.result("pg3")
         .label("\u7535\u6c60\u548c\u5e73\u5747\u7535\u6781\u7535\u6c60\u7684\u8377\u7535\u72b6\u6001 (liion)");
    model.result("pg3").set("ylabel", "SOC (1)");
    model.result("pg3").set("titletype", "none");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").label("\u7535\u89e3\u8d28\u7535\u4f4d (liion)");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x");
    model.result("pg4").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg4").feature("lngr1").selection().set(1, 2);
    model.result("pg4").feature("lngr1").set("expr", new String[]{"phil"});
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (liion)");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "x");
    model.result("pg5").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr1").selection().set(1, 2);
    model.result("pg5").feature("lngr1").set("expr", new String[]{"phis"});
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "x");
    model.result("pg6").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg6").feature("lngr1").selection().set(1, 2);
    model.result("pg6").feature("lngr1").set("expr", new String[]{"cl"});
    model.result("pg6").label("\u7535\u89e3\u8d28\u76d0\u6d53\u5ea6 (liion)");
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").set("titletype", "manual");
    model.result("pg7").set("title", "\u6d53\u5ea6\uff0c\u6240\u6709\u7269\u8d28");
    model.result("pg7").label("\u6d53\u5ea6\uff0c\u6240\u6709\u7269\u8d28 (tds)");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("xdata", "expr");
    model.result("pg7").feature("lngr1").set("xdataexpr", "x");
    model.result("pg7").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg7").feature("lngr1").selection().set(1, 2);
    model.result("pg7").feature("lngr1").set("expr", new String[]{"cH"});
    model.result("pg7").feature("lngr1").label("\u7269\u8d28 H");
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").feature("lngr1").set("autosolution", false);
    model.result("pg7").feature("lngr1").set("autoexpr", false);
    model.result("pg7").feature("lngr1").set("autodescr", false);
    model.result("pg7").feature("lngr1").set("legendprefix", "H ");
    model.result("pg7").feature("lngr1").set("descractive", true);
    model.result("pg7").feature("lngr1").set("descr", "\u6d53\u5ea6");
    model.result("pg7").create("lngr2", "LineGraph");
    model.result("pg7").feature("lngr2").set("xdata", "expr");
    model.result("pg7").feature("lngr2").set("xdataexpr", "x");
    model.result("pg7").feature("lngr2").selection().geom("geom1", 1);
    model.result("pg7").feature("lngr2").selection().set(1, 2);
    model.result("pg7").feature("lngr2").set("expr", new String[]{"cMn"});
    model.result("pg7").feature("lngr2").label("\u7269\u8d28 Mn");
    model.result("pg7").feature("lngr2").set("legend", true);
    model.result("pg7").feature("lngr2").set("autosolution", false);
    model.result("pg7").feature("lngr2").set("autoexpr", false);
    model.result("pg7").feature("lngr2").set("autodescr", false);
    model.result("pg7").feature("lngr2").set("legendprefix", "Mn ");
    model.result("pg7").feature("lngr2").set("descractive", true);
    model.result("pg7").feature("lngr2").set("descr", "\u6d53\u5ea6");
    model.result("pg7").create("lngr3", "LineGraph");
    model.result("pg7").feature("lngr3").set("xdata", "expr");
    model.result("pg7").feature("lngr3").set("xdataexpr", "x");
    model.result("pg7").feature("lngr3").selection().geom("geom1", 1);
    model.result("pg7").feature("lngr3").selection().set(1, 2);
    model.result("pg7").feature("lngr3").set("expr", new String[]{"cH2O"});
    model.result("pg7").feature("lngr3").label("\u7269\u8d28 H2O");
    model.result("pg7").feature("lngr3").set("legend", true);
    model.result("pg7").feature("lngr3").set("autosolution", false);
    model.result("pg7").feature("lngr3").set("autoexpr", false);
    model.result("pg7").feature("lngr3").set("autodescr", false);
    model.result("pg7").feature("lngr3").set("legendprefix", "H2O ");
    model.result("pg7").feature("lngr3").set("descractive", true);
    model.result("pg7").feature("lngr3").set("descr", "\u6d53\u5ea6");
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").set("data", "dset1");
    model.result("pg8").label("\u6d53\u5ea6, H (tds)");
    model.result("pg8").set("titletype", "custom");
    model.result("pg8").set("prefixintitle", "");
    model.result("pg8").set("expressionintitle", false);
    model.result("pg8").set("typeintitle", false);
    model.result("pg8").create("lngr1", "LineGraph");
    model.result("pg8").feature("lngr1").set("xdata", "expr");
    model.result("pg8").feature("lngr1").set("xdataexpr", "x");
    model.result("pg8").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg8").feature("lngr1").selection().set(1, 2);
    model.result("pg8").feature("lngr1").set("expr", new String[]{"cH"});
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").set("data", "dset1");
    model.result("pg9").label("\u6d53\u5ea6, Mn (tds)");
    model.result("pg9").set("titletype", "custom");
    model.result("pg9").set("prefixintitle", "");
    model.result("pg9").set("expressionintitle", false);
    model.result("pg9").set("typeintitle", false);
    model.result("pg9").create("lngr1", "LineGraph");
    model.result("pg9").feature("lngr1").set("xdata", "expr");
    model.result("pg9").feature("lngr1").set("xdataexpr", "x");
    model.result("pg9").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg9").feature("lngr1").selection().set(1, 2);
    model.result("pg9").feature("lngr1").set("expr", new String[]{"cMn"});
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").set("data", "dset1");
    model.result("pg10").label("\u6d53\u5ea6, H2O (tds)");
    model.result("pg10").set("titletype", "custom");
    model.result("pg10").set("prefixintitle", "");
    model.result("pg10").set("expressionintitle", false);
    model.result("pg10").set("typeintitle", false);
    model.result("pg10").create("lngr1", "LineGraph");
    model.result("pg10").feature("lngr1").set("xdata", "expr");
    model.result("pg10").feature("lngr1").set("xdataexpr", "x");
    model.result("pg10").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg10").feature("lngr1").selection().set(1, 2);
    model.result("pg10").feature("lngr1").set("expr", new String[]{"cH2O"});
    model.result("pg1").run();
    model.result("pg1").set("data", "dset4");
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("showlegends", false);
    model.result("pg1").run();
    model.result("pg1").feature("glob1").set("xdata", "expr");
    model.result("pg1").feature("glob1").set("xdataexpr", "t+t_cycle_first");
    model.result("pg1").feature("glob1").set("xdataunit", "h");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("data", "dset4");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("showlegends", false);
    model.result("pg2").run();
    model.result("pg2").feature("glob1").set("expr", new String[]{"liion.Q_cell"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u7535\u6c60\u5355\u5143\u5bb9\u91cf"});
    model.result("pg2").feature("glob1").set("unit", new String[]{"C"});
    model.result("pg2").feature("glob1").setIndex("unit", "Ah", 0);
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "t+t_cycle_first");
    model.result("pg2").feature("glob1").set("xdataunit", "h");
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").set("data", "dset4");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").run();
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").feature("glob1").set("xdataexpr", "t+t_cycle_first");
    model.result("pg3").feature("glob1").set("xdataunit", "h");
    model.result("pg3").run();
    model.result("pg3").feature("glob2").set("xdata", "expr");
    model.result("pg3").feature("glob2").set("xdataexpr", "t+t_cycle_first");
    model.result("pg3").feature("glob2").set("xdataunit", "h");
    model.result("pg3").run();
    model.result("pg4").run();
    model.result().remove("pg4");
    model.result().remove("pg5");
    model.result().remove("pg6");
    model.result().remove("pg7");
    model.result().remove("pg8");
    model.result().remove("pg9");
    model.result().remove("pg10");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").set("data", "dset4");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("showlegends", false);
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "epss_LMO_pos", 0);
    model.result("pg4").feature("glob1").set("xdataexpr", "liion.cdc1.cycle_counter");
    model.result("pg4").feature("glob1").set("xdatadescr", "\u5faa\u73af\u6b21\u6570");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").run();
    model.result("pg5").feature("glob1").setIndex("expr", "D_LMO_pos", 0);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").set("data", "none");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("showlegends", true);
    model.result("pg6").run();
    model.result("pg6").feature("glob1").set("data", "dset3");
    model.result("pg6").feature("glob1").set("expr", new String[]{"liion.cdc1.phis0"});
    model.result("pg6").feature("glob1").set("descr", new String[]{"\u7535\u6c60\u7535\u4f4d"});
    model.result("pg6").feature("glob1").set("unit", new String[]{"V"});
    model.result("pg6").feature("glob1").set("xdataexpr", "t_cycle_dch_first");
    model.result("pg6").feature("glob1").set("linestyle", "dashed");
    model.result("pg6").feature("glob1").set("legendmethod", "manual");
    model.result("pg6").feature("glob1").setIndex("legends", "1st cycle", 0);
    model.result("pg6").feature("glob1").create("filt1", "GlobalFilter");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").feature("filt1").set("xdec", false);
    model.result("pg6").feature("glob1").feature("filt1").set("yinc", false);
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("glob2", "glob1");
    model.result("pg6").run();
    model.result("pg6").feature("glob2").set("data", "dset1");
    model.result("pg6").feature("glob2").set("xdataexpr", "t_cycle_dch_last");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg6").feature("glob2").set("linestyle", "solid");
    model.result("pg6").feature("glob2").setIndex("legends", "50th cycle", 0);
    model.result("pg6").run();
    model.result("pg5").run();
    model.result().duplicate("pg7", "pg5");
    model.result("pg7").run();
    model.result("pg7").set("showlegends", true);
    model.result("pg7").set("legendpos", "lowerleft");
    model.result("pg7").run();
    model.result("pg7").feature("glob1").set("expr", new String[]{"liion.SOH_cell"});
    model.result("pg7").feature("glob1").set("descr", new String[]{"\u7535\u6c60\u5065\u5eb7\u72b6\u6001"});
    model.result("pg7").feature("glob1").set("unit", new String[]{"1"});
    model.result("pg7").feature("glob1").set("legendmethod", "manual");
    model.result("pg7").feature("glob1").setIndex("legends", "Based on cyclable lithium and host capacity", 0);
    model.result("pg7").feature().duplicate("glob2", "glob1");
    model.result("pg7").run();
    model.result("pg7").feature("glob2").setIndex("expr", "(t-liion.cdc1.t_dch_start)/t_cycle_first", 0);
    model.result("pg7").feature("glob2").setIndex("legends", "Nominal C/3 discharge capacity", 0);
    model.result("pg7").feature("glob2").create("filt1", "GlobalFilter");
    model.result("pg7").run();
    model.result("pg7").feature("glob2").feature("filt1").set("xdec", false);
    model.result("pg7").run();
    model.result("pg5").run();
    model.result().duplicate("pg8", "pg5");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").feature("glob1").setIndex("expr", "cl_battery", 0);
    model.result("pg8").run();

    model.title("LMO \u5206\u89e3");

    model
         .description("\u672c\u4f8b\u6a21\u62df LMO \u4e0e\u9502\u7b94\u7535\u6c60\u5355\u5143\u7684\u5bb9\u91cf\u8870\u51cf\u60c5\u51b5\uff0c\u5206\u6790\u4e86\u4e0e LMO \u5206\u89e3\u76f8\u5173\u7684\u591a\u79cd\u4e0d\u5229\u53cd\u5e94\uff0c\u5e76\u7814\u7a76\u4e86\u5728\u9ad8\u6e29\u6761\u4ef6\u4e0b\u8fc7\u5ea6\u5145\u7535\u7684\u6ee5\u7528\u7535\u5faa\u73af\u5bf9\u7535\u6c60\u7684\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("lmo_decomposition.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
