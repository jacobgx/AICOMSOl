/*
 * co2_corrosion.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:44 by COMSOL 6.3.0.290. */
public class co2_corrosion {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Corrosion_Module\\General_Corrosion");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tcd", "TertiaryCurrentDistributionNernstPlanck", "geom1");
    model.component("comp1").physics("tcd").prop("SpeciesProperties").set("ChargeTransportModel", "WaterBased");
    model.component("comp1").physics("tcd").field("concentration").field("cCO2");
    model.component("comp1").physics("tcd").field("concentration")
         .component(new String[]{"cCO2", "cH2CO3", "cHCO3", "cFe"});

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/tcd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("DCO2", "1.96e-9[m^2/s]", "CO2 \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("DH2CO3", "2e-9[m^2/s]", "H2CO3 \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("DHCO3", "1.105e-9[m^2/s]", "HCO3 \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("DH", "9.312e-9[m^2/s]", "H \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("DOH", "5.26e-9[m^2/s]", "OH \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("DFe", "0.72e-9[m^2/s]", "Fe \u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("T", "20[degC]", "\u5de5\u4f5c\u6e29\u5ea6");
    model.param().set("T_init", "20[degC]", "\u521d\u59cb\u5de5\u4f5c\u6e29\u5ea6");
    model.param()
         .set("TF", "(T-0[degC])/1[K]*1.8+32", "\u5de5\u4f5c\u6e29\u5ea6\uff08\u5355\u4f4d\uff1a\u534e\u6c0f\uff09");
    model.param()
         .set("TF_init", "(T_init-0[degC])/1[K]*1.8+32", "\u521d\u59cb\u6e29\u5ea6\uff08\u5355\u4f4d\uff1a\u534e\u6c0f\uff09");
    model.param().set("PCO2", "1", "CO2 \u7684\u5206\u538b\uff08\u5355\u4f4d\uff1abar\uff09");
    model.param().set("PCO2_init", "1", "\u68d2\u4e2d\u521d\u59cb CO2 \u5206\u538b");
    model.param()
         .set("KCO2", "0.0454*(1.6616-5.736e-2*(T[1/K]-273)+1.031e-3*(T[1/K]-273)^2-9.68e-6*(T[1/K]-273)^3+4.471e-8*(T[1/K]-273)^4-7.912e-11*(T[1/K]-273)^5)", "\u4ea8\u5229\u5e38\u6570");
    model.param()
         .set("KCO2_init", "0.0454*(1.6616-5.736e-2*(T_init[1/K]-273)+1.031e-3*(T_init[1/K]-273)^2-9.68e-6*(T_init[1/K]-273)^3+4.471e-8*(T_init[1/K]-273)^4-7.912e-11*(T_init[1/K]-273)^5)", "\u521d\u59cb\u5de5\u4f5c\u6e29\u5ea6\u7684\u4ea8\u5229\u5e38\u6570");
    model.param().set("KCO2H", "2.58e-3", "CO2 \u6c34\u5408\u4f5c\u7528\u5e73\u8861\u5e38\u6570");
    model.param()
         .set("KH2CO3", "387.6*10^(-(6.41-1.594e-3*TF+8.52e-6*(TF)^2))", "H2CO3 \u89e3\u79bb\u5e73\u8861\u5e38\u6570");
    model.param()
         .set("KH2CO3_init", "387.6*10^(-(6.41-1.594e-3*TF_init+8.52e-6*(TF_init)^2))", "\u521d\u59cb\u6e29\u5ea6\u7684 H2CO3 \u89e3\u79bb\u5e73\u8861\u5e38\u6570");
    model.param()
         .set("KHCO3", "10^(-(10.61-4.97e-3*TF+1.331e-5*(TF)^2))", "HCO3 \u89e3\u79bb\u5e73\u8861\u5e38\u6570");
    model.param()
         .set("kCO2H", "10^(169.2-53*log10(T[1/K])-11715/T[1/K])[1/s]", "CO2 \u6c34\u5408\u4f5c\u7528\u6b63\u53cd\u5e94\u901f\u7387");
    model.param().set("c_unit", "1000[mol/m^3]", "\u5355\u4f4d\u6d3b\u6027\u6d53\u5ea6");
    model.param().set("cCO20", "KCO2*PCO2*1e3[mol/m^3]", "CO2 \u7684\u672c\u4f53\u6d53\u5ea6");
    model.param().set("cCO2_init", "KCO2_init*PCO2_init*1e3[mol/m^3]", "CO2 \u7684\u521d\u59cb\u6d53\u5ea6");
    model.param().set("cH2CO30", "KCO2H*cCO20", "H2CO3 \u7684\u672c\u4f53\u6d53\u5ea6");
    model.param().set("cH2CO3_init", "KCO2H*cCO2_init", "H2CO3 \u7684\u521d\u59cb\u6d53\u5ea6");
    model.param().set("cHCO30", "sqrt(KH2CO3*c_unit*cH2CO30)", "HCO3- \u7684\u672c\u4f53\u6d53\u5ea6");
    model.param().set("cHCO3_init", "sqrt(KH2CO3_init*c_unit*cH2CO3_init)", "HCO3- \u7684\u521d\u59cb\u6d53\u5ea6");
    model.param().set("cFe0", "0[mol/m^3]", "Fe++ \u7684\u521d\u59cb\u6d53\u5ea6");
    model.param().set("phil0", "0.5[V]", "\u521d\u59cb\u7535\u89e3\u8d28\u7535\u4f4d");
    model.param().set("delta", "50[um]", "\u8fb9\u754c\u5c42\u539a\u5ea6");
    model.param().set("rho_steel", "7850[kg/m^3]", "\u94a2\u5bc6\u5ea6");
    model.param().set("Mw_steel", "55.845[g/mol]", "\u94a2\u7684\u5206\u5b50\u91cf");
    model.param().set("b_H2", "0.118[V]", "H+ \u8fd8\u539f\u7684 Tafel \u659c\u7387");
    model.param().set("b_Fe", "0.04[V]", "Fe \u6c27\u5316\u7684 Tafel \u659c\u7387");
    model.param().set("TrefFe", "25[degC]", "\u53c2\u8003\u6e29\u5ea6");
    model.param().set("TrefH2", "25[degC]", "\u53c2\u8003\u6e29\u5ea6");
    model.param()
         .set("alphaa_Fe", "log(10)*R_const*T/(b_Fe*F_const)", "\u94c1\u6eb6\u89e3\u7684\u9633\u6781\u4f20\u9012\u7cfb\u6570");
    model.param()
         .set("alphaa_H2", "1-log(10)*R_const*T/(b_H2*F_const)", "\u6790\u6c22\u7684\u9633\u6781\u4f20\u9012\u7cfb\u6570");
    model.param().set("Eeq_ref_Fe", "-0.409[V]", "\u94c1\u6eb6\u89e3\u7684\u53c2\u8003\u5e73\u8861\u7535\u4f4d");
    model.param().set("Eeq_ref_H2", "0[V]", "\u6790\u6c22\u7684\u53c2\u8003\u5e73\u8861\u7535\u4f4d");
    model.param()
         .set("i0_ref_Fe", "1[A/m^2]*exp(-37.5e3[J/mol]/R_const*(1/T-1/TrefFe))", "\u94c1\u6eb6\u89e3\u7684\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param()
         .set("i0_ref_H2", "1[A/m^2]*exp(-30e3[J/mol]/R_const*(1/T-1/TrefH2))", "\u6790\u6c22\u7684\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "delta", 1);
    model.component("comp1").geom("geom1").run("i1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", -1, 2);
    model.component("comp1").physics("tcd").feature("sp1").setIndex("z", 2, 3);
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cCO2", new String[]{"DCO2", "0", "0", "0", "DCO2", "0", "0", "0", "DCO2"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cH2CO3", new String[]{"DH2CO3", "0", "0", "0", "DH2CO3", "0", "0", "0", "DH2CO3"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cHCO3", new String[]{"DHCO3", "0", "0", "0", "DHCO3", "0", "0", "0", "DHCO3"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cFe", new String[]{"DFe", "0", "0", "0", "DFe", "0", "0", "0", "DFe"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("DH", new String[]{"DH", "0", "0", "0", "DH", "0", "0", "0", "DH"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("DOH", new String[]{"DOH", "0", "0", "0", "DOH", "0", "0", "0", "DOH"});
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "cCO2_init", 0);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "cH2CO3_init", 1);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "cHCO3_init", 2);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "cFe0", 3);
    model.component("comp1").physics("tcd").feature("init1").set("initphil", "phil0");
    model.component("comp1").physics("tcd").create("es1", "ElectrodeSurface", 0);
    model.component("comp1").physics("tcd").feature("es1").selection().set(1);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("rhos", "rho_steel", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").setIndex("Ms", "Mw_steel", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("nm", 2);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vi0", -1, 3);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vib", -1, 0, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("Eeq_ref", "Eeq_ref_Fe");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("i0_ref", "i0_ref_Fe");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("alphaa", "alphaa_Fe");
    model.component("comp1").physics("tcd").feature("es1").create("er2", "ElectrodeReaction", 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er2").set("Eeq_ref", "Eeq_ref_H2");
    model.component("comp1").physics("tcd").feature("es1").feature("er2").set("i0_ref", "i0_ref_H2");
    model.component("comp1").physics("tcd").feature("es1").feature("er2").set("alphaa", "alphaa_H2");
    model.component("comp1").physics("tcd").create("eqreac1", "EquilibriumReaction", 1);
    model.component("comp1").physics("tcd").feature("eqreac1").selection().all();
    model.component("comp1").physics("tcd").feature("eqreac1").set("Keq0", "KCO2H");
    model.component("comp1").physics("tcd").feature("eqreac1").setIndex("nu", -1, 0);
    model.component("comp1").physics("tcd").feature("eqreac1").setIndex("nu", 1, 1);
    model.component("comp1").physics("tcd").create("eqreac2", "EquilibriumReaction", 1);
    model.component("comp1").physics("tcd").feature("eqreac2").selection().all();
    model.component("comp1").physics("tcd").feature("eqreac2").set("Keq0", "KH2CO3");
    model.component("comp1").physics("tcd").feature("eqreac2").setIndex("nu", -1, 1);
    model.component("comp1").physics("tcd").feature("eqreac2").setIndex("nu", 1, 2);
    model.component("comp1").physics("tcd").feature("eqreac2").set("nuH", 1);
    model.component("comp1").physics("tcd").create("conc1", "Concentration", 0);
    model.component("comp1").physics("tcd").feature("conc1").selection().set(2);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 3);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "cCO20", 0);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "cFe0", 3);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "1e-6");
    model.component("comp1").mesh("mesh1").feature("edg1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("size1").set("hmax", "1e-7");
    model.component("comp1").mesh("mesh1").run("edg1");

    model.study("std1").setGenPlots(false);
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "DCO2", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m^2/s", 0);
    model.study("std1").feature("param").setIndex("pname", "DCO2", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m^2/s", 0);
    model.study("std1").feature("param").setIndex("pname", "DH2CO3", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "m^2/s", 1);
    model.study("std1").feature("param").setIndex("pname", "DH2CO3", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "m^2/s", 1);
    model.study("std1").feature("param").setIndex("pname", "PCO2", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0.01 0.1 range(0.5,0.5,3)", 0);
    model.study("std1").feature("param").setIndex("pname", "T", 1);
    model.study("std1").feature("param").setIndex("plistarr", "293.15[K] 323.15[K] 353.15[K]", 1);
    model.study("std1").feature("param").set("sweeptype", "filled");
    model.study("std1").feature("stat").set("usestol", true);
    model.study("std1").feature("stat").set("stol", "1e-6");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u6d53\u5ea6");
    model.result("pg1").setIndex("looplevelinput", "last", 1);
    model.result("pg1").setIndex("looplevelinput", "first", 0);
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "PCO2=1 bar\uff0cT=20<sup>\\circ</sup>C");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u5230\u94a2\u8868\u9762\u7684\u8ddd\u79bb (m)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u6d53\u5ea6\u504f\u5dee (mol/m<sup>3</sup>)");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg1").feature("lngr1").set("linewidth", "preference");
    model.result("pg1").feature("lngr1").selection().all();
    model.result("pg1").feature("lngr1").set("expr", "cCO2-cCO20");
    model.result("pg1").feature("lngr1").set("titletype", "none");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").feature("lngr1").set("legendmethod", "manual");
    model.result("pg1").feature("lngr1").setIndex("legends", "CO<sub>2</sub>", 0);
    model.result("pg1").feature().duplicate("lngr2", "lngr1");
    model.result("pg1").run();
    model.result("pg1").feature("lngr2").set("expr", "cH2CO3-cH2CO30");
    model.result("pg1").feature("lngr2").setIndex("legends", "H<sub>2</sub>CO<sub>3</sub>", 0);
    model.result("pg1").feature().duplicate("lngr3", "lngr2");
    model.result("pg1").run();
    model.result("pg1").feature("lngr3").set("expr", "cHCO3-cHCO30");
    model.result("pg1").feature("lngr3").setIndex("legends", "HCO<sub>3</sub><sup>-</sup>", 0);
    model.result("pg1").feature().duplicate("lngr4", "lngr3");
    model.result("pg1").run();
    model.result("pg1").feature("lngr4").set("expr", "cFe-cFe0");
    model.result("pg1").feature("lngr4").setIndex("legends", "Fe<sup>2+</sup>", 0);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u8150\u8680\u901f\u7387");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "CO2 \u5206\u538b (bar)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u8150\u8680\u901f\u7387 (mm/year)");
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").selection().set(1);
    model.result("pg2").feature("ptgr1").set("expr", "tcd.vbtot");
    model.result("pg2").feature("ptgr1").set("descr", "\u7535\u6781\u603b\u53d8\u5316\u901f\u5ea6");
    model.result("pg2").feature("ptgr1").set("unit", "mm/yr");
    model.result("pg2").feature("ptgr1").set("xdatasolnumtype", "level2");
    model.result("pg2").feature("ptgr1").set("xdata", "expr");
    model.result("pg2").feature("ptgr1").set("xdataexpr", "PCO2");
    model.result("pg2").feature("ptgr1").set("legend", true);
    model.result("pg2").feature("ptgr1").set("legendmethod", "evaluated");
    model.result("pg2").feature("ptgr1").set("legendpattern", "T=eval(T) K");
    model.result("pg2").run();
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u8868\u9762 pH");
    model.result("pg3").set("ylabel", "\u8868\u9762 pH");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr1").set("expr", "tcd.pH");
    model.result("pg3").run();
    model.result("pg3").set("legendpos", "upperright");
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u4f53\u5185 pH");
    model.result("pg4").set("ylabel", "\u4f53\u5185 pH");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr1").selection().set(2);
    model.result("pg4").run();

    model.title("\u94a2\u7ba1\u4e2d\u7684\u4e8c\u6c27\u5316\u78b3\u8150\u8680");

    model
         .description("\u6c34\u6eb6\u6db2\u4e2d\u7684\u4e8c\u6c27\u5316\u78b3\u5177\u6709\u5f88\u5f3a\u7684\u8150\u8680\u6027\uff0c\u4f1a\u4e25\u91cd\u5f71\u54cd\u94a2\u7ba1\u7684\u4f7f\u7528\u5bff\u547d\u3002\u7279\u522b\u662f\u77f3\u5316\u884c\u4e1a\u7684\u7ba1\u9053\u5e94\u7528\u4e2d\uff0c\u8fd9\u79cd\u8150\u8680\u5f88\u5e38\u89c1\u3002\u672c\u4f8b\u7814\u7a76\u5728\u7ba1\u9053\u4e2d\u8f93\u9001\u4e8c\u6c27\u5316\u78b3\u548c\u6c34\u5f62\u6210\u6e4d\u6d41\u65f6\uff0c\u53d1\u751f\u5728\u94a2\u8868\u9762\u7684\u8150\u8680\u3002\u6a21\u578b\u4e2d\u8fd8\u7814\u7a76\u4e86\u6e29\u5ea6\u548c pH \u503c\u7684\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("co2_corrosion.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
