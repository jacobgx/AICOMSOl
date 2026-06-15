/*
 * gold_recycling.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:18 by COMSOL 6.3.0.290. */
public class gold_recycling {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Ideal_Tank_Reactors");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("re", "ReactionEng");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/re", true);

    model.component("comp1").physics("re").label("\u6db2\u76f8");
    model.component("comp1").physics("re").prop("reactor").set("Vr", "V_liquid");
    model.component("comp1").physics("re").prop("energybalance").set("T", "T");
    model.component("comp1").physics("re").prop("mixture").set("mixture", "liquid");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param()
         .set("loading_Au", "150[mg/dm^3]", "\u91d1\u7684\u8d28\u91cf\u4e0e\u6db2\u4f53\u4f53\u79ef\u4e4b\u6bd4");
    model.param().set("V_reactor", "1[dm^3]", "\u53cd\u5e94\u5668\u4f53\u79ef");
    model.param().set("V_liquid", "0.98*V_reactor", "\u6db2\u4f53\u7684\u4f53\u79ef");
    model.param().set("m0_Au", "loading_Au*V_liquid", "\u91d1\u7684\u521d\u59cb\u8d28\u91cf");
    model.param().set("rho_Au", "19.3[g/cm^3]", "\u91d1\u7684\u5bc6\u5ea6");
    model.param().set("V0_Au", "m0_Au/rho_Au", "\u91d1\u7684\u521d\u59cb\u672c\u4f53\u4f53\u79ef");
    model.param().set("V0_gas", "V_reactor-V_liquid-V0_Au", "\u6c14\u76f8\u7684\u521d\u59cb\u4f53\u79ef");
    model.param().set("k_Au", "8.6e-6[mol/m^2/s]", "\u6781\u9650\u7387\uff0cG. Senanayake 2005");
    model.param().set("K_ads", "5.3e-3[m^12/mol^4]", "\u5438\u9644\u7cfb\u6570\uff0cWadsworth 2000");
    model.param().set("H_O2", "1.3[mol/m^3/bar]", "\u6c34\u4e2d O2(g) \u7684\u4ea8\u5229\u5b9a\u5f8b\u5e38\u6570");
    model.param().set("T", "298.15[K]", "\u6e29\u5ea6");
    model.param().set("p0_O2g", "0.2[bar]", "\u6c27\u7684\u521d\u59cb\u5206\u538b");
    model.param().set("p0_N2g", "0.8[bar]", "\u6c2e\u7684\u521d\u59cb\u5206\u538b");
    model.param()
         .set("gas_c0_O2g", "p0_O2g/R_const/T", "\u6c14\u76f8\u4e2d\u4ea7\u751f\u7684\u521d\u59cb\u6c27\u6d53\u5ea6");
    model.param().set("Mw_Au", "196.966[g/mol]", "\u91d1\u7684\u6469\u5c14\u8d28\u91cf");
    model.param()
         .set("liquid_c0_Au", "m0_Au/Mw_Au/V_liquid", "\u91d1\u539f\u5b50 (s) \u7684\u865a\u62df\u6d53\u5ea6\uff0c\u5982\u540c\u6eb6\u89e3\u5728\u6db2\u76f8\u4e2d\u4e00\u6837");
    model.param().set("spherical_d0", "150[nm]", "\u7403\u5f62\u91d1\u9897\u7c92\u7684\u521d\u59cb\u76f4\u5f84");
    model.param()
         .set("spherical_S0_V0", "6/spherical_d0", "\u7403\u5f62\u9897\u7c92\u7684\u521d\u59cb\u8868\u9762\u4e0e\u4f53\u79ef\u6bd4");
    model.param().set("spherical_S0", "spherical_S0_V0*V0_Au", "\u91d1\u7684\u521d\u59cb\u603b\u8868\u9762\u79ef");
    model.param().set("cyanide_c0", "3[mol/m^3]", "\u6c34\u5408 CN- \u79bb\u5b50\u7684\u521d\u59cb\u6d53\u5ea6");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("c_prod", "K_ads*re.c_O2_aq*re.c_CN_1m^3", "\u901a\u7528\u5b50\u8868\u8fbe\u5f0f\uff08\u4e3a\u7b80\u6d01\u8d77\u89c1\uff0c\u5df2\u8fdb\u884c\u56e0\u5b50\u5316\u5904\u7406\uff09");
    model.component("comp1").variable("var1")
         .set("Rs_Au", "k_Au*c_prod/(1+c_prod)", "\u8868\u9762\u6eb6\u89e3\u901f\u7387\uff0cSenanayake 2005 \u4e2d\u7684\u65b9\u7a0b 32");
    model.component("comp1").variable("var1")
         .set("flakes_R_Au", "Rs_Au*spherical_S0/V_liquid*step1(re.c_Au_solid/liquid_c0_Au*1e9)", "\u6eb6\u89e3\u7684\u4f53\u79ef\u901f\u7387\uff0c\u7531\u5269\u4f59\u8868\u9762\u79ef\u8c03\u8282");
    model.component("comp1").variable("var1")
         .set("V_Au", "(liquid_c0_Au - re.c_AuCNCN_1m)*V_liquid*Mw_Au/rho_Au", "\u56fa\u76f8\u4f53\u79ef");
    model.component("comp1").variable("var1").set("V_gas", "V_reactor-V_liquid-V_Au", "\u6c14\u76f8\u4f53\u79ef");

    model.component("comp1").physics("re").create("rch1", "ReactionChem", -1);
    model.component("comp1").physics("re").feature("rch1")
         .set("formula", "4 Au(s) + 8 CN- + O2(aq) + 2 H2O => 4 AuCNCN- + 4 OH-");
    model.component("comp1").physics("re").feature("rch1").set("ReactionExpression", "UserDefined");
    model.component("comp1").physics("re").feature("rch1").set("r", "flakes_R_Au/4");
    model.component("comp1").physics("re").feature("rch1").set("bulkFwdOrder", 4);
    model.component("comp1").physics("re").feature("rch1").set("kf", 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "liquid_c0_Au", 0, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "0[mol/dm^3]", 1, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "cyanide_c0", 2, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "55[mol/dm^3]", 3, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "p0_O2g * H_O2", 4, 0);
    model.component("comp1").physics("re").feature("inits1").setIndex("initialValue", "4e-4[mol/dm^3]", 5, 0);

    model.component("comp1").label("\u91d1\u7247\u7684\u6eb6\u89e3");

    model.component("comp1").physics().create("re2", "ReactionEng");

    model.study("std1").feature("time").setSolveFor("/physics/re2", true);

    model.component("comp1").physics("re2").label("\u6c14\u76f8");
    model.component("comp1").physics("re2").prop("reactor").set("reactor", "batch");
    model.component("comp1").physics("re2").prop("reactor").set("Vr", "V_gas");
    model.component("comp1").physics("re2").prop("energybalance").set("T", "T");
    model.component("comp1").physics("re2").create("spec1", "SpeciesChem", -1);
    model.component("comp1").physics("re2").feature("spec1").set("specName", "O2(g)");
    model.component("comp1").physics("re2").create("spec1", "SpeciesChem", -1);
    model.component("comp1").physics("re2").feature("spec1").set("specName", "N2(g)");
    model.component("comp1").physics("re2").create("gconstr1", "GlobalConstraint", -1);
    model.component("comp1").physics("re2").feature("gconstr1").label("\u5206\u5b50\u6c27\u4ea8\u5229\u5b9a\u5f8b");
    model.component("comp1").physics("re2").feature("gconstr1")
         .set("constraintExpression", "re.c_O2_aq - H_O2*R_const*T*re2.c_O2_gas");
    model.component("comp1").physics("re2").create("gconstr2", "GlobalConstraint", -1);
    model.component("comp1").physics("re2").feature("gconstr2").label("\u6c27\u7684\u8d28\u91cf\u5b88\u6052");
    model.component("comp1").physics("re2").feature("gconstr2")
         .set("constraintExpression", "V0_gas*(2*re2.c_O2_gas - 2*re2.c0_O2_gas) + V_liquid*(2*re.c_O2_aq + re.c_H2O + re.c_OH_1m - 2*re.c0_O2_aq - re.c0_H2O - re.c0_OH_1m)");
    model.component("comp1").physics("re2").feature("inits1").setIndex("initialValue", "p0_N2g/R_const/T", 0, 0);
    model.component("comp1").physics("re2").feature("inits1").setIndex("initialValue", "gas_c0_O2g", 1, 0);

    model.func().create("step1", "Step");
    model.func("step1").set("from", -1);

    model.component().copy("comp2", "comp1");

    model.component("comp2").label("\u7403\u5f62\u91d1\u9897\u7c92\u7684\u6eb6\u89e3");

    model.component("comp2").variable("var2").clear();

//    To import content from file, use:
//    model.component("comp2").variable("var2").loadFile("FILENAME");
    model.component("comp2").variable("var2")
         .set("c2_prod", "K_ads*re3.c_O2_aq*re3.c_CN_1m^3", "\u901a\u7528\u5b50\u8868\u8fbe\u5f0f\uff08\u4e3a\u7b80\u6d01\u8d77\u89c1\uff0c\u5df2\u8fdb\u884c\u56e0\u5b50\u5316\u5904\u7406\uff09");
    model.component("comp2").variable("var2")
         .set("Rs2_Au", "k_Au*c2_prod/(1+c2_prod)", "\u8868\u9762\u6eb6\u89e3\u901f\u7387\uff0cSenanayake 2005 \u4e2d\u7684\u65b9\u7a0b 32");
    model.component("comp2").variable("var2")
         .set("spherical_S", "max(re3.c_Au_solid/liquid_c0_Au, 1e-100)^(2/3)*spherical_S0", "\u91d1\u7403\u7684\u77ac\u6001\u603b\u8868\u9762\u79ef");
    model.component("comp2").variable("var2")
         .set("spherical_R_Au", "Rs2_Au*spherical_S/V_liquid", "\u6eb6\u89e3\u7684\u4f53\u79ef\u901f\u7387\uff0c\u7531\u5269\u4f59\u8868\u9762\u79ef\u8c03\u8282");
    model.component("comp2").variable("var2")
         .set("V_Au", "(liquid_c0_Au - re3.c_AuCNCN_1m)*V_liquid*Mw_Au/rho_Au", "\u56fa\u76f8\u4f53\u79ef");
    model.component("comp2").variable("var2").set("V_gas", "V_reactor-V_liquid-V_Au", "\u6c14\u76f8\u4f53\u79ef");

    model.component("comp2").physics("re3").feature("rch1").set("r", "spherical_R_Au/4");
    model.component("comp2").physics("re4").feature("gconstr1")
         .set("constraintExpression", "re3.c_O2_aq - H_O2*R_const*T*re4.c_O2_gas");
    model.component("comp2").physics("re4").feature("gconstr2")
         .set("constraintExpression", "V0_gas*(2*re4.c_O2_gas - 2*re4.c0_O2_gas) + V_liquid*(2*re3.c_O2_aq + re3.c_H2O + re3.c_OH_1m - 2*re3.c0_O2_aq - re3.c0_H2O - re3.c0_OH_1m)");

    model.study("std1").setGenPlots(false);
    model.study("std1").feature("time").set("tunit", "min");
    model.study("std1").feature("time").set("tlist", "range(0,1,600)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u8584\u7247\u548c\u7403\u4f53\u91d1\u7684\u6c34\u6eb6\u89e3");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("markerpos", "datapoints");
    model.result("pg1").feature("glob1").set("linewidth", "preference");
    model.result("pg1").feature("glob1").label("\u6db2\u4f53\uff08\u8584\u7247\uff09");
    model.result("pg1").feature("glob1").set("expr", new String[]{});
    model.result("pg1").feature("glob1").set("descr", new String[]{});
    model.result("pg1").feature("glob1")
         .set("expr", new String[]{"comp1.re.c_Au_solid", "comp1.re.c_CN_1m", "comp1.re.c_AuCNCN_1m", "comp1.re.c_OH_1m", "comp1.re.c_O2_aq"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u8584\u7247\uff1a<I>n</I><sub>Au(s)</sub>/<I>V</I><sub>aq</sub>", "\u8584\u7247\uff1a[CN<sup>-</sup>]", "\u8584\u7247\uff1a[Au(CN)<sup>-</sup><sub>2</sub>]", "\u8584\u7247\uff1a[OH<sup>-</sup>]", "\u8584\u7247\uff1a[O<sub>2</sub>(aq)]"});
    model.result("pg1").feature("glob1").set("autodescr", true);
    model.result("pg1").feature("glob1").set("autoexpr", false);
    model.result("pg1").run();
    model.result("pg1").create("glob2", "Global");
    model.result("pg1").feature("glob2").set("markerpos", "datapoints");
    model.result("pg1").feature("glob2").set("linewidth", "preference");
    model.result("pg1").feature("glob2").label("\u6db2\u4f53\uff08\u7403\u4f53\uff09");
    model.result("pg1").feature("glob2").set("expr", new String[]{});
    model.result("pg1").feature("glob2").set("descr", new String[]{});
    model.result("pg1").feature("glob2")
         .set("expr", new String[]{"comp2.re3.c_Au_solid", "comp2.re3.c_CN_1m", "comp2.re3.c_AuCNCN_1m", "comp2.re3.c_OH_1m", "comp2.re3.c_O2_aq"});
    model.result("pg1").feature("glob2")
         .set("descr", new String[]{"\u7403\u4f53\uff1a<I>n</I><sub>Au(s)</sub>/<I>V</I><sub>aq</sub>", "\u7403\u4f53\uff1a[CN<sup>-</sup>]", "\u7403\u4f53\uff1a[Au(CN)<sup>-</sup><sub>2</sub>]", "\u7403\u4f53\uff1a[OH<sup>-</sup>]", "\u7403\u4f53\uff1a[O<sub>2</sub>(aq)]"});
    model.result("pg1").feature("glob2").set("linestyle", "dashed");
    model.result("pg1").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg1").feature("glob2").set("autodescr", true);
    model.result("pg1").feature("glob2").set("autoexpr", false);
    model.result("pg1").run();
    model.result("pg1").create("glob3", "Global");
    model.result("pg1").feature("glob3").set("markerpos", "datapoints");
    model.result("pg1").feature("glob3").set("linewidth", "preference");
    model.result("pg1").feature("glob3").label("\u6c14\u4f53");
    model.result("pg1").feature("glob3").set("expr", new String[]{});
    model.result("pg1").feature("glob3").set("descr", new String[]{});
    model.result("pg1").feature("glob3")
         .set("expr", new String[]{"R_const*T*(comp1.re2.c_O2_gas)", "R_const*T*(comp2.re4.c_O2_gas)"});
    model.result("pg1").feature("glob3")
         .set("descr", new String[]{"\u8584\u7247\uff1a<I>p</I><sub>O<sub>2</sub></sub>", "\u7403\u4f53\uff1a<I>p</I><sub>O<sub>2</sub></sub>"});
    model.result("pg1").feature("glob3").set("linestyle", "cycle");
    model.result("pg1").feature("glob3").set("linecolor", "black");
    model.result("pg1").feature("glob3").set("autodescr", true);
    model.result("pg1").feature("glob3").set("autoexpr", false);
    model.result("pg1").run();
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("twoyaxes", true);
    model.result("pg1").setIndex("plotonsecyaxis", true, 2, 1);
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u6d53\u5ea6 (mM)");
    model.result("pg1").set("yseclabelactive", true);
    model.result("pg1").set("yseclabel", "\u5206\u538b (Pa)");
    model.result("pg1").set("axislimits", true);
    model.result("pg1").set("ymin", 0);
    model.result("pg1").set("yminsec", 0);
    model.result("pg1").set("xmax", 600);
    model.result("pg1").set("legendlayout", "outside");
    model.result("pg1").run();

    model.title("\u901a\u8fc7\u6c27\u5316\u6eb6\u89e3\u56de\u6536\u9ec4\u91d1");

    model
         .description("\u672c\u6a21\u578b\u7814\u7a76\u9ec4\u91d1\u8fd9\u79cd\u8d35\u91d1\u5c5e\u5728\u7a7a\u6c14\u9971\u548c\u6c30\u5316\u7269\u6eb6\u6db2\u4e2d\u7684\u6c27\u5316\u6eb6\u89e3\u3002\u8fd9\u79cd\u7cfb\u7edf\u5305\u542b\u4e09\u4e2a\u76f8\uff1a\u6c14\u76f8\uff08\u7a7a\u6c14\uff09\u3001\u6c34\u76f8\u548c\u56fa\u6001\u91d1\u76f8\u3002\u672c\u4f8b\u5047\u8bbe\u5b83\u5728\u5b8f\u89c2\u5c3a\u5ea6\u4e0a\u662f\u5747\u5300\u7684\uff0c\u4f8b\u5982\uff0c\u5206\u6563\u5728\u6c34\u4e2d\u7684\u7ec6\u9897\u7c92\u7269\u8d28\u88ab\u6c14\u6ce1\u6d41\u4e0d\u65ad\u6405\u52a8\u3002");

    model.label("gold_recycling.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
