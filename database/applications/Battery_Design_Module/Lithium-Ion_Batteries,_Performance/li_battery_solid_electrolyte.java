/*
 * li_battery_solid_electrolyte.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:24 by COMSOL 6.3.0.290. */
public class li_battery_solid_electrolyte {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Lithium-Ion_Batteries,_Performance");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("liion", "LithiumIonBatteryMPH", "geom1");
    model.component("comp1").physics("liion").prop("ElectrolyteType").set("ElectrolyteType", "SingleIonConductor");
    model.component("comp1").physics("liion").feature("sep1").set("epsl", new String[]{"1"});

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
    model.param().set("L_pos", "65[um]", "\u6b63\u6781\u539a\u5ea6");
    model.param().set("L_neg", "85[um]", "\u8d1f\u6781\u539a\u5ea6");
    model.param().set("L_electrolyte", "45[um]", "\u7535\u89e3\u8d28\u533a\u539a\u5ea6");
    model.param().set("epss_pos", "0.44", "\u6b63\u6781\u7684\u56fa\u76f8\u4f53\u79ef\u5206\u6570");
    model.param().set("epss_neg", "0.42", "\u8d1f\u6781\u7684\u56fa\u76f8\u4f53\u79ef\u5206\u6570");
    model.param().set("rp", "5e-7[m]", "\u6b63\u8d1f\u6781\u7684\u9897\u7c92\u5927\u5c0f");
    model.param().set("sigmas_neg", "100[S/m]", "\u8d1f\u6781\u7535\u5bfc\u7387");
    model.param().set("sigmas_pos", "1.13[mS/cm]", "\u6b63\u6781\u7535\u5bfc\u7387");
    model.param().set("sigmal", "0.02[S/m]", "\u7535\u89e3\u8d28\u7535\u5bfc\u7387");
    model.param()
         .set("i0ref_neg", "3.04e-2[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u8d1f\u6781");
    model.param()
         .set("i0ref_pos", "5.43e-2[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6b63\u6781");
    model.param().set("SOC_0", "1", "\u521d\u59cb SOC");
    model.param().set("C_rate", "1", "\u5145\u653e\u7535\u500d\u7387\u53c2\u6570");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").set("specify", "len");
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_neg", 0);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_electrolyte", 1);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_pos", 2);
    model.component("comp1").geom("geom1").run("i1");

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

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("int2", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ElectrodePotential", "ElectrodePotential", "Equilibrium potential");
    model.component("comp1").material("mat2").propertyGroup()
         .create("OperationalSOC", "OperationalSOC", "Operational electrode state of charge");
    model.component("comp1").material("mat2").propertyGroup().create("ic", "ic", "Intercalation strain");
    model.component("comp1").material("mat2").propertyGroup("ic").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("EquilibriumConcentration", "EquilibriumConcentration", "Equilibrium concentration");
    model.component("comp1").material("mat2").propertyGroup()
         .create("EquilibriumPotentialWithDOCInput", "EquilibriumPotentialWithDOCInput", "Equilibrium potential (using degree of conversion as model input)");
    model.component("comp1").material("mat2").propertyGroup()
         .create("EquilibriumDegreeOfConversion", "EquilibriumDegreeOfConversion", "Equilibrium degree of conversion");
    model.component("comp1").material("mat2").label("LCO, LiCoO2 (Positive, Li-ion Battery)");
    model.component("comp1").material("mat2").comments("\n");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("funcname", "Eeq");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0.43", "4.3"}, 
         {"0.44", "4.281"}, 
         {"0.48", "4.22"}, 
         {"0.52", "4.164"}, 
         {"0.53", "4.153"}, 
         {"0.54", "4.138"}, 
         {"0.55", "4.128"}, 
         {"0.56", "4.116"}, 
         {"0.57", "4.103"}, 
         {"0.58", "4.09"}, 
         {"0.59", "4.0796"}, 
         {"0.6", "4.068"}, 
         {"0.61", "4.057"}, 
         {"0.62", "4.0456"}, 
         {"0.63", "4.036"}, 
         {"0.64", "4.027"}, 
         {"0.65", "4.01789"}, 
         {"0.66", "4.00836937"}, 
         {"0.67", "3.9986331"}, 
         {"0.68", "3.989454406"}, 
         {"0.69", "3.980817079"}, 
         {"0.7", "3.972704909"}, 
         {"0.71", "3.965101686"}, 
         {"0.72", "3.9579912"}, 
         {"0.73", "3.951357242"}, 
         {"0.74", "3.945183603"}, 
         {"0.75", "3.939454073"}, 
         {"0.76", "3.934152442"}, 
         {"0.77", "3.9292625"}, 
         {"0.78", "3.924768038"}, 
         {"0.79", "3.920652847"}, 
         {"0.8", "3.916900717"}, 
         {"0.81", "3.913495438"}, 
         {"0.82", "3.9104208"}, 
         {"0.83", "3.907660594"}, 
         {"0.84", "3.905198611"}, 
         {"0.85", "3.903018641"}, 
         {"0.86", "3.901104473"}, 
         {"0.87", "3.899439898"}, 
         {"0.88", "3.898008701"}, 
         {"0.89", "3.896794651"}, 
         {"0.9", "3.895781427"}, 
         {"0.91", "3.894952278"}, 
         {"0.92", "3.894288329"}, 
         {"0.93", "3.89376025"}, 
         {"0.94", "3.89328727"}, 
         {"0.95", "3.892535492"}, 
         {"0.96", "3.889926117"}, 
         {"0.97", "3.877773567"}, 
         {"0.98", "3.818815742"}, 
         {"0.99", "3.541235608"}, 
         {"1", "2.422342044"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("fununit", new String[]{"V"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("argunit", new String[]{""});
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("defineinv", true);
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("funcinvname", "Eeq_inv");
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").set("funcname", "dEeqdT");
    model.component("comp1").material("mat2").propertyGroup("def").func("int2")
         .set("table", new String[][]{{"0.545", "-0.33e-3"}, 
         {"0.55", "-0.33e-3"}, 
         {"0.56", "-0.562e-3"}, 
         {"0.575", "-0.51e-3"}, 
         {"0.63", "-0.09e-3"}, 
         {"0.65", "-0.2e-3"}, 
         {"0.663", "-0.22e-3"}, 
         {"0.685", "-0.25e-3"}, 
         {"0.7", "-0.274e-3"}, 
         {"0.72", "-0.29e-3"}, 
         {"0.744", "-0.303e-3"}, 
         {"0.748", "-0.324e-3"}, 
         {"0.77", "-0.357e-3"}, 
         {"0.791", "-0.39e-3"}, 
         {"0.82", "-0.44e-3"}, 
         {"0.846", "-0.518e-3"}, 
         {"0.87", "-0.561e-3"}, 
         {"0.885", "-0.58e-3"}, 
         {"0.92", "-0.59e-3"}, 
         {"0.945", "-0.60e-3"}, 
         {"0.968", "-0.61e-3"}, 
         {"0.99", "-0.64e-3"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").set("interp", "piecewisecubic");
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").set("fununit", new String[]{"V/K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int2").set("argunit", new String[]{""});
    model.component("comp1").material("mat2").propertyGroup("def").set("poissonsratio", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("youngsmodulus", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("poissonsratio", "0.24");
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("poissonsratio", "E. Cheng, N. Taylor, J. Wolfenstine and J. Sakamoto, Elastic properties of lithium cobalt oxide (LiCoO2), Journal of Asian Ceramic Societies, 5:2,\n113-117, (2017), DOI: 10.1016/j.jascer.2017.03.001");
    model.component("comp1").material("mat2").propertyGroup("def").set("youngsmodulus", "191[GPa]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("youngsmodulus", "E. Cheng, N. Taylor, J. Wolfenstine and J. Sakamoto, Elastic properties of lithium cobalt oxide (LiCoO2), Journal of Asian Ceramic Societies, 5:2,\n113-117, (2017), DOI: 10.1016/j.jascer.2017.03.001");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"1.3e-5[1/K]", "0", "0", "0", "1.3e-5[1/K]", "0", "0", "0", "1.3e-5[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "E. Cheng, N. Taylor, J. Wolfenstine and J. Sakamoto, Elastic properties of lithium cobalt oxide (LiCoO2), Journal of Asian Ceramic Societies, 5:2,\n113-117, (2017), DOI: 10.1016/j.jascer.2017.03.001");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"1.13[mS/cm]", "0", "0", "0", "1.13[mS/cm]", "0", "0", "0", "1.13[mS/cm]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("electricconductivity", "M. Tang, P. Albertus, J. Harb, and J. Newman, \"Two-Dimensional Modeling of Lithium Deposition during Cell Charging\", J. Electrochemical Society, vol. 156, p. A390, 2009");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("diffusion", new String[]{"5e-13[m^2/s]", "0", "0", "0", "5e-13[m^2/s]", "0", "0", "0", "5e-13[m^2/s]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("diffusion", "D. Stephenson, E. Hartman, J. Harb, D. Wheeler, \"Modeling of Particle-Particle Interactions in Porous Cathodes for Lithium-Ion Batteries\", J. Electrochemical Society, vol. 154, p. A1146, 2007");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "5000[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("density", "N. Nitta, F. Wu, J. Tae Lee, and G. Yushin, Materials Today, Volume 18, Number 5, June 2015");
    model.component("comp1").material("mat2").propertyGroup("def").set("csmax", "56250[mol/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def").descr("csmax", "");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").identifier("eeq");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .set("Eeq", "def.Eeq(doc)+def.dEeqdT(doc)*(T-298[K])");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .setPropertyInfo("Eeq", "M. Tang, P. Albertus, J. Harb, and J. Newman, \"Two-Dimensional Modeling of Lithium Deposition during Cell Charging\", J. Electrochemical Society, vol. 156, p. A390, 2009");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").set("dEeqdT", "def.dEeqdT(doc)");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .setPropertyInfo("dEeqdT", "K. E. Thomas, and J. Newman, \u201cHeats of mixing and of entropy in porous insertion electrodes,\u201d J. Power Sources., vol. 119-121, p. 844, 2003.");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").set("cEeqref", "def.csmax");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .setPropertyInfo("cEeqref", "M. Tang, P. Albertus, J. Harb, and J. Newman, \"Two-Dimensional Modeling of Lithium Deposition during Cell Charging\", J. Electrochemical Society, vol. 156, p. A390, 2009");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").set("doc", "c/cEeqref");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .descr("doc", "Degree of conversion (state of lithiation)");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").addInput("concentration");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC")
         .label("Operational electrode state of charge");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").identifier("opsoc");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").set("socmax", "def.Eeq_inv(E_min)");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").set("socmin", "def.Eeq_inv(E_max)");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").set("E_max", "4.2[V]");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").set("E_min", "3.8[V]");
    model.component("comp1").material("mat2").propertyGroup("ic").label("Intercalation strain");
    model.component("comp1").material("mat2").propertyGroup("ic").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("ic").func("int1").set("funcname", "dVOLdSOL");
    model.component("comp1").material("mat2").propertyGroup("ic").func("int1")
         .set("table", new String[][]{{"1", "0"}, 
         {"0.9843643779741672", "0.014846235418874976"}, 
         {"0.9598912304554724", "0.15058324496288344"}, 
         {"0.9340584636301835", "0.26935312831389124"}, 
         {"0.9095853161114887", "0.4050901378578997"}, 
         {"0.8905506458191705", "0.5577942735949093"}, 
         {"0.8701563562202583", "0.6765641569459167"}, 
         {"0.8429639700883752", "0.795334040296924"}, 
         {"0.8212100611828688", "0.9480381760339334"}, 
         {"0.7940176750509857", "1.1177094379639443"}, 
         {"0.7749830047586675", "1.2704135737009536"}, 
         {"0.7545887151597552", "1.4400848356309641"}, 
         {"0.7314751869476546", "1.6097560975609748"}, 
         {"0.7070020394289598", "1.7794273594909853"}, 
         {"0.6838885112168592", "1.9321314952279947"}, 
         {"0.6621346023113528", "2.0509013785790025"}, 
         {"0.6403806934058464", "2.1357370095440076"}, 
         {"0.6145479265805573", "2.186638388123011"}, 
         {"0.5927940176750509", "2.186638388123011"}, 
         {"0.5710401087695445", "2.1187698833510065"}, 
         {"0.5506458191706322", "1.983032873806998"}, 
         {"0.5261726716519374", "1.7624602332979842"}, 
         {"0.5003399048266485", "1.2534464475079525"}, 
         {"0.4772263766145478", "0.6765641569459167"}, 
         {"0.46091094493541795", "-0.10392364793213194"}, 
         {"0.4296397008837525", "-1.0540827147401917"}});
    model.component("comp1").material("mat2").propertyGroup("ic").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("ic").func("int1").set("fununit", new String[]{"%"});
    model.component("comp1").material("mat2").propertyGroup("ic").func("int1").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("ic").identifier("is");
    model.component("comp1").material("mat2").propertyGroup("ic").set("dvol", "dVOLdSOL(c/eeq.cEeqref)");
    model.component("comp1").material("mat2").propertyGroup("ic")
         .setPropertyInfo("dvol", "R. Koerver and others, \u201cChemo-mechanical expansion of lithium electrode materials \u2014 on the route to mechanically optimized all-solid-state batteries,\u201d Energy Environ. Sci., vol. 11, pp. 2142\u20132158, 201");
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
    model.component("comp1").material("mat1").selection().set(1);
    model.component("comp1").material("mat2").selection().set(3);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "PositiveCC");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(4);
    model.component("comp1").cpl().duplicate("intop2", "intop1");
    model.component("comp1").cpl("intop2").set("opname", "NegativeCC");
    model.component("comp1").cpl("intop2").selection().set(1);

    model.component("comp1").physics("liion").feature("sep1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("liion").feature("sep1")
         .set("sigmal", new String[]{"sigmal", "0", "0", "0", "sigmal", "0", "0", "0", "sigmal"});
    model.component("comp1").physics("liion").create("pce1", "PorousElectrode", 1);
    model.component("comp1").physics("liion").feature("pce1").selection().set(1);
    model.component("comp1").physics("liion").feature("pce1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce1")
         .set("sigmal", new String[]{"sigmal", "0", "0", "0", "sigmal", "0", "0", "0", "sigmal"});
    model.component("comp1").physics("liion").feature("pce1")
         .set("sigma", new String[]{"sigmas_neg", "0", "0", "0", "sigmas_neg", "0", "0", "0", "sigmas_neg"});
    model.component("comp1").physics("liion").feature("pce1").set("epss", "epss_neg");
    model.component("comp1").physics("liion").feature("pce1").feature("pin1").set("rp", "rp");
    model.component("comp1").physics("liion").feature("pce1").feature("per1").set("i0_ref", "i0ref_neg");
    model.component("comp1").physics("liion").create("pce2", "PorousElectrode", 1);
    model.component("comp1").physics("liion").feature("pce2").selection().set(3);
    model.component("comp1").physics("liion").feature("pce2").set("sigmal_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce2")
         .set("sigmal", new String[]{"sigmal", "0", "0", "0", "sigmal", "0", "0", "0", "sigmal"});
    model.component("comp1").physics("liion").feature("pce2")
         .set("sigma", new String[]{"sigmas_pos", "0", "0", "0", "sigmas_pos", "0", "0", "0", "sigmas_pos"});
    model.component("comp1").physics("liion").feature("pce2").set("epss", "epss_pos");
    model.component("comp1").physics("liion").feature("pce2").feature("pin1").set("rp", "rp");
    model.component("comp1").physics("liion").feature("pce2").feature("per1").set("i0_ref", "i0ref_pos");
    model.component("comp1").physics("liion").prop("CellSettings").set("CellSOCandInitialChargeInventory", true);
    model.component("comp1").physics("liion").feature("socicd1").set("SOC_init", "SOC_0");
    model.component("comp1").physics("liion").feature("socicd1").set("AddFormationLoss", false);
    model.component("comp1").physics("liion").feature("socicd1").feature("neges1").selection().set(1);
    model.component("comp1").physics("liion").feature("socicd1").feature("poses1").selection().set(3);
    model.component("comp1").physics("liion").create("egnd1", "ElectricGround", 0);
    model.component("comp1").physics("liion").feature("egnd1").selection().set(1);
    model.component("comp1").physics("liion").create("ec1", "ElectrodeCurrent", 0);
    model.component("comp1").physics("liion").feature("ec1").selection().set(4);
    model.component("comp1").physics("liion").feature("ec1").set("TotalCurrentType", "CrateMultiple");
    model.component("comp1").physics("liion").feature("ec1").set("Crate", "-C_rate");
    model.component("comp1").physics("liion").feature("ec1").set("phis0init", "4[V]");

    model.study("std1").feature("time").set("tlist", "0 4000");
    model.study("std1").feature("time").set("useparam", true);
    model.study("std1").feature("time").set("sweeptype", "filled");
    model.study("std1").feature("time").setIndex("pname", "L_pos", 0);
    model.study("std1").feature("time").setIndex("plistarr", "", 0);
    model.study("std1").feature("time").setIndex("punit", "m", 0);
    model.study("std1").feature("time").setIndex("pname", "L_pos", 0);
    model.study("std1").feature("time").setIndex("plistarr", "", 0);
    model.study("std1").feature("time").setIndex("punit", "m", 0);
    model.study("std1").feature("time").setIndex("pname", "C_rate", 0);
    model.study("std1").feature("time").setIndex("plistarr", "1 2 4", 0);
    model.study("std1").feature("time").setIndex("pname", "L_pos", 1);
    model.study("std1").feature("time").setIndex("plistarr", "", 1);
    model.study("std1").feature("time").setIndex("punit", "m", 1);
    model.study("std1").feature("time").setIndex("pname", "L_pos", 1);
    model.study("std1").feature("time").setIndex("plistarr", "", 1);
    model.study("std1").feature("time").setIndex("punit", "m", 1);
    model.study("std1").feature("time").setIndex("pname", "sigmal", 1);
    model.study("std1").feature("time").setIndex("plistarr", "0.02 0.05 0.5 1", 1);
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
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "comp1.PositiveCC(comp1.phis)<2.7", 0);
    model.sol("sol1").feature("t1").feature("st1").set("storestopcondsol", "stepbefore");
    model.sol("sol1").feature("t1").feature("st1").set("stopcondwarn", false);

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u7535\u6c60\u7535\u538b\uff1asigmal = 0.02 S/m");
    model.result("pg1").setIndex("looplevelinput", "first", 1);
    model.result("pg1").create("ptgr1", "PointGraph");
    model.result("pg1").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg1").feature("ptgr1").set("linewidth", "preference");
    model.result("pg1").feature("ptgr1").selection().set(4);
    model.result("pg1").feature("ptgr1").set("expr", "phis");
    model.result("pg1").feature("ptgr1").set("descr", "\u7535\u52bf");
    model.result("pg1").feature("ptgr1").set("legend", true);
    model.result("pg1").feature("ptgr1").set("legendmethod", "evaluated");
    model.result("pg1").feature("ptgr1").set("legendpattern", "eval(C_rate) C");
    model.result("pg1").run();
    model.result("pg1").set("titletype", "manual");
    model.result("pg1")
         .set("title", "\\sigma<sub>l</sub> = 0.02 S/m \u65f6\u7684\u7535\u6c60\u7535\u538b\u66f2\u7ebf");
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u6c60\u7535\u538b\uff1asigmal = 1 S/m");
    model.result("pg2").setIndex("looplevelinput", "last", 1);
    model.result("pg2").set("title", "\\sigma<sub>l</sub> = 1 S/m \u65f6\u7684\u7535\u6c60\u7535\u538b\u66f2\u7ebf");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u7535\u6c60\u7535\u538b\uff1a1 C");
    model.result("pg3").setIndex("looplevelinput", "first", 2);
    model.result("pg3").create("ptgr1", "PointGraph");
    model.result("pg3").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr1").set("linewidth", "preference");
    model.result("pg3").feature("ptgr1").selection().set(4);
    model.result("pg3").feature("ptgr1").set("expr", "phis");
    model.result("pg3").feature("ptgr1").set("descr", "\u7535\u52bf");
    model.result("pg3").feature("ptgr1").set("legend", true);
    model.result("pg3").feature("ptgr1").set("legendmethod", "evaluated");
    model.result("pg3").feature("ptgr1").set("legendpattern", "\\sigma<sub>l</sub> = eval(sigmal) S/m");
    model.result("pg3").run();
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "1 C \u65f6\u7684\u7535\u6c60\u7535\u538b\u66f2\u7ebf");
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u7535\u6c60\u7535\u538b\uff1a4 C");
    model.result("pg4").setIndex("looplevelinput", "last", 2);
    model.result("pg4").set("title", "4 C \u65f6\u7684\u7535\u6c60\u7535\u538b\u66f2\u7ebf");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u7535\u89e3\u8d28\u7535\u4f4d\u964d\uff1a1 C \u548c sigmal = 0.02 S/m");
    model.result("pg5").setIndex("looplevelinput", "first", 2);
    model.result("pg5").setIndex("looplevelinput", "first", 1);
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").selection().all();
    model.result("pg5").feature("lngr1").set("expr", "phil-NegativeCC(phil)");
    model.result("pg5").run();
    model.result("pg5").set("titletype", "manual");
    model.result("pg5")
         .set("title", "1 C \u548c \\sigma<sub>l</sub> = 0.02 S/m \u65f6\u7684\u7535\u89e3\u8d28\u7535\u4f4d\u964d");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u7535\u6c60\u957f\u5ea6 (m)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u7535\u89e3\u8d28\u7535\u4f4d\u964d (V)");
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u7535\u89e3\u8d28\u7535\u4f4d\u964d\uff1a1 C \u548c sigmal = 1 S/m");
    model.result("pg6").setIndex("looplevelinput", "last", 1);
    model.result("pg6")
         .set("title", "1 C \u548c \\sigma<sub>l</sub> = 1 S/m \u65f6\u7684\u7535\u89e3\u8d28\u7535\u4f4d\u964d");
    model.result("pg6").run();

    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup()
         .create("ElectrolyteConductivity", "ElectrolyteConductivity", "Electrolyte conductivity");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup()
         .create("SpeciesProperties", "SpeciesProperties", "Species properties");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").func()
         .create("int2", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup()
         .create("ElectrolyteSaltConcentration", "ElectrolyteSaltConcentration", "Electrolyte salt concentration");
    model.component("comp1").material("mat3").label("LiPF6 in 3:7 EC:EMC (Liquid, Li-ion Battery)");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("funcname", "DL_int1");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"200", "3.9e-10/(1-200*59e-6)"}, 
         {"500", "4.12e-10/(1-500*59e-6)"}, 
         {"800", "4e-10/(1-800*59e-6)"}, 
         {"1000", "3.8e-10/(1-1000*59e-6)"}, 
         {"1200", "3.50e-10/(1-1200*59e-6)"}, 
         {"1600", "2.68e-10/(1-1600*59e-6)"}, 
         {"2000", "1.9e-10/(1-2000*59e-6)"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("interp", "piecewisecubic");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1")
         .set("fununit", new String[]{"m^2/s"});
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("argunit", new String[]{""});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("diffusion", new String[]{"DL_int1(c/1[mol/m^3])*exp(16500/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))", "0", "0", "0", "DL_int1(c/1[mol/m^3])*exp(16500/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))", "0", "0", "0", "DL_int1(c/1[mol/m^3])*exp(16500/8.314*(1/(T_ref/1[K])-1/(T2/1[K])))"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("diffusion", "T. G. Zavalis, M. Behm, and G. Lindbergh, \"Investigations of Short-Circuit Scenarios in a Lithium-Ion Battery Cell,\" J. Electrochem. Soc., vol. 159, p. A848, 2012.");
    model.component("comp1").material("mat3").propertyGroup("def").set("T_ref", "298[K]");
    model.component("comp1").material("mat3").propertyGroup("def").descr("T_ref", "");
    model.component("comp1").material("mat3").propertyGroup("def").set("T2", "min(393.15,max(T,223.15))");
    model.component("comp1").material("mat3").propertyGroup("def").descr("T2", "");
    model.component("comp1").material("mat3").propertyGroup("def").addInput("concentration");
    model.component("comp1").material("mat3").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity")
         .label("Electrolyte conductivity");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("funcname", "sigmal_int1");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("table", new String[][]{{"0", "1e-6"}, 
         {"200", "0.455"}, 
         {"500", "0.783"}, 
         {"800", "0.935"}, 
         {"1000", "0.95"}, 
         {"1200", "0.927"}, 
         {"1600", "0.78"}, 
         {"2000", "0.60"}, 
         {"2200", "0.515"}});
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("interp", "piecewisecubic");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("fununit", new String[]{"S/m"});
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").func("int1")
         .set("argunit", new String[]{""});
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity")
         .set("sigmal", new String[]{"sigmal_int1(c/1[mol/m^3])*exp(4000/8.314*(1/(T_ref2/1[K])-1/(T3/1[K])))", "0", "0", "0", "sigmal_int1(c/1[mol/m^3])*exp(4000/8.314*(1/(T_ref2/1[K])-1/(T3/1[K])))", "0", "0", "0", "sigmal_int1(c/1[mol/m^3])*exp(4000/8.314*(1/(T_ref2/1[K])-1/(T3/1[K])))"});
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity")
         .setPropertyInfo("sigmal", "T. G. Zavalis, M. Behm, and G. Lindbergh, \"Investigations of Short-Circuit Scenarios in a Lithium-Ion Battery Cell,\" J. Electrochem. Soc., vol. 159, p. A848, 2012.");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").set("T_ref2", "298[K]");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").descr("T_ref2", "");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity")
         .set("T3", "min(393.15,max(T,223.15))");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").descr("T3", "");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").addInput("concentration");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteConductivity").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").label("Species properties");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").func("int1")
         .set("funcname", "transpNm_int1");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").func("int1")
         .set("table", new String[][]{{"200", "0.37"}, 
         {"500", "0.322"}, 
         {"800", "0.27"}, 
         {"1000", "0.251"}, 
         {"1200", "0.248"}, 
         {"1600", "0.236"}, 
         {"2000", "0.11"}});
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").func("int1")
         .set("interp", "piecewisecubic");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").func("int1")
         .set("fununit", new String[]{""});
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").func("int1")
         .set("argunit", new String[]{""});
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").func("int2")
         .label("Interpolation 2");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").func("int2")
         .set("funcname", "actdep_int1");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").func("int2")
         .set("table", new String[][]{{"200", "0"}, 
         {"500", "0.29"}, 
         {"800", "0.695"}, 
         {"1000", "1"}, 
         {"1200", "1.32"}, 
         {"1600", "2.07"}, 
         {"2000", "2.50"}});
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").func("int2")
         .set("interp", "piecewisecubic");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").func("int2")
         .set("fununit", new String[]{""});
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").func("int2")
         .set("argunit", new String[]{""});
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties")
         .set("transpNum", "transpNm_int1(c/1[mol/m^3])");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties")
         .setPropertyInfo("transpNum", "A. Nyman, M. Behm, and G. Lindbergh, \u201cElectrochemical characterisation and modelling of the mass transport phenomena in LiPF6-EC-EMC,\u201d Electrochim. Acta, vol. 53, p. 6356, 2008.");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties")
         .set("fcl", "actdep_int1(c/1[mol/m^3])*exp(-1000/8.314*(1/(T_ref3/1[K])-1/(T4/1[K])))");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties")
         .setPropertyInfo("fcl", "T. G. Zavalis, M. Behm, and G. Lindbergh, \"Investigations of Short-Circuit Scenarios in a Lithium-Ion Battery Cell,\" J. Electrochem. Soc., vol. 159, p. A848, 2012.");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties")
         .set("T4", "min(393.15,max(T,223.15))");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").descr("T4", "");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").set("T_ref3", "298[K]");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").descr("T_ref3", "");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").addInput("concentration");
    model.component("comp1").material("mat3").propertyGroup("SpeciesProperties").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteSaltConcentration")
         .label("Electrolyte salt concentration");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteSaltConcentration").identifier("cElsalt");
    model.component("comp1").material("mat3").propertyGroup("ElectrolyteSaltConcentration")
         .set("cElsalt", "1200[mol/m^3]");
    model.component("comp1").material("mat3").selection().set(2);

    model.component("comp1").physics("liion").prop("ElectrolyteType")
         .set("ElectrolyteType", "Binary11LiquidElectrolyte");
    model.component("comp1").physics("liion").feature("sep1").set("sigmal_mat", "from_mat");
    model.component("comp1").physics("liion").feature("pce1").set("ElectrolyteMaterial", "mat3");
    model.component("comp1").physics("liion").feature("pce1").set("sigmal_mat", "from_mat");
    model.component("comp1").physics("liion").feature("pce2").set("ElectrolyteMaterial", "mat3");
    model.component("comp1").physics("liion").feature("pce2").set("sigmal_mat", "from_mat");

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
    model.study("std2").feature("time").set("tlist", "0 4000");
    model.study("std2").feature("time").set("useparam", true);
    model.study("std2").feature("time").setIndex("pname", "L_pos", 0);
    model.study("std2").feature("time").setIndex("plistarr", "", 0);
    model.study("std2").feature("time").setIndex("punit", "m", 0);
    model.study("std2").feature("time").setIndex("pname", "L_pos", 0);
    model.study("std2").feature("time").setIndex("plistarr", "", 0);
    model.study("std2").feature("time").setIndex("punit", "m", 0);
    model.study("std2").feature("time").setIndex("pname", "C_rate", 0);
    model.study("std2").feature("time").setIndex("plistarr", "1 2 4", 0);
    model.study("std2").showAutoSequences("all");

    model.sol("sol3").feature("t1").set("tout", "tsteps");
    model.sol("sol3").feature("t1").create("st1", "StopCondition");
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol3").feature("t1").feature("st1").setIndex("stopcondarr", "comp1.PositiveCC(comp1.phis)<2.7", 0);
    model.sol("sol3").feature("t1").feature("st1").set("storestopcondsol", "stepbefore");
    model.sol("sol3").feature("t1").feature("st1").set("stopcondwarn", false);

    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u7535\u6c60\u7535\u538b\uff1a\u5355\u79bb\u5b50  vs. \u4e8c\u5143\u79bb\u5b50");
    model.result("pg7").set("data", "none");
    model.result("pg7").create("ptgr1", "PointGraph");
    model.result("pg7").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg7").feature("ptgr1").set("linewidth", "preference");
    model.result("pg7").feature("ptgr1").set("data", "dset1");
    model.result("pg7").feature("ptgr1").setIndex("looplevelinput", "last", 1);
    model.result("pg7").feature("ptgr1").selection().set(4);
    model.result("pg7").feature("ptgr1").set("expr", "phis");
    model.result("pg7").feature("ptgr1").set("descr", "\u7535\u52bf");
    model.result("pg7").feature("ptgr1").set("xdatasolnumtype", "level1");
    model.result("pg7").feature("ptgr1").set("legend", true);
    model.result("pg7").feature("ptgr1").set("legendmethod", "evaluated");
    model.result("pg7").feature("ptgr1").set("legendpattern", "eval(C_rate) C\uff0c\u5355\u79bb\u5b50");
    model.result("pg7").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg7").run();
    model.result("pg7").feature("ptgr2").set("data", "dset3");
    model.result("pg7").feature("ptgr2").set("linestyle", "dashed");
    model.result("pg7").feature("ptgr2").set("linecolor", "cyclereset");
    model.result("pg7").feature("ptgr2").set("legendpattern", "eval(C_rate) C\uff0c\u4e8c\u5143\u79bb\u5b50");
    model.result("pg7").run();
    model.result("pg7").set("titletype", "manual");
    model.result("pg7")
         .set("title", "\u7535\u6c60\u7535\u538b\u66f2\u7ebf\uff1a\u5355\u79bb\u5b50  vs. \u4e8c\u5143\u79bb\u5b50");
    model.result("pg7").run();

    model.component("comp1").physics("liion").prop("ElectrolyteType").set("ElectrolyteType", "SingleIonConductor");
    model.component("comp1").physics("liion").feature("sep1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("liion").feature("pce1").set("ElectrolyteMaterial", "dommat");
    model.component("comp1").physics("liion").feature("pce1").set("sigmal_mat", "userdef");

    return model;
  }

  public static Model run3(Model model) {
    model.component("comp1").physics("liion").feature("pce2").set("ElectrolyteMaterial", "dommat");
    model.component("comp1").physics("liion").feature("pce2").set("sigmal_mat", "userdef");

    model.component("comp1").material().remove("mat3");

    model.study().remove("std2");

    model.result("pg7").run();
    model.result().remove("pg7");

    model.title("\u5355\u79bb\u5b50\u5bfc\u7535\u56fa\u4f53\u7535\u89e3\u8d28\u9502\u79bb\u5b50\u7535\u6c60");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u9502\u79bb\u5b50\u7535\u6c60\uff0c\u5355\u79bb\u5b50\u5bfc\u4f53\u201d\u63a5\u53e3\u6765\u7814\u7a76\u5177\u6709\u56fa\u4f53\u7535\u89e3\u8d28\u7684\u9502\u79bb\u5b50\u7535\u6c60\u7684\u653e\u7535\uff0c\u5206\u6790\u4e86\u5404\u79cd\u653e\u7535\u7535\u6d41\u548c\u56fa\u4f53\u7535\u89e3\u8d28\u7535\u5bfc\u7387\u4e0b\u7684\u7279\u6027\u3002\u5176\u4e2d\u91c7\u7528\u4e00\u7ef4\u51e0\u4f55\u7ed3\u6784\u548c\u7b49\u6e29\u6a21\u578b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("li_battery_solid_electrolyte.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
