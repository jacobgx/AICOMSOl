/*
 * heterogeneous_lib.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:19 by COMSOL 6.3.0.290. */
public class heterogeneous_lib {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Heterogeneous_Models");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ts", "TransportInSolids", "geom1");
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
    model.study("std1").feature("cdi").setSolveFor("/physics/ts", true);
    model.study("std1").feature("cdi").setSolveFor("/physics/liion", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/ts", true);
    model.study("std1").feature("time").setSolveFor("/physics/liion", true);

    model.component("comp1").geom("geom1").insertFile("heterogeneous_lib_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("unisel4");

    model.component("comp1").view("view1").set("transparency", true);
    model.component("comp1").view("view1").set("showaxisorientation", false);

    model.param().create("par2");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2")
         .set("cs0_pos", "csmax_pos*solmaxpos", "\u521d\u59cb\u6d53\u5ea6\uff0c\u6b63\u6781\u9897\u7c92");
    model.param("par2").set("cs0_Si", "csmax_Si*solmin_Si", "\u521d\u59cb\u6d53\u5ea6\uff0c\u7845");
    model.param("par2").set("cs0_Gr", "csmax_Gr*solmin_Gr", "\u521d\u59cb\u6d53\u5ea6\uff0c\u77f3\u58a8");
    model.param("par2").set("E_sep", "500[MPa]", "\u6768\u6c0f\u6a21\u91cf\uff0c\u9694\u819c");
    model.param("par2").set("nu_sep", "0.25", "\u6cca\u677e\u6bd4\uff0c\u9694\u819c");
    model.param("par2").set("rho", "1000[kg/m^3]", "\u5bc6\u5ea6\uff0c\u9694\u819c");
    model.param("par2")
         .set("E_pb", "5[GPa]", "\u6768\u6c0f\u6a21\u91cf\uff0c\u591a\u5b54\u5bfc\u7535\u7c98\u7ed3\u5242");
    model.param("par2").set("nu_pb", "0.25", "\u6cca\u677e\u6bd4\uff0c\u591a\u5b54\u5bfc\u7535\u7c98\u7ed3\u5242");
    model.param("par2").set("C_rate", "1", "\u653e\u7535\u500d\u7387");
    model.param("par2").set("I1C", "Q_cell/1[h]", "1C \u653e\u7535\u7535\u6d41");
    model.param("par2").set("i_app", "I1C*C_rate", "\u5e73\u5747\u7535\u6c60\u7535\u6d41\u5bc6\u5ea6");
    model.param("par2")
         .set("sigmas_pb", "10[S/m]", "\u7535\u5bfc\u7387\uff0c\u591a\u5b54\u5bfc\u7535\u7c98\u7ed3\u5242");
    model.param("par2")
         .set("epsl_ref_pb", "0.6", "\u65e0\u53d8\u5f62\u7535\u89e3\u6db2\u4f53\u79ef\u5206\u6570\uff0c\u591a\u5b54\u5bfc\u7535\u7c98\u7ed3\u5242");
    model.param("par2")
         .set("epsl_ref_sep", "0.4", "\u65e0\u53d8\u5f62\u7535\u89e3\u6db2\u4f53\u79ef\u5206\u6570\uff0c\u9694\u819c");
    model.param("par2").set("i0_ref", "10[A/m^2]", "50% \u9502\u5316\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int2", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int3", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int4", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElectrodePotential", "ElectrodePotential", "Equilibrium potential");
    model.component("comp1").material("mat1").propertyGroup()
         .create("OperationalSOC", "OperationalSOC", "Operational electrode state of charge");
    model.component("comp1").material("mat1").propertyGroup().create("ic", "ic", "Intercalation strain");
    model.component("comp1").material("mat1").propertyGroup("ic").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("EquilibriumConcentration", "EquilibriumConcentration", "Equilibrium concentration");
    model.component("comp1").material("mat1").propertyGroup()
         .create("EquilibriumPotentialWithDOCInput", "EquilibriumPotentialWithDOCInput", "Equilibrium potential (using degree of conversion as model input)");
    model.component("comp1").material("mat1").propertyGroup()
         .create("EquilibriumDegreeOfConversion", "EquilibriumDegreeOfConversion", "Equilibrium degree of conversion");
    model.component("comp1").material("mat1").label("Graphite, LixC6 MCMB (Negative, Li-ion Battery)");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("funcname", "E_int");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0", "32.47"}, {"0.333", "28.56"}, {"0.5", "58.06"}, {"1", "108.67"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("fununit", new String[]{"GPa"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").set("funcname", "nu_int");
    model.component("comp1").material("mat1").propertyGroup("def").func("int2")
         .set("table", new String[][]{{"0", "0.32"}, {"0.333", "0.39"}, {"0.5", "0.34"}, {"1", "0.24"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").set("fununit", new String[]{""});
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").label("Interpolation 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("int3")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").set("funcname", "Eeq");
    model.component("comp1").material("mat1").propertyGroup("def").func("int3")
         .set("table", new String[][]{{"0", "2.781186612"}, 
         {"0.01", "1.520893224"}, 
         {"0.02", "0.893922607"}, 
         {"0.03", "0.581284406"}, 
         {"0.04", "0.42452844"}, 
         {"0.05", "0.344895805"}, 
         {"0.06", "0.303146342"}, 
         {"0.07", "0.279578072"}, 
         {"0.08", "0.264093089"}, 
         {"0.09", "0.251347845"}, 
         {"0.1", "0.238588379"}, 
         {"0.11", "0.224803164"}, 
         {"0.12", "0.210294358"}, 
         {"0.13", "0.196408586"}, 
         {"0.14", "0.184624188"}, 
         {"0.15", "0.175188157"}, 
         {"0.16", "0.167373311"}, 
         {"0.17", "0.160452107"}, 
         {"0.18", "0.154025412"}, 
         {"0.19", "0.147948522"}, 
         {"0.2", "0.142214997"}, 
         {"0.21", "0.13688271"}, 
         {"0.22", "0.132033114"}, 
         {"0.23", "0.127747573"}, 
         {"0.24", "0.124091616"}, 
         {"0.25", "0.121103387"}, 
         {"0.26", "0.11878567"}, 
         {"0.27", "0.117102317"}, 
         {"0.28", "0.115980205"}, 
         {"0.29", "0.115317054"}, 
         {"0.3", "0.114993965"}, 
         {"0.31", "0.114890105"}, 
         {"0.32", "0.114886278"}, 
         {"0.33", "0.114884619"}, 
         {"0.34", "0.114873068"}, 
         {"0.35", "0.114824904"}, 
         {"0.36", "0.114644725"}, 
         {"0.37", "0.114372614"}, 
         {"0.38", "0.114017954"}, 
         {"0.39", "0.11359371"}, 
         {"0.4", "0.11311133"}, 
         {"0.41", "0.112575849"}, 
         {"0.42", "0.111980245"}, 
         {"0.43", "0.111297682"}, 
         {"0.44", "0.110470149"}, 
         {"0.45", "0.109393081"}, 
         {"0.46", "0.107900592"}, 
         {"0.47", "0.10576964"}, 
         {"0.48", "0.102783317"}, 
         {"0.49", "0.09889031"}, 
         {"0.5", "0.094391564"}, 
         {"0.51", "0.089921069"}, 
         {"0.52", "0.086112415"}, 
         {"0.53", "0.083265315"}, 
         {"0.54", "0.081326247"}, 
         {"0.55", "0.080074892"}, 
         {"0.56", "0.07928329"}, 
         {"0.57", "0.078778765"}, 
         {"0.58", "0.078447703"}, 
         {"0.59", "0.078220432"}, 
         {"0.6", "0.078055641"}, 
         {"0.61", "0.077929111"}, 
         {"0.62", "0.077826563"}, 
         {"0.63", "0.077739397"}, 
         {"0.64", "0.077662227"}, 
         {"0.65", "0.077591472"}, 
         {"0.66", "0.077524557"}, 
         {"0.67", "0.077459463"}, 
         {"0.68", "0.077394455"}, 
         {"0.69", "0.077327934"}, 
         {"0.7", "0.077258337"}, 
         {"0.71", "0.077184077"}, 
         {"0.72", "0.077103499"}, 
         {"0.73", "0.077014851"}, 
         {"0.74", "0.076916258"}, 
         {"0.75", "0.07680571"}, 
         {"0.76", "0.07668104"}, 
         {"0.77", "0.07653992"}, 
         {"0.78", "0.076379839"}, 
         {"0.79", "0.076198086"}, 
         {"0.8", "0.075991699"}, 
         {"0.81", "0.075757371"}, 
         {"0.82", "0.075491288"}, 
         {"0.83", "0.075188813"}, 
         {"0.84", "0.07484398"}, 
         {"0.85", "0.074448647"}, 
         {"0.86", "0.07399118"}, 
         {"0.87", "0.073454466"}, 
         {"0.88", "0.072812991"}, 
         {"0.89", "0.072028722"}, 
         {"0.9", "0.071045433"}, 
         {"0.91", "0.069780996"}, 
         {"0.92", "0.068116222"}, 
         {"0.93", "0.065874599"}, 
         {"0.94", "0.062770873"}, 
         {"0.95", "0.058253898"}, 
         {"0.96", "0.051075794"}, 
         {"0.97", "0.038790069"}, 
         {"0.98", "0.020172191"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").set("fununit", new String[]{"V"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").set("argunit", new String[]{""});
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").set("defineinv", true);
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").set("funcinvname", "Eeq_inv");
    model.component("comp1").material("mat1").propertyGroup("def").func("int4").label("Interpolation 4");
    model.component("comp1").material("mat1").propertyGroup("def").func("int4")
         .set("funcnametable", new String[][]{{"int2", "1"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int4").set("funcname", "dEeqdT");
    model.component("comp1").material("mat1").propertyGroup("def").func("int4")
         .set("table", new String[][]{{"0", "3.0e-4"}, 
         {"0.17", "0"}, 
         {"0.24", "-6e-5"}, 
         {"0.28", "-1.6e-4"}, 
         {"0.5", "-1.6e-4"}, 
         {"0.54", "-9e-5"}, 
         {"0.71", "-9e-5"}, 
         {"0.85", "-1.0e-4"}, 
         {"1.0", "-1.2e-4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int4").set("fununit", new String[]{"V/K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int4").set("argunit", new String[]{""});
    model.component("comp1").material("mat1").propertyGroup("def").set("youngsmodulus", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("poissonsratio", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("youngsmodulus", "E_int(c/csmax)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("youngsmodulus", "Yue Qi et al 2010 J. Electrochem. Soc. 157 A558");
    model.component("comp1").material("mat1").propertyGroup("def").set("poissonsratio", "nu_int(c/csmax)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("poissonsratio", "Yue Qi et al 2010 J. Electrochem. Soc. 157 A558");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"100[S/m]", "0", "0", "0", "100[S/m]", "0", "0", "0", "100[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("electricconductivity", "V. Srinivasan, and J. Newman, \u201cDesign and Optimization of a Natural Graphite/Iron Phosphate Lithium Ion Cell,\u201d J. Electrochem. Soc., vol. 151, p. 1530, 2004.");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("diffusion", new String[]{"1.4523e-13*exp(68025.7/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]", "0", "0", "0", "1.4523e-13*exp(68025.7/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]", "0", "0", "0", "1.4523e-13*exp(68025.7/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))[m^2/s]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("diffusion", "K. Kumaresan, G. Sikha, and R. E. White, \u201cThermal Model for a Li-Ion Cell,\u201d J. Electrochem. Soc., vol. 155, p. A164, 2008.");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1[W/(m*K)]", "0", "0", "0", "1[W/(m*K)]", "0", "0", "0", "1[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("thermalconductivity", "S. Chen, C. Wan, and Y. Wang, J. Power Sources, 140, 111 (2005).");
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "750[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("heatcapacity", "SI Chemical Data, John Wiley & Sons, 1994");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2300[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("density", "SI Chemical Data, John Wiley & Sons, 1994");
    model.component("comp1").material("mat1").propertyGroup("def").set("csmax", "31507[mol/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def").descr("csmax", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("T_ref", "318[K]");
    model.component("comp1").material("mat1").propertyGroup("def").descr("T_ref", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("T2", "min(393.15,max(T,223.15))");
    model.component("comp1").material("mat1").propertyGroup("def").descr("T2", "");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("concentration");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").identifier("eeq");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .set("Eeq", "def.Eeq(doc)+def.dEeqdT(doc)*(T-298[K])");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .setPropertyInfo("Eeq", "D. P Karthikeyan, G. Sikha, and R. E. White, \u201cThermodynamic model development for lithium intercalation electrodes,\u201d J. Power Sources, vol. 185, p. 1398, 2008.");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("dEeqdT", "def.dEeqdT(doc)");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential")
         .setPropertyInfo("dEeqdT", "K. E. Thomas, and J. Newman, \u201cHeats of mixing and of entropy in porous insertion electrodes,\u201d J. Power Sources., vol. 119-121, p. 844, 2003.");
    model.component("comp1").material("mat1").propertyGroup("ElectrodePotential").set("cEeqref", "def.csmax");
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
    model.component("comp1").material("mat1").propertyGroup("OperationalSOC").set("E_max", "1[V]");
    model.component("comp1").material("mat1").propertyGroup("OperationalSOC").set("E_min", "0.075[V]");
    model.component("comp1").material("mat1").propertyGroup("ic").label("Intercalation strain");
    model.component("comp1").material("mat1").propertyGroup("ic").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("ic").func("int1").set("funcname", "dVOLdSOL");
    model.component("comp1").material("mat1").propertyGroup("ic").func("int1")
         .set("table", new String[][]{{"0", "0"}, 
         {"0.006802721088435382", "0.12500000000000178"}, 
         {"0.06316812439261421", "1.2736486486486491"}, 
         {"0.11175898931000966", "2.523648648648649"}, 
         {"0.17978620019436342", "3.5709459459459474"}, 
         {"0.2400388726919339", "4.449324324324325"}, 
         {"0.2905733722060252", "5.192567567567568"}, 
         {"0.3566569484936831", "5.66554054054054"}, 
         {"0.4188532555879494", "5.969594594594595"}, 
         {"0.48104956268221566", "6.10472972972973"}, 
         {"0.5432458697764819", "6.173648648648647"}, 
         {"0.58600583090379", "6.306081081081081"}, 
         {"0.6112730806608356", "7.726351351351352"}, 
         {"0.6443148688046647", "8.570945945945946"}, 
         {"0.694849368318756", "9.449324324324323"}, 
         {"0.7414965986394557", "10.29391891891892"}, 
         {"0.7764820213799805", "10.902027027027025"}, 
         {"0.8231292517006802", "11.543918918918918"}, 
         {"0.8542274052478133", "12.152027027027026"}, 
         {"0.8833819241982507", "12.827702702702702"}, 
         {"0.9183673469387755", "12.996621621621621"}, 
         {"0.9494655004859086", "13.16554054054054"}});
    model.component("comp1").material("mat1").propertyGroup("ic").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("ic").func("int1").set("fununit", new String[]{"%"});
    model.component("comp1").material("mat1").propertyGroup("ic").func("int1").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("ic").identifier("is");
    model.component("comp1").material("mat1").propertyGroup("ic").set("dvol", "dVOLdSOL(c/def.csmax)");
    model.component("comp1").material("mat1").propertyGroup("ic")
         .setPropertyInfo("dvol", "S. Schweidler, L. de Biasi, A. Schiele, P. Hartmann, T. Brezesinski and J. Janek, \"Volume Changes of Graphite Anodes Revisited: A Combined Operando X-Ray Diffraction and In Situ Pressure Analysis Study\", J. Phys. Chem. C, 2018, 122, 8829\u20138835");
    model.component("comp1").material("mat1").propertyGroup("ic").addInput("concentration");
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
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ElectrodePotential", "ElectrodePotential", "Equilibrium potential");
    model.component("comp1").material("mat2").propertyGroup()
         .create("OperationalSOC", "OperationalSOC", "Operational electrode state of charge");
    model.component("comp1").material("mat2").propertyGroup().create("ic", "ic", "Intercalation strain");
    model.component("comp1").material("mat2").propertyGroup()
         .create("EquilibriumConcentration", "EquilibriumConcentration", "Equilibrium concentration");
    model.component("comp1").material("mat2").propertyGroup()
         .create("EquilibriumPotentialWithDOCInput", "EquilibriumPotentialWithDOCInput", "Equilibrium potential (using degree of conversion as model input)");
    model.component("comp1").material("mat2").propertyGroup()
         .create("EquilibriumDegreeOfConversion", "EquilibriumDegreeOfConversion", "Equilibrium degree of conversion");
    model.component("comp1").material("mat2").label("Silicon, LixSi (Negative, Li-ion Battery)");
    model.component("comp1").material("mat2")
         .comments("vs Li/Li+, T=25 C\nEeq for fully lithiated at 1\nEeq for delithiated at 0\n\nReferences\nElectrochemical properties:\nV. A. Sethuraman, V. Srinivasan, and J. Newman, \"Analysis of Electrochemical Lithiation and Delithiation Kinetics in Silicon\", J. Electrochem. Soc., vol. 160, p. A394, 2013\n\nDensity averaged from:\nV. A. Sethuraman, V. Srinivasan, and J. Newman, \"Analysis of Electrochemical Lithiation and Delithiation Kinetics in Silicon\", J. Electrochem. Soc., vol. 160, p. A394, 2013\nB. A. Boukamp, G. C. Lesh, R. A. Huggins, \"All\u2010Solid Lithium Electrodes with Mixed\u2010Conductor Matrix\", J. Electrochem. Soc., vol. 128, p. 725, 1981\n");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("funcname", "Eeq");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0.000", "0.800"}, 
         {"0.004", "0.613"}, 
         {"0.031", "0.569"}, 
         {"0.066", "0.515"}, 
         {"0.095", "0.488"}, 
         {"0.124", "0.461"}, 
         {"0.156", "0.434"}, 
         {"0.194", "0.416"}, 
         {"0.220", "0.403"}, 
         {"0.255", "0.390"}, 
         {"0.281", "0.381"}, 
         {"0.311", "0.372"}, 
         {"0.343", "0.367"}, 
         {"0.375", "0.358"}, 
         {"0.401", "0.354"}, 
         {"0.430", "0.340"}, 
         {"0.468", "0.331"}, 
         {"0.491", "0.322"}, 
         {"0.520", "0.313"}, 
         {"0.546", "0.305"}, 
         {"0.576", "0.296"}, 
         {"0.596", "0.287"}, 
         {"0.625", "0.278"}, 
         {"0.651", "0.264"}, 
         {"0.675", "0.255"}, 
         {"0.701", "0.246"}, 
         {"0.727", "0.237"}, 
         {"0.759", "0.228"}, 
         {"0.788", "0.219"}, 
         {"0.826", "0.210"}, 
         {"0.849", "0.202"}, 
         {"0.873", "0.197"}, 
         {"0.896", "0.193"}, 
         {"0.913", "0.184"}, 
         {"0.934", "0.179"}, 
         {"0.957", "0.170"}, 
         {"0.983", "0.157"}, 
         {"0.992", "0.143"}, 
         {"1", "0.062"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("fununit", new String[]{"V"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("argunit", new String[]{""});
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("defineinv", true);
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("funcinvname", "Eeq_inv");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0.001[S/m]", "0", "0", "0", "0.001[S/m]", "0", "0", "0", "0.001[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "1500[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("density", "Density averaged from:\nV. A. Sethuraman, V. Srinivasan, and J. Newman, \"Analysis of Electrochemical Lithiation and Delithiation Kinetics in Silicon\", J. Electrochem. Soc., vol. 160, p. A394, 2013 and \nB. A. Boukamp, G. C. Lesh, R. A. Huggins, \"All\u2010Solid Lithium Electrodes with Mixed\u2010Conductor Matrix\", J. Electrochem. Soc., vol. 128, p. 725, 1981");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("diffusion", new String[]{"1e-12[m^2/s]", "0", "0", "0", "1e-12[m^2/s]", "0", "0", "0", "1e-12[m^2/s]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("diffusion", "V. A. Sethuraman, V. Srinivasan, and J. Newman, \"Analysis of Electrochemical Lithiation and Delithiation Kinetics in Silicon\", J. Electrochem. Soc., vol. 160, p. A394, 2013");
    model.component("comp1").material("mat2").propertyGroup("def").set("csmax", "278000[mol/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def").descr("csmax", "");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").identifier("eeq");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").set("Eeq", "def.Eeq(doc)");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .setPropertyInfo("Eeq", "V. A. Sethuraman, V. Srinivasan, and J. Newman, \"Analysis of Electrochemical Lithiation and Delithiation Kinetics in Silicon\", J. Electrochem. Soc., vol. 160, p. A394, 2013");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").set("dEeqdT", "0[V/K]");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").set("cEeqref", "def.csmax");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .setPropertyInfo("cEeqref", "V. A. Sethuraman, V. Srinivasan, and J. Newman, \"Analysis of Electrochemical Lithiation and Delithiation Kinetics in Silicon\", J. Electrochem. Soc., vol. 160, p. A394, 2013");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").set("doc", "c/cEeqref");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .descr("doc", "Degree of conversion (state of lithiation)");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").addInput("concentration");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC")
         .label("Operational electrode state of charge");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").identifier("opsoc");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").set("socmax", "def.Eeq_inv(E_min)");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").set("socmin", "def.Eeq_inv(E_max)");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").set("E_max", "0.7[V]");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").set("E_min", "0.1[V]");
    model.component("comp1").material("mat2").propertyGroup("ic").label("Intercalation strain");
    model.component("comp1").material("mat2").propertyGroup("ic").identifier("is");
    model.component("comp1").material("mat2").propertyGroup("ic").set("dvol", "8.85[cm^3/mol]*c");
    model.component("comp1").material("mat2").propertyGroup("ic")
         .setPropertyInfo("dvol", "Note: 8.85 cm^3/mol corresponds to the transition between alpha-Si and Li15Si4.\n\nR. Koerver and others, \u201cChemo-mechanical expansion of lithium electrode materials \u2014 on the route to mechanically optimized all-solid-state batteries,\u201d Energy Environ. Sci., vol. 11, pp. 2142\u20132158, 201");
    model.component("comp1").material("mat2").propertyGroup("ic").addInput("concentration");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumConcentration")
         .label("Equilibrium concentration");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumConcentration")
         .set("csEq", "def.csmax*def.Eeq_inv(V)");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumConcentration")
         .addInput("electricpotential");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumPotentialWithDOCInput")
         .label("Equilibrium potential (using degree of conversion as model input)");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("Eeq", "def.Eeq(doc)");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("dEeqdT", "0[V/K]");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumPotentialWithDOCInput")
         .addInput("degreeofconversion");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumDegreeOfConversion")
         .label("Equilibrium degree of conversion");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumDegreeOfConversion")
         .set("docEq", "def.Eeq_inv(V)");
    model.component("comp1").material("mat2").propertyGroup("EquilibriumDegreeOfConversion")
         .addInput("electricpotential");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("int1", "Interpolation");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat3").propertyGroup()
         .create("ElectrodePotential", "ElectrodePotential", "Equilibrium potential");
    model.component("comp1").material("mat3").propertyGroup()
         .create("OperationalSOC", "OperationalSOC", "Operational electrode state of charge");
    model.component("comp1").material("mat3").propertyGroup().create("ic", "ic", "Intercalation strain");
    model.component("comp1").material("mat3").propertyGroup("ic").func().create("int1", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup()
         .create("EquilibriumConcentration", "EquilibriumConcentration", "Equilibrium concentration");
    model.component("comp1").material("mat3").propertyGroup()
         .create("EquilibriumPotentialWithDOCInput", "EquilibriumPotentialWithDOCInput", "Equilibrium potential (using degree of conversion as model input)");
    model.component("comp1").material("mat3").propertyGroup()
         .create("EquilibriumDegreeOfConversion", "EquilibriumDegreeOfConversion", "Equilibrium degree of conversion");
    model.component("comp1").material("mat3").propertyGroup().create("pg1", "def", "Electric conductivity");
    model.component("comp1").material("mat3").propertyGroup("pg1").func().create("int1", "Interpolation");
    model.component("comp1").material("mat3").label("NMC 111, LiNi0.33Mn0.33Co0.33O2 (Positive, Li-ion Battery)");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("funcname", "Eeq");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0", "4.44"}, 
         {"0.032", "4.34"}, 
         {"0.102", "4.23"}, 
         {"0.187", "4.13"}, 
         {"0.289", "4.025"}, 
         {"0.38", "3.945"}, 
         {"0.543", "3.835"}, 
         {"0.775", "3.71"}, 
         {"0.872", "3.62"}, 
         {"0.925", "3.51"}, 
         {"0.943", "3.42"}, 
         {"0.957", "3.30"}, 
         {"0.966", "3.165"}, 
         {"0.970", "3.02"}, 
         {"0.972", "2.90"}, 
         {"0.975", "2.688"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("fununit", new String[]{"V"});
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("argunit", new String[]{""});
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("defineinv", true);
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("funcinvname", "Eeq_inv");
    model.component("comp1").material("mat3").propertyGroup("def").set("poissonsratio", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("youngsmodulus", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("thermalconductivity", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("poissonsratio", "0.25");
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("poissonsratio", "Mechanical and physical properties of LiNi0.33Mn0.33Co0.33O2 (NMC),\nE Chenga, K. Hong, N. Taylor, H. Choe,\nJ. Wolfenstinec, J. Sakamotoa,\nJournal of the European Ceramic Society 37 (2017) 3213\u20133217");
    model.component("comp1").material("mat3").propertyGroup("def").set("youngsmodulus", "78[GPa]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("youngsmodulus", "Mechanical and physical properties of LiNi0.33Mn0.33Co0.33O2 (NMC),\nE Chenga, K. Hong, N. Taylor, H. Choe,\nJ. Wolfenstinec, J. Sakamotoa,\nJournal of the European Ceramic Society 37 (2017) 3213\u20133217");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"3.6[W/(m*K)]", "0", "0", "0", "3.6[W/(m*K)]", "0", "0", "0", "3.6[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("thermalconductivity", "Mechanical and physical properties of LiNi0.33Mn0.33Co0.33O2 (NMC),\nE Chenga, K. Hong, N. Taylor, H. Choe,\nJ. Wolfenstinec, J. Sakamotoa,\nJournal of the European Ceramic Society 37 (2017) 3213\u20133217");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"1.2e-5[1/K]", "0", "0", "0", "1.2e-5[1/K]", "0", "0", "0", "1.2e-5[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Mechanical and physical properties of LiNi0.33Mn0.33Co0.33O2 (NMC),\nE Chenga, K. Hong, N. Taylor, H. Choe,\nJ. Wolfenstinec, J. Sakamotoa,\nJournal of the European Ceramic Society 37 (2017) 3213\u20133217");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("diffusion", new String[]{"1e-14[m^2/s]", "0", "0", "0", "1e-14[m^2/s]", "0", "0", "0", "1e-14[m^2/s]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("diffusion", "Jing Ying Ko et al, J. Electrochem. Soc., 166, A2939");
    model.component("comp1").material("mat3").propertyGroup("def").set("csmax", "49000[mol/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def").descr("csmax", "");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").identifier("eeq");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .set("Eeq", "def.Eeq(doc)+dEeqdT*(T-298[K])");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .setPropertyInfo("Eeq", "W. Zheng, M. Shui, J. Shu, S. Gao, D. Xu, L. Chen, L. Feng and Y. Ren, \" GITT studies on oxide cathode LiNi1/3Co1/3Mn1/3O2 synthesized by citric acid assisted high-energy ball milling\", Bull. Mater. Sci., vol. 36, p. A495, 2013");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .set("dEeqdT", "-10[J/mol/K]/F_const");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .setPropertyInfo("dEeqdT", "V Viswanathan, D Choi, D Wang, W Xu, S Towne, R Williford, JG Zhang, J Liu and Z Yang \"Effect of entropy change on lithium intercalation in cathodes and anodes on Li-ion battery thermal management\", Journal of Power Sources 195 (2010) 3720-3729");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").set("cEeqref", "def.csmax");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .setPropertyInfo("cEeqref", "W. Zheng, M. Shui, J. Shu, S. Gao, D. Xu, L. Chen, L. Feng and Y. Ren, \" GITT studies on oxide cathode LiNi1/3Co1/3Mn1/3O2 synthesized by citric acid assisted high-energy ball milling\", Bull. Mater. Sci., vol. 36, p. A495, 2013");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").set("doc", "c/cEeqref");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .descr("doc", "Degree of conversion (state of lithiation)");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").addInput("concentration");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC")
         .label("Operational electrode state of charge");
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC").identifier("opsoc");
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC").set("socmax", "def.Eeq_inv(E_min)");
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC").set("socmin", "def.Eeq_inv(E_max)");
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC").set("E_max", "4.4[V]");
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC").set("E_min", "3.3[V]");
    model.component("comp1").material("mat3").propertyGroup("ic").label("Intercalation strain");
    model.component("comp1").material("mat3").propertyGroup("ic").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("ic").func("int1").set("funcname", "dVOLdSOL");
    model.component("comp1").material("mat3").propertyGroup("ic").func("int1")
         .set("table", new String[][]{{"1", "0"}, 
         {"0.9260263416001121", "-0.010256410256411108"}, 
         {"0.8670351688384477", "-0.1948717948717955"}, 
         {"0.8113086731119519", "-0.27692307692307727"}, 
         {"0.7506669468964551", "-0.37948717948718036"}, 
         {"0.6949460557657279", "-0.502564102564103"}, 
         {"0.628563822334314", "-0.5846153846153856"}, 
         {"0.55562421185372", "-0.6666666666666674"}, 
         {"0.501531455793751", "-0.7076923076923083"}, 
         {"0.4441600112091916", "-0.7487179487179496"}, 
         {"0.3851716407454113", "-0.953846153846154"}, 
         {"0.3278338237354632", "-1.241025641025642"}, 
         {"0.2737943113352951", "-1.671794871794872"}, 
         {"0.24269440941572107", "-2.0205128205128213"}});
    model.component("comp1").material("mat3").propertyGroup("ic").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat3").propertyGroup("ic").func("int1").set("fununit", new String[]{"%"});
    model.component("comp1").material("mat3").propertyGroup("ic").func("int1").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("ic").identifier("is");
    model.component("comp1").material("mat3").propertyGroup("ic").set("dvol", "dVOLdSOL(c/def.csmax)");
    model.component("comp1").material("mat3").propertyGroup("ic")
         .setPropertyInfo("dvol", "R. Koerver and others, \u201cChemo-mechanical expansion of lithium electrode materials \u2014 on the route to mechanically optimized all-solid-state batteries,\u201d Energy Environ. Sci., vol. 11, pp. 2142\u20132158, 201");
    model.component("comp1").material("mat3").propertyGroup("ic").addInput("concentration");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumConcentration")
         .label("Equilibrium concentration");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumConcentration")
         .set("csEq", "def.csmax*def.Eeq_inv(V)");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumConcentration")
         .addInput("electricpotential");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumPotentialWithDOCInput")
         .label("Equilibrium potential (using degree of conversion as model input)");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("Eeq", "def.Eeq(doc)+dEeqdT*(T-298[K])");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("dEeqdT", "-10[J/mol/K]/F_const");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumPotentialWithDOCInput")
         .addInput("degreeofconversion");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumPotentialWithDOCInput")
         .addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumDegreeOfConversion")
         .label("Equilibrium degree of conversion");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumDegreeOfConversion")
         .set("docEq", "def.Eeq_inv(V)");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumDegreeOfConversion")
         .addInput("electricpotential");
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1").set("source", "file");
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1").set("importedname", "NMC_333.txt");
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1").set("importeddim", "2D");
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1")
         .set("funcnametable", new String[][]{{"log_sigmas", "1"}});
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1").set("filecolumns", 3);
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1")
         .set("columnKeys", new String[]{"col1", "col2", "col3"});
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1")
         .set("columnType", new String[]{"col1", "arg", "col2", "arg", "col3", "value"});
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1")
         .set("funcnames", new String[]{"col1", "int1", "col2", "int1", "col3", "log_sigmas"});
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1").set("fununit", new String[]{""});
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1")
         .set("argunit", new String[]{"1", "1/K"});
    model.component("comp1").material("mat3").propertyGroup("pg1").func("int1").set("sourcetype", "model");
    model.component("comp1").material("mat3").propertyGroup("pg1")
         .set("electricconductivity", new String[]{"10^log_sigmas(x,1000/T)[S/cm]", "0", "0", "0", "10^log_sigmas(x,1000/T)[S/cm]", "0", "0", "0", "10^log_sigmas(x,1000/T)[S/cm]"});
    model.component("comp1").material("mat3").propertyGroup("pg1")
         .setPropertyInfo("electricconductivity", "Ruhul Amin and Yet-Ming Chiang 2016 J. Electrochem. Soc. 163 A1512");
    model.component("comp1").material("mat3").propertyGroup("pg1").set("x", "min(max(1-c/def.csmax,0),1)");
    model.component("comp1").material("mat3").propertyGroup("pg1").descr("x", "Degree of delithiation");
    model.component("comp1").material("mat3").propertyGroup("pg1").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("pg1").addInput("concentration");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat4").propertyGroup()
         .create("ElectrolyteConductivity", "ElectrolyteConductivity", "Electrolyte conductivity");
    model.component("comp1").material("mat4").propertyGroup("ElectrolyteConductivity").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat4").propertyGroup()
         .create("SpeciesProperties", "SpeciesProperties", "Species properties");
    model.component("comp1").material("mat4").propertyGroup("SpeciesProperties").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat4").propertyGroup("SpeciesProperties").func()
         .create("int2", "Interpolation");
    model.component("comp1").material("mat4").propertyGroup()
         .create("ElectrolyteSaltConcentration", "ElectrolyteSaltConcentration", "Electrolyte salt concentration");
    model.component("comp1").material("mat4").label("LiPF6 in 3:7 EC:EMC (Liquid, Li-ion Battery)");
    model.component("comp1").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat4").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat4").propertyGroup("def").func("int1").set("funcname", "DL_int1");
    model.component("comp1").material("mat4").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"200", "3.9e-10/(1-200*59e-6)"}, 
         {"500", "4.12e-10/(1-500*59e-6)"}, 
         {"800", "4e-10/(1-800*59e-6)"}, 
         {"1000", "3.8e-10/(1-1000*59e-6)"}, 
         {"1200", "3.50e-10/(1-1200*59e-6)"}, 
         {"1600", "2.68e-10/(1-1600*59e-6)"}, 
         {"2000", "1.9e-10/(1-2000*59e-6)"}});
    model.component("comp1").material("mat4").propertyGroup("def").func("int1").set("interp", "piecewisecubic");
    model.component("comp1").material("mat4").propertyGroup("def").func("int1")
         .set("fununit", new String[]{"m^2/s"});
    model.component("comp1").material("mat4").propertyGroup("def").func("int1").set("argunit", new String[]{""});
    model.component("comp1").material("mat4").propertyGroup("def")
         .set("diffusion", new String[]{"DL_int1(c/1[mol/m^3])*exp(16500/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))", "0", "0", "0", "DL_int1(c/1[mol/m^3])*exp(16500/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))", "0", "0", "0", "DL_int1(c/1[mol/m^3])*exp(16500/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))"});
    model.component("comp1").material("mat4").propertyGroup("def")
         .setPropertyInfo("diffusion", "T. G. Zavalis, M. Behm, and G. Lindbergh, \"Investigations of Short-Circuit Scenarios in a Lithium-Ion Battery Cell,\" J. Electrochem. Soc., vol. 159, p. A848, 2012.");
    model.component("comp1").material("mat4").propertyGroup("def").set("T_ref", "298[K]");
    model.component("comp1").material("mat4").propertyGroup("def").descr("T_ref", "");
    model.component("comp1").material("mat4").propertyGroup("def").set("T2", "min(393.15,max(T,223.15))");
    model.component("comp1").material("mat4").propertyGroup("def").descr("T2", "");
    model.component("comp1").material("mat4").propertyGroup("def").addInput("concentration");
    model.component("comp1").material("mat4").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat4").propertyGroup("ElectrolyteConductivity")
         .label("Electrolyte conductivity");
    model.component("comp1").material("mat4").propertyGroup("ElectrolyteConductivity").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat4").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("funcname", "sigmal_int1");
    model.component("comp1").material("mat4").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("table", new String[][]{{"0", "1e-6"}, 
         {"200", "0.455"}, 
         {"500", "0.783"}, 
         {"800", "0.935"}, 
         {"1000", "0.95"}, 
         {"1200", "0.927"}, 
         {"1600", "0.78"}, 
         {"2000", "0.60"}, 
         {"2200", "0.515"}});
    model.component("comp1").material("mat4").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("interp", "piecewisecubic");
    model.component("comp1").material("mat4").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("fununit", new String[]{"S/m"});
    model.component("comp1").material("mat4").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("argunit", new String[]{""});
    model.component("comp1").material("mat4").propertyGroup("ElectrolyteConductivity")
         .set("sigmal", new String[]{"sigmal_int1(c/1[mol/m^3])*exp(4000/8.314*(1/(T_ref2/1[K])-1/(T3/1[K])))", "0", "0", "0", "sigmal_int1(c/1[mol/m^3])*exp(4000/8.314*(1/(T_ref2/1[K])-1/(T3/1[K])))", "0", "0", "0", "sigmal_int1(c/1[mol/m^3])*exp(4000/8.314*(1/(T_ref2/1[K])-1/(T3/1[K])))"});
    model.component("comp1").material("mat4").propertyGroup("ElectrolyteConductivity")
         .setPropertyInfo("sigmal", "T. G. Zavalis, M. Behm, and G. Lindbergh, \"Investigations of Short-Circuit Scenarios in a Lithium-Ion Battery Cell,\" J. Electrochem. Soc., vol. 159, p. A848, 2012.");
    model.component("comp1").material("mat4").propertyGroup("ElectrolyteConductivity").set("T_ref2", "298[K]");
    model.component("comp1").material("mat4").propertyGroup("ElectrolyteConductivity").descr("T_ref2", "");
    model.component("comp1").material("mat4").propertyGroup("ElectrolyteConductivity")
         .set("T3", "min(393.15,max(T,223.15))");
    model.component("comp1").material("mat4").propertyGroup("ElectrolyteConductivity").descr("T3", "");
    model.component("comp1").material("mat4").propertyGroup("ElectrolyteConductivity").addInput("concentration");
    model.component("comp1").material("mat4").propertyGroup("ElectrolyteConductivity").addInput("temperature");
    model.component("comp1").material("mat4").propertyGroup("SpeciesProperties").label("Species properties");
    model.component("comp1").material("mat4").propertyGroup("SpeciesProperties").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat4").propertyGroup("SpeciesProperties").func("int1")
         .set("funcname", "transpNm_int1");
    model.component("comp1").material("mat4").propertyGroup("SpeciesProperties").func("int1")
         .set("table", new String[][]{{"200", "0.37"}, 
         {"500", "0.322"}, 
         {"800", "0.27"}, 
         {"1000", "0.251"}, 
         {"1200", "0.248"}, 
         {"1600", "0.236"}, 
         {"2000", "0.11"}});
    model.component("comp1").material("mat4").propertyGroup("SpeciesProperties").func("int1")
         .set("interp", "piecewisecubic");
    model.component("comp1").material("mat4").propertyGroup("SpeciesProperties").func("int1")
         .set("fununit", new String[]{""});
    model.component("comp1").material("mat4").propertyGroup("SpeciesProperties").func("int1")
         .set("argunit", new String[]{""});
    model.component("comp1").material("mat4").propertyGroup("SpeciesProperties").func("int2")
         .label("Interpolation 2");
    model.component("comp1").material("mat4").propertyGroup("SpeciesProperties").func("int2")
         .set("funcname", "actdep_int1");
    model.component("comp1").material("mat4").propertyGroup("SpeciesProperties").func("int2")
         .set("table", new String[][]{{"200", "0"}, 
         {"500", "0.29"}, 
         {"800", "0.695"}, 
         {"1000", "1"}, 
         {"1200", "1.32"}, 
         {"1600", "2.07"}, 
         {"2000", "2.50"}});
    model.component("comp1").material("mat4").propertyGroup("SpeciesProperties").func("int2")
         .set("interp", "piecewisecubic");
    model.component("comp1").material("mat4").propertyGroup("SpeciesProperties").func("int2")
         .set("fununit", new String[]{""});
    model.component("comp1").material("mat4").propertyGroup("SpeciesProperties").func("int2")
         .set("argunit", new String[]{""});
    model.component("comp1").material("mat4").propertyGroup("SpeciesProperties")
         .set("transpNum", "transpNm_int1(c/1[mol/m^3])");
    model.component("comp1").material("mat4").propertyGroup("SpeciesProperties")
         .setPropertyInfo("transpNum", "A. Nyman, M. Behm, and G. Lindbergh, \u201cElectrochemical characterisation and modelling of the mass transport phenomena in LiPF6-EC-EMC,\u201d Electrochim. Acta, vol. 53, p. 6356, 2008.");
    model.component("comp1").material("mat4").propertyGroup("SpeciesProperties")
         .set("fcl", "actdep_int1(c/1[mol/m^3])*exp(-1000/8.314*(1/(T_ref3/1[K])-1/(T4/1[K])))");
    model.component("comp1").material("mat4").propertyGroup("SpeciesProperties")
         .setPropertyInfo("fcl", "T. G. Zavalis, M. Behm, and G. Lindbergh, \"Investigations of Short-Circuit Scenarios in a Lithium-Ion Battery Cell,\" J. Electrochem. Soc., vol. 159, p. A848, 2012.");
    model.component("comp1").material("mat4").propertyGroup("SpeciesProperties")
         .set("T4", "min(393.15,max(T,223.15))");
    model.component("comp1").material("mat4").propertyGroup("SpeciesProperties").descr("T4", "");
    model.component("comp1").material("mat4").propertyGroup("SpeciesProperties").set("T_ref3", "298[K]");
    model.component("comp1").material("mat4").propertyGroup("SpeciesProperties").descr("T_ref3", "");
    model.component("comp1").material("mat4").propertyGroup("SpeciesProperties").addInput("concentration");
    model.component("comp1").material("mat4").propertyGroup("SpeciesProperties").addInput("temperature");
    model.component("comp1").material("mat4").propertyGroup("ElectrolyteSaltConcentration")
         .label("Electrolyte salt concentration");
    model.component("comp1").material("mat4").propertyGroup("ElectrolyteSaltConcentration").identifier("cElsalt");
    model.component("comp1").material("mat4").propertyGroup("ElectrolyteSaltConcentration")
         .set("cElsalt", "1200[mol/m^3]");
    model.component("comp1").material("mat1").selection().named("geom1_mov2_dom");
    model.component("comp1").material("mat2").selection().named("geom1_rot2_dom");
    model.component("comp1").material("mat3").selection().named("geom1_mov1_dom");
    model.component("comp1").material("mat4").selection().named("geom1_unisel3");
    model.component("comp1").material().duplicate("mat5", "mat1");
    model.component("comp1").material("mat5").selection().geom("geom1", 2);
    model.component("comp1").material("mat5").selection().named("geom1_intsel1");
    model.component("comp1").material().duplicate("mat6", "mat2");
    model.component("comp1").material("mat6").selection().geom("geom1", 2);
    model.component("comp1").material("mat6").selection().named("geom1_intsel3");
    model.component("comp1").material().duplicate("mat7", "mat3");
    model.component("comp1").material("mat7").selection().geom("geom1", 2);
    model.component("comp1").material("mat7").selection().named("geom1_intsel2");

    model.component("comp1").physics("liion").selection().named("geom1_unisel3");
    model.component("comp1").physics("liion").prop("ShapeProperty").set("order_electricpotentialionicphase", 1);
    model.component("comp1").physics("liion").prop("ShapeProperty").set("order_concentration", 1);
    model.component("comp1").physics("liion").prop("ShapeProperty").set("order_electricpotential", 1);
    model.component("comp1").physics("liion").feature("sep1").set("epsl", "epsl_ref_sep");
    model.component("comp1").physics("liion").create("pcb1", "PorousConductiveBinder", 3);
    model.component("comp1").physics("liion").feature("pcb1").selection().named("geom1_dif1_dom");
    model.component("comp1").physics("liion").feature("pcb1")
         .set("sigma", new String[]{"sigmas_pb", "0", "0", "0", "sigmas_pb", "0", "0", "0", "sigmas_pb"});
    model.component("comp1").physics("liion").feature("pcb1").set("epss", "1-epsl_ref_pb");
    model.component("comp1").physics("liion").feature("pcb1").set("epsl", "epsl_ref_pb");
    model.component("comp1").physics("liion").feature("pcb1").set("ElectricCorrModel", "NoCorr");
    model.component("comp1").physics("liion").create("bei1", "InternalElectrodeSurface", 2);
    model.component("comp1").physics("liion").feature("bei1").selection().named("geom1_unisel2");
    model.component("comp1").physics("liion").feature("bei1").feature("er1").set("minput_concentration", "c");
    model.component("comp1").physics("liion").feature("bei1").feature("er1")
         .set("ElectrodeKinetics", "LithiumInsertion");
    model.component("comp1").physics("liion").feature("bei1").feature("er1").set("i0_ref", "i0_ref");
    model.component("comp1").physics("liion").create("egnd1", "ElectricGround", 2);
    model.component("comp1").physics("liion").feature("egnd1").selection().named("geom1_boxsel2");
    model.component("comp1").physics("liion").create("ec1", "ElectrodeCurrent", 2);
    model.component("comp1").physics("liion").feature("ec1").selection().named("geom1_boxsel3");
    model.component("comp1").physics("liion").feature("ec1").set("ElectronicCurrentType", "AverageCurrentDensity");
    model.component("comp1").physics("liion").feature("ec1").set("Ias", "i_app");
    model.component("comp1").physics("ts").selection().named("geom1_unisel1");
    model.component("comp1").physics("ts").feature("solid1").set("DiffusionMaterialList", "mat1");
    model.component("comp1").physics("ts").feature("solid1").set("D_c_mat", "def");
    model.component("comp1").physics("ts").create("solid2", "Solid", 3);
    model.component("comp1").physics("ts").feature("solid2").selection().named("geom1_rot2_dom");
    model.component("comp1").physics("ts").feature("solid2").set("DiffusionMaterialList", "mat2");
    model.component("comp1").physics("ts").feature("solid2").set("D_c_mat", "def");
    model.component("comp1").physics("ts").create("solid3", "Solid", 3);
    model.component("comp1").physics("ts").feature("solid3").selection().named("geom1_mov1_dom");
    model.component("comp1").physics("ts").feature("solid3").set("DiffusionMaterialList", "mat3");
    model.component("comp1").physics("ts").feature("solid3").set("D_c_mat", "def");
    model.component("comp1").physics("ts").create("esc1", "ElectrodeSurfaceCoupling", 2);
    model.component("comp1").physics("ts").feature("esc1").selection().named("geom1_unisel2");
    model.component("comp1").physics("ts").feature("esc1").set("iloc_src", "root.comp1.liion.bei1.er1.iloc");
    model.component("comp1").physics("ts").feature("esc1").set("n", 1);
    model.component("comp1").physics("ts").feature("esc1").setIndex("Vib", -1, 0);
    model.component("comp1").physics("ts").create("init2", "init", 3);
    model.component("comp1").physics("ts").feature("init2").selection().named("geom1_mov2_dom");
    model.component("comp1").physics("ts").feature("init2").setIndex("initc", "cs0_Gr", 0);
    model.component("comp1").physics("ts").create("init3", "init", 3);
    model.component("comp1").physics("ts").feature("init3").selection().named("geom1_rot2_dom");
    model.component("comp1").physics("ts").feature("init3").setIndex("initc", "cs0_Si", 0);
    model.component("comp1").physics("ts").feature("init1").setIndex("initc", "cs0_pos", 0);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature().clear();
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().named("geom1_rot2_dom");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "s_unit_cell/3");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "s_gap/4");
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 1.3);
    model.component("comp1").mesh("mesh1").feature("size").set("hnarrow", 2.5);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").view("view1").set("transparency", false);
    model.component("comp1").view("view1").set("showgrid", false);

    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").set("expr", "liion.phis0_ec1");
    model.component("comp1").probe("var1").set("descr", "\u8fb9\u754c\u7535\u4f4d");
    model.component("comp1").probe("var1").set("descractive", true);

    model.study("std1").feature("time").set("tunit", "h");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.component("comp1").probe("var1").genResult("none");

    model.sol("sol1").runAll();

    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();
    model.result("pg1").set("showlegends", false);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("edges", false);
    model.result("pg2").set("showlegendstitle", true);
    model.result("pg2").set("legendpos", "bottom");
    model.result("pg2").create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("expr", "c/csmax_Gr");
    model.result("pg2").feature("vol1").create("sel1", "Selection");
    model.result("pg2").feature("vol1").feature("sel1").selection().named("geom1_mov2_dom");
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("vol2", "vol1");
    model.result("pg2").run();
    model.result("pg2").feature("vol2").set("expr", "c/csmax_Si");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg2").feature("vol2").set("colortable", "Prism");
    model.result("pg2").run();
    model.result("pg2").feature("vol2").feature("sel1").selection().named("geom1_rot2_dom");
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("vol3", "vol2");
    model.result("pg2").run();
    model.result("pg2").feature("vol3").set("expr", "c/csmax_pos");
    model.result("pg2").feature("vol3").set("colortable", "AuroraBorealis");
    model.result("pg2").run();
    model.result("pg2").feature("vol3").feature("sel1").selection().named("geom1_mov1_dom");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").set("expr", new String[]{"liion.Nposx", "liion.Nposy", "liion.Nposz"});
    model.result("pg2").feature("str1").set("descr", "\u6b63\u79bb\u5b50\u901a\u91cf");
    model.result("pg2").feature("str1").set("selnumber", 4);
    model.result("pg2").feature("str1").selection().set(59);
    model.result("pg2").feature("str1").set("pointtype", "arrow");
    model.result("pg2").feature("str1").set("arrowdistr", "equalinvtime");
    model.result("pg2").feature("str1").set("color", "white");
    model.result("pg2").run();
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "1");
    model.result("pg2").feature("surf1").set("coloring", "uniform");
    model.result("pg2").feature("surf1").set("color", "gray");
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().set(1, 3, 5, 56, 58, 61, 63, 84);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 2, 0);
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
    model.result().export("anim1").set("plotgroup", "pg2");
    model.result().export("anim1").set("framesel", "all");
    model.result().export("anim1").set("frametime", 0.5);
    model.result().export("anim1").run();

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study("std1").feature("cdi").setSolveFor("/physics/solid", true);
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);

    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics("solid").feature("lemm1").set("E_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").set("E", "E_pb");
    model.component("comp1").physics("solid").feature("lemm1").set("nu_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").set("nu", "nu_pb");
    model.component("comp1").physics("solid").feature("lemm1").set("rho_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").set("rho", "rho");
    model.component("comp1").physics("solid").create("lemm2", "LinearElasticModel", 3);
    model.component("comp1").physics("solid").feature("lemm2").selection().named("geom1_unisel1");
    model.component("comp1").physics("solid").feature("lemm2").set("minput_concentration", "c");
    model.component("comp1").physics("solid").feature("lemm2").create("ic1", "IntercalationStrain", 3);
    model.component("comp1").physics("solid").feature("lemm2").feature("ic1").set("minput_concentration", "c");
    model.component("comp1").physics("solid").create("lemm3", "LinearElasticModel", 3);
    model.component("comp1").physics("solid").feature("lemm3").selection().named("geom1_blk2_dom");
    model.component("comp1").physics("solid").feature("lemm3").set("E_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm3").set("E", "E_sep");
    model.component("comp1").physics("solid").feature("lemm3").set("nu_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm3").set("nu", "nu_sep");
    model.component("comp1").physics("solid").feature("lemm3").set("rho_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm3").set("rho", "rho");
    model.component("comp1").physics("solid").create("hmm1", "HyperelasticModel", 3);
    model.component("comp1").physics("solid").feature("hmm1").selection().set(1);
    model.component("comp1").physics("solid").feature("hmm1").set("IsotropicOption", "Enu");
    model.component("comp1").physics("solid").feature("hmm1").set("E_mat", "userdef");
    model.component("comp1").physics("solid").feature("hmm1").set("E", "E_pb");
    model.component("comp1").physics("solid").feature("hmm1").set("nu_mat", "userdef");
    model.component("comp1").physics("solid").feature("hmm1").set("nu", "nu_pb");
    model.component("comp1").physics("solid").feature("hmm1").set("rho_mat", "userdef");
    model.component("comp1").physics("solid").feature("hmm1").set("rho", "rho");
    model.component("comp1").physics("solid").create("roll1", "Roller", 2);
    model.component("comp1").physics("solid").feature("roll1").selection().named("geom1_comsel1");
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("geom1_unisel4");

    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", new String[]{"150[GPa]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"1000"});

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").selection().geom("geom1", 3);
    model.component("comp1").variable("var1").selection().named("geom1_blk2_dom");
    model.component("comp1").variable("var1").set("eps_solid", "(1-epsl_ref_sep)/(1+solid.evol)");
    model.component("comp1").variable("var1")
         .descr("eps_solid", "Solid (nonelectrolyte) volume fraction in deformed geometry");
    model.component("comp1").variable("var1").set("epsl", "max(1-eps_solid,0.01)");
    model.component("comp1").variable("var1").descr("epsl", "Electrolyte volume fraction in deformed geometry");
    model.component("comp1").variable().duplicate("var2", "var1");
    model.component("comp1").variable("var2").selection().named("geom1_dif1_dom");
    model.component("comp1").variable("var2").set("eps_solid", "(1-epsl_ref_pb)/(1+solid.evol)");

    model.component("comp1").physics("liion").feature("sep1").set("epsl", "epsl");
    model.component("comp1").physics("liion").feature("pcb1").set("epss", "eps_solid");
    model.component("comp1").physics("liion").feature("pcb1").set("epsl", "epsl");

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
    model.study("std2").feature("cdi").setSolveFor("/physics/ts", true);
    model.study("std2").feature("cdi").setSolveFor("/physics/liion", true);
    model.study("std2").feature("cdi").setSolveFor("/physics/solid", true);
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").set("plotgroup", "Default");
    model.study("std2").feature("time").set("ftplistmethod", "manual");
    model.study("std2").feature("time").set("initialtime", "0");
    model.study("std2").feature("time").set("solnum", "auto");
    model.study("std2").feature("time").set("notsolnum", "auto");
    model.study("std2").feature("time").set("outputmap", new String[]{});
    model.study("std2").feature("time").setSolveFor("/physics/ts", true);
    model.study("std2").feature("time").setSolveFor("/physics/liion", true);
    model.study("std2").feature("time").setSolveFor("/physics/solid", true);

    model.component("comp1").probe().duplicate("var2", "var1");

    model.result().table().create("tbl2", "Table");

    model.component("comp1").probe("var2").set("table", "tbl2");
    model.component("comp1").probe().create("bnd1", "Boundary");
    model.component("comp1").probe("bnd1").set("intsurface", true);
    model.component("comp1").probe("bnd1").selection().named("geom1_boxsel3");
    model.component("comp1").probe("bnd1").set("expr", "solid.stn");
    model.component("comp1").probe("bnd1").set("descr", "\u6b63\u5e94\u529b");
    model.component("comp1").probe("bnd1").set("expr", "-solid.stn");
    model.component("comp1").probe("bnd1").set("unit", "MPa");

    model.result().table().create("tbl3", "Table");

    model.component("comp1").probe("bnd1").set("table", "tbl3");
    model.component("comp1").probe("bnd1").set("window", "window3");
    model.component("comp1").probe("bnd1").set("windowtitle", "\u63a2\u9488\u56fe\u201c3\u201d");

    model.study("std1").feature("time").setSolveFor("/physics/solid", false);
    model.study("std1").feature("time").set("probesel", "manual");
    model.study("std1").feature("time").set("probes", new String[]{"var1"});
    model.study("std2").feature("time").set("tunit", "h");
    model.study("std2").feature("time").set("probesel", "manual");
    model.study("std2").feature("time").set("probes", new String[]{"var2", "bnd1"});
    model.study("std2").feature("time").set("autoremesh", true);
    model.study("std2").showAutoSequences("all");

    model.sol("sol3").feature("t1").feature("arDef").set("stopexpr", "comp1.spatial.relVolMin");
    model.sol("sol3").feature("t1").feature("arDef").set("stopval", "0.5");
    model.sol("sol3").feature("t1").feature("arDef").set("consistentremesh", true);
    model.sol("sol3").feature("t1").feature("se1").feature("ss2").set("subjtech", "once");
    model.sol("sol3").feature("t1").feature("se1").feature("ss3").set("subjtech", "once");

    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol5");
    model.sol("sol5").label("\u91cd\u65b0\u5212\u5206\u7f51\u683c\u540e\u7684\u89e3 1");
    model.sol("sol5").study("std2");
    model.sol("sol3").feature("t1").feature("arDef").set("tadapsol", "sol5");

    model.component("comp1").probe("var2").genResult("none");
    model.component("comp1").probe("bnd1").genResult("none");

    model.sol("sol3").runAll();

    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();
    model.result("pg1").set("showlegends", true);
    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();
    model.result("pg1").feature("tblp1").set("legendmethod", "manual");
    model.result("pg1").feature("tblp1").setIndex("legends", "Without deformation", 0);
    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();
    model.result("pg1").feature("tblp2").set("legendmethod", "manual");
    model.result("pg1").feature("tblp2").setIndex("legends", "With deformation", 0);
    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();
    model.result("pg1").set("legendpos", "lowerright");
    model.result("pg1").set("window", "window2");
    model.result("pg1").set("windowtitle", "\u63a2\u9488\u56fe\u201c2\u201d");
    model.result("pg1").run();
    model.result().dataset("dset6").set("frametype", "spatial");
    model.result("pg2").run();
    model.result("pg2").set("data", "dset6");
    model.result("pg2").set("looplevel", new String[]{"interp"});
    model.result("pg2").run();
    model.result("pg2").set("looplevel", new String[]{"last"});
    model.result("pg2").run();
    model.result("pg2").set("edges", true);
    model.result("pg2").run();
    model.result("pg2").set("edges", false);
    model.result("pg3").set("window", "window3");
    model.result("pg3").set("windowtitle", "\u63a2\u9488\u56fe\u201c3\u201d");
    model.result("pg3").run();
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("showlegends", false);
    model.result("pg3").set("window", "window3");
    model.result("pg3").set("windowtitle", "\u63a2\u9488\u56fe\u201c3\u201d");
    model.result("pg3").run();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("looplevelinput", "interp");
    model.result().export("anim1").set("interp", "range(0,0.1,1)");
    model.result().export("anim1").run();

    model.title("\u5f02\u8d28\u9502\u79bb\u5b50\u7535\u6c60");

    model
         .description("\u672c\u6a21\u578b\u63cf\u8ff0\u5982\u4f55\u4f7f\u7528\u7406\u60f3\u5316\u7684\u5f02\u8d28\u4e09\u7ef4\u51e0\u4f55\u6a21\u62df\u9502\u79bb\u5b50\u7535\u6c60\u5355\u5143\u7684\u884c\u4e3a\u3002\n\n\u4e0e\u63cf\u8ff0\u591a\u5b54\u7535\u6781\u7684\u5178\u578b\u5747\u8d28\u5316\u65b9\u6cd5\u4e0d\u540c\uff0c\u5f02\u8d28\u6a21\u578b\u53ef\u5b9a\u4e49\u7535\u6781\u9897\u7c92\u7684\u5b9e\u9645\u5f62\u72b6\u3002\n\n\u8be5\u6a21\u578b\u6db5\u76d6\u4e86\u7535\u89e3\u8d28\u79bb\u5b50\u4f20\u8f93\u3001\u7535\u6781\u52a8\u529b\u5b66\u4ee5\u53ca\u9502\u539f\u5b50\u5728\u56fa\u4f53\u7535\u6781\u9897\u7c92\u4e2d\u7684\u6269\u6563\u8fc7\u7a0b\u3002\u8d1f\u6781\u7531\u77f3\u58a8\u548c\u7845\u7684\u6df7\u5408\u6750\u6599\u6784\u6210\u3002\n\n\u672c\u6559\u7a0b\u7684\u7b2c\u4e8c\u90e8\u5206\u63a2\u8ba8\u4e86\u5728\u6a21\u578b\u4e2d\u5f15\u5165\u7ed3\u6784\u529b\u5b66\u540e\uff0c\u5bf9\u5145\u7535\u7535\u538b\u7279\u6027\u7684\u5f71\u54cd\uff0c\u5305\u542b\u7531\u4e8e\u5d4c\u9502\u5e94\u53d8\u5f15\u8d77\u7684\u5168\u8026\u5408\u51e0\u4f55\u53d8\u5f62\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("heterogeneous_lib.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
