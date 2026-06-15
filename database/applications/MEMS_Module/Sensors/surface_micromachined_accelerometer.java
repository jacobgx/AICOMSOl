/*
 * surface_micromachined_accelerometer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:05 by COMSOL 6.3.0.290. */
public class surface_micromachined_accelerometer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Sensors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics("es").prop("ShapeProperty").set("order_electricpotential", "2m");
    model.component("comp1").physics("es").create("ccns1", "ChargeConservationSolid");
    model.component("comp1").physics("es").feature("ccns1").selection().all();

    model.component("comp1").multiphysics().create("eme1", "ElectromechanicalForces", 3);
    model.component("comp1").multiphysics("eme1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("eme1").set("Electrostatics_physics", "es");

    model.component("comp1").common().create("free1", "DeformingDomain");
    model.component("comp1").common("free1").set("smoothingType", "hyperelastic");
    model.component("comp1").common("free1").selection().set();
    model.component("comp1").common().create("sym1", "Symmetry");
    model.component("comp1").common("sym1").selection().set();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/physics/es", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/eme1", true);

    model.geom()
         .load(new String[]{"part1", "part2", "part3"}, "surface_micromachined_accelerometer_geom_subsequence.mph", new String[]{"part1", "part2", "part3"});

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("acceleration", "0", "\u52a0\u901f\u5ea6 (g)");
    model.param().set("VtestL", "0[V]", "\u6d4b\u8bd5\u7535\u538b\uff0c\u5de6\u4fa7");
    model.param().set("VtestR", "0[V]", "\u6d4b\u8bd5\u7535\u538b\uff0c\u53f3\u4fa7");
    model.param().set("tSi", "2[um]", "\u7845\u539a\u5ea6");
    model.param().set("tOx", "1.6[um]", "\u6c27\u5316\u5c42\u539a\u5ea6");
    model.param().set("l_PM", "448[um]", "\u68c0\u6d4b\u8d28\u91cf\u5757\u957f\u5ea6");
    model.param().set("w_PM", "100[um]", "\u68c0\u6d4b\u8d28\u91cf\u5757\u5bbd\u5ea6");
    model.param().set("n_st", "3", "\u81ea\u68c0\u6307\u7684\u6570\u91cf");
    model.param().set("n_f", "21", "\u611f\u5e94\u6307\u7684\u6570\u91cf");
    model.param().set("w_f", "4[um]", "\u6307\u5bbd");
    model.param().set("l_f", "114[um]", "\u6307\u957f");
    model.param().set("g_f", "1[um]", "\u6307\u95f4\u9699");
    model.param().set("g_st", "3[um]", "\u81ea\u68c0\u6307\u95f4\u9699");
    model.param().set("x_st", "3[um]+1*(w_f+g_st)", "\u81ea\u68c0\u6307\u8d77\u59cb\u4f4d\u7f6e");
    model.param().set("x_f", "(l_PM-(n_f-1)*3*(w_f+g_f)-w_f)/2", "\u611f\u5e94\u6307\u8d77\u59cb\u4f4d\u7f6e");
    model.param().set("w_eh", "4[um]", "\u523b\u8680\u5b54\u5927\u5c0f");
    model.param().set("p_eh", "18[um]", "\u523b\u8680\u5b54\u5468\u671f");
    model.param().set("l_sp", "280[um]", "\u5f39\u7c27\u957f\u5ea6");
    model.param().set("w_sp", "2[um]", "\u5f39\u7c27\u5bbd\u5ea6");
    model.param().set("g_sp", "1[um]", "\u5f39\u7c27\u95f4\u9699");
    model.param().set("w_sp_conn", "4[um]", "\u5f39\u7c27\u8fde\u63a5\u5bbd\u5ea6");
    model.param().set("l_anch_base", "17[um]", "\u951a\u5e95\u5ea7\u957f\u5ea6");
    model.param().set("w_anch_base", "17[um]", "\u951a\u5e95\u5ea7\u5bbd\u5ea6");
    model.param().set("r_anch", "3[um]", "\u951a\u534a\u5f84");
    model.param().set("x_anch", "12[um]", "\u951a\u4f4d\u7f6e");
    model.param().set("l_e_s", "120[um]", "\u77ed\u7535\u6781\u957f\u5ea6");
    model.param().set("l_e_l", "140[um]", "\u957f\u7535\u6781\u957f\u5ea6");
    model.param().set("l_p", "16[um]", "\u6781\u677f\u957f\u5ea6");
    model.param().set("w_p", "8[um]", "\u6781\u677f\u5bbd\u5ea6");
    model.param().set("r_an", "3[um]", "\u7535\u6781\u951a\u534a\u5f84");
    model.param().set("l_ovrlp", "104[um]", "\u6307\u91cd\u53e0\u957f\u5ea6");
    model.param().set("l_spAssm", "l_anch_base+2*(w_f+w_sp)+3*g_sp", "\u5f39\u7c27\u88c5\u914d\u957f\u5ea6");
    model.param().set("l_polySi", "l_PM+2*l_spAssm", "\u603b\u957f\u5ea6");
    model.param().set("hw_polySi", "w_PM/2+l_f+l_p+l_e_l-l_ovrlp", "\u603b\u534a\u5bbd");

    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1")
         .label("\u96f6\u4ef6\u94fe\u63a5\uff1a\u68c0\u6d4b\u8d28\u91cf\u5757");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "l_PM", "l_PM");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "w_PM", "w_PM");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "t_PM", "tSi");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "l_f", "l_f");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "w_f", "w_f");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "n_st", "n_st");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "n_f", "n_f");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "g_f", "g_f");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "g_st", "g_st");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "x_st", "x_st");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "x_f", "x_f");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "w_eh", "w_eh");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "p_eh", "p_eh");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi2").label("\u96f6\u4ef6\u94fe\u63a5\uff1a\u5f39\u7c27 1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "l_sp", "l_sp");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "w_sp", "w_sp");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "g_sp", "g_sp");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "w_sp_conn", "w_sp_conn");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "w_f", "w_f");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "l_anch_base", "l_anch_base");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "w_anch_base", "w_anch_base");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "r_anch", "r_anch");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "x_anch", "x_anch");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "t_sp", "tSi");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "t_anch", "tOx");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "x_sp", "l_PM");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().duplicate("pi3", "pi2");
    model.component("comp1").geom("geom1").feature("pi2").set("selcontributetoobj", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepobj", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi2").set("selcontributetopnt", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeeppnt", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi2").set("selshowpnt", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi2").set("selcontributetoedg", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepedg", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi2").set("selshowedg", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi2").set("selcontributetobnd", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepbnd", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi2").set("selshowbnd", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi2").set("selcontributetodom", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepdom", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi2").set("selshowdom", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi3").label("\u96f6\u4ef6\u94fe\u63a5\uff1a\u5f39\u7c27 2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "mirror", 0);
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "l_sp", "l_sp+10[um]");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "w_anch_base", "w_anch_base+10[um]");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "x_sp", "0[um]");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("pi4", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi4").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi4").set("part", "part3");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "LH", 0);
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "l_e", "l_e_l");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "w_e", "w_f");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "l_p", "l_p");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "w_p", "w_p");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "r_an", "r_an");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "t_e", "tSi");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "t_an", "tOx");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "n_e", "n_f+1");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "p_e", "3*(w_f+g_f)");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "x_e", "x_f-w_f-g_f");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "y_e", "w_PM/2+l_f-l_ovrlp");
    model.component("comp1").geom("geom1").feature("pi4")
         .label("\u96f6\u4ef6\u94fe\u63a5\uff1a\u611f\u5e94\u7535\u6781 L");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature().duplicate("pi5", "pi4");
    model.component("comp1").geom("geom1").feature("pi4").set("selcontributetoobj", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi4").set("selkeepobj", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi4").set("selcontributetopnt", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi4").set("selkeeppnt", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi4").set("selshowpnt", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi4").set("selcontributetoedg", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi4").set("selkeepedg", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi4").set("selshowedg", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi4").set("selcontributetobnd", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi4").set("selkeepbnd", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi4").set("selshowbnd", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi4").set("selcontributetodom", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi4").set("selkeepdom", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi4").set("selshowdom", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi5")
         .label("\u96f6\u4ef6\u94fe\u63a5\uff1a\u611f\u5e94\u7535\u6781 R");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "LH", 1);
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "l_e", "l_e_s");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "x_e", "x_f-2*(w_f+g_f)");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().duplicate("pi6", "pi5");
    model.component("comp1").geom("geom1").feature("pi5").set("selcontributetoobj", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi5").set("selkeepobj", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi5").set("selcontributetopnt", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi5").set("selkeeppnt", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi5").set("selshowpnt", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi5").set("selcontributetoedg", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi5").set("selkeepedg", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi5").set("selshowedg", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi5").set("selcontributetobnd", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi5").set("selkeepbnd", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi5").set("selshowbnd", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi5").set("selcontributetodom", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi5").set("selkeepdom", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi5").set("selshowdom", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi6")
         .label("\u96f6\u4ef6\u94fe\u63a5\uff1a\u81ea\u68c0\u7535\u6781 L 1");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "LH", 0);
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "n_e", "n_st");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "p_e", "3*w_f+2*g_f+g_st");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("inputexpr", "x_e", "x_st-w_f-g_f");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().duplicate("pi7", "pi6");
    model.component("comp1").geom("geom1").feature("pi6").set("selcontributetoobj", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi6").set("selkeepobj", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi6").set("selcontributetopnt", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi6").set("selkeeppnt", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi6").set("selshowpnt", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi6").set("selcontributetoedg", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi6").set("selkeepedg", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi6").set("selshowedg", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi6").set("selcontributetobnd", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi6").set("selkeepbnd", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi6").set("selshowbnd", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi6").set("selcontributetodom", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi6").set("selkeepdom", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi6").set("selshowdom", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi7")
         .label("\u96f6\u4ef6\u94fe\u63a5\uff1a\u81ea\u68c0\u7535\u6781 L 2");
    model.component("comp1").geom("geom1").feature("pi7").setEntry("inputexpr", "l_e", "l_e_l");
    model.component("comp1").geom("geom1").feature("pi7")
         .setEntry("inputexpr", "x_e", "l_PM-(x_st+w_f+g_f)-(n_st-1)*(3*w_f+2*g_f+g_st)-w_f");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().duplicate("pi8", "pi7");
    model.component("comp1").geom("geom1").feature("pi7").set("selcontributetoobj", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi7").set("selkeepobj", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi7").set("selcontributetopnt", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi7").set("selkeeppnt", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi7").set("selshowpnt", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi7").set("selcontributetoedg", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi7").set("selkeepedg", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi7").set("selshowedg", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi7").set("selcontributetobnd", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi7").set("selkeepbnd", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi7").set("selshowbnd", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi7").set("selcontributetodom", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi7").set("selkeepdom", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi7").set("selshowdom", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi8")
         .label("\u96f6\u4ef6\u94fe\u63a5\uff1a\u81ea\u68c0\u7535\u6781 R 1");
    model.component("comp1").geom("geom1").feature("pi8").setEntry("inputexpr", "LH", 1);
    model.component("comp1").geom("geom1").feature("pi8").setEntry("inputexpr", "x_e", "x_st-w_f-g_f+2*(w_f+g_f)");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature().duplicate("pi9", "pi8");
    model.component("comp1").geom("geom1").feature("pi8").set("selcontributetoobj", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi8").set("selkeepobj", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi8").set("selcontributetopnt", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi8").set("selkeeppnt", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi8").set("selshowpnt", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi8").set("selcontributetoedg", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi8").set("selkeepedg", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi8").set("selshowedg", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi8").set("selcontributetobnd", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi8").set("selkeepbnd", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi8").set("selshowbnd", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi8").set("selcontributetodom", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi8").set("selkeepdom", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi8").set("selshowdom", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi9")
         .label("\u96f6\u4ef6\u94fe\u63a5\uff1a\u81ea\u68c0\u7535\u6781 R 2");
    model.component("comp1").geom("geom1").feature("pi9").setEntry("inputexpr", "l_e", "l_e_s");
    model.component("comp1").geom("geom1").feature("pi9")
         .setEntry("inputexpr", "x_e", "l_PM-(x_st+w_f+g_f)-(n_st-1)*(3*w_f+2*g_f+g_st)-w_f+2*(w_f+g_f)");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").label("\u6c14\u7bb1");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"l_polySi+40[um]", "1", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "hw_polySi+20[um]", 1);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "10[um]", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"-l_spAssm-20[um]", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("pos", "-tOx", 2);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", "tOx", 0);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", "tSi", 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").label("\u63a5\u5730\u5e73\u9762");
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", "-tOx");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"l_PM", "w_PM/2+l_f"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "tOx", 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u591a\u6676\u7845");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetodom", "pi1_csel1.dom", "csel1");
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u68c0\u6d4b\u8d28\u91cf\u5757\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_csel1.bnd", "csel2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetodom", "pi2_csel1.dom", "csel1");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetobnd", "pi2_csel1.bnd", "csel2");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetodom", "pi3_csel1.dom", "csel1");
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetobnd", "pi3_csel1.bnd", "csel2");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("selcontributetodom", "pi4_csel1.dom", "csel1");
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("\u611f\u5e94\u7535\u6781\u5de6\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("pi4").setEntry("selcontributetobnd", "pi4_csel1.bnd", "csel3");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("selcontributetodom", "pi5_csel1.dom", "csel1");
    model.component("comp1").geom("geom1").selection().create("csel4", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel4").label("\u611f\u5e94\u7535\u6781\u53f3\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("pi5").setEntry("selcontributetobnd", "pi5_csel1.bnd", "csel4");
    model.component("comp1").geom("geom1").selection().create("csel5", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel5").label("\u81ea\u68c0\u7535\u6781\u5de6\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("selcontributetobnd", "pi6_csel1.bnd", "csel5");
    model.component("comp1").geom("geom1").feature("pi7").setEntry("selcontributetodom", "pi7_csel1.dom", "csel1");
    model.component("comp1").geom("geom1").feature("pi7").setEntry("selcontributetobnd", "pi7_csel1.bnd", "csel5");
    model.component("comp1").geom("geom1").feature("pi8").setEntry("selcontributetodom", "pi8_csel1.dom", "csel1");
    model.component("comp1").geom("geom1").selection().create("csel6", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel6").label("\u81ea\u68c0\u7535\u6781\u53f3\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("pi8").setEntry("selcontributetobnd", "pi8_csel1.bnd", "csel6");
    model.component("comp1").geom("geom1").feature("pi9").setEntry("selcontributetodom", "pi9_csel1.dom", "csel1");
    model.component("comp1").geom("geom1").feature("pi9").setEntry("selcontributetobnd", "pi9_csel1.bnd", "csel6");
    model.component("comp1").geom("geom1").feature("pi6").setEntry("selcontributetodom", "pi6_csel1.dom", "csel1");

    model.component("comp1").selection().create("box1", "Box");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("box1").set("entitydim", 2);
    model.component("comp1").selection("box1").set("ymin", -0.1);
    model.component("comp1").selection("box1").set("ymax", 0.1);
    model.component("comp1").selection("box1").set("condition", "inside");
    model.component("comp1").selection("box1").label("\u5bf9\u79f0\u5e73\u9762");
    model.component("comp1").selection().create("box2", "Box");
    model.component("comp1").selection("box2").label("\u951a\u5e73\u9762");
    model.component("comp1").selection("box2").set("entitydim", 2);
    model.component("comp1").selection("box2").set("zmin", "-tOx*1.01");
    model.component("comp1").selection("box2").set("zmax", "-tOx*0.99");
    model.component("comp1").selection("box2").set("condition", "inside");
    model.component("comp1").selection().create("box3", "Box");
    model.component("comp1").selection("box3").label("\u7f51\u683c\u5212\u5206\u5e73\u9762");
    model.component("comp1").selection("box3").set("entitydim", 2);
    model.component("comp1").selection("box3").set("zmin", "-tOx*0.01");
    model.component("comp1").selection("box3").set("zmax", "tOx*0.01");
    model.component("comp1").selection("box3").set("condition", "inside");

    model.title("\u8868\u9762\u5fae\u673a\u68b0\u52a0\u901f\u5ea6\u8ba1\u51e0\u4f55");

    model
         .description("\u8fd9\u662f\u4e00\u4e2a\u6a21\u677f MPH \u6587\u4ef6\uff0c\u5305\u542b\u201c\u8868\u9762\u5fae\u673a\u68b0\u52a0\u901f\u5ea6\u8ba1\u201d\u6a21\u578b\u7684\u7269\u7406\u573a\u63a5\u53e3\u548c\u53c2\u6570\u5316\u51e0\u4f55\u3002");

    model.label("surface_micromachined_accelerometer_geom.mph");

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("Si - Polycrystalline silicon");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.7);
    model.component("comp1").material("mat1").set("roughness", 0.5);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]", "0", "0", "0", "2.6e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "678[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.5", "0", "0", "0", "4.5", "0", "0", "0", "4.5"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2320[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"34[W/(m*K)]", "0", "0", "0", "34[W/(m*K)]", "0", "0", "0", "34[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "160e9[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.22");
    model.component("comp1").material("mat1").selection().named("geom1_csel1_dom");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u6240\u6709\u57df");
    model.component("comp1").selection("sel1").all();
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("\u7a7a\u6c14");
    model.component("comp1").selection("dif1").set("add", new String[]{"sel1"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"geom1_csel1_dom"});

    model.component("comp1").common("free1").selection().named("dif1");

    model.component("comp1").physics("solid").selection().named("geom1_csel1_dom");
    model.component("comp1").physics("solid").create("bl1", "BodyLoad", 3);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("solid").feature("bl1")
         .set("forceReferenceVolume", new String[]{"acceleration*solid.rho*g_const", "0", "0"});
    model.component("comp1").physics("solid").feature("bl1").selection().named("geom1_csel1_dom");
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("box2");
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().named("box1");

    model.component("comp1").common("sym1").selection().named("box1");

    model.component("comp1").physics("es").feature("ccns1").selection().named("geom1_csel1_dom");
    model.component("comp1").physics("es").create("gnd1", "Ground", 2);
    model.component("comp1").physics("es").feature("gnd1").selection().set(45);
    model.component("comp1").physics("es").create("term1", "Terminal", 2);
    model.component("comp1").physics("es").feature("term1").label("\u611f\u5e94\u7ec8\u7aef L");
    model.component("comp1").physics("es").feature("term1").selection().named("geom1_csel3_bnd");
    model.component("comp1").physics("es").feature("term1").set("TerminalType", "Voltage");
    model.component("comp1").physics("es").feature("term1").set("V0", "-2.5[mV]");
    model.component("comp1").physics("es").create("term2", "Terminal", 2);
    model.component("comp1").physics("es").feature("term2").label("\u611f\u5e94\u7ec8\u7aef R");
    model.component("comp1").physics("es").feature("term2").selection().named("geom1_csel4_bnd");
    model.component("comp1").physics("es").feature("term2").set("TerminalType", "Voltage");
    model.component("comp1").physics("es").feature("term2").set("V0", "2.5[mV]");
    model.component("comp1").physics("es").create("fp1", "FloatingPotential", 2);
    model.component("comp1").physics("es").feature("fp1").selection().named("geom1_csel2_bnd");
    model.component("comp1").physics("es").create("term3", "Terminal", 2);
    model.component("comp1").physics("es").feature("term3").label("\u81ea\u68c0\u7ec8\u7aef L");
    model.component("comp1").physics("es").feature("term3").selection().named("geom1_csel5_bnd");
    model.component("comp1").physics("es").feature("term3").set("TerminalType", "Voltage");
    model.component("comp1").physics("es").feature("term3").set("V0", "VtestL");
    model.component("comp1").physics("es").create("term4", "Terminal", 2);
    model.component("comp1").physics("es").feature("term4").label("\u81ea\u68c0\u7ec8\u7aef R");
    model.component("comp1").physics("es").feature("term4").selection().named("geom1_csel6_bnd");
    model.component("comp1").physics("es").feature("term4").set("TerminalType", "Voltage");
    model.component("comp1").physics("es").feature("term4").set("V0", "VtestR");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("box3");
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1\uff1a\u6b63\u5e38\u8fd0\u884c");
    model.study("std1").feature("stat").set("plot", true);
    model.study("std1").feature("stat").set("probefreq", "psteps");
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "acceleration", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "acceleration", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(-50,25,50)", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_spatial_disp").set("scaleval", "1e-7");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-7");
    model.sol("sol1").feature("v1").feature("comp1_es_depV").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_es_depV").set("scaleval", "1e-3");
    model.sol("sol1").feature("v1").feature("comp1_es_fp1_V0_ode").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_es_fp1_V0_ode").set("scaleval", "5e-5");
    model.sol("sol1").feature("s1").feature("se1").feature().move("ss1", 1);
    model.sol("sol1").feature("s1").feature("se1").feature().move("ss1", 2);
    model.sol("sol1").feature("s1").feature("se1").feature("ss2").set("linsolver", "dDef");
    model.sol("sol1").feature("s1").feature("se1").feature("ss3").set("linsolver", "dDef");
    model.sol("sol1").feature("s1").feature("se1").feature("ss1").set("linsolver", "dDef");

    model.study("std1").createAutoSequences("all");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u4f4d\u79fb (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.disp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "SpectrumLight");

    model.sol("sol1").runAll();

    model.result().remove("pg1");

    model.study("std1").feature("stat").set("plotgroup", "Default");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 5, 0);
    model.result("pg1").label("\u4f4d\u79fb (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.disp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "SpectrumLight");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u7535\u52bf (es)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("solutionparams", "parent");
    model.result("pg2").feature("mslc1").set("expr", "V");
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg2").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg2").feature("mslc1").set("colortable", "Dipole");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("data", "parent");
    model.result("pg2").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg2").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg2").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg2").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg2").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg2").feature("strmsl1").set("titletype", "none");
    model.result("pg2").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg2").feature("strmsl1").set("udist", 0.02);
    model.result("pg2").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg2").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("inheritcolor", false);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("data", "parent");
    model.result("pg2").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg2").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg2").feature("strmsl1").feature("col1").set("expr", "V");
    model.result("pg2").feature("strmsl1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg2").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u7535\u573a (es)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("mslc1", "Multislice");
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("solutionparams", "parent");
    model.result("pg3").feature("mslc1").set("expr", "es.normE");
    model.result("pg3").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg3").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg3").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg3").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg3").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg3").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg3").feature("mslc1").set("colortable", "Prism");
    model.result("pg3").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("data", "parent");
    model.result("pg3").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg3").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg3").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg3").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg3").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg3").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg3").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg3").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg3").feature("strmsl1").set("titletype", "none");
    model.result("pg3").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg3").feature("strmsl1").set("udist", 0.02);
    model.result("pg3").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg3").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("inheritcolor", false);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg3").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("strmsl1").set("data", "parent");
    model.result("pg3").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg3").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg3").feature("strmsl1").feature("col1").set("expr", "es.normE");
    model.result("pg3").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg3").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg3").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg3").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 5, 0);
    model.result("pg4").label("\u52a8\u7f51\u683c");
    model.result("pg4").create("mesh1", "Mesh");
    model.result("pg4").feature("mesh1").set("meshdomain", "volume");
    model.result("pg4").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg4").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg4").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg4").feature("mesh1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198);
    model.result("pg4").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg4").feature("mesh1").set("qualexpr", "comp1.spatial.relVol");
    model.result("pg4").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("expr", "u");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("mslc1").set("unit", "mV");
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "number");
    model.result("pg2").feature("mslc1").set("xnumber", "0");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "number");
    model.result("pg2").feature("mslc1").set("ynumber", "0");
    model.result("pg2").run();
    model.result("pg2").feature("strmsl1").set("multiplanexmethod", "number");
    model.result("pg2").feature("strmsl1").set("xnumber", "0");
    model.result("pg2").feature("strmsl1").set("multiplaneymethod", "number");
    model.result("pg2").feature("strmsl1").set("ynumber", "0");
    model.result("pg2").run();
    model.result("pg2").feature("strmsl1").feature("col1").set("unit", "mV");
    model.result("pg2").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr1").set("linewidth", "preference");
    model.result("pg5").feature("ptgr1").selection().set(65);
    model.result("pg5").feature("ptgr1").set("expr", "u");
    model.result("pg5").run();
    model.result("pg5").label("\u4f4d\u79fb vs. \u52a0\u901f\u5ea6");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u52a0\u901f\u5ea6 (g)");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").setIndex("expr", "es.fp1.V0*1000", 0);
    model.result("pg6").feature("glob1").setIndex("unit", "mV", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "\u539f\u59cb\u611f\u5e94\u7535\u538b", 0);
    model.result("pg6").feature("glob1").set("legend", false);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").label("\u611f\u5e94\u7535\u538b vs. \u52a0\u901f\u5ea6");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").setSolveFor("/physics/es", true);
    model.study("std2").feature("stat").setSolveFor("/multiphysics/eme1", true);
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "acceleration", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "", 0);
    model.study("std2").feature("stat").setIndex("pname", "acceleration", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "", 0);
    model.study("std2").feature("stat").setIndex("pname", "VtestL", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "2 0", 0);
    model.study("std2").feature("stat").setIndex("pname", "acceleration", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "", 1);
    model.study("std2").feature("stat").setIndex("punit", "", 1);
    model.study("std2").feature("stat").setIndex("pname", "acceleration", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "", 1);
    model.study("std2").feature("stat").setIndex("punit", "", 1);
    model.study("std2").feature("stat").setIndex("pname", "VtestR", 1);
    model.study("std2").feature("stat").setIndex("plistarr", "0 2", 1);
    model.study("std2").feature("stat").set("pcontinuationmode", "no");
    model.study("std2").label("\u7814\u7a76 2\uff1a\u81ea\u68c0");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("v1").feature("comp1_spatial_disp").set("scaleval", "1e-8");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scaleval", "1e-8");
    model.sol("sol2").feature("v1").feature("comp1_es_depV").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_es_depV").set("scaleval", 1);
    model.sol("sol2").feature("v1").feature("comp1_es_fp1_V0_ode").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_es_fp1_V0_ode").set("scaleval", 0.1);
    model.sol("sol2").feature("s1").feature("se1").feature("ss1").set("linsolver", "dDef");
    model.sol("sol2").feature("s1").feature("se1").feature("ss2").set("linsolver", "dDef");
    model.sol("sol2").feature("s1").feature("se1").feature("ss3").set("linsolver", "dDef");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").setIndex("looplevel", 2, 0);
    model.result("pg7").label("\u4f4d\u79fb (solid) 1");
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").create("vol1", "Volume");
    model.result("pg7").feature("vol1").set("expr", new String[]{"solid.disp"});
    model.result("pg7").feature("vol1").set("threshold", "manual");
    model.result("pg7").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg7").feature("vol1").set("colortable", "Rainbow");
    model.result("pg7").feature("vol1").set("colortabletrans", "none");
    model.result("pg7").feature("vol1").set("colorscalemode", "linear");
    model.result("pg7").feature("vol1").set("resolution", "custom");
    model.result("pg7").feature("vol1").set("refine", 2);
    model.result("pg7").feature("vol1").set("colortable", "SpectrumLight");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").label("\u7535\u52bf (es) 1");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").setIndex("looplevel", 2, 0);
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").set("showlegendsmaxmin", true);
    model.result("pg8").feature().create("mslc1", "Multislice");
    model.result("pg8").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg8").feature("mslc1").set("solutionparams", "parent");
    model.result("pg8").feature("mslc1").set("expr", "V");
    model.result("pg8").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg8").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg8").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg8").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg8").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg8").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg8").feature("mslc1").set("colortable", "Dipole");
    model.result("pg8").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg8").feature("mslc1").set("data", "parent");
    model.result("pg8").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg8").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg8").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg8").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg8").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg8").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg8").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg8").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg8").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg8").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg8").feature("strmsl1").set("titletype", "none");
    model.result("pg8").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg8").feature("strmsl1").set("udist", 0.02);
    model.result("pg8").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg8").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg8").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg8").feature("strmsl1").set("inheritcolor", false);
    model.result("pg8").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg8").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg8").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg8").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg8").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg8").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg8").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg8").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg8").feature("strmsl1").set("data", "parent");
    model.result("pg8").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg8").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg8").feature("strmsl1").feature("col1").set("expr", "V");
    model.result("pg8").feature("strmsl1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg8").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg8").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg8").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").label("\u7535\u573a (es) 1");
    model.result("pg9").set("data", "dset2");
    model.result("pg9").setIndex("looplevel", 2, 0);
    model.result("pg9").set("frametype", "spatial");
    model.result("pg9").set("showlegendsmaxmin", true);
    model.result("pg9").feature().create("mslc1", "Multislice");
    model.result("pg9").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg9").feature("mslc1").set("solutionparams", "parent");
    model.result("pg9").feature("mslc1").set("expr", "es.normE");
    model.result("pg9").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg9").feature("mslc1").set("xcoord", "es.CPx");
    model.result("pg9").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg9").feature("mslc1").set("ycoord", "es.CPy");
    model.result("pg9").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg9").feature("mslc1").set("zcoord", "es.CPz");
    model.result("pg9").feature("mslc1").set("colortable", "Prism");
    model.result("pg9").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg9").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg9").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg9").feature("mslc1").set("data", "parent");
    model.result("pg9").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg9").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg9").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg9").feature("strmsl1").set("expr", new String[]{"es.Ex", "es.Ey", "es.Ez"});
    model.result("pg9").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg9").feature("strmsl1").set("xcoord", "es.CPx");
    model.result("pg9").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg9").feature("strmsl1").set("ycoord", "es.CPy");
    model.result("pg9").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg9").feature("strmsl1").set("zcoord", "es.CPz");
    model.result("pg9").feature("strmsl1").set("titletype", "none");
    model.result("pg9").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg9").feature("strmsl1").set("udist", 0.02);
    model.result("pg9").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg9").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg9").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg9").feature("strmsl1").set("inheritcolor", false);
    model.result("pg9").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg9").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg9").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg9").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg9").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg9").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg9").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg9").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg9").feature("strmsl1").set("data", "parent");
    model.result("pg9").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg9").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg9").feature("strmsl1").feature("col1").set("expr", "es.normE");
    model.result("pg9").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg9").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg9").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg9").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg9").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg9").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").set("data", "dset2");
    model.result("pg10").setIndex("looplevel", 2, 0);
    model.result("pg10").label("\u52a8\u7f51\u683c 1");
    model.result("pg10").create("mesh1", "Mesh");
    model.result("pg10").feature("mesh1").set("meshdomain", "volume");
    model.result("pg10").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg10").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg10").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg10").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg10").feature("mesh1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198);

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg10").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg10").feature("mesh1").set("qualexpr", "comp1.spatial.relVol");
    model.result("pg10").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature("vol1").set("expr", "u");
    model.result("pg7").run();
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").feature("mslc1").set("multiplanexmethod", "number");
    model.result("pg8").feature("mslc1").set("xnumber", "0");
    model.result("pg8").feature("mslc1").set("multiplaneymethod", "number");
    model.result("pg8").feature("mslc1").set("ynumber", "0");
    model.result("pg8").run();
    model.result("pg8").feature("strmsl1").set("multiplanexmethod", "number");
    model.result("pg8").feature("strmsl1").set("xnumber", "0");
    model.result("pg8").feature("strmsl1").set("multiplaneymethod", "number");
    model.result("pg8").feature("strmsl1").set("ynumber", "0");
    model.result("pg8").run();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").set("data", "dset2");
    model.result("pg11").create("ptgr1", "PointGraph");
    model.result("pg11").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg11").feature("ptgr1").set("linewidth", "preference");
    model.result("pg11").feature("ptgr1").selection().set(65);
    model.result("pg11").feature("ptgr1").set("expr", "u");
    model.result("pg11").feature("ptgr1").set("xdata", "expr");
    model.result("pg11").feature("ptgr1").set("xdataexpr", "VtestR");
    model.result("pg11").run();
    model.result("pg11").run();
    model.result("pg11").label("\u4f4d\u79fb vs. \u81ea\u68c0\u7535\u538b");
    model.result("pg1").run();

    model.title("\u8868\u9762\u5fae\u673a\u68b0\u52a0\u901f\u5ea6\u8ba1");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u673a\u7535\u201d\u63a5\u53e3\u6a21\u62df\u7535\u5bb9\u9a71\u52a8\u7684\u8868\u9762\u5fae\u673a\u68b0\u52a0\u901f\u5ea6\u8ba1\u3002\u5b83\u57fa\u4e8e Stephen D. Senturia \u6240\u8457\u7684\u300a\u5fae\u7cfb\u7edf\u8bbe\u8ba1\u300b\uff08Kluwer Academic Publishers\uff0c\u7b2c 5 \u7248\uff0c2003 \u5e74\uff0c\u7b2c 513-525 \u9875\uff09\u4e00\u4e66\u4e2d\u7684\u6848\u4f8b\u7814\u7a76\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("surface_micromachined_accelerometer.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
