/*
 * nicd_battery_1d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:18 by COMSOL 6.3.0.290. */
public class nicd_battery_1d {

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

    model.param().label("\u901a\u7528");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("T", "298.15[K]", "\u6e29\u5ea6");
    model.param().set("Q_max", "74.16[C/cm^2]", "\u603b\u7535\u6c60\u5bb9\u91cf");
    model.param().set("l_pos", "0.036[cm]", "\u539a\u5ea6\uff0c\u6b63\u6781");
    model.param().set("l_sep", "0.025[cm]", "\u539a\u5ea6\uff0c\u9694\u819c");
    model.param().set("l_neg", "0.040[cm]", "\u539a\u5ea6\uff0c\u8d1f\u6781");
    model.param().set("y_positive_active", "1.4*10^-4[cm]", "\u6d3b\u6027\u5c42\u539a\u5ea6\uff0c\u6b63\u6781");
    model.param().set("y_positive_substrate", "1.5*10^-4[cm]", "\u57fa\u677f\u539a\u5ea6\uff0c\u6b63\u6781");
    model.param()
         .set("y_positive_total", "y_positive_active+y_positive_substrate", "\u603b\u539a\u5ea6\uff0c\u6b63\u6781");
    model.param().set("cl_init", "7.1*10^-3[mol/cm^3]", "\u521d\u59cb\u7535\u89e3\u8d28\u6d53\u5ea6");
    model.param().set("I1C", "Q_max/3600[s]", "1C \u7535\u6d41\u5bc6\u5ea6");
    model.param()
         .set("sigma_electrode", "100[S/cm]", "Cd\uff08\u8d1f\uff09\u548c Ni\uff08\u6b63\uff09\u7535\u6781\u4e2d\u56fa\u4f53\u6750\u6599\u7684\u6709\u6548\u7535\u5bfc\u7387");
    model.param()
         .set("D_O2", "10^-3[cm^2/s]", "\u6709\u6548\u6269\u6563\u7cfb\u6570\uff0cO2\uff08\u6c14\u76f8\u548c\u6db2\u76f8\uff09");
    model.param()
         .set("c_O2_init", "10^-20[mol/cm^3]", "O2 \u7684\u521d\u59cb\u6d53\u5ea6\uff08\u5145\u653e\u7535\uff09");
    model.param().set("P_O2_ref", "1[atm]", "\u6c27\u53c2\u8003\u538b\u529b");
    model.param().set("epsilon_2", "0.68", "\u9694\u819c\u7684\u5b54\u9699\u7387");
    model.param().set("gamma_2", "1.5", "\u66f2\u6298\u56e0\u5b50\uff0c\u9694\u819c");
    model.param().set("i_app", "C_rate*I1C", "\u5145\u7535/\u653e\u7535\u7535\u6d41");
    model.param().set("t_charge_limit", "1.2[h]/C_rate", "\u5145\u653e\u7535\u622a\u6b62\u65f6\u95f4");
    model.param().create("par2");
    model.param("par2").label("Cd \u7535\u6781");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("rho_Cd", "8.64[g/cm^3]", "Cd \u7684\u5bc6\u5ea6");
    model.param("par2").set("rho_CdO2H2", "4.79[g/cm^3]", "Cd(OH)2 \u7684\u5bc6\u5ea6");
    model.param("par2").set("M_Cd", "112.4[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff0cCd");
    model.param("par2")
         .set("a_Cd", "4000[cm^2/cm^3]", "\u6bd4\u6d3b\u6027\u8868\u9762\u79ef\uff0cCd\uff08\u8d1f\uff09\u7535\u6781");
    model.param("par2").set("M_CdO2H2", "146.4[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff0cCd(OH)2");
    model.param("par2").set("L_neg", "2.3[g/cm^3]", "\u8d1f\u6781\u8d1f\u8f7d\u7684 Cd(OH)2");
    model.param("par2").set("epsilon0_N", "0.80", "\u8d1f\u6781\u4e2d Cd \u57fa\u677f\u7684\u5b54\u9699\u7387");
    model.param("par2")
         .set("epsilon_3_max", "epsilon0_N*(1-L_neg/rho_Cd*M_Cd/M_CdO2H2)", "\u8d1f\u6781\u7684\u5b54\u9699\u7387\uff0c\u5b8c\u5168\u5145\u7535");
    model.param("par2")
         .set("epsilon_3_min", "epsilon0_N*(1-L_neg/rho_CdO2H2)", "\u8d1f\u6781\u7684\u5b54\u9699\u7387\uff0c\u5b8c\u5168\u653e\u7535");
    model.param().create("par3");
    model.param("par3").label("Ni \u7535\u6781");

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3")
         .set("a_Ni", "3864[cm^2/cm^3]", "\u6bd4\u6d3b\u6027\u8868\u9762\u79ef\uff0cNi\uff08\u6b63\uff09\u7535\u6781");
    model.param("par3").set("M_NiO2H2", "92.71[g/mol]", "\u6469\u5c14\u8d28\u91cf\uff0cNi(OH)2");
    model.param("par3").set("epsilon0_P", "0.85", "\u6b63\u6781\u4e2d Ni \u57fa\u677f\u7684\u5b54\u9699\u7387");
    model.param("par3").set("rho_NiO2H2", "4.83[g/cm^3]", "Ni(OH)2 \u7684\u5bc6\u5ea6");
    model.param("par3").set("c_H_max", "rho_NiO2H2/M_NiO2H2", "\u6700\u5927\u8d28\u5b50\u6d53\u5ea6");
    model.param("par3")
         .set("c_H_ref", "0.5*c_H_max", "\u6d3b\u6027\u6750\u6599\u4e2d\u7684\u53c2\u8003\u8d28\u5b50\u6d53\u5ea6");
    model.param("par3")
         .set("D_H", "4.6*10^-11[cm^2/s]", "\u6c22\u5728\u6b63\u6781\u4e2d\u7684\u6269\u6563\u7cfb\u6570");
    model.param("par3")
         .set("epss", "Q_max/(F_const*c_H_max*l_pos)", "\u954d\u7535\u6781\u56fa\u76f8\u4f53\u79ef\u5206\u6570");
    model.param().create("par4");
    model.param("par4").label("\u7535\u6781\u53cd\u5e94");

//    To import content from file, use:
//    model.param("par4").loadFile("FILENAME");
    model.param("par4")
         .set("E_ref_pos", "0.427[V]-R_const*T/F_const*log(c_H_ref/(c_H_max-c_H_ref))", "\u53c2\u6bd4\u7535\u6781\u7535\u4f4d\uff0c\u6b63\u6781\u53cd\u5e94");
    model.param("par4")
         .set("E_ref_OER", "0.401[V]-E_RE+R_const*T/(4*F_const)*log(P_O2_ref/1[atm])", "\u53c2\u6bd4\u7535\u6781\u7535\u4f4d\uff0cORR/OER");
    model.param("par4")
         .set("E_ref_neg", "-0.808[V]-E_RE", "\u53c2\u6bd4\u7535\u6781\u7535\u4f4d\uff0c\u8d1f\u6781\u53cd\u5e94");
    model.param("par4").set("E_RE", "0.0983[V]", "\u53c2\u6bd4\u7535\u6781 (Hg/HgO) \u7535\u4f4d");
    model.param("par4")
         .set("alpha_a_1", "0.5", "\u9633\u6781\u4f20\u9012\u7cfb\u6570\uff0cNiOOH <=> Ni(OH)2 \u53cd\u5e94");
    model.param("par4")
         .set("alpha_a_2", "1.5", "\u9633\u6781\u4f20\u9012\u7cfb\u6570\uff0cOER/ORR\uff0c\u6b63\u6781");
    model.param("par4")
         .set("alpha_a_3", "1", "\u9633\u6781\u4f20\u9012\u7cfb\u6570\uff0cCd <=> Cd(OH)2 \u53cd\u5e94");
    model.param("par4")
         .set("alpha_a_4", "alpha_a_2", "\u9633\u6781\u4f20\u9012\u7cfb\u6570\uff0cOER/ORR\uff0c\u8d1f\u6781");
    model.param("par4")
         .set("i0_1_ref", "6.1*10^-5[A/cm^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0cNiOOH <=> Ni(OH)2 \u53cd\u5e94");
    model.param("par4")
         .set("i0_2_ref", "1*10^-11[A/cm^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0cOER/ORR\uff0c\u6b63\u6781");
    model.param("par4")
         .set("i0_3_ref", "i0_1_ref", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0cCd <=> Cd(OH)2 \u53cd\u5e94");
    model.param("par4")
         .set("c_ref", "7.1*10^-3[mol/cm^3]", "\u4e8c\u5143\u7535\u89e3\u8d28\u7684\u53c2\u8003\u6d53\u5ea6");
    model.param("par4")
         .set("c_O2_ref", "10^-7[mol/cm^3]", "35 wt% KOH \u6eb6\u6db2\u4e2d\uff0c\u6c27\u5728\u6c14\u76f8\u4e2d 1 \u4e2a\u5927\u6c14\u538b\u7684\u6eb6\u89e3\u5ea6\u3002");
    model.param().create("par5");
    model.param("par5").label("\u5145\u7535/\u653e\u7535\u6848\u4f8b");
    model.param("par5").set("c_H_init", "c_H_max/500");
    model.param("par5").descr("c_H_init", "\u6b63\u6781\u4e2d\u7684\u521d\u59cb H \u6d53\u5ea6");
    model.param("par5").set("epsilon_3_init", "epsilon_3_max");
    model.param("par5").descr("epsilon_3_init", "\u8d1f\u6781\u7684\u521d\u59cb\u5b54\u9699\u7387");
    model.param("par5").set("sign", "-1");
    model.param("par5")
         .descr("sign", "\u5916\u52a0\u7535\u6d41\u7684\u7b26\u53f7\uff0c1 \u8868\u793a\u5145\u7535\uff0c-1 \u8868\u793a\u653e\u7535");
    model.param("par5").paramCase().create("case1");
    model.param("par5").paramCase("case1").label("\u653e\u7535");
    model.param("par5").paramCase().create("case2");
    model.param("par5").paramCase("case2").label("\u5145\u7535");
    model.param("par5").paramCase("case2").set("c_H_init", "c_H_max");
    model.param("par5").paramCase("case2").set("epsilon_3_init", "epsilon_3_min+0.05");
    model.param("par5").paramCase("case2").set("sign", "1");
    model.param().create("par6");
    model.param("par6").label("\u500d\u7387\u5b9e\u4f8b");
    model.param("par6").set("C_rate", "1");
    model.param("par6").descr("C_rate", "\u5145/\u653e\u7535\u500d\u7387");
    model.param("par6").paramCase().create("case1");
    model.param("par6").paramCase("case1").set("C_rate", "1/10");
    model.param("par6").paramCase().create("case2");
    model.param("par6").paramCase("case2").set("C_rate", "1/2.1");
    model.param("par6").paramCase().create("case3");
    model.param("par6").paramCase("case3").set("C_rate", "1/0.7");

    model.component().create("xdim1", "ExtraDim");

    model.component("xdim1").geom().create("geom2", 1);
    model.geom("geom2").axisymmetric(true);

    model.mesh().create("mesh2", "geom2");

    model.extraDim().create("pa1", "PointsToAttach");
    model.extraDim("pa1").model("xdim1");

    model.component("xdim1").label("\u989d\u5916\u7ef4\u5ea6\uff1a\u6b63\u6781");

    model.component("xdim1").spatialCoord(new String[]{"rxd", "phi1", "z1"});

    model.geom("geom2").create("i1", "Interval");
    model.geom("geom2").feature("i1").set("specify", "len");
    model.geom("geom2").feature("i1").set("left", "y_positive_substrate");
    model.geom("geom2").feature("i1").setIndex("len", "y_positive_active", 0);
    model.geom("geom2").run("i1");

    model.extraDim().create("xdintop1", "Integration");
    model.extraDim("xdintop1").model("xdim1");

    model.geom("geom2").run();

    model.extraDim("xdintop1").label("\u989d\u5916\u7ef4\u5ea6\u8868\u9762\u79ef\u5206");
    model.extraDim("xdintop1").set("opname", "xdsurfop");
    model.extraDim("xdintop1").selection().geom("geom2", 0);
    model.extraDim("xdintop1").selection().set(2);
    model.extraDim("xdintop1").set("axisym", false);
    model.extraDim().create("xdintop2", "Integration");
    model.extraDim("xdintop2").model("xdim1");
    model.extraDim("xdintop2").label("\u989d\u5916\u7ef4\u5ea6\u57df\u79ef\u5206");
    model.extraDim("xdintop2").set("opname", "xdintopDomain");
    model.extraDim("xdintop2").selection().set(1);
    model.extraDim("xdintop2").set("axisym", false);

    model.mesh("mesh2").automatic(false);
    model.mesh("mesh2").create("dis1", "Distribution");
    model.mesh("mesh2").feature().move("dis1", 1);
    model.mesh("mesh2").feature("dis1").selection().set(1);
    model.mesh("mesh2").feature("dis1").set("type", "predefined");
    model.mesh("mesh2").feature("dis1").set("elemcount", 31);
    model.mesh("mesh2").feature("dis1").set("elemratio", 0.1);
    model.mesh("mesh2").run();

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").set("specify", "len");
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "l_neg", 0);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "l_sep", 1);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "l_pos", 2);
    model.component("comp1").geom("geom1").runPre("fin");

    model.extraDim().create("ad1", "AttachDimensions");
    model.component("comp1").extraDim("ad1").model("comp1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").extraDim("ad1").selection().geom("geom1", 1);
    model.component("comp1").extraDim("ad1").selection().geom("geom1", 1);
    model.component("comp1").extraDim("ad1").selection().set(3);
    model.component("comp1").extraDim("ad1").set("extradim", new String[]{"xdim1"});

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u8d1f\u6781");
    model.component("comp1").variable("var1").selection().geom("geom1", 1);
    model.component("comp1").variable("var1").selection().set(1);
    model.component("comp1").variable("var1")
         .set("theta_N", "(batbe.epsl-epsilon_3_min)/(epsilon_3_max-epsilon_3_min)");
    model.component("comp1").variable("var1").descr("theta_N", "\u8377\u7535\u72b6\u6001\uff0c\u8d1f\u6781");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").label("\u6b63\u6781\uff08\u9897\u7c92\u5185\uff09");
    model.component("comp1").variable("var2").selection().geom("geom1", 1);
    model.component("comp1").variable("var2").selection().set(3);
    model.component("comp1").variable("var2").selection().extraDim("ad1");
    model.component("comp1").variable("var2").set("theta_P", "cH[mol/m^3]/c_H_max");
    model.component("comp1").variable("var2").descr("theta_P", "\u8377\u7535\u72b6\u6001\uff0c\u6b63\u6781");
    model.component("comp1").variable("var2").set("particle_diffusive_flux", "-D_H*d(cH[mol/m^3],rxd)");
    model.component("comp1").variable("var2")
         .descr("particle_diffusive_flux", "\u6b63\u6781\u4e2d\u7684\u83f2\u514b\u6269\u6563\u901a\u91cf");
    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3").label("\u6b63\u6781\uff08\u9897\u7c92\u8868\u9762\uff09");
    model.component("comp1").variable("var3").selection().geom("geom1", 1);
    model.component("comp1").variable("var3").selection().set(3);
    model.component("comp1").variable("var3").set("cH_surf", "xdim1.xdsurfop(cH)[mol/m^3]");
    model.component("comp1").variable("var3")
         .descr("cH_surf", "\u9897\u7c92\u8868\u9762 H \u6d53\u5ea6\uff0c\u6b63\u6781");
    model.component("comp1").variable("var3")
         .set("cH_average", "xdim1.xdintopDomain(rxd*cH[mol/m^3])/xdim1.xdintopDomain(rxd)");
    model.component("comp1").variable("var3").descr("cH_average", "\u5e73\u5747 H \u6d53\u5ea6\uff0c\u6b63\u6781");
    model.component("comp1").variable("var3").set("soc_average", "cH_average/c_H_max");
    model.component("comp1").variable("var3")
         .descr("soc_average", "\u5e73\u5747\u8377\u7535\u72b6\u6001\uff0c\u6b63\u6781");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").label("\u6b63\u6781\u96c6\u6d41\u4f53\u7684\u79ef\u5206\u7b97\u5b50");
    model.component("comp1").cpl("intop1").set("opname", "intop_posCC");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(4);
    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").label("\u6b63\u6781\u5e73\u5747\u7b97\u5b50");
    model.component("comp1").cpl("aveop1").set("opname", "ave_pos");
    model.component("comp1").cpl("aveop1").selection().set(3);
    model.component("comp1").cpl().create("aveop2", "Average");
    model.component("comp1").cpl("aveop2").set("axisym", true);
    model.component("comp1").cpl("aveop2").label("\u8d1f\u6781\u5e73\u5747\u7b97\u5b50");
    model.component("comp1").cpl("aveop2").set("opname", "ave_neg");
    model.component("comp1").cpl("aveop2").selection().set(1);

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

    model.component("comp1").physics("batbe").feature("sep1").set("epsl", "epsilon_2");
    model.component("comp1").physics("batbe").feature("init1").set("cl", "cl_init");
    model.component("comp1").physics("batbe").create("pce1", "PorousElectrode", 1);
    model.component("comp1").physics("batbe").feature("pce1")
         .label("\u591a\u5b54\u7535\u6781\uff1aNi\uff08\u6b63\u6781\uff09");
    model.component("comp1").physics("batbe").feature("pce1").selection().set(3);
    model.component("comp1").physics("batbe").feature("pce1")
         .set("sigma", new String[]{"sigma_electrode", "0", "0", "0", "sigma_electrode", "0", "0", "0", "sigma_electrode"});
    model.component("comp1").physics("batbe").feature("pce1")
         .set("IntercalationOption", "NonIntercalatingParticles");
    model.component("comp1").physics("batbe").feature("pce1").set("epss", "epss");
    model.component("comp1").physics("batbe").feature("pce1").set("epsl", "epsilon0_P-epss");
    model.component("comp1").physics("batbe").feature("pce1").set("ElectricCorrModel", "NoCorr");
    model.component("comp1").physics("batbe").feature("pce1").feature("per1")
         .label("\u591a\u5b54\u7535\u6781\u53cd\u5e94\uff1aNiOOH + H2O + e- <=> Ni(OH)2 + OH-");
    model.component("comp1").physics("batbe").feature("pce1").feature("per1").set("Eeq_mat", "NernstEquation");
    model.component("comp1").physics("batbe").feature("pce1").feature("per1").set("Eeq_ref", "E_ref_pos");
    model.component("comp1").physics("batbe").feature("pce1").feature("per1")
         .set("CRNernst", "(cl/c_ref)*(cH_surf/c_H_ref)");
    model.component("comp1").physics("batbe").feature("pce1").feature("per1")
         .set("CONernst", "(c_H_max-cH_surf)/(c_H_max-c_H_ref)");
    model.component("comp1").physics("batbe").feature("pce1").feature("per1")
         .set("ElectrodeKinetics", "ButlerVolmer");
    model.component("comp1").physics("batbe").feature("pce1").feature("per1").set("i0Type", "FromNernstEquation");
    model.component("comp1").physics("batbe").feature("pce1").feature("per1").set("i0_ref", "i0_1_ref");
    model.component("comp1").physics("batbe").feature("pce1").feature("per1").set("alphaa", "alpha_a_1");
    model.component("comp1").physics("batbe").feature("pce1").feature("per1")
         .set("ActiveSpecificSurfaceAreaType", "userdef");
    model.component("comp1").physics("batbe").feature("pce1").feature("per1").set("Av", "a_Ni");
    model.component("comp1").physics("batbe").feature("pce1").feature("per1").set("dEeqdT_mat", "userdef");
    model.component("comp1").physics("batbe").feature("pce1").create("per2", "PorousElectrodeReaction", 1);
    model.component("comp1").physics("batbe").feature("pce1").feature("per2")
         .label("\u591a\u5b54\u7535\u6781\u53cd\u5e94\uff1a1/2 O2 + H2O + 2e- <=> 2 OH-");
    model.component("comp1").physics("batbe").feature("pce1").feature("per2").set("Eeq_mat", "NernstEquation");
    model.component("comp1").physics("batbe").feature("pce1").feature("per2").set("Eeq_ref", "E_ref_OER");
    model.component("comp1").physics("batbe").feature("pce1").feature("per2").set("CONernst", "c_O2/c_O2_ref");
    model.component("comp1").physics("batbe").feature("pce1").feature("per2")
         .set("ElectrodeKinetics", "ButlerVolmer");
    model.component("comp1").physics("batbe").feature("pce1").feature("per2").set("i0Type", "FromNernstEquation");
    model.component("comp1").physics("batbe").feature("pce1").feature("per2")
         .set("i0_ref", "i0_2_ref*max(cl/c_ref,1e-20)^2");
    model.component("comp1").physics("batbe").feature("pce1").feature("per2").set("alphaa", "alpha_a_2");
    model.component("comp1").physics("batbe").feature("pce1").feature("per2")
         .set("ActiveSpecificSurfaceAreaType", "userdef");
    model.component("comp1").physics("batbe").feature("pce1").feature("per2").set("Av", "a_Ni");
    model.component("comp1").physics("batbe").feature("pce1").feature("per2").set("nm", 4);
    model.component("comp1").physics("batbe").feature("pce1").feature("per2").set("dEeqdT_mat", "userdef");
    model.component("comp1").physics("batbe").create("weak1", "WeakContribution", 1);
    model.component("comp1").physics("batbe").feature("weak1").label("\u6b63\u6781\u5185\u7684 H+ \u6269\u6563");
    model.component("comp1").physics("batbe").feature("weak1").selection().set(3);
    model.component("comp1").physics("batbe").feature("weak1").selection().extraDim("ad1");
    model.component("comp1").physics("batbe").feature("weak1").selection().extraDimSel("geom2").set(1);
    model.component("comp1").physics("batbe").feature("weak1")
         .set("weakExpression", "2*pi*rxd*(particle_diffusive_flux*test(cHrxd)-cHt*test(cH))");
    model.component("comp1").physics("batbe").feature("weak1").create("aux1", "AuxiliaryField", 1);
    model.component("comp1").physics("batbe").feature("weak1").feature("aux1")
         .label("\u9897\u7c92\u5185 H+ \u6d53\u5ea6");
    model.component("comp1").physics("batbe").feature("weak1").feature("aux1").selection().extraDim("ad1");
    model.component("comp1").physics("batbe").feature("weak1").feature("aux1").selection().extraDimSel("geom2")
         .set(1);
    model.component("comp1").physics("batbe").feature("weak1").feature("aux1").set("fieldVariableName", "cH");
    model.component("comp1").physics("batbe").feature("weak1").feature("aux1").set("initialValue", "c_H_init");
    model.component("comp1").physics("batbe").create("weak2", "WeakContribution", 1);
    model.component("comp1").physics("batbe").feature("weak2")
         .label("\u9897\u7c92\u5916\u8868\u9762\u6d53\u5ea6\u7684\u8fb9\u754c\u6761\u4ef6");
    model.component("comp1").physics("batbe").feature("weak2").selection().set(3);
    model.component("comp1").physics("batbe").feature("weak2").selection().extraDim("ad1");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("batbe").feature("weak2").selection().extraDimSel("geom2").geom("geom2", 0);
    model.component("comp1").physics("batbe").feature("weak2").selection().extraDimSel("geom2").set(2);
    model.component("comp1").physics("batbe").feature("weak2")
         .set("weakExpression", "-2*batbe.pce1.per1.iloc*test(cH)*pi*rxd/(1[m]*F_const)");

    model.nodeGroup().create("grp1", "Physics", "batbe");
    model.nodeGroup("grp1").placeAfter("pce1");
    model.nodeGroup("grp1").add("weak1");
    model.nodeGroup("grp1").add("weak2");
    model.nodeGroup("grp1").label("\u9897\u7c92\u5185\u7684 H+ \u6269\u6563");

    model.component("comp1").physics("batbe").create("pce2", "PorousElectrode", 1);
    model.component("comp1").physics("batbe").feature("pce2")
         .label("\u591a\u5b54\u7535\u6781\uff1aCd\uff08\u8d1f\u6781\uff09");
    model.component("comp1").physics("batbe").feature("pce2").selection().set(1);
    model.component("comp1").physics("batbe").feature("pce2")
         .set("sigma", new String[]{"sigma_electrode", "0", "0", "0", "sigma_electrode", "0", "0", "0", "sigma_electrode"});
    model.component("comp1").physics("batbe").feature("pce2")
         .set("IntercalationOption", "NonIntercalatingParticles");
    model.component("comp1").physics("batbe").feature("pce2").set("epss", "1-epsilon_3_init");
    model.component("comp1").physics("batbe").feature("pce2").set("epsl", "epsilon_3_init");
    model.component("comp1").physics("batbe").feature("pce2").set("ElectricCorrModel", "NoCorr");
    model.component("comp1").physics("batbe").feature("pce2").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("Species", "Cd", 0, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("rhos", "rho_Cd", 0, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("Ms", "M_Cd", 0, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("Species", "s1", 1, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("rhos", 8960, 1, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("Ms", 0.06355, 1, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("rhos", 8960, 1, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("Ms", 0.06355, 1, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("Species", "s1", 1, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("rhos", 8960, 1, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("Ms", 0.06355, 1, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("Species", "CdO2H2", 1, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("rhos", "rho_CdO2H2", 1, 0);
    model.component("comp1").physics("batbe").feature("pce2").setIndex("Ms", "M_CdO2H2", 1, 0);
    model.component("comp1").physics("batbe").feature("pce2").feature("per1")
         .label("\u591a\u5b54\u7535\u6781\u53cd\u5e94\uff1aCd + 2 OH- <=> Cd(OH)2 + 2e-");
    model.component("comp1").physics("batbe").feature("pce2").feature("per1").set("Eeq_mat", "NernstEquation");
    model.component("comp1").physics("batbe").feature("pce2").feature("per1").set("Eeq_ref", "E_ref_neg");
    model.component("comp1").physics("batbe").feature("pce2").feature("per1").set("CRNernst", "(cl/c_ref)^2");
    model.component("comp1").physics("batbe").feature("pce2").feature("per1")
         .set("ElectrodeKinetics", "ButlerVolmer");
    model.component("comp1").physics("batbe").feature("pce2").feature("per1").set("i0Type", "FromNernstEquation");
    model.component("comp1").physics("batbe").feature("pce2").feature("per1").set("i0_ref", "theta_N*i0_3_ref");
    model.component("comp1").physics("batbe").feature("pce2").feature("per1").set("alphaa", "alpha_a_3");
    model.component("comp1").physics("batbe").feature("pce2").feature("per1")
         .set("ActiveSpecificSurfaceAreaType", "userdef");
    model.component("comp1").physics("batbe").feature("pce2").feature("per1").set("Av", "a_Cd");
    model.component("comp1").physics("batbe").feature("pce2").feature("per1").set("nm", 2);
    model.component("comp1").physics("batbe").feature("pce2").feature("per1").setIndex("Vib", 1, 0, 0);
    model.component("comp1").physics("batbe").feature("pce2").feature("per1").setIndex("Vib", -1, 1, 0);
    model.component("comp1").physics("batbe").feature("pce2").feature("per1").set("dEeqdT_mat", "userdef");
    model.component("comp1").physics("batbe").create("bei1", "InternalElectrodeSurface", 0);
    model.component("comp1").physics("batbe").feature("bei1").label("Cd \u7535\u6781\u4e0a\u7684\u6c27\u590d\u5408");
    model.component("comp1").physics("batbe").feature("bei1").selection().set(2);
    model.component("comp1").physics("batbe").feature("bei1").feature("er1")
         .label("\u7535\u6781\u53cd\u5e94\uff1a1/2 O2 + H2O + 2e- <=> 2 OH-");
    model.component("comp1").physics("batbe").feature("bei1").feature("er1").set("Eeq_mat", "userdef");
    model.component("comp1").physics("batbe").feature("bei1").feature("er1").set("ilocmat_mat", "userdef");
    model.component("comp1").physics("batbe").feature("bei1").feature("er1")
         .set("ilocmat", "tds.tflux_c_O2x*4*F_const");
    model.component("comp1").physics("batbe").feature("bei1").feature("er1").set("dEeqdT_mat", "userdef");
    model.component("comp1").physics("batbe").create("egnd1", "ElectricGround", 0);
    model.component("comp1").physics("batbe").feature("egnd1").selection().set(1);
    model.component("comp1").physics("batbe").create("ec1", "ElectrodeCurrent", 0);
    model.component("comp1").physics("batbe").feature("ec1").selection().set(4);
    model.component("comp1").physics("batbe").feature("ec1").set("ElectronicCurrentType", "AverageCurrentDensity");
    model.component("comp1").physics("batbe").feature("ec1").set("Ias", "sign*i_app");
    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");

    model.study("std1").feature("cdi").setSolveFor("/physics/tds", true);
    model.study("std1").feature("time").setSolveFor("/physics/tds", true);

    model.component("comp1").physics("tds").prop("TransportMechanism").set("Convection", false);
    model.component("comp1").physics("tds").prop("TransportMechanism").set("MassTransferInPorousMedia", true);
    model.component("comp1").physics("tds").field("concentration").component(1, "c_O2");
    model.component("comp1").physics("tds").selection().set(2, 3);
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_c_O2", new String[]{"D_O2", "0", "0", "0", "D_O2", "0", "0", "0", "D_O2"});
    model.component("comp1").physics("tds").feature("init1").setIndex("initc", "c_O2_init", 0);
    model.component("comp1").physics("tds").create("pec1", "PorousElectrodeCoupling", 1);
    model.component("comp1").physics("tds").feature("pec1")
         .label("\u591a\u5b54\u7535\u6781\u8026\u5408\uff1a\u6b63\u6781");
    model.component("comp1").physics("tds").feature("pec1").selection().set(3);
    model.component("comp1").physics("tds").feature("pec1").feature("rc1")
         .set("iv_src", "root.comp1.batbe.pce1.per2.iv");
    model.component("comp1").physics("tds").feature("pec1").feature("rc1").set("nm", 4);
    model.component("comp1").physics("tds").feature("pec1").feature("rc1").setIndex("Vi", -1, 0);
    model.component("comp1").physics("tds").create("conc1", "Concentration", 0);
    model.component("comp1").physics("tds").feature("conc1").label("Cd \u7535\u6781\u4e0a\u7684\u6c27\u590d\u5408");
    model.component("comp1").physics("tds").feature("conc1").selection().set(2);
    model.component("comp1").physics("tds").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tds").create("init2", "init", 1);
    model.component("comp1").physics("tds").feature("init2").selection().set(2);
    model.component("comp1").physics("tds").feature("init2").setIndex("initc", "c_O2_init*((x-l_neg)/l_sep)", 0);

    model.study("std1").label("\u653e\u7535\u548c\u7535\u8377");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").set("sweeptype", "switch");
    model.study("std1").feature("param").setIndex("switchname", "default", 0);
    model.study("std1").feature("param").setIndex("switchcase", "all", 0);
    model.study("std1").feature("param").setIndex("switchlistarr", "", 0);
    model.study("std1").feature("param").setIndex("switchname", "default", 0);
    model.study("std1").feature("param").setIndex("switchcase", "all", 0);
    model.study("std1").feature("param").setIndex("switchlistarr", "", 0);
    model.study("std1").feature("param").setIndex("switchname", "par5", 0);
    model.study("std1").feature("param").setIndex("switchname", "default", 1);
    model.study("std1").feature("param").setIndex("switchcase", "all", 1);
    model.study("std1").feature("param").setIndex("switchlistarr", "", 1);
    model.study("std1").feature("param").setIndex("switchname", "default", 1);
    model.study("std1").feature("param").setIndex("switchcase", "all", 1);
    model.study("std1").feature("param").setIndex("switchlistarr", "", 1);
    model.study("std1").feature("param").setIndex("switchname", "par6", 1);
    model.study("std1").feature("cdi").label("\u7535\u6d41\u5206\u5e03\u521d\u59cb\u5316\uff1a\u4e00\u6b21");
    model.study("std1").create("cdi2", "CurrentDistributionInitialization");
    model.study("std1").feature().move("cdi2", 2);
    model.study("std1").feature("cdi2").label("\u7535\u6d41\u5206\u5e03\u521d\u59cb\u5316\uff1a\u4e8c\u6b21");
    model.study("std1").feature("cdi2").set("initType", "secondary");
    model.study("std1").feature("time").set("tunit", "h");
    model.study("std1").feature("time").set("tlist", "range(0,t_charge_limit/100,t_charge_limit)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v3").feature("comp1_c_O2").set("scalemethod", "manual");
    model.sol("sol1").feature("v3").feature("comp1_c_O2").set("scaleval", "c_O2_ref");
    model.sol("sol1").feature("v3").feature("comp1_cH").set("scalemethod", "manual");
    model.sol("sol1").feature("v3").feature("comp1_cH").set("scaleval", "c_H_ref");
    model.sol("sol1").feature("t1").create("st1", "StopCondition");
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "comp1.intop_posCC(comp1.phis)<0.8", 0);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 1);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 1);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 1);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 2", 1);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "", 1);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondterminateon", "true", 1);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondActive", true, 1);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 2", 1);
    model.sol("sol1").feature("t1").feature("st1").setIndex("stopcondarr", "comp1.intop_posCC(comp1.phis)>1.6", 1);
    model.sol("sol1").feature("t1").feature("st1").set("storestopcondsol", "stepafter");

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol4");
    model.sol("sol4").study("std1");
    model.sol("sol4").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol4");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset4");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("unit", new String[]{""});
    model.result("pg1").feature("glob1").set("expr", new String[]{"batbe.phis0_ec1"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u8fb9\u754c\u7535\u4f4d"});
    model.result("pg1").label("\u5bf9\u5730\u8fb9\u754c\u7535\u6781\u7535\u4f4d (batbe)");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "inner");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset4");
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u4f4d (batbe)");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "x");
    model.result("pg2").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr1").selection().set(1, 2, 3);
    model.result("pg2").feature("lngr1").set("expr", new String[]{"phil"});
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset4");
    model.result("pg3").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (batbe)");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg3").feature("lngr1").selection().set(1, 2, 3);
    model.result("pg3").feature("lngr1").set("expr", new String[]{"phis"});
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset4");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x");
    model.result("pg4").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg4").feature("lngr1").selection().set(1, 2, 3);
    model.result("pg4").feature("lngr1").set("expr", new String[]{"cl"});
    model.result("pg4").label("\u7535\u89e3\u8d28\u76d0\u6d53\u5ea6 (batbe)");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "dset4");
    model.result("pg5").label("\u6d53\u5ea6 (tds)");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("prefixintitle", "");
    model.result("pg5").set("expressionintitle", false);
    model.result("pg5").set("typeintitle", false);
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "x");
    model.result("pg5").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr1").selection().set(2, 3);
    model.result("pg5").feature("lngr1").set("expr", new String[]{"c_O2"});
    model.result("pg1").run();
    model.result("pg1").label("\u653e\u7535\uff1a\u5bf9\u5730\u8fb9\u754c\u7535\u6781\u7535\u4f4d (batbe)");
    model.result("pg1").set("outerinput", "manual");
    model.result("pg1").set("outersolnum", new int[]{1, 2, 3});
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("glob1").set("xdata", "expr");
    model.result("pg1").feature("glob1").set("xdataexpr", "t");
    model.result("pg1").feature("glob1").set("xdataunit", "h");
    model.result("pg1").feature("glob1").set("legendmethod", "evaluated");
    model.result("pg1").feature("glob1").set("legendpattern", "C/eval(1/C_rate)");
    model.result("pg1").run();
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u653e\u7535");
    model.result("pg1").set("legendpos", "lowerright");
    model.result("pg1").run();
    model.result().duplicate("pg6", "pg1");
    model.result("pg6").run();
    model.result("pg6").label("\u5145\u7535\uff1a\u5bf9\u5730\u8fb9\u754c\u7535\u6781\u7535\u4f4d (batbe)");
    model.result("pg6").set("outersolnum", new int[]{4, 5, 6});
    model.result("pg6").set("title", "\u5145\u7535");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u5145\u7535\uff1a\u5e73\u5747\u6b63\u6781\u6c34\u5408\u4f5c\u7528\u7a0b\u5ea6");
    model.result("pg7").set("data", "dset4");
    model.result("pg7").set("outerinput", "manual");
    model.result("pg7").set("outersolnum", new int[]{4, 5, 6});
    model.result("pg7").set("titletype", "manual");
    model.result("pg7").set("title", "\u5e73\u5747\u6b63\u6781\u6c34\u5408\u4f5c\u7528\u7a0b\u5ea6");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").setIndex("expr", "comp1.ave_pos(cH_average/c_H_max)", 0);
    model.result("pg7").feature("glob1").setIndex("descr", "\u5e73\u5747\u6c34\u5408\u4f5c\u7528\u7a0b\u5ea6", 0);
    model.result("pg7").feature("glob1").set("xdata", "expr");
    model.result("pg7").feature("glob1").set("xdataexpr", "t*C_rate/1[h]");
    model.result("pg7").feature("glob1").set("legendmethod", "evaluated");
    model.result("pg7").feature("glob1").set("legendpattern", "C/eval(1/C_rate)");
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u7535\u8377\uff1a\u5e73\u5747\u8d1f\u6781\u4f53\u79ef\u5206\u6570");
    model.result("pg8").set("data", "dset4");
    model.result("pg8").set("outerinput", "manual");
    model.result("pg8").set("outersolnum", new int[]{4, 5, 6});
    model.result("pg8").run();
    model.result("pg8").set("titletype", "manual");
    model.result("pg8").set("title", "\u5e73\u5747\u8d1f\u6781\u4f53\u79ef\u5206\u6570");
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("linewidth", "preference");
    model.result("pg8").feature("glob1").setIndex("expr", "comp1.ave_neg(batbe.epss)", 0);
    model.result("pg8").feature("glob1").setIndex("descr", "\u5e73\u5747\u4f53\u79ef\u5206\u6570", 0);
    model.result("pg8").feature("glob1").set("xdata", "expr");
    model.result("pg8").feature("glob1").set("xdataexpr", "t*C_rate/1[h]");
    model.result("pg8").feature("glob1").set("legendmethod", "evaluated");
    model.result("pg8").feature("glob1").set("legendpattern", "C/eval(1/C_rate)");
    model.result("pg8").run();
    model.result("pg1").run();

    model.title("\u954d\u9549\u7535\u6c60\u7b49\u6e29\u6a21\u578b - \u4e00\u7ef4");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u201c\u4e8c\u5143\u7535\u89e3\u8d28\u7535\u6c60\u201d\u63a5\u53e3\u7814\u7a76\u954d\u9549\u7535\u6c60\u7684\u5145\u653e\u7535\uff0c\u5176\u4e2d\u91c7\u7528\u4e00\u7ef4\u51e0\u4f55\u7ed3\u6784\uff0c\u52a0\u4e0a\u4e00\u4e2a\u5706\u67f1\u5f62\u5bf9\u79f0\u7684\u4e00\u7ef4\u51e0\u4f55\uff0c\u7528\u4e8e\u5206\u6790\u6b63\u6781\u7684\u8d28\u5b50\u6269\u6563\u3002\u8fd9\u662f\u4e00\u4e2a\u7b49\u6e29\u6a21\u578b\uff0c\u5176\u4e2d\u8fd8\u5305\u542b\u5176\u4ed6\u4e00\u4e9b\u8fc7\u7a0b\uff1a\u6b63\u6781\u7684\u6790\u6c27/\u8fd8\u539f\uff0c\u7535\u6c60\u5355\u5143\u4e2d\u6c27\u7684\u8d28\u91cf\u4f20\u9012\u4ee5\u53ca\u8d1f\u6781\u7684\u6790\u6c27/\u8fd8\u539f\u3002");

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
    model.sol("sol10").clearSolutionData();

    model.label("nicd_battery_1d.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
