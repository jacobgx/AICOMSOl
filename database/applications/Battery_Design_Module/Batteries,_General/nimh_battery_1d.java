/*
 * nimh_battery_1d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:18 by COMSOL 6.3.0.290. */
public class nimh_battery_1d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Batteries,_General");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("batbe", "BatteryBinaryElectrolyte", "geom1");

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
    model.study("std1").feature("cdi").setSolveFor("/physics/batbe", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/batbe", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L_neg", "350[um]", "\u539a\u5ea6\uff0c\u8d1f\u6781");
    model.param().set("L_sep", "250[um]", "\u539a\u5ea6\uff0c\u9694\u819c");
    model.param().set("L_pos", "843[um]", "\u539a\u5ea6\uff0c\u6b63\u6781");
    model.param().set("sigmas_neg", "1000[S/cm]", "\u7535\u5bfc\u7387\uff0c\u8d1f\u6781");
    model.param().set("sigmas_pos", "25[S/cm]", "\u7535\u5bfc\u7387\uff0c\u6b63\u6781");
    model.param().set("eps_l_neg", "0.4", "\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570\uff0c\u8d1f\u6781");
    model.param().set("eps_l_pos", "0.3", "\u7535\u89e3\u8d28\u4f53\u79ef\u5206\u6570\uff0c\u6b63\u6781");
    model.param().set("alpha_a_neg", "0.25", "\u9633\u6781\u4f20\u9012\u7cfb\u6570\uff0c\u8d1f\u6781");
    model.param().set("alpha_a_pos", "0.13", "\u9633\u6781\u4f20\u9012\u7cfb\u6570\uff0c\u6b63\u6781");
    model.param().set("alpha_c_neg", "0.54", "\u9634\u6781\u4f20\u9012\u7cfb\u6570\uff0c\u8d1f\u6781");
    model.param().set("alpha_c_pos", "0.074", "\u9634\u6781\u4f20\u9012\u7cfb\u6570\uff0c\u6b63\u6781");
    model.param().set("r_neg", "1.5[um]", "\u7535\u6781\u9897\u7c92\u534a\u5f84\uff0c\u8d1f\u6781");
    model.param().set("r_pos", "2.5e-6[m]", "\u7535\u6781\u9897\u7c92\u534a\u5f84\uff0c\u6b63\u6781");
    model.param()
         .set("i0_ref_neg", "8[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u8d1f\u6781");
    model.param()
         .set("i0_ref_pos", "1[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6b63\u6781");
    model.param().set("T", "298[K]", "\u5de5\u4f5c\u6e29\u5ea6");
    model.param().set("cl_init", "6.91[mol/dm^3]", "\u521d\u59cb\u7535\u89e3\u8d28\u6d53\u5ea6");
    model.param().set("M_K", "39.1[g/mol]", "\u94be\u7684\u6469\u5c14\u8d28\u91cf");
    model.param().set("M_OH", "17[g/mol]", "\u9634\u79bb\u5b50\u7684\u6469\u5c14\u8d28\u91cf");
    model.param().set("M_H2O", "18[g/mol]", "\u6eb6\u5242\u6469\u5c14\u8d28\u91cf");
    model.param().set("C1", "43[mA/cm^2]", "1 \u5c0f\u65f6\u653e\u7535\u7535\u6d41");
    model.param().set("eps_s_neg", "0.5", "\u7535\u6781\u4f53\u79ef\u5206\u6570\uff0c\u8d1f\u6781");
    model.param().set("eps_s_pos", "0.5", "\u7535\u6781\u4f53\u79ef\u5206\u6570\uff0c\u6b63\u6781");
    model.param().set("cl_ref", "6.91[mol/dm^3]", "\u7535\u89e3\u8d28\u76d0\u53c2\u8003\u6d53\u5ea6");
    model.param().set("c0_ref", "5e4[mol/m^3]", "\u6eb6\u5242\uff08\u6c34\uff09\u53c2\u8003\u6d53\u5ea6");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").set("specify", "len");
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_neg", 0);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_sep", 1);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L_pos", 2);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("cs_max_neg", "mat2.def.csmax", "\u6700\u5927\u63d2\u5c42\u6d53\u5ea6\uff0c\u8d1f\u6781");
    model.component("comp1").variable("var1")
         .set("cs_max_pos", "mat3.def.csmax", "\u6700\u5927\u63d2\u5c42\u6d53\u5ea6\uff0c\u6b63\u6781");
    model.component("comp1").variable("var1")
         .set("cs_init_neg", "0.95*cs_max_neg", "\u521d\u59cb\u8377\u7535\u72b6\u6001\u6d53\u5ea6\uff0c\u8d1f\u6781");
    model.component("comp1").variable("var1")
         .set("cs_init_pos", "0.01*cs_max_pos", "\u521d\u59cb\u8377\u7535\u72b6\u6001\u6d53\u5ea6\uff0c\u6b63\u6781");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(4);
    model.component("comp1").cpl("intop1").set("opname", "PositiveCC");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int2", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int3", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElectrolyteConductivity", "ElectrolyteConductivity", "Electrolyte conductivity");
    model.component("comp1").material("mat1").propertyGroup()
         .create("SpeciesProperties", "SpeciesProperties", "Species properties");
    model.component("comp1").material("mat1").label("KOH (Liquid)");
    model.component("comp1").material("mat1").comments("\n");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("funcname", "A_rho");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0", "-0.5031"}, 
         {"5", "-0.4821"}, 
         {"10", "-0.5026"}, 
         {"15", "-0.482"}, 
         {"20", "-0.4824"}, 
         {"25", "-0.4931"}, 
         {"30", "-0.4812"}, 
         {"35", "-0.4918"}, 
         {"40", "-0.4863"}, 
         {"45", "-0.4912"}, 
         {"50", "-0.4756"}, 
         {"55", "-0.4898"}, 
         {"60", "-0.4916"}, 
         {"65", "-0.4906"}, 
         {"70", "-0.4876"}, 
         {"80", "-0.4942"}, 
         {"90", "-0.5021"}, 
         {"100", "-0.501"}, 
         {"150", "-0.5206"}, 
         {"200", "-0.5538"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("filecolumns", 2);
    model.component("comp1").material("mat1").propertyGroup("def").func("int1")
         .set("columnKeys", new String[]{"col1", "col2"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").set("funcname", "B_rho");
    model.component("comp1").material("mat1").propertyGroup("def").func("int2")
         .set("table", new String[][]{{"0", "45.876"}, 
         {"5", "45.648"}, 
         {"10", "45.889"}, 
         {"15", "45.659"}, 
         {"20", "45.649"}, 
         {"25", "45.761"}, 
         {"30", "45.568"}, 
         {"35", "45.698"}, 
         {"40", "45.601"}, 
         {"45", "45.62"}, 
         {"50", "45.336"}, 
         {"55", "45.543"}, 
         {"60", "45.53"}, 
         {"65", "45.45"}, 
         {"70", "45.396"}, 
         {"80", "45.409"}, 
         {"90", "45.432"}, 
         {"100", "45.361"}, 
         {"150", "45.217"}, 
         {"200", "45.173"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").set("filecolumns", 2);
    model.component("comp1").material("mat1").propertyGroup("def").func("int2")
         .set("columnKeys", new String[]{"col1", "col2"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int2")
         .set("funcnames", new String[]{"col1", "int2", "col2", "int1"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").label("Interpolation 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("int3")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").set("funcname", "C_rho");
    model.component("comp1").material("mat1").propertyGroup("def").func("int3")
         .set("table", new String[][]{{"0", "1004.4"}, 
         {"5", "1003.8"}, 
         {"10", "1002.5"}, 
         {"15", "1002"}, 
         {"20", "1001"}, 
         {"25", "999.63"}, 
         {"30", "998.66"}, 
         {"35", "996.7"}, 
         {"40", "994.89"}, 
         {"45", "992.84"}, 
         {"50", "991.51"}, 
         {"55", "988.4"}, 
         {"60", "985.91"}, 
         {"65", "983.39"}, 
         {"70", "980.71"}, 
         {"80", "974.59"}, 
         {"90", "967.98"}, 
         {"100", "960.99"}, 
         {"150", "919.52"}, 
         {"200", "869.35"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").set("filecolumns", 2);
    model.component("comp1").material("mat1").propertyGroup("def").func("int3")
         .set("columnKeys", new String[]{"col1", "col2"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int3")
         .set("funcnames", new String[]{"col1", "int3", "col2", "int1"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("diffusion", new String[]{"3.75e-9[m^2/s]", "0", "0", "0", "3.75e-9[m^2/s]", "0", "0", "0", "3.75e-9[m^2/s]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("diffusion", "B. Paxton and J. Newman,  J. Electrochem. Soc., vol. 144, no. 11, (1997) 3818\u20133831, ");
    model.component("comp1").material("mat1").propertyGroup("def").set("T_reg", "min(max(T,0[degC]),200[degC])");
    model.component("comp1").material("mat1").propertyGroup("def").descr("T_reg", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("M_reg", "min(max(c,1e-6[M]),12[M])/1[M]");
    model.component("comp1").material("mat1").propertyGroup("def").descr("M_reg", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("density", "(A_rho(T_degC)*M_reg^2+B_rho(T_degC)*M_reg+C_rho(T_degC))*1[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("density", "R.J. Gilliama, J.W. Graydonb, D.W. Kirkb, S.J. Thorpea, Int. J. Hydrogen Energy 32 (2007) 359 \u2013 364");
    model.component("comp1").material("mat1").propertyGroup("def").set("T_degC", "(T_reg-0[degC])/1[K]");
    model.component("comp1").material("mat1").propertyGroup("def").descr("T_degC", "");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("concentration");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .label("Electrolyte conductivity");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .set("sigmal", new String[]{"(A*M+B*M^2+C*M*T_K+D*M/T_K+E*M^3+F*M^2*T_K^2)*1[S/cm]", "0", "0", "0", "(A*M+B*M^2+C*M*T_K+D*M/T_K+E*M^3+F*M^2*T_K^2)*1[S/cm]", "0", "0", "0", "(A*M+B*M^2+C*M*T_K+D*M/T_K+E*M^3+F*M^2*T_K^2)*1[S/cm]"});
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity")
         .setPropertyInfo("sigmal", "R.J. Gilliama, J.W. Graydonb, D.W. Kirkb, S.J. Thorpea, Int. J. Hydrogen Energy 32 (2007) 359 \u2013 364");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").set("T_K", "def.T_reg/1[K]");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").descr("T_K", "");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").set("M", "def.M_reg");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").descr("M", "");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").set("A", "-2.041");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").descr("A", "");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").set("B", "-0.0028");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").descr("B", "");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").set("C", "0.005332");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").descr("C", "");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").set("D", "207.2");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").descr("D", "");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").set("E", "0.001043");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").descr("E", "");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").set("F", "-0.0000003");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").descr("F", "");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").addInput("concentration");
    model.component("comp1").material("mat1").propertyGroup("ElectrolyteConductivity").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").label("Species properties");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").set("transpNum", "0.22");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties")
         .setPropertyInfo("transpNum", "B. Paxton and J. Newman,  J. Electrochem. Soc., vol. 144, no. 11, (1997) 3818\u20133831, ");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties").set("fcl", "2");
    model.component("comp1").material("mat1").propertyGroup("SpeciesProperties")
         .setPropertyInfo("fcl", "B. Paxton and J. Newman,  J. Electrochem. Soc., vol. 144, no. 11, (1997) 3818\u20133831, ");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("int1", "Interpolation");
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
    model.component("comp1").material("mat2").label("HxLiN5 (Negative, NiMH Battery)");
    model.component("comp1").material("mat2").comments("\n");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("funcname", "Eeq");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0.0100", "-0.5104"}, 
         {"0.0200", "-0.6421"}, 
         {"0.0300", "-0.7304"}, 
         {"0.0400", "-0.7896"}, 
         {"0.0500", "-0.8293"}, 
         {"0.0600", "-0.8559"}, 
         {"0.0700", "-0.8737"}, 
         {"0.0800", "-0.8857"}, 
         {"0.0900", "-0.8937"}, 
         {"0.1000", "-0.8991"}, 
         {"0.1100", "-0.9027"}, 
         {"0.1200", "-0.9051"}, 
         {"0.1300", "-0.9067"}, 
         {"0.1400", "-0.9078"}, 
         {"0.1500", "-0.9085"}, 
         {"0.1600", "-0.9090"}, 
         {"0.1700", "-0.9093"}, 
         {"0.1800", "-0.9096"}, 
         {"0.1900", "-0.9097"}, 
         {"0.2000", "-0.9098"}, 
         {"0.2200", "-0.9099"}, 
         {"0.5000", "-0.9100"}, 
         {"0.7800", "-0.9101"}, 
         {"0.8000", "-0.9102"}, 
         {"0.8100", "-0.9103"}, 
         {"0.8200", "-0.9104"}, 
         {"0.8300", "-0.9107"}, 
         {"0.8400", "-0.9110"}, 
         {"0.8500", "-0.9115"}, 
         {"0.8600", "-0.9122"}, 
         {"0.8700", "-0.9133"}, 
         {"0.8800", "-0.9149"}, 
         {"0.8900", "-0.9173"}, 
         {"0.9000", "-0.9209"}, 
         {"0.9100", "-0.9263"}, 
         {"0.9200", "-0.9343"}, 
         {"0.9300", "-0.9463"}, 
         {"0.9400", "-0.9641"}, 
         {"0.9500", "-0.9907"}, 
         {"0.9600", "-1.0304"}, 
         {"0.9700", "-1.0896"}, 
         {"0.9800", "-1.1779"}, 
         {"0.9900", "-1.3096"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("fununit", new String[]{"V"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("argunit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("defineinv", true);
    model.component("comp1").material("mat2").propertyGroup("def").func("int1").set("funcinvname", "Eeq_inv");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"1000[S/cm]", "0", "0", "0", "1000[S/cm]", "0", "0", "0", "1000[S/cm]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("diffusion", new String[]{"2e-12[m^2/s]", "0", "0", "0", "2e-12[m^2/s]", "0", "0", "0", "2e-12[m^2/s]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("diffusion", "B. Paxton and J. Newman, \u201cModelling Nickel/Metal Hydride Batteries,\u201d J. Electrochem. Soc., vol. 144, p. 3818, 1997.");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "7490[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("density", "W. B. Gu and C. Y. Wang, \u201cThermal-Electrochemical Modeling of Battery Systems,\u201d J. Electrochem. Soc., vol. 147, p. 2910, 2000.");
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "350[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .setPropertyInfo("heatcapacity", "W. B. Gu and C. Y. Wang, \u201cThermal-Electrochemical Modeling of Battery Systems,\u201d J. Electrochem. Soc., vol. 147, p. 2910, 2000.");
    model.component("comp1").material("mat2").propertyGroup("def").set("csmax", "0.1025e6[mol/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def").descr("csmax", "");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").identifier("eeq");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").set("Eeq", "def.Eeq(doc)");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .setPropertyInfo("Eeq", "P. Albertus, J. Christensen, and J. Newman, \u201cModelling Side Reactions and Nonisothermal Effects in Nickel Metal-Hydride Batteries,\u201d J. Electrochem. Soc., vol. 155, p. A48, 2008.");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").set("dEeqdT", "-0.836e-3[V/K]");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .setPropertyInfo("dEeqdT", "W. B. Gu and C. Y. Wang, \u201cThermal-Electrochemical Modeling of Battery Systems,\u201d J. Electrochem. Soc., vol. 147, p. 2910, 2000.");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").set("cEeqref", "def.csmax");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .setPropertyInfo("cEeqref", "P. Albertus, J. Christensen, and J. Newman, \u201cModelling Side Reactions and Nonisothermal Effects in Nickel Metal-Hydride Batteries,\u201d J. Electrochem. Soc., vol. 155, p. A48, 2008.");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").set("doc", "c/cEeqref");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential")
         .descr("doc", "Degree of conversion");
    model.component("comp1").material("mat2").propertyGroup("ElectrodePotential").addInput("concentration");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC")
         .label("Operational electrode state of charge");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").identifier("opsoc");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").set("socmax", "def.Eeq_inv(E_min)");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").set("socmin", "def.Eeq_inv(E_max)");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").set("E_max", "-0.6[V]");
    model.component("comp1").material("mat2").propertyGroup("OperationalSOC").set("E_min", "-1.2[V]");
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
         .set("dEeqdT", "-0.836e-3[V/K]");
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
    model.component("comp1").material("mat3").propertyGroup()
         .create("ElectrodePotential", "ElectrodePotential", "Equilibrium potential");
    model.component("comp1").material("mat3").propertyGroup()
         .create("OperationalSOC", "OperationalSOC", "Operational electrode state of charge");
    model.component("comp1").material("mat3").propertyGroup()
         .create("EquilibriumConcentration", "EquilibriumConcentration", "Equilibrium concentration");
    model.component("comp1").material("mat3").propertyGroup()
         .create("EquilibriumPotentialWithDOCInput", "EquilibriumPotentialWithDOCInput", "Equilibrium potential (using degree of conversion as model input)");
    model.component("comp1").material("mat3").propertyGroup()
         .create("EquilibriumDegreeOfConversion", "EquilibriumDegreeOfConversion", "Equilibrium degree of conversion");
    model.component("comp1").material("mat3").label("NiOHO-Hx (Positive discharge, NiMH Battery)");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("funcname", "Eeq");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"0.0100", "0.1710"}, 
         {"0.0200", "0.2199"}, 
         {"0.0300", "0.2499"}, 
         {"0.0400", "0.2695"}, 
         {"0.0500", "0.2827"}, 
         {"0.0600", "0.2921"}, 
         {"0.0700", "0.2992"}, 
         {"0.0800", "0.3047"}, 
         {"0.0900", "0.3092"}, 
         {"0.1000", "0.3131"}, 
         {"0.1100", "0.3166"}, 
         {"0.1200", "0.3197"}, 
         {"0.1300", "0.3227"}, 
         {"0.1400", "0.3255"}, 
         {"0.1500", "0.3282"}, 
         {"0.1600", "0.3309"}, 
         {"0.1700", "0.3335"}, 
         {"0.1800", "0.3360"}, 
         {"0.1900", "0.3385"}, 
         {"0.2000", "0.3410"}, 
         {"0.2100", "0.3434"}, 
         {"0.2200", "0.3458"}, 
         {"0.2300", "0.3482"}, 
         {"0.2400", "0.3506"}, 
         {"0.2500", "0.3529"}, 
         {"0.2600", "0.3552"}, 
         {"0.2700", "0.3574"}, 
         {"0.2800", "0.3595"}, 
         {"0.2900", "0.3615"}, 
         {"0.3000", "0.3635"}, 
         {"0.3100", "0.3654"}, 
         {"0.3200", "0.3671"}, 
         {"0.3300", "0.3687"}, 
         {"0.3400", "0.3702"}, 
         {"0.3500", "0.3716"}, 
         {"0.3600", "0.3728"}, 
         {"0.3700", "0.3739"}, 
         {"0.3800", "0.3749"}, 
         {"0.3900", "0.3758"}, 
         {"0.4000", "0.3766"}, 
         {"0.4100", "0.3773"}, 
         {"0.4200", "0.3780"}, 
         {"0.4300", "0.3786"}, 
         {"0.4400", "0.3791"}, 
         {"0.4500", "0.3796"}, 
         {"0.4600", "0.3802"}, 
         {"0.4700", "0.3807"}, 
         {"0.4800", "0.3812"}, 
         {"0.4900", "0.3818"}, 
         {"0.5000", "0.3824"}, 
         {"0.5100", "0.3830"}, 
         {"0.5200", "0.3836"}, 
         {"0.5300", "0.3843"}, 
         {"0.5400", "0.3850"}, 
         {"0.5500", "0.3858"}, 
         {"0.5600", "0.3865"}, 
         {"0.5700", "0.3873"}, 
         {"0.5800", "0.3882"}, 
         {"0.5900", "0.3890"}, 
         {"0.6000", "0.3899"}, 
         {"0.6100", "0.3908"}, 
         {"0.6200", "0.3918"}, 
         {"0.6300", "0.3927"}, 
         {"0.6400", "0.3936"}, 
         {"0.6500", "0.3946"}, 
         {"0.6600", "0.3956"}, 
         {"0.6700", "0.3966"}, 
         {"0.6800", "0.3976"}, 
         {"0.6900", "0.3986"}, 
         {"0.7000", "0.3996"}, 
         {"0.7100", "0.4007"}, 
         {"0.7200", "0.4018"}, 
         {"0.7300", "0.4029"}, 
         {"0.7400", "0.4040"}, 
         {"0.7500", "0.4051"}, 
         {"0.7600", "0.4063"}, 
         {"0.7700", "0.4076"}, 
         {"0.7800", "0.4089"}, 
         {"0.7900", "0.4103"}, 
         {"0.8000", "0.4117"}, 
         {"0.8100", "0.4132"}, 
         {"0.8200", "0.4149"}, 
         {"0.8300", "0.4167"}, 
         {"0.8400", "0.4186"}, 
         {"0.8500", "0.4207"}, 
         {"0.8600", "0.4231"}, 
         {"0.8700", "0.4257"}, 
         {"0.8800", "0.4287"}, 
         {"0.8900", "0.4320"}, 
         {"0.9000", "0.4359"}, 
         {"0.9100", "0.4404"}, 
         {"0.9200", "0.4456"}, 
         {"0.9300", "0.4517"}, 
         {"0.9400", "0.4589"}, 
         {"0.9500", "0.4676"}, 
         {"0.9600", "0.4781"}, 
         {"0.9700", "0.4911"}, 
         {"0.9800", "0.5079"}, 
         {"0.9900", "0.5314"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("fununit", new String[]{"V"});
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("argunit", new String[]{""});
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("defineinv", true);
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("funcinvname", "Eeq_inv");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"25[S/cm]", "0", "0", "0", "25[S/cm]", "0", "0", "0", "25[S/cm]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("diffusion", new String[]{"1e-12[m^2/s]", "0", "0", "0", "1e-12[m^2/s]", "0", "0", "0", "1e-12[m^2/s]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("diffusion", "B. Paxton and J. Newman, \u201cModelling Nickel/Metal Hydride Batteries,\u201d J. Electrochem. Soc., vol. 144, p. 3818, 1997");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "3550[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("density", "W. B. Gu and C. Y. Wang, \u201cThermal-Electrochemical Modeling of Battery Systems,\u201d J. Electrochem. Soc., vol. 147, p. 2910, 2000.");
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "880[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("heatcapacity", "W. B. Gu and C. Y. Wang, \u201cThermal-Electrochemical Modeling of Battery Systems,\u201d J. Electrochem. Soc., vol. 147, p. 2910, 2000.");
    model.component("comp1").material("mat3").propertyGroup("def").set("csmax", "0.0383[mol/cm^3]");
    model.component("comp1").material("mat3").propertyGroup("def").descr("csmax", "");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").label("Equilibrium potential");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").identifier("eeq");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").set("Eeq", "def.Eeq(1-doc)");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .setPropertyInfo("Eeq", "P. Albertus, J. Christensen, and J. Newman, \u201cModelling Side Reactions and Nonisothermal Effects in Nickel Metal-Hydride Batteries,\u201d J. Electrochem. Soc., vol. 155, p. A48, 2008.");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").set("dEeqdT", "-1.35e-3[V/K]");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .setPropertyInfo("dEeqdT", "W. B. Gu and C. Y. Wang, \u201cThermal-Electrochemical Modeling of Battery Systems,\u201d J. Electrochem. Soc., vol. 147, p. 2910, 2000.");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").set("cEeqref", "def.csmax");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .setPropertyInfo("cEeqref", "P. Albertus, J. Christensen, and J. Newman, \u201cModelling Side Reactions and Nonisothermal Effects in Nickel Metal-Hydride Batteries,\u201d J. Electrochem. Soc., vol. 155, p. A48, 2008.");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").set("doc", "c/cEeqref");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential")
         .descr("doc", "Degree of conversion");
    model.component("comp1").material("mat3").propertyGroup("ElectrodePotential").addInput("concentration");
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC")
         .label("Operational electrode state of charge");
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC").identifier("opsoc");
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC").set("socmax", "def.Eeq_inv(E_min)");
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC").set("socmin", "def.Eeq_inv(E_max)");
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC").set("E_max", "0.53[V]");
    model.component("comp1").material("mat3").propertyGroup("OperationalSOC").set("E_min", "0.18[V]");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumConcentration")
         .label("Equilibrium concentration");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumConcentration")
         .set("csEq", "def.csmax*(1-def.Eeq_inv(V))");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumConcentration")
         .addInput("electricpotential");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumPotentialWithDOCInput")
         .label("Equilibrium potential (using degree of conversion as model input)");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("Eeq", "def.Eeq(1-doc)");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumPotentialWithDOCInput")
         .set("dEeqdT", "-1.35e-3[V/K]");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumPotentialWithDOCInput")
         .addInput("degreeofconversion");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumDegreeOfConversion")
         .label("Equilibrium degree of conversion");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumDegreeOfConversion")
         .set("docEq", "(1-def.Eeq_inv(V))");
    model.component("comp1").material("mat3").propertyGroup("EquilibriumDegreeOfConversion")
         .addInput("electricpotential");

    model.component("comp1").physics("batbe").prop("BatBeSpecies").set("ManMinus", "M_OH");
    model.component("comp1").physics("batbe").prop("BatBeSpecies").set("McatPlus", "M_K");
    model.component("comp1").physics("batbe").prop("BatBeSpecies").set("M0", "M_H2O");
    model.component("comp1").physics("batbe").feature("sep1").set("epsl", 1);
    model.component("comp1").physics("batbe").create("pce1", "PorousElectrode", 1);
    model.component("comp1").physics("batbe").feature("pce1").selection().set(1);
    model.component("comp1").physics("batbe").feature("pce1")
         .set("sigma", new String[]{"sigmas_neg", "0", "0", "0", "sigmas_neg", "0", "0", "0", "sigmas_neg"});
    model.component("comp1").physics("batbe").feature("pce1").set("epss", "eps_s_neg");
    model.component("comp1").physics("batbe").feature("pce1").set("epsl", "eps_l_neg");
    model.component("comp1").physics("batbe").feature("pce1").feature("pin1").set("ParticleMaterial", "mat2");
    model.component("comp1").physics("batbe").feature("pce1").feature("pin1").set("csinit", "cs_init_neg");
    model.component("comp1").physics("batbe").feature("pce1").feature("pin1").set("rp", "r_neg");
    model.component("comp1").physics("batbe").feature("pce1").feature("per1").set("MaterialOption", "mat2");
    model.component("comp1").physics("batbe").feature("pce1").feature("per1").set("i0_ref", "i0_ref_neg");
    model.component("comp1").physics("batbe").feature("pce1").feature("per1").set("alphaa", "alpha_a_neg");
    model.component("comp1").physics("batbe").feature("pce1").feature("per1").set("alphac", "alpha_c_neg");
    model.component("comp1").physics("batbe").feature("pce1").feature("per1").set("cl_ref", "cl_ref");
    model.component("comp1").physics("batbe").feature("pce1").feature("per1").set("c0_ref", "c0_ref");
    model.component("comp1").physics("batbe").create("pce2", "PorousElectrode", 1);
    model.component("comp1").physics("batbe").feature("pce2").selection().set(3);
    model.component("comp1").physics("batbe").feature("pce2")
         .set("sigma", new String[]{"sigmas_pos", "0", "0", "0", "sigmas_pos", "0", "0", "0", "sigmas_pos"});
    model.component("comp1").physics("batbe").feature("pce2").set("epss", "eps_s_pos");
    model.component("comp1").physics("batbe").feature("pce2").set("epsl", "eps_l_pos");
    model.component("comp1").physics("batbe").feature("pce2").feature("pin1").set("ParticleMaterial", "mat3");
    model.component("comp1").physics("batbe").feature("pce2").feature("pin1").set("csinit", "cs_init_pos");
    model.component("comp1").physics("batbe").feature("pce2").feature("pin1").set("rp", "r_pos");
    model.component("comp1").physics("batbe").feature("pce2").feature("per1").set("MaterialOption", "mat3");
    model.component("comp1").physics("batbe").feature("pce2").feature("per1").set("i0_ref", "i0_ref_pos");
    model.component("comp1").physics("batbe").feature("pce2").feature("per1").set("alphaa", "alpha_a_pos");
    model.component("comp1").physics("batbe").feature("pce2").feature("per1").set("alphac", "alpha_c_pos");
    model.component("comp1").physics("batbe").feature("pce2").feature("per1").set("cl_ref", "cl_ref");
    model.component("comp1").physics("batbe").feature("pce2").feature("per1").set("c0_ref", "c0_ref");
    model.component("comp1").physics("batbe").create("egnd1", "ElectricGround", 0);
    model.component("comp1").physics("batbe").feature("egnd1").selection().set(1);
    model.component("comp1").physics("batbe").create("ecd1", "ElectrodeNormalCurrentDensity", 0);
    model.component("comp1").physics("batbe").feature("ecd1").selection().set(4);
    model.component("comp1").physics("batbe").feature("ecd1").set("nis", "-C1/10");
    model.component("comp1").physics("batbe").feature("init1").set("cl", "cl_init");

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.study("std1").feature("time").set("tlist", "range(0,600,36000)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").create("st1", "StopCondition");
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "comp1.PositiveCC(comp1.phis)<0.99", 0);
    model.sol("sol1").feature("t1").feature("st1").set("storestopcondsol", "stepbefore_stepafter");
    model.sol("sol1").feature("t1").feature("st1").set("stopcondwarn", false);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("ptgr1", "PointGraph");
    model.result("pg1").feature("ptgr1").selection().all();
    model.result("pg1").feature("ptgr1").set("expr", new String[]{"phis"});
    model.result("pg1").feature("ptgr1").selection().set(4);
    model.result("pg1").label("\u5bf9\u5730\u8fb9\u754c\u7535\u6781\u7535\u4f4d (batbe)");
    model.result("pg1").feature("ptgr1").set("xdatasolnumtype", "level1");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{"", ""});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"batbe.soc_average_pce1", "batbe.soc_average_pce2"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u5e73\u5747 SOC\uff0c\u591a\u5b54\u7535\u6781 1", "\u5e73\u5747 SOC\uff0c\u591a\u5b54\u7535\u6781 2"});
    model.result("pg2").label("\u7535\u6781\u5e73\u5747\u8377\u7535\u72b6\u6001 (batbe)");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").label("\u7535\u89e3\u8d28\u7535\u4f4d (batbe)");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg3").feature("lngr1").selection().set(1, 2, 3);
    model.result("pg3").feature("lngr1").set("expr", new String[]{"phil"});
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (batbe)");
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
    model.result("pg5").label("\u7535\u89e3\u8d28\u76d0\u6d53\u5ea6 (batbe)");
    model.result("pg1").run();
    model.result("pg1").label("\u7535\u6c60\u7535\u538b");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u4e0d\u540c\u653e\u7535\u901f\u7387\u65f6\u7684\u7535\u6c60\u7535\u538b");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u5229\u7528\u7387");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u7535\u6c60\u7535\u538b (V)");
    model.result("pg1").run();
    model.result("pg1").feature("ptgr1").set("xdata", "expr");
    model.result("pg1").feature("ptgr1").set("xdataexpr", "t/36000");
    model.result("pg1").feature("ptgr1").set("legend", true);
    model.result("pg1").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg1").feature("ptgr1").setIndex("legends", "C/10", 0);

    model.component("comp1").physics("batbe").feature("ecd1").set("nis", "-C1");

    model.sol("sol1").copySolution("sol3");

    model.study("std1").feature("time").set("tlist", "range(0,60,3600)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("ptgr1").set("xdataexpr", "t/3600");
    model.result("pg1").feature("ptgr1").setIndex("legends", "1C", 0);
    model.result("pg1").run();
    model.result("pg1").feature("ptgr2").set("data", "dset3");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("titletype", "label");
    model.result("pg2").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevelinput", "manual", 0);
    model.result("pg6").setIndex("looplevel", new int[]{11}, 0);
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "t=600 s \u65f6\u7684\u7535\u538b\u635f\u5931\u6bd4\u8f83");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "x (m)");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u7535\u538b (V)");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").selection().all();
    model.result("pg6").feature("lngr1").set("legend", true);
    model.result("pg6").feature("lngr1").set("legendmethod", "manual");
    model.result("pg6").feature("lngr1").setIndex("legends", "\u7535\u89e3\u8d28\u7535\u4f4d -0.91 V", 0);
    model.result("pg6").feature("lngr1").set("expr", "phil-0.91");
    model.result("pg6").run();
    model.result("pg6").create("lngr2", "LineGraph");
    model.result("pg6").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr2").set("linewidth", "preference");
    model.result("pg6").feature("lngr2").selection().all();
    model.result("pg6").feature("lngr2").set("expr", "batbe.eta_per1");
    model.result("pg6").feature("lngr2").set("legend", true);
    model.result("pg6").feature("lngr2").set("legendmethod", "manual");
    model.result("pg6").feature("lngr2").setIndex("legends", "\u8fc7\u7535\u4f4d", 0);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").label("\u7535\u538b\u635f\u5931");
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevelinput", "manual", 0);
    model.result("pg5").setIndex("looplevel", new int[]{1, 2, 11, 51}, 0);
    model.result("pg5").set("titletype", "manual");
    model.result("pg5")
         .set("title", "\u5404\u4e2a\u65f6\u95f4\u70b9\u7684\u7535\u89e3\u8d28\u76d0\u6d53\u5ea6\u5206\u5e03");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "x (m)");
    model.result("pg5").run();
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").setIndex("looplevelinput", "manual", 0);
    model.result("pg7").setIndex("looplevel", new int[]{2, 11, 51}, 0);
    model.result("pg7").set("titletype", "manual");
    model.result("pg7").set("title", "\u5404\u4e2a\u65f6\u95f4\u70b9\u7684\u7535\u5316\u5b66\u7535\u6d41\u6e90");
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("xlabel", "x (m)");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u7535\u6d41\u6e90 (A/m<sup>3</sup>)");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1").selection().all();
    model.result("pg7").feature("lngr1").set("expr", "batbe.iv_per1");
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").label("\u7535\u6d41\u6e90");
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").setIndex("looplevelinput", "manual", 0);
    model.result("pg8").setIndex("looplevel", new int[]{2, 11, 51}, 0);
    model.result("pg8").set("titletype", "manual");
    model.result("pg8")
         .set("title", "\u7535\u6781\u9897\u7c92\u4e2d\u7684\u63d2\u5c42\u7269\u8d28\u6d53\u5ea6\uff0c\u5b9e\u7ebf=\u8868\u9762\uff0c\u865a\u7ebf=\u4e2d\u5fc3");
    model.result("pg8").set("xlabelactive", true);
    model.result("pg8").set("xlabel", "x (m)");
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("ylabel", "\u6d53\u5ea6 (mol/m<sup>3</sup>)");
    model.result("pg8").create("lngr1", "LineGraph");
    model.result("pg8").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg8").feature("lngr1").set("linewidth", "preference");
    model.result("pg8").feature("lngr1").selection().all();
    model.result("pg8").feature("lngr1").set("expr", "batbe.cs_surface");
    model.result("pg8").feature("lngr1").set("descr", "\u5d4c\u5165\u9897\u7c92\u6d53\u5ea6\uff0c\u8868\u9762");
    model.result("pg8").feature("lngr1").set("legend", true);
    model.result("pg8").run();
    model.result("pg8").create("lngr2", "LineGraph");
    model.result("pg8").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg8").feature("lngr2").set("linewidth", "preference");
    model.result("pg8").feature("lngr2").selection().all();
    model.result("pg8").feature("lngr2").set("expr", "batbe.cs_center");
    model.result("pg8").feature("lngr2").set("descr", "\u5d4c\u5165\u9897\u7c92\u6d53\u5ea6\uff0c\u4e2d\u5fc3");
    model.result("pg8").feature("lngr2").set("linestyle", "dashed");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").label("\u56fa\u76f8\u4e2d\u7684\u6d53\u5ea6");
    model.result("pg8").run();

    model.title("\u954d\u6c22\u7535\u6c60\u7b49\u6e29\u6a21\u578b - \u4e00\u7ef4");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u201c\u4e8c\u5143\u7535\u89e3\u8d28\u7535\u6c60\u201d\u63a5\u53e3\u6765\u7814\u7a76\u954d\u6c22\u7535\u6c60\u7684\u653e\u7535\uff0c\u5176\u4e2d\u91c7\u7528\u4e00\u7ef4\u51e0\u4f55\u7ed3\u6784\u548c\u7b49\u6e29\u6a21\u578b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("nimh_battery_1d.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
