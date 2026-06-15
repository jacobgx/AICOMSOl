/*
 * comb_drive_tuning_fork_gyroscope_manufacturing_variation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:03 by COMSOL 6.3.0.290. */
public class comb_drive_tuning_fork_gyroscope_manufacturing_variation {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Sensors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");

    model.param().label("\u53c2\u6570 1 - \u51e0\u4f55\u4e0e\u7f51\u683c");
    model.param().set("l_mass", "200[um]");
    model.param().descr("l_mass", "\u8d28\u91cf\u5757\u957f\u5ea6");
    model.param().set("w_mass", "l_mass");
    model.param().descr("w_mass", "\u8d28\u91cf\u5757\u5bbd\u5ea6");
    model.param().set("y_spring_l", "350[um]");
    model.param().descr("y_spring_l", "Y \u5f39\u7c27\u957f\u5ea6");
    model.param().set("y_spring_w", "10[um]");
    model.param().descr("y_spring_w", "Y \u5f39\u7c27\u5bbd\u5ea6");
    model.param().set("etch_dim", "10[um]");
    model.param().descr("etch_dim", "\u523b\u8680\u5b54\u5c3a\u5bf8");
    model.param().set("n_etch_x", "8");
    model.param().descr("n_etch_x", "\u523b\u8680\u5b54\u6570\uff0cx\u00a0\u65b9\u5411");
    model.param().set("n_etch_y", "8");
    model.param().descr("n_etch_y", "\u523b\u8680\u5b54\u6570\uff0cy\u00a0\u65b9\u5411");
    model.param().set("t_beam", "12[um]");
    model.param().descr("t_beam", "\u7ed3\u6784\u5c42\u539a\u5ea6");
    model.param().set("w_anchor", "5[um]");
    model.param().descr("w_anchor", "\u951a\u5bbd\u5ea6");
    model.param().set("tether_x", "55[um]");
    model.param().descr("tether_x", "\u7cfb\u6881 x \u5750\u6807");
    model.param().set("x_spring_l", "150[um]");
    model.param().descr("x_spring_l", "X \u5f39\u7c27\u957f\u5ea6");
    model.param().set("x_spring_w", "4[um]");
    model.param().descr("x_spring_w", "X \u5f39\u7c27\u5bbd\u5ea6");
    model.param().set("tether_l", "120[um]");
    model.param().descr("tether_l", "\u7cfb\u6881\u957f\u5ea6");
    model.param().set("tether_w", "8[um]");
    model.param().descr("tether_w", "\u7cfb\u6881\u5bbd\u5ea6");
    model.param().set("w_stator_base", "15[um]");
    model.param().descr("w_stator_base", "\u5b9a\u5b50\u5e95\u5ea7\u5bbd\u5ea6");
    model.param().set("rotor_stator_overlap", "40[um]");
    model.param().descr("rotor_stator_overlap", "\u8f6c\u5b50/\u5b9a\u5b50\u91cd\u53e0\u957f\u5ea6");
    model.param().set("l_rotor", "50[um]");
    model.param().descr("l_rotor", "\u8f6c\u5b50\u68b3\u957f");
    model.param().set("w_rotor", "8[um]");
    model.param().descr("w_rotor", "\u8f6c\u5b50\u68b3\u5bbd");
    model.param().set("n_combs", "8");
    model.param().descr("n_combs", "\u68b3\u9f7f\u6570");
    model.param().set("t_anchor", "2[um]");
    model.param().descr("t_anchor", "\u951a\u5c42\u539a\u5ea6");
    model.param().set("gap_combs", "2[um]");
    model.param().descr("gap_combs", "\u9f7f\u95f4\u9699");
    model.param().set("rotor_spacing", "(w_mass-w_rotor*n_combs)/(n_combs+1)");
    model.param().descr("rotor_spacing", "\u8f6c\u5b50\u68b3\u9f7f\u95f4\u8ddd");
    model.param().set("w_stator", "rotor_spacing-2*gap_combs");
    model.param().descr("w_stator", "\u5b9a\u5b50\u68b3\u5bbd");
    model.param().set("electrode_ratio", "0.9");
    model.param().descr("electrode_ratio", "\u611f\u5e94\u7535\u6781\u7684\u5c3a\u5bf8\u6bd4");
    model.param().set("delta", "0.01[um]");
    model.param().descr("delta", "\u9009\u62e9\u7684\u5c0f\u8ddd\u79bb");
    model.param().set("mesh_factor", "1");
    model.param()
         .descr("mesh_factor", "\u7f51\u683c\u5927\u5c0f\u56e0\u5b50\uff08\u503c\u8d8a\u5927\uff0c\u7f51\u683c\u5355\u5143\u8d8a\u5927\uff09");
    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570 2 - \u7269\u7406\u573a");
    model.param("par2").set("Vbase", "5[V]");
    model.param("par2")
         .descr("Vbase", "\u8d28\u91cf\u5757\u4e0e\u611f\u5e94\u7535\u6781\u4e4b\u95f4\u7684\u7535\u52bf\u5dee");
    model.param("par2").set("Vcomb", "60[V]");
    model.param("par2")
         .descr("Vcomb", "\u8f6c\u5b50\u68b3\u4e0e\u5b9a\u5b50\u68b3\u4e4b\u95f4\u7684\u7535\u52bf\u5dee");
    model.param("par2").set("Q", "500");
    model.param("par2").descr("Q", "\u8c10\u632f\u5668\u54c1\u8d28\u56e0\u5b50");
    model.param("par2").set("V_ac", "3[V]");
    model.param("par2").descr("V_ac", "\u4ea4\u6d41\u68b3\u7535\u538b");
    model.param("par2").set("Omega", "0[deg/s]");
    model.param("par2").descr("Omega", "\u89d2\u65cb\u8f6c\u901f\u7387");
    model.param("par2").set("AC_on", "0");
    model.param("par2")
         .descr("AC_on", "1 \u8868\u793a\u5f00\u542f\u4ea4\u6d41\u9a71\u52a8\u5668\uff0c0 \u8868\u793a\u5173\u95ed\u4ea4\u6d41\u9a71\u52a8\u5668");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").label("\u5de5\u4f5c\u5e73\u9762 1 - \u8d28\u91cf\u5757");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .label("\u77e9\u5f62 1 - \u8d28\u91cf\u5757\uff1a+X");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"l_mass", "w_mass"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"y_spring_l/2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").feature("wp1").geom().selection("csel1").label("\u8d28\u91cf");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("r2", "r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .label("\u77e9\u5f62 2 - \u611f\u5e94\u7535\u6781\u7684\u8986\u76d6\u533a");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"electrode_ratio*l_mass", "w_mass"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .setIndex("size", "electrode_ratio*w_mass", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pt1")
         .label("\u70b9 1 - \u7528\u4e8e\u7f51\u683c\u590d\u5236");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pt1")
         .setIndex("p", "y_spring_l/2-electrode_ratio*l_mass/2", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pt1")
         .setIndex("p", "electrode_ratio*w_mass/5", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pt1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pt1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("size", new String[]{"etch_dim", "etch_dim"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("pos", new String[]{"y_spring_l/2+w_mass/2-w_mass/(n_etch_x+1)", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .setIndex("pos", "l_mass/2-l_mass/(n_etch_x+1)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").feature("wp1").geom().selection("csel2").label("\u51cf\u53bb\u7269");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").selection("input").named("csel2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("fullsize", new String[]{"n_etch_x", "n_etch_y"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("displ", new String[]{"-w_mass/(n_etch_x+1)", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .setIndex("displ", "-l_mass/(n_etch_x+1)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("arr1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").named("csel2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").label("\u62c9\u4f38 1 - \u8d28\u91cf\u5757");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "t_beam", 0);
    model.component("comp1").geom("geom1").feature("ext1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("ext1").set("selresultshow", "all");
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").label("\u5de5\u4f5c\u5e73\u9762 2 - \u951a");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .label("\u77e9\u5f62 1 - \u5f39\u7c27\u951a");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("size", new String[]{"w_anchor", "w_anchor"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("pos", new String[]{"tether_x", "l_mass/2+x_spring_l+y_spring_w/2-y_spring_w/2-tether_l+w_anchor"});
    model.component("comp1").geom("geom1").feature("wp2").geom().selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").feature("wp2").geom().selection("csel1").label("\u951a");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature().duplicate("r2", "r1");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r2")
         .label("\u77e9\u5f62 2 - \u5b9a\u5b50\u951a");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r2")
         .setIndex("pos", "y_spring_l/2+w_mass/2+w_stator_base/2+2*l_rotor-rotor_stator_overlap", 0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r2")
         .setIndex("pos", "l_mass/2-0.5*l_mass/(n_combs+1)", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("mir1").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature().duplicate("r3", "r2");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r3")
         .label("\u77e9\u5f62 3 - \u5b9a\u5b50\u951a 2");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r3").setIndex("pos", 0, 0);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("mir2", "Mirror");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("mir2").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("mir2").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("mir2").set("axis", new int[]{0, 1});
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").label("\u62c9\u4f38 2 - \u951a");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "t_anchor", 0);
    model.component("comp1").geom("geom1").feature("ext2").set("reverse", true);
    model.component("comp1").geom("geom1").feature("ext2").set("selresult", true);
    model.component("comp1").geom("geom1").feature("ext2").set("selresultshow", "all");
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").label("\u5de5\u4f5c\u5e73\u9762 3 - \u5f39\u7c27");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1")
         .label("\u77e9\u5f62 1 - Y \u5f39\u7c27\uff1a+Y");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1")
         .set("size", new String[]{"y_spring_l+x_spring_w", "1"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1").setIndex("size", "y_spring_w", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1")
         .set("pos", new String[]{"0", "l_mass/2+x_spring_l+y_spring_w/2"});
    model.component("comp1").geom("geom1").feature("wp3").geom().selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").feature("wp3").geom().selection("csel1").label("\u955c\u50cf Y");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp3").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r2")
         .label("\u77e9\u5f62 2 - X \u5f39\u7c27\uff1a+X +Y");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r2")
         .set("size", new String[]{"x_spring_w", "x_spring_l+y_spring_w"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r2").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r2")
         .set("pos", new String[]{"y_spring_l/2", "0"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r2")
         .setIndex("pos", "l_mass/2+x_spring_l/2+y_spring_w/2", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").feature("wp3").geom().selection("csel2").label("\u955c\u50cf XY");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r2").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").feature("wp3").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r3")
         .label("\u77e9\u5f62 3 - \u7cfb\u7ef3\uff1a+X +Y");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r3")
         .set("size", new String[]{"tether_w", "tether_l+y_spring_w"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r3")
         .set("pos", new String[]{"tether_x-tether_w/2", "0"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r3")
         .setIndex("pos", "l_mass/2+x_spring_l+y_spring_w/2-y_spring_w/2-tether_l", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("r3").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").feature("wp3").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("mir1").selection("input").named("csel2");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("mir1").set("axis", new int[]{0, 1});
    model.component("comp1").geom("geom1").feature("wp3").geom().run("mir1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("mir2", "Mirror");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("mir2").selection("input").named("csel2");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("mir2").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("mir2");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("mir3", "Mirror");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("mir3").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("mir3").set("axis", new int[]{0, 1});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("mir3").set("keep", true);
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").feature().create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3").label("\u62c9\u4f38 3 - \u5f39\u7c27");
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "t_beam", 0);
    model.component("comp1").geom("geom1").feature("ext3").set("selresult", true);
    model.component("comp1").geom("geom1").feature("ext3").set("selresultshow", "all");
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").create("wp4", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp4").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp4").label("\u5de5\u4f5c\u5e73\u9762 4 - \u8f6c\u5b50");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("r1")
         .label("\u77e9\u5f62 1 - \u7b2c\u4e00\u4e2a\u68b3");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("r1")
         .set("size", new String[]{"l_rotor", "w_rotor"});
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("r1")
         .set("pos", new String[]{"y_spring_l/2+w_mass/2+l_rotor/2", "0"});
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("r1")
         .setIndex("pos", "w_mass/2-rotor_spacing-w_rotor/2", 1);
    model.component("comp1").geom("geom1").feature("wp4").geom().selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").feature("wp4").geom().selection("csel1").label("\u8f6c\u5b50");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("r1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp4").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("arr1").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("arr1").set("type", "linear");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("arr1").set("linearsize", "n_combs");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("arr1")
         .set("displ", new String[]{"0", "-(rotor_spacing+w_rotor)"});
    model.component("comp1").geom("geom1").feature("wp4").geom().run("arr1");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("mir1").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("mir1")
         .set("pos", new String[]{"y_spring_l/2", "0"});
    model.component("comp1").geom("geom1").feature("wp4").geom().run("mir1");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("mir2", "Mirror");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("mir2").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("mir2").set("keep", true);
    model.component("comp1").geom("geom1").run("wp4");
    model.component("comp1").geom("geom1").feature().create("ext4", "Extrude");
    model.component("comp1").geom("geom1").feature("ext4").label("\u62c9\u4f38 4 - \u8f6c\u5b50");
    model.component("comp1").geom("geom1").feature("ext4").setIndex("distance", "t_beam", 0);
    model.component("comp1").geom("geom1").feature("ext4").set("selresult", true);
    model.component("comp1").geom("geom1").feature("ext4").set("selresultshow", "all");
    model.component("comp1").geom("geom1").run("ext4");
    model.component("comp1").geom("geom1").create("wp5", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp5").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp5").label("\u5de5\u4f5c\u5e73\u9762 5 - \u5b9a\u5b50");
    model.component("comp1").geom("geom1").feature("wp5").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("r1")
         .label("\u77e9\u5f62 1 - \u7b2c\u4e00\u4e2a\u68b3");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("r1")
         .set("size", new String[]{"l_rotor", "w_stator"});
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("r1")
         .set("pos", new String[]{"y_spring_l/2+w_mass/2+l_rotor/2+l_rotor-rotor_stator_overlap", "0"});
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("r1")
         .setIndex("pos", "w_mass/2-0.5*rotor_spacing", 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").feature("wp5").geom().selection("csel1").label("\u5b9a\u5b50");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("r1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp5").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp5").geom().create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("arr1").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("arr1").set("type", "linear");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("arr1").set("linearsize", "n_combs+1");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("arr1")
         .set("displ", new String[]{"0", "-(rotor_spacing+w_rotor)"});
    model.component("comp1").geom("geom1").feature("wp5").geom().run("arr1");
    model.component("comp1").geom("geom1").feature("wp5").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("r2")
         .label("\u77e9\u5f62 2 - \u5b9a\u5b50\u5e95\u5ea7");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("r2")
         .set("size", new String[]{"w_stator_base", "1"});
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("r2")
         .setIndex("size", "w_mass-rotor_spacing+w_stator", 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("r2").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("r2")
         .set("pos", new String[]{"y_spring_l/2+w_mass/2+w_stator_base/2+2*l_rotor-rotor_stator_overlap", "0"});
    model.component("comp1").geom("geom1").feature("wp5").geom().selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").feature("wp5").geom().selection("csel2")
         .label("\u5b9a\u5b50\u5e95\u5ea7");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("r2").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").feature("wp5").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp5").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("mir1").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("mir1")
         .set("pos", new String[]{"y_spring_l/2", "0"});
    model.component("comp1").geom("geom1").feature("wp5").geom().run("mir1");
    model.component("comp1").geom("geom1").feature("wp5").geom().create("mir2", "Mirror");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("mir2").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("mir2").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp5").geom().run("mir2");
    model.component("comp1").geom("geom1").feature("wp5").geom().create("mir3", "Mirror");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("mir3").selection("input").named("csel2");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("mir3").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp5").geom().run("mir3");
    model.component("comp1").geom("geom1").feature("wp5").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("r3")
         .label("\u77e9\u5f62 3 - \u5b9a\u5b50\u5e95\u5ea7");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("r3")
         .set("size", new String[]{"2*(y_spring_l/2-l_mass/2-l_rotor-(l_rotor-rotor_stator_overlap))", "1"});
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("r3")
         .setIndex("size", "w_mass-rotor_spacing+w_stator", 1);
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("r3").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp5").geom().feature("r3").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("wp5");
    model.component("comp1").geom("geom1").feature().create("ext5", "Extrude");
    model.component("comp1").geom("geom1").feature("ext5").label("\u62c9\u4f38 5 - \u5b9a\u5b50");
    model.component("comp1").geom("geom1").feature("ext5").setIndex("distance", "t_beam", 0);
    model.component("comp1").geom("geom1").feature("ext5").set("selresult", true);
    model.component("comp1").geom("geom1").feature("ext5").set("selresultshow", "all");
    model.component("comp1").geom("geom1").run("ext5");
    model.component("comp1").geom("geom1").create("wp6", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp6").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp6")
         .label("\u5de5\u4f5c\u5e73\u9762 6 - \u611f\u5e94\u7535\u6781");
    model.component("comp1").geom("geom1").feature("wp6").set("quickz", "-t_anchor");
    model.component("comp1").geom("geom1").feature("wp6").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp6").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("r1")
         .label("\u77e9\u5f62 1 - \u611f\u5e94\u7535\u6781");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("r1")
         .set("size", new String[]{"electrode_ratio*l_mass", "1"});
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("r1")
         .setIndex("size", "electrode_ratio*w_mass", 1);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("r1")
         .set("pos", new String[]{"y_spring_l/2", "0"});
    model.component("comp1").geom("geom1").feature("wp6").geom().selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").feature("wp6").geom().selection("csel1")
         .label("\u611f\u5e94\u7535\u6781");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("r1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp6").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp6").geom().create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("pt1")
         .label("\u70b9 1 - \u7528\u4e8e\u7f51\u683c\u590d\u5236");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("pt1")
         .setIndex("p", "y_spring_l/2-electrode_ratio*l_mass/2", 0);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("pt1")
         .setIndex("p", "electrode_ratio*w_mass/5", 1);
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("pt1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("wp6").geom().run("pt1");
    model.component("comp1").geom("geom1").feature("wp6").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("mir1").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("wp6").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").run("wp6");
    model.component("comp1").geom("geom1").create("wp7", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp7").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp7")
         .label("\u5de5\u4f5c\u5e73\u9762 7 - \u5bf9\u79f0\u5e73\u9762");
    model.component("comp1").geom("geom1").feature("wp7").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp7").geom().create("cro1", "CrossSection");
    model.component("comp1").geom("geom1").feature("wp7").geom().selection().create("csel1", "CumulativeSelection");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").geom("geom1").feature("wp7").geom().selection("csel1")
         .label("\u5bf9\u79f0\u5e73\u9762");
    model.component("comp1").geom("geom1").feature("wp7").geom().feature("cro1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("box1", "Box");
    model.component("comp1").selection("box1").label("\u6846 1 - \u6881\u5e95\u90e8");
    model.component("comp1").selection("box1").set("entitydim", 2);
    model.component("comp1").selection("box1").set("zmin", "-delta");
    model.component("comp1").selection("box1").set("zmax", "delta");
    model.component("comp1").selection("box1").set("condition", "inside");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").selection().create("box2", "Box");
    model.component("comp1").selection("box2").label("\u6846 2 - \u6574\u4e2a\u6881\u5c42");
    model.component("comp1").selection("box2").set("zmin", "t_beam/2-delta");
    model.component("comp1").selection("box2").set("zmax", "t_beam/2+delta");
    model.component("comp1").selection().duplicate("box3", "box1");
    model.component("comp1").selection("box3").label("\u6846 3 - \u951a\u5e95\u5ea7");
    model.component("comp1").selection("box3").set("zmin", "-t_anchor-delta");
    model.component("comp1").selection("box3").set("zmax", "-t_anchor+delta");
    model.component("comp1").selection().create("int1", "Intersection");
    model.component("comp1").selection("int1").label("\u4ea4\u96c6 1 - \u4e0b\u90e8\u7535\u6781");
    model.component("comp1").selection("int1").set("entitydim", 2);
    model.component("comp1").selection("int1").set("input", new String[]{"box1", "geom1_ext1_bnd"});
    model.component("comp1").selection().create("box4", "Box");
    model.component("comp1").selection("box4").label("\u6846 4 - \u68b3\u9f7f\u5782\u76f4\u58c1 1");
    model.component("comp1").selection("box4").set("entitydim", 2);
    model.component("comp1").selection("box4").set("xmin", "y_spring_l/2+w_mass/2+l_rotor/2-delta");
    model.component("comp1").selection("box4").set("xmax", "y_spring_l/2+w_mass/2+l_rotor/2+delta");
    model.component("comp1").selection("box4").set("ymin", "-w_mass/2+delta");
    model.component("comp1").selection("box4").set("ymax", "w_mass/2-delta");
    model.component("comp1").selection("box4").set("zmin", "t_beam/2");
    model.component("comp1").selection("box4").set("zmax", "t_beam/2+delta");
    model.component("comp1").selection().duplicate("box5", "box4");
    model.component("comp1").selection("box5").label("\u6846 5 - \u68b3\u9f7f\u5782\u76f4\u58c1 2");
    model.component("comp1").selection("box5").set("xmin", "-(y_spring_l/2+w_mass/2+l_rotor/2)-delta");
    model.component("comp1").selection("box5").set("xmax", "-(y_spring_l/2+w_mass/2+l_rotor/2)+delta");
    model.component("comp1").selection().duplicate("box6", "box4");
    model.component("comp1").selection("box6").label("\u6846 6 - \u68b3\u9f7f\u5782\u76f4\u58c1 3");
    model.component("comp1").selection("box6").set("xmin", "y_spring_l/2-w_mass/2-l_rotor/2 -delta");
    model.component("comp1").selection("box6").set("xmax", "y_spring_l/2-w_mass/2-l_rotor/2+delta");
    model.component("comp1").selection().duplicate("box7", "box5");
    model.component("comp1").selection("box7").label("\u6846 7 - \u68b3\u9f7f\u5782\u76f4\u58c1 4");
    model.component("comp1").selection("box7").set("xmin", "-(y_spring_l/2-w_mass/2-l_rotor/2)-delta");
    model.component("comp1").selection("box7").set("xmax", "-(y_spring_l/2-w_mass/2-l_rotor/2)+delta");
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u5e76\u96c6 1 - \u68b3\u9f7f\u5782\u76f4\u58c1");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("input", new String[]{"box4", "box5", "box6", "box7"});
    model.component("comp1").selection().create("int2", "Intersection");
    model.component("comp1").selection("int2").label("\u4ea4\u96c6 2 - \u5b9a\u5b50\u5782\u76f4\u58c1");
    model.component("comp1").selection("int2").set("entitydim", 2);
    model.component("comp1").selection("int2").set("input", new String[]{"uni1", "geom1_ext5_bnd"});
    model.component("comp1").selection().duplicate("box8", "box4");
    model.component("comp1").selection("box8").label("\u6846 8 - \u8f6c\u5b50\u5c16\u7aef\u8fb9\u7f18 1");
    model.component("comp1").selection("box8").set("entitydim", 1);
    model.component("comp1").selection("box8").set("xmin", "y_spring_l/2+w_mass/2+l_rotor-delta");
    model.component("comp1").selection("box8").set("xmax", "y_spring_l/2+w_mass/2+l_rotor+delta");
    model.component("comp1").selection().duplicate("box9", "box8");
    model.component("comp1").selection("box9").label("\u6846 9 - \u8f6c\u5b50\u5c16\u7aef\u8fb9\u7f18 2");
    model.component("comp1").selection("box9").set("xmin", "-(y_spring_l/2+w_mass/2+l_rotor)-delta");
    model.component("comp1").selection("box9").set("xmax", "-(y_spring_l/2+w_mass/2+l_rotor)+delta");
    model.component("comp1").selection().duplicate("box10", "box8");
    model.component("comp1").selection("box10").label("\u6846 10 - \u8f6c\u5b50\u5c16\u7aef\u8fb9\u7f18 3");
    model.component("comp1").selection("box10").set("xmin", "y_spring_l/2-w_mass/2-l_rotor -delta");
    model.component("comp1").selection("box10").set("xmax", "y_spring_l/2-w_mass/2-l_rotor+delta");
    model.component("comp1").selection().duplicate("box11", "box9");
    model.component("comp1").selection("box11").label("\u6846 11 - \u8f6c\u5b50\u5c16\u7aef\u8fb9\u7f18 4");
    model.component("comp1").selection("box11").set("xmin", "-(y_spring_l/2-w_mass/2-l_rotor)-delta");
    model.component("comp1").selection("box11").set("xmax", "-(y_spring_l/2-w_mass/2-l_rotor)+delta");
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("\u5e76\u96c6 2 - \u8f6c\u5b50\u5c16\u7aef\u8fb9\u7f18 +X DC");
    model.component("comp1").selection("uni2").set("entitydim", 1);
    model.component("comp1").selection("uni2").set("input", new String[]{"box8", "box11"});
    model.component("comp1").selection().create("uni3", "Union");
    model.component("comp1").selection("uni3").label("\u5e76\u96c6 3 - \u8f6c\u5b50\u5c16\u7aef\u8fb9\u7f18 -X DC");
    model.component("comp1").selection("uni3").set("entitydim", 1);
    model.component("comp1").selection("uni3").set("input", new String[]{"box9", "box10"});
    model.component("comp1").selection().create("uni4", "Union");
    model.component("comp1").selection("uni4").label("\u5e76\u96c6 4 - \u8f6c\u5b50\u5c16\u7aef\u8fb9\u7f18 +X AC");
    model.component("comp1").selection("uni4").set("entitydim", 1);
    model.component("comp1").selection("uni4").set("input", new String[]{"box8", "box9"});
    model.component("comp1").selection().create("uni5", "Union");
    model.component("comp1").selection("uni5").label("\u5e76\u96c6 5 - \u8f6c\u5b50\u5c16\u7aef\u8fb9\u7f18 -X AC");
    model.component("comp1").selection("uni5").set("entitydim", 1);
    model.component("comp1").selection("uni5").set("input", new String[]{"box10", "box11"});
    model.component("comp1").selection().create("uni6", "Union");
    model.component("comp1").selection("uni6").label("\u5e76\u96c6 6 - \u8f6c\u5b50\u5c16\u7aef\u8fb9\u7f18");
    model.component("comp1").selection("uni6").set("entitydim", 1);
    model.component("comp1").selection("uni6").set("input", new String[]{"uni2", "uni3"});
    model.component("comp1").selection().create("box12", "Box");
    model.component("comp1").selection("box12").label("\u6846 13 - x > 0 \u6881\u5e95\u5ea7");
    model.component("comp1").selection("box12").set("entitydim", 2);
    model.component("comp1").selection("box12").set("xmin", "-delta");
    model.component("comp1").selection("box12").set("zmin", "-delta");
    model.component("comp1").selection("box12").set("zmax", "delta");
    model.component("comp1").selection("box12").set("condition", "inside");
    model.component("comp1").selection().duplicate("box13", "box12");
    model.component("comp1").selection("box13").label("\u6846 14 - x < 0 \u6881\u5e95\u5ea7");
    model.component("comp1").selection("box13").set("xmin", Double.NEGATIVE_INFINITY);
    model.component("comp1").selection("box13").set("xmax", "delta");
    model.component("comp1").selection().duplicate("box14", "box13");
    model.component("comp1").selection("box14").label("\u6846 15 - x > 0 \u5f39\u7c27\u951a");
    model.component("comp1").selection("box14").set("xmin", "-delta+tether_x-w_anchor/2");
    model.component("comp1").selection("box14").set("xmax", "delta+tether_x+w_anchor/2");
    model.component("comp1").selection().duplicate("box15", "box14");
    model.component("comp1").selection("box15").label("\u6846 16 - x < 0 \u5f39\u7c27\u951a");
    model.component("comp1").selection("box15").set("xmin", "-delta-tether_x-w_anchor/2");
    model.component("comp1").selection("box15").set("xmax", "delta-tether_x+w_anchor/2");
    model.component("comp1").selection().create("int3", "Intersection");
    model.component("comp1").selection("int3")
         .label("\u4ea4\u96c6 3 - \u56db\u8fb9\u5f62\u7f51\u683c - \u5f39\u7c27\u6784\u5efa");
    model.component("comp1").selection("int3").set("entitydim", 2);
    model.component("comp1").selection("int3").set("input", new String[]{"box12", "geom1_ext3_bnd"});
    model.component("comp1").selection().create("int4", "Intersection");
    model.component("comp1").selection("int4")
         .label("\u4ea4\u96c6 4 - \u56db\u8fb9\u5f62\u7f51\u683c - \u5f39\u7c27\u6784\u5efa\u526f\u672c");
    model.component("comp1").selection("int4").set("entitydim", 2);
    model.component("comp1").selection("int4").set("input", new String[]{"box13", "geom1_ext3_bnd"});
    model.component("comp1").selection().create("int5", "Intersection");
    model.component("comp1").selection("int5").label("\u4ea4\u96c6 5 - \u6620\u5c04\u7f51\u683c - \u951a");
    model.component("comp1").selection("int5").set("entitydim", 2);
    model.component("comp1").selection("int5").set("input", new String[]{"box14", "geom1_ext2_bnd"});
    model.component("comp1").selection().create("int6", "Intersection");
    model.component("comp1").selection("int6")
         .label("\u4ea4\u96c6 6 - \u6620\u5c04\u7f51\u683c - \u951a\u526f\u672c");
    model.component("comp1").selection("int6").set("entitydim", 2);
    model.component("comp1").selection("int6").set("input", new String[]{"box15", "geom1_ext2_bnd"});
    model.component("comp1").selection().create("int7", "Intersection");
    model.component("comp1").selection("int7")
         .label("\u4ea4\u96c6 7 - \u4e09\u89d2\u5f62\u7f51\u683c - \u8d28\u91cf\u5757");
    model.component("comp1").selection("int7").set("entitydim", 2);
    model.component("comp1").selection("int7").set("input", new String[]{"box12", "geom1_ext1_bnd"});
    model.component("comp1").selection().create("int8", "Intersection");
    model.component("comp1").selection("int8")
         .label("\u4ea4\u96c6 8 - \u4e09\u89d2\u5f62\u7f51\u683c - \u8d28\u91cf\u5757\u526f\u672c");
    model.component("comp1").selection("int8").set("entitydim", 2);
    model.component("comp1").selection("int8").set("input", new String[]{"box13", "geom1_ext1_bnd"});
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1")
         .label("\u5dee\u96c6 1 - \u56db\u8fb9\u5f62\u7f51\u683c - \u5f39\u7c27");
    model.component("comp1").selection("dif1").set("entitydim", 2);
    model.component("comp1").selection("dif1").set("add", new String[]{"int3"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"int5"});
    model.component("comp1").selection().create("dif2", "Difference");
    model.component("comp1").selection("dif2")
         .label("\u5dee\u96c6 2 - \u56db\u8fb9\u5f62\u7f51\u683c - \u5f39\u7c27\u526f\u672c");
    model.component("comp1").selection("dif2").set("entitydim", 2);
    model.component("comp1").selection("dif2").set("add", new String[]{"int4"});
    model.component("comp1").selection("dif2").set("subtract", new String[]{"int6"});
    model.component("comp1").selection().create("dif3", "Difference");
    model.component("comp1").selection("dif3")
         .label("\u5dee\u96c6 3 - \u56db\u8fb9\u5f62\u7f51\u683c - \u5b9a\u5b50 & \u68b3");
    model.component("comp1").selection("dif3").set("entitydim", 2);
    model.component("comp1").selection("dif3").set("add", new String[]{"box12"});
    model.component("comp1").selection("dif3")
         .set("subtract", new String[]{"box14", "geom1_ext1_bnd", "geom1_ext3_bnd", "geom1_wp6_bnd"});
    model.component("comp1").selection().create("dif4", "Difference");
    model.component("comp1").selection("dif4")
         .label("\u5dee\u96c6 4 - \u56db\u8fb9\u5f62\u7f51\u683c - \u5b9a\u5b50 & \u68b3\u526f\u672c");
    model.component("comp1").selection("dif4").set("entitydim", 2);
    model.component("comp1").selection("dif4").set("add", new String[]{"box13"});
    model.component("comp1").selection("dif4")
         .set("subtract", new String[]{"box15", "geom1_ext1_bnd", "geom1_ext3_bnd", "geom1_wp6_bnd"});
    model.component("comp1").selection().create("box16", "Box");
    model.component("comp1").selection("box16").label("\u6846 17 - x > 0 \u951a\u5e95\u5ea7");
    model.component("comp1").selection("box16").set("entitydim", 2);
    model.component("comp1").selection("box16").set("xmin", "-delta");
    model.component("comp1").selection("box16").set("zmin", "-delta-t_anchor");
    model.component("comp1").selection("box16").set("zmax", "delta-t_anchor");
    model.component("comp1").selection("box16").set("condition", "inside");
    model.component("comp1").selection().duplicate("box17", "box16");
    model.component("comp1").selection("box17").label("\u6846 18 - x < 0 \u951a\u5e95\u5ea7");
    model.component("comp1").selection("box17").set("xmin", Double.NEGATIVE_INFINITY);
    model.component("comp1").selection("box17").set("xmax", "delta");
    model.component("comp1").selection().create("int9", "Intersection");
    model.component("comp1").selection("int9")
         .label("\u4ea4\u96c6 9 - \u4e09\u89d2\u5f62\u7f51\u683c - \u611f\u5e94\u7535\u6781");
    model.component("comp1").selection("int9").set("entitydim", 2);
    model.component("comp1").selection("int9").set("input", new String[]{"box16", "geom1_wp6_bnd"});
    model.component("comp1").selection().create("int10", "Intersection");
    model.component("comp1").selection("int10")
         .label("\u4ea4\u96c6 10 - \u4e09\u89d2\u5f62\u7f51\u683c - \u611f\u5e94\u7535\u6781\u526f\u672c");
    model.component("comp1").selection("int10").set("entitydim", 2);
    model.component("comp1").selection("int10").set("input", new String[]{"box17", "geom1_wp6_bnd"});
    model.component("comp1").selection().create("box18", "Box");
    model.component("comp1").selection("box18")
         .label("\u6846 19 - x > 0 \u4e0b\u90e8\u7535\u6781\u6709\u6548\u533a\u57df");
    model.component("comp1").selection("box18").set("entitydim", 2);
    model.component("comp1").selection("box18").set("xmin", "-delta+y_spring_l/2-electrode_ratio*l_mass/2");
    model.component("comp1").selection("box18").set("xmax", "delta+y_spring_l/2+electrode_ratio*l_mass/2");
    model.component("comp1").selection("box18").set("ymin", "-delta-electrode_ratio*w_mass/2");
    model.component("comp1").selection("box18").set("ymax", "delta+electrode_ratio*w_mass/2");
    model.component("comp1").selection("box18").set("zmin", "-delta");
    model.component("comp1").selection("box18").set("zmax", "delta");
    model.component("comp1").selection("box18").set("condition", "inside");
    model.component("comp1").selection().duplicate("box19", "box18");
    model.component("comp1").selection("box19")
         .label("\u6846 20 - x < 0 \u4e0b\u90e8\u7535\u6781\u6709\u6548\u533a\u57df");
    model.component("comp1").selection("box19").set("xmin", "-delta-y_spring_l/2-electrode_ratio*l_mass/2");
    model.component("comp1").selection("box19").set("xmax", "delta-y_spring_l/2+electrode_ratio*l_mass/2");
    model.component("comp1").selection().create("uni7", "Union");
    model.component("comp1").selection("uni7")
         .label("\u5e76\u96c6 7 - \u4e0b\u90e8\u7535\u6781\u6709\u6548\u533a\u57df");
    model.component("comp1").selection("uni7").set("entitydim", 2);
    model.component("comp1").selection("uni7").set("input", new String[]{"box18", "box19"});

    model.component("comp1").cpl().create("genext1", "GeneralExtrusion");
    model.component("comp1").cpl("genext1").label("\u5e7f\u4e49\u62c9\u4f38 1 - \u5b9a\u5b50\u58c1");
    model.component("comp1").cpl("genext1").set("opname", "genextcmb");
    model.component("comp1").cpl("genext1").selection().geom("geom1", 2);
    model.component("comp1").cpl("genext1").selection().named("int2");
    model.component("comp1").cpl("genext1").set("usesrcmap", true);
    model.component("comp1").cpl("genext1").set("method", "closest");
    model.component("comp1").cpl().duplicate("genext2", "genext1");
    model.component("comp1").cpl("genext2").label("\u5e7f\u4e49\u62c9\u4f38 2 - \u611f\u5e94\u7535\u6781");
    model.component("comp1").cpl("genext2").set("opname", "genextpp");
    model.component("comp1").cpl("genext2").selection().named("geom1_wp6_bnd");
    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").label("\u79ef\u5206 1 - \u4e0b\u90e8\u7535\u6781");
    model.component("comp1").cpl("intop1").set("opname", "intoppp");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().named("uni7");
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").label("\u79ef\u5206 2 - \u68b3\u8fb9\u7f18");
    model.component("comp1").cpl("intop2").set("opname", "intopcmb");
    model.component("comp1").cpl("intop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop2").selection().named("uni6");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u53d8\u91cf 1 - \u611f\u5e94\u7535\u5bb9");
    model.component("comp1").variable("var1").selection().geom("geom1", 2);
    model.component("comp1").variable("var1").selection().named("uni7");
    model.component("comp1").variable("var1").set("dpp_x", "genextpp(X)-(X+u)");
    model.component("comp1").variable("var1")
         .descr("dpp_x", "\u6765\u81ea\u4e0b\u90e8\u7535\u6781\u7684\u77e2\u91cf\uff0cx");
    model.component("comp1").variable("var1").set("dpp_y", "genextpp(Y)-(Y+v)");
    model.component("comp1").variable("var1")
         .descr("dpp_y", "\u6765\u81ea\u4e0b\u90e8\u7535\u6781\u7684\u77e2\u91cf\uff0cy");
    model.component("comp1").variable("var1").set("dpp_z", "genextpp(Z)-(Z+w)");
    model.component("comp1").variable("var1")
         .descr("dpp_z", "\u6765\u81ea\u4e0b\u90e8\u7535\u6781\u7684\u77e2\u91cf\uff0cz");
    model.component("comp1").variable("var1").set("dpp_sq", "dpp_x^2+dpp_y^2+dpp_z^2");
    model.component("comp1").variable("var1").descr("dpp_sq", "\u5e73\u884c\u677f\u8ddd\u79bb\u7684\u5e73\u65b9");
    model.component("comp1").variable("var1").set("F_A", "0.5*epsilon0_const*Vbase^2/dpp_sq");
    model.component("comp1").variable("var1").descr("F_A", "\u7535\u6781\u5355\u4f4d\u9762\u79ef\u7684\u529b");
    model.component("comp1").variable("var1").set("C_A", "epsilon0_const/sqrt(dpp_sq)");
    model.component("comp1").variable("var1").descr("C_A", "\u7535\u6781\u5355\u4f4d\u9762\u79ef\u7684\u7535\u5bb9");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").label("\u53d8\u91cf 2 - \u611f\u5e94\u7535\u5bb9 + sign AC");
    model.component("comp1").variable("var2").selection().geom("geom1", 2);
    model.component("comp1").variable("var2").selection().named("box19");
    model.component("comp1").variable("var2").set("sign", "1");
    model.component("comp1").variable("var2").descr("sign", "\u7535\u5bb9\u53d8\u5316\u7684\u7b26\u53f7");
    model.component("comp1").variable().duplicate("var3", "var2");
    model.component("comp1").variable("var3").label("\u53d8\u91cf 3 - \u611f\u5e94\u7535\u5bb9 - sign AC");
    model.component("comp1").variable("var3").selection().named("box18");
    model.component("comp1").variable("var3").set("sign", "-1");
    model.component("comp1").variable().create("var4");
    model.component("comp1").variable("var4").label("\u53d8\u91cf 4 - \u68b3\u9f7f\u9a71\u52a8\u5668");
    model.component("comp1").variable("var4").selection().geom("geom1", 1);
    model.component("comp1").variable("var4").selection().named("uni6");
    model.component("comp1").variable("var4").set("dcmb_x", "genextcmb(X+u)-(X+u)");
    model.component("comp1").variable("var4")
         .descr("dcmb_x", "\u6765\u81ea\u8f6c\u5b50\u68b3\u8fb9\u7f18\u7684\u77e2\u91cf\uff0cx");
    model.component("comp1").variable("var4").set("dcmb_y", "genextcmb(Y+v)-(Y+v)");
    model.component("comp1").variable("var4")
         .descr("dcmb_y", "\u6765\u81ea\u8f6c\u5b50\u68b3\u8fb9\u7f18\u7684\u77e2\u91cf\uff0cy");
    model.component("comp1").variable("var4").set("dcmb_z", "genextcmb(Z+w)-(Z+w)");
    model.component("comp1").variable("var4")
         .descr("dcmb_z", "\u6765\u81ea\u8f6c\u5b50\u68b3\u8fb9\u7f18\u7684\u77e2\u91cf\uff0cz");
    model.component("comp1").variable("var4").set("dcmb", "sqrt(dcmb_x^2+dcmb_y^2+dcmb_z^2)");
    model.component("comp1").variable("var4").descr("dcmb", "\u5e73\u884c\u677f\u8ddd");
    model.component("comp1").variable("var4").set("Vtot", "Vcomb+AC_on*AC*linper(V_ac)");
    model.component("comp1").variable("var4").descr("Vtot", "\u603b\u7535\u538b");
    model.component("comp1").variable("var4").set("F_l", "sign*0.5*epsilon0_const*Vtot^2/dcmb");
    model.component("comp1").variable("var4").descr("F_l", "\u5355\u4f4d\u8fb9\u957f\u7684\u529b");
    model.component("comp1").variable("var4").set("C_l", "linpoint(epsilon0_const/dcmb)*u*sign*AC");
    model.component("comp1").variable("var4").descr("C_l", "\u5355\u4f4d\u957f\u5ea6\u7684\u7535\u5bb9\u53d8\u5316");
    model.component("comp1").variable().create("var5");
    model.component("comp1").variable("var5").label("\u53d8\u91cf 5 - \u68b3\u9f7f\u9a71\u52a8\u5668 + sign DC");
    model.component("comp1").variable("var5").selection().geom("geom1", 1);
    model.component("comp1").variable("var5").selection().named("uni2");
    model.component("comp1").variable("var5").set("sign", "1", "\u7535\u5bb9\u53d8\u5316\u7684\u7b26\u53f7");
    model.component("comp1").variable("var5").descr("sign", "\u68b3\u9f7f\u9a71\u52a8\u529b\u7684\u7b26\u53f7");
    model.component("comp1").variable().duplicate("var6", "var5");
    model.component("comp1").variable("var6").label("\u53d8\u91cf 6 - \u68b3\u9f7f\u9a71\u52a8\u5668 - sign DC");
    model.component("comp1").variable("var6").selection().named("uni3");
    model.component("comp1").variable("var6").set("sign", "-1");
    model.component("comp1").variable().create("var7");
    model.component("comp1").variable("var7").label("\u53d8\u91cf 7 - \u68b3\u9f7f\u9a71\u52a8\u5668 + sign AC");
    model.component("comp1").variable("var7").selection().geom("geom1", 1);
    model.component("comp1").variable("var7").selection().named("uni4");
    model.component("comp1").variable("var7").set("AC", "1");
    model.component("comp1").variable("var7").descr("AC", "\u4ea4\u6d41\u68b3\u9f7f\u7535\u538b\u7684\u7b26\u53f7");
    model.component("comp1").variable().duplicate("var8", "var7");
    model.component("comp1").variable("var8").label("\u53d8\u91cf 8 - \u68b3\u9f7f\u9a71\u52a8\u5668 - sign AC");
    model.component("comp1").variable("var8").selection().named("uni5");
    model.component("comp1").variable("var8").set("AC", "-1");

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

    model.component("comp1").physics("solid").feature("lemm1").create("dmp1", "Damping", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1")
         .set("DampingType", "IsotropicLossFactor");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("eta_s_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("eta_s", "1/Q");
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("box3");
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1")
         .label("\u8fb9\u754c\u8f7d\u8377 1 - \u611f\u5e94\u7535\u6781");
    model.component("comp1").physics("solid").feature("bndl1").selection().named("uni7");
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "0", "-F_A"});
    model.component("comp1").physics("solid").create("el1", "EdgeLoad", 1);
    model.component("comp1").physics("solid").feature("el1")
         .label("\u8fb9\u8f7d\u8377 1 - \u68b3\u9f7f\u9a71\u52a8\u5668");
    model.component("comp1").physics("solid").feature("el1").selection().named("uni6");
    model.component("comp1").physics("solid").feature("el1")
         .set("forceReferenceLength", new String[]{"F_l", "0", "0"});
    model.component("comp1").physics("solid").create("rotf1", "RotatingFrame", 3);
    model.component("comp1").physics("solid").feature("rotf1").set("AxisOfRotation", "yAxis");
    model.component("comp1").physics("solid").feature("rotf1").set("Ovm", "Omega");
    model.component("comp1").physics("solid").feature("rotf1").set("CoriolisForce", true);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("box14");
    model.component("comp1").mesh("mesh1").feature("map1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmax", "mesh_factor*x_spring_w/2");
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("map1").feature("size1").set("hmin", "mesh_factor*x_spring_w/10");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "mesh_factor*tether_w/3");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "mesh_factor*tether_w/30");
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", "1.1+mesh_factor*0.2");
    model.component("comp1").mesh("mesh1").create("cpf1", "CopyFace");
    model.component("comp1").mesh("mesh1").feature("cpf1").selection("source").geom(2);
    model.component("comp1").mesh("mesh1").feature("cpf1").selection("destination").geom(2);
    model.component("comp1").mesh("mesh1").feature("cpf1").selection("source").named("box14");
    model.component("comp1").mesh("mesh1").feature("cpf1").selection("destination").named("box15");
    model.component("comp1").mesh("mesh1").create("fq1", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("fq1").selection().named("dif1");
    model.component("comp1").mesh("mesh1").feature("fq1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").selection().set(945, 950);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hmax", "mesh_factor*x_spring_w/3");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hmin", "mesh_factor*x_spring_w/30");
    model.component("comp1").mesh("mesh1").create("cpf2", "CopyFace");
    model.component("comp1").mesh("mesh1").feature("cpf2").selection("source").geom(2);
    model.component("comp1").mesh("mesh1").feature("cpf2").selection("destination").geom(2);
    model.component("comp1").mesh("mesh1").feature("cpf2").selection("source").named("dif1");
    model.component("comp1").mesh("mesh1").feature("cpf2").selection("destination").named("dif2");
    model.component("comp1").mesh("mesh1").run("cpf2");
    model.component("comp1").mesh("mesh1").feature().duplicate("size1", "size");
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", "mesh_factor*w_rotor/2");
    model.component("comp1").mesh("mesh1").feature("size1").set("hmin", "mesh_factor*w_rotor/20");
    model.component("comp1").mesh("mesh1").create("fq2", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("fq2").selection().named("dif3");
    model.component("comp1").mesh("mesh1").create("cpf3", "CopyFace");
    model.component("comp1").mesh("mesh1").feature("cpf3").selection("source").geom(2);
    model.component("comp1").mesh("mesh1").feature("cpf3").selection("destination").geom(2);

    return model;
  }

  public static Model run3(Model model) {
    model.component("comp1").mesh("mesh1").feature("cpf3").selection("source").named("dif3");
    model.component("comp1").mesh("mesh1").feature("cpf3").selection("destination").named("dif4");
    model.component("comp1").mesh("mesh1").run("cpf3");
    model.component("comp1").mesh("mesh1").feature().duplicate("size2", "size1");
    model.component("comp1").mesh("mesh1").feature("size2").set("hmax", "mesh_factor*w_rotor");
    model.component("comp1").mesh("mesh1").feature("size2").set("hmin", "mesh_factor*w_rotor/10");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("int7");
    model.component("comp1").mesh("mesh1").create("cpf4", "CopyFace");
    model.component("comp1").mesh("mesh1").feature("cpf4").selection("source").geom(2);
    model.component("comp1").mesh("mesh1").feature("cpf4").selection("destination").geom(2);
    model.component("comp1").mesh("mesh1").feature("cpf4").selection("source").named("int7");
    model.component("comp1").mesh("mesh1").feature("cpf4").selection("destination").named("int8");
    model.component("comp1").mesh("mesh1").create("ftri2", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri2").selection().named("int9");
    model.component("comp1").mesh("mesh1").create("cpf5", "CopyFace");
    model.component("comp1").mesh("mesh1").feature("cpf5").selection("source").geom(2);
    model.component("comp1").mesh("mesh1").feature("cpf5").selection("destination").geom(2);
    model.component("comp1").mesh("mesh1").feature("cpf5").selection("source").named("int9");
    model.component("comp1").mesh("mesh1").feature("cpf5").selection("destination").named("int10");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("box2");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1")
         .set("numelem", "max(1,floor(3/mesh_factor))");
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe2").selection().named("geom1_ext2_dom");
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1")
         .set("numelem", "max(1,floor(3/mesh_factor))");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - \u7a33\u6001");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").create("def", "Deform");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").set("plotgroup", "Default");
    model.study("std2").feature("stat").set("geometricNonlinearity", true);
    model.study("std2").feature("stat").set("outputmap", new String[]{});
    model.study("std2").feature("stat").set("ngenAUX", "1");
    model.study("std2").feature("stat").set("goalngenAUX", "1");
    model.study("std2").feature("stat").set("ngenAUX", "1");
    model.study("std2").feature("stat").set("goalngenAUX", "1");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").create("eig", "Eigenfrequency");
    model.study("std2").feature("eig").set("plotgroup", "Default");
    model.study("std2").feature("eig").set("storefact", false);
    model.study("std2").feature("eig").set("geometricNonlinearity", true);
    model.study("std2").feature("eig").set("outputmap", new String[]{});
    model.study("std2").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").setSolveFor("/physics/solid", true);

    model.component("comp1").common().create("mpf1", "ParticipationFactors");

    model.study("std2").label("\u7814\u7a76 2 - \u9884\u5e94\u529b\u7279\u5f81\u9891\u7387");
    model.study("std2").feature("eig").set("neigsactive", true);
    model.study("std2").feature("eig").set("neigs", 3);
    model.study("std2").feature("eig").set("shift", "38000[Hz]");
    model.study("std2").feature("eig").set("eigwhich", "lr");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset("dset2").set("frametype", "spatial");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").label("\u632f\u578b (solid)");
    model.result("pg2").set("showlegends", false);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid.disp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("std2EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std2EvgFrq").set("data", "dset2");
    model.result().evaluationGroup("std2EvgFrq")
         .label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 2 - \u9884\u5e94\u529b\u7279\u5f81\u9891\u7387)");
    model.result().evaluationGroup("std2EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std2EvgFrq").run();
    model.result().evaluationGroup().create("std2mpf1", "EvaluationGroup");
    model.result().evaluationGroup("std2mpf1").set("data", "dset2");
    model.result().evaluationGroup("std2mpf1")
         .label("\u53c2\u4e0e\u56e0\u5b50 (\u7814\u7a76 2 - \u9884\u5e94\u529b\u7279\u5f81\u9891\u7387)");
    model.result().evaluationGroup("std2mpf1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormX", 0);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 0);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u5e73\u79fb", 0);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormY", 1);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u5e73\u79fb", 1);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormZ", 2);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u5e73\u79fb", 2);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormX", 3);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 3);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u65cb\u8f6c", 3);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormY", 4);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 4);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u65cb\u8f6c", 4);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormZ", 5);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "1", 5);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u65cb\u8f6c", 5);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLX", 6);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg", 6);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u5e73\u79fb", 6);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLY", 7);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg", 7);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u5e73\u79fb", 7);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLZ", 8);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg", 8);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u5e73\u79fb", 8);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRX", 9);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg*m^2", 9);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u65cb\u8f6c", 9);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRY", 10);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg*m^2", 10);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u65cb\u8f6c", 10);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRZ", 11);
    model.result().evaluationGroup("std2mpf1").feature("gev1").setIndex("unit", "kg*m^2", 11);
    model.result().evaluationGroup("std2mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u65cb\u8f6c", 11);
    model.result().evaluationGroup("std2mpf1").run();
    model.result("pg2").run();
    model.result("pg2").stepNext(0);
    model.result("pg2").run();

    model.param().create("par3");
    model.param("par3").label("\u53c2\u6570 3 - \u4f30\u7b97\u9a71\u52a8\u6a21\u5f0f\u9891\u7387");
    model.param("par3").set("rho0", "2320[kg/m^3]");
    model.param("par3").descr("rho0", "\u5bc6\u5ea6");
    model.param("par3")
         .set("m0", "rho0*t_beam*2*(2*n_combs*l_rotor*w_rotor+l_mass*w_mass-n_etch_x*n_etch_y*etch_dim^2)");
    model.param("par3").descr("m0", "\u603b\u8d28\u91cf");
    model.param("par3").set("E0", "160e9[Pa]");
    model.param("par3").descr("E0", "\u6768\u6c0f\u6a21\u91cf");
    model.param("par3").set("I0", "t_beam*x_spring_w^3/12");
    model.param("par3").descr("I0", "X \u5f39\u7c27\u9762\u5185\u5f2f\u66f2\u7684\u9762\u79ef\u60ef\u6027\u77e9");
    model.param("par3").set("k0", "4*12*E0*I0/x_spring_l^3");
    model.param("par3").descr("k0", "X \u5f39\u7c27\u9762\u5185\u5f2f\u66f2\u7684\u5f39\u7c27\u5e38\u6570");
    model.param("par3").set("I1", "t_beam*y_spring_w^3/12");
    model.param("par3").descr("I1", "Y \u5f39\u7c27\u9762\u5185\u5f2f\u66f2\u7684\u9762\u79ef\u60ef\u6027\u77e9");
    model.param("par3").set("y_spring_l3", "y_spring_l/2-tether_x");
    model.param("par3")
         .descr("y_spring_l3", "\u7cfb\u7ef3\u4e0e X \u5f39\u7c27\u4e4b\u95f4\u7684 Y \u5f39\u7c27\u957f\u5ea6");
    model.param("par3").set("k1", "4*12*E0*I1/y_spring_l3^3");
    model.param("par3").descr("k1", "Y \u5f39\u7c27\u9762\u5185\u5f2f\u66f2\u7684\u5f39\u7c27\u5e38\u6570");
    model.param("par3").set("k_tot", "1/(1/k0+1/k1)");
    model.param("par3").descr("k_tot", "\u9762\u5185\u5f2f\u66f2\u7684\u603b\u5f39\u7c27\u5e38\u6570");
    model.param("par3").set("f0", "sqrt(k_tot/m0)/2/pi");
    model.param("par3").descr("f0", "\u4f30\u7b97\u7684\u9a71\u52a8\u6a21\u5f0f\u9891\u7387");
    model.param().create("par4");
    model.param("par4").label("\u53c2\u6570 4 - \u4f30\u7b97\u611f\u5e94\u6a21\u5f0f\u9891\u7387");
    model.param("par4").set("I2", "t_beam^3*x_spring_w/12");
    model.param("par4").descr("I2", "X \u5f39\u7c27\u9762\u5916\u5f2f\u66f2\u7684\u9762\u79ef\u60ef\u6027\u77e9");
    model.param("par4").set("k2", "4*12*E0*I2/x_spring_l^3");
    model.param("par4").descr("k2", "X \u5f39\u7c27\u9762\u5916\u5f2f\u66f2\u7684\u5f39\u7c27\u5e38\u6570");
    model.param("par4").set("I3", "t_beam^3*y_spring_w/12");
    model.param("par4").descr("I3", "Y \u5f39\u7c27\u9762\u5916\u5f2f\u66f2\u7684\u9762\u79ef\u60ef\u6027\u77e9");
    model.param("par4").set("k3", "4*12*E0*I3/y_spring_l3^3");
    model.param("par4").descr("k3", "Y \u5f39\u7c27\u9762\u5916\u5f2f\u66f2\u7684\u5f39\u7c27\u5e38\u6570");
    model.param("par4").set("I4", "t_beam^3*tether_w/12");
    model.param("par4").descr("I4", "\u7cfb\u6881\u9762\u5916\u5f2f\u66f2\u7684\u9762\u79ef\u60ef\u6027\u77e9");
    model.param("par4").set("k4", "4*12*E0*I4/tether_l^3");
    model.param("par4").descr("k4", "\u7cfb\u6881\u9762\u5916\u5f2f\u66f2\u7684\u5f39\u7c27\u5e38\u6570");
    model.param("par4").set("G0", "80[GPa]");
    model.param("par4").descr("G0", "\u526a\u5207\u6a21\u91cf");
    model.param("par4")
         .set("k_th", "G0*t_beam*tether_w^3/tether_l*(1/3-0.21*tether_w/t_beam*(1-((tether_w/t_beam)^4)/12))");
    model.param("par4").descr("k_th", "\u7cfb\u6881\u7684\u626d\u8f6c\u5f39\u7c27\u5e38\u6570");
    model.param("par4").set("k5", "4*k_th/y_spring_l3^2");
    model.param("par4").descr("k5", "\u7cfb\u6881\u626d\u8f6c\u7684\u5f39\u7c27\u5e38\u6570");
    model.param("par4").set("k_tot2", "1/(1/k2+1/k3+1/k4+1/k5)");
    model.param("par4").descr("k_tot2", "\u9762\u5916\u5f2f\u66f2\u7684\u603b\u5f39\u7c27\u5e38\u6570");
    model.param("par4").set("f1", "sqrt(k_tot2/m0)/2/pi");
    model.param("par4").descr("f1", "\u4f30\u7b97\u7684\u611f\u5e94\u6a21\u5f0f\u9891\u7387");
    model.param().create("par5");
    model.param("par5").label("\u53c2\u6570 5 - \u7814\u7a76 2 \u7684\u7ed3\u679c");
    model.param("par5").set("fd", "38262[Hz]");
    model.param("par5")
         .descr("fd", "\u9a71\u52a8\u9891\u7387\uff08\u8ba1\u7b97\u7684\u9a71\u52a8\u6a21\u5f0f\u7279\u5f81\u9891\u7387\uff09");

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").set("plotgroup", "Default");
    model.study("std3").feature("stat").set("geometricNonlinearity", true);
    model.study("std3").feature("stat").set("outputmap", new String[]{});
    model.study("std3").feature("stat").set("ngenAUX", "1");
    model.study("std3").feature("stat").set("goalngenAUX", "1");
    model.study("std3").feature("stat").set("ngenAUX", "1");
    model.study("std3").feature("stat").set("goalngenAUX", "1");
    model.study("std3").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std3").create("frlin", "Frequencylinearized");
    model.study("std3").feature("frlin").set("plotgroup", "Default");
    model.study("std3").feature("frlin").set("geometricNonlinearity", true);
    model.study("std3").feature("frlin").set("outputmap", new String[]{});
    model.study("std3").feature("frlin").set("ngenAUX", "1");
    model.study("std3").feature("frlin").set("goalngenAUX", "1");
    model.study("std3").feature("frlin").set("ngenAUX", "1");
    model.study("std3").feature("frlin").set("goalngenAUX", "1");
    model.study("std3").feature("frlin").setSolveFor("/physics/solid", true);
    model.study("std3").label("\u7814\u7a76 3 - \u9884\u5e94\u529b\u9891\u57df");
    model.study("std3").feature("frlin").set("plist", "fd");
    model.study("std3").feature("frlin").set("useparam", true);
    model.study("std3").feature("frlin").set("sweeptype", "filled");
    model.study("std3").feature("frlin").setIndex("pname_aux", "AC_on", 0);
    model.study("std3").feature("frlin").setIndex("plistarr_aux", "", 0);
    model.study("std3").feature("frlin").setIndex("punit_aux", "", 0);
    model.study("std3").feature("frlin").setIndex("pname_aux", "AC_on", 0);
    model.study("std3").feature("frlin").setIndex("plistarr_aux", "", 0);
    model.study("std3").feature("frlin").setIndex("punit_aux", "", 0);
    model.study("std3").feature("frlin").setIndex("plistarr_aux", 1, 0);
    model.study("std3").feature("frlin").setIndex("pname_aux", "delta", 1);
    model.study("std3").feature("frlin").setIndex("plistarr_aux", "", 1);
    model.study("std3").feature("frlin").setIndex("punit_aux", "m", 1);
    model.study("std3").feature("frlin").setIndex("pname_aux", "delta", 1);
    model.study("std3").feature("frlin").setIndex("plistarr_aux", "", 1);
    model.study("std3").feature("frlin").setIndex("punit_aux", "m", 1);
    model.study("std3").feature("frlin").setIndex("pname_aux", "Omega", 1);
    model.study("std3").feature("frlin").setIndex("plistarr_aux", "0 100", 1);
    model.study("std3").feature("frlin").setIndex("punit_aux", "deg/s", 1);
    model.study("std3").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().dataset("dset4").set("frametype", "spatial");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset4");
    model.result("pg3").setIndex("looplevel", 2, 0);
    model.result("pg3").setIndex("looplevel", 1, 1);
    model.result("pg3").setIndex("looplevel", 1, 2);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").label("\u5e94\u529b (solid) 1");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("expr", new String[]{"solid.misesGp_peak"});
    model.result("pg3").feature("vol1").set("threshold", "manual");
    model.result("pg3").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("vol1").set("differential", true);
    model.result("pg3").feature("vol1").set("colortable", "Rainbow");
    model.result("pg3").feature("vol1").set("colortabletrans", "none");
    model.result("pg3").feature("vol1").set("colorscalemode", "linear");
    model.result("pg3").feature("vol1").set("resolution", "custom");
    model.result("pg3").feature("vol1").set("refine", 2);
    model.result("pg3").feature("vol1").set("colortable", "Prism");
    model.result("pg3").feature("vol1").create("def", "Deform");
    model.result("pg3").feature("vol1").feature("def").set("differential", true);
    model.result("pg3").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg3").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u865a\u90e8 X \u4f4d\u79fb - \u9a71\u52a8\u6a21\u5f0f\u5e45\u5ea6");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u9a71\u52a8\u6a21\u5f0f\u5e45\u5ea6 (\u00b5m)");
    model.result("pg4").set("frametype", "material");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").run();
    model.result("pg4").feature("vol1").set("expr", "imag(u)");
    model.result("pg4").run();
    model.result("pg4").feature("vol1").feature("def").set("expr", new String[]{"imag(u)", "imag(v)", "w"});
    model.result("pg4").feature("vol1").feature("def").setIndex("expr", "imag(w)", 2);
    model.result("pg4").run();

    model.param().create("par6");
    model.param("par6").label("\u53c2\u6570 6 - \u4f30\u7b97\u9a71\u52a8\u6a21\u5f0f\u5e45\u5ea6");
    model.param("par6").set("n_overlaps", "2*n_combs*4");
    model.param("par6").descr("n_overlaps", "\u68b3\u9a71\u52a8\u7535\u6781\u91cd\u53e0\u7684\u603b\u6570");
    model.param("par6").set("F_comb_dc", "n_overlaps*epsilon0_const*t_beam*Vcomb^2/(2*gap_combs)");
    model.param("par6").descr("F_comb_dc", "\u76f4\u6d41\u68b3\u529b");
    model.param("par6").set("F_comb_ac", "2*F_comb_dc*V_ac/Vcomb");
    model.param("par6").descr("F_comb_ac", "\u4ea4\u6d41\u68b3\u529b");
    model.param("par6").set("u_ac0", "F_comb_ac*Q/k_tot");
    model.param("par6").descr("u_ac0", "\u4f30\u7b97\u7684\u9a71\u52a8\u6a21\u5f0f\u4ea4\u6d41\u5e45\u5ea6");

    model.result("pg3").run();
    model.result().duplicate("pg5", "pg3");
    model.result("pg5").run();
    model.result("pg5").label("\u5b9e\u90e8 Z \u4f4d\u79fb - \u65e0\u65cb\u8f6c");
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").set("frametype", "material");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").run();
    model.result("pg5").feature("vol1").set("expr", "w");
    model.result("pg5").feature("vol1").set("differential", false);
    model.result("pg5").run();
    model.result("pg5").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w*1e3"});
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u5b9e\u90e8 Z \u4f4d\u79fb - \u65cb\u8f6c");
    model.result("pg6").setIndex("looplevel", 2, 0);
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u5b9e\u90e8 Z \u4f4d\u79fb - \u51c0\u611f\u5e94\u4fe1\u53f7");
    model.result("pg7").set("titletype", "manual");
    model.result("pg7").set("title", "\u611f\u5e94\u6a21\u5f0f\u5e45\u5ea6 (\u00b5m)");
    model.result("pg7").run();
    model.result("pg7").feature("vol1").set("expr", "w-withsol('sol4',w,setind(Omega,1))");
    model.result("pg7").run();
    model.result("pg7").feature("vol1").feature("def")
         .set("expr", new String[]{"u-withsol('sol4',u,setind(Omega,1))", "v", "w*1e3"});
    model.result("pg7").feature("vol1").feature("def").setIndex("expr", "v-withsol('sol4',v,setind(Omega,1))", 1);
    model.result("pg7").feature("vol1").feature("def")
         .setIndex("expr", "(w-withsol('sol4',w,setind(Omega,1)))*1e3", 2);
    model.result("pg7").run();

    model.param().create("par7");
    model.param("par7").label("\u53c2\u6570 7 - \u4f30\u7b97\u611f\u5e94\u6a21\u5f0f\u5e45\u5ea6");
    model.param("par7").set("fs", "41129[Hz]");
    model.param("par7").descr("fs", "\u8ba1\u7b97\u7684\u611f\u5e94\u6a21\u5f0f\u7279\u5f81\u9891\u7387");
    model.param("par7").set("k_from_fd", "m0*(2*pi*fd)^2");
    model.param("par7")
         .descr("k_from_fd", "\u8ba1\u7b97\u7684\u9a71\u52a8\u6a21\u5f0f\u9891\u7387\u7684\u603b\u5f39\u7c27\u5e38\u6570");
    model.param("par7").set("u_ac0_from_fd", "F_comb_ac*Q/k_from_fd");
    model.param("par7")
         .descr("u_ac0_from_fd", "\u8ba1\u7b97\u7684\u9891\u7387\u7684\u9a71\u52a8\u6a21\u5f0f\u4ea4\u6d41\u5e45\u5ea6");
    model.param("par7").set("v_ac0_from_fd", "u_ac0_from_fd*2*pi*fd");
    model.param("par7")
         .descr("v_ac0_from_fd", "\u8ba1\u7b97\u7684\u9891\u7387\u7684\u9a71\u52a8\u6a21\u5f0f\u901f\u5ea6\u5e45\u503c");
    model.param("par7").set("F_c", "2*m0*v_ac0_from_fd*100[deg/s]");
    model.param("par7").descr("F_c", "\u79d1\u91cc\u5965\u5229\u529b");
    model.param("par7").set("k_from_fs", "m0*(2*pi*fs)^2");
    model.param("par7")
         .descr("k_from_fs", "\u8ba1\u7b97\u7684\u611f\u5e94\u6a21\u5f0f\u9891\u7387\u7684\u5f39\u7c27\u5e38\u6570");
    model.param("par7").set("u_s_at_fs", "F_c*Q/k_from_fs");
    model.param("par7")
         .descr("u_s_at_fs", "\u4ee5\u611f\u5e94\u9891\u7387\u9a71\u52a8\u65f6\u7684\u611f\u5e94\u6a21\u5f0f\u5e45\u5ea6");
    model.param("par7").set("u_s", "u_s_at_fs/sqrt(1+Q^2*(fd/fs-fs/fd)^2)");
    model.param("par7")
         .descr("u_s", "\u9a71\u52a8\u9891\u7387\u4e0b\u4f30\u7b97\u7684\u611f\u5e94\u6a21\u5f0f\u5e45\u5ea6");

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").label("\u5e73\u5747\u503c 1 - \u4e0b\u90e8\u7535\u6781");
    model.component("comp1").cpl("aveop1").set("opname", "aveoppp");
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("aveop1").selection().named("uni7");

    model.sol("sol1").updateSolution();

    model.result("pg1").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u8ba1\u7b97\u7ec4 1 - \u7814\u7a76 1 - \u7a33\u6001");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "aveoppp(w)", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u5e73\u5747 Z \u4f4d\u79fb", 0);
    model.result().evaluationGroup("eg1").run();

    model.sol("sol4").updateSolution();

    model.result("pg3").run();
    model.result().evaluationGroup().duplicate("eg2", "eg1");
    model.result().evaluationGroup("eg2")
         .label("\u8ba1\u7b97\u7ec4 2 - \u7814\u7a76 3 - \u9884\u5e94\u529b\u9891\u57df");
    model.result().evaluationGroup("eg2").set("data", "dset4");
    model.result().evaluationGroup("eg2").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("expr", "aveoppp(imag(sign*u))", 0);
    model.result().evaluationGroup("eg2").feature("gev1")
         .setIndex("descr", "\u9a71\u52a8\u6a21\u5f0f\u5e45\u5ea6", 0);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("expr", "aveoppp(real(sign*w))", 1);
    model.result().evaluationGroup("eg2").feature("gev1")
         .setIndex("descr", "\u611f\u5e94\u6a21\u5f0f\u5e45\u5ea6", 1);
    model.result().evaluationGroup("eg2").feature("gev1")
         .setIndex("expr", "intoppp(sign*C_A)/Omega/1[aF/(deg/s)]", 2);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("descr", "\u7075\u654f\u5ea6 (aF/(deg/s))", 2);
    model.result().evaluationGroup("eg2").feature("gev1")
         .setIndex("expr", "-aveoppp(real(sign*w))*epsilon0_const*intoppp(1)/t_anchor^2/Omega/1[aF/(deg/s)]", 3);
    model.result().evaluationGroup("eg2").feature("gev1")
         .setIndex("descr", "\u4f30\u7b97\u7684\u7075\u654f\u5ea6", 3);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("expr", "intoppp(sign*C_A)", 4);
    model.result().evaluationGroup("eg2").feature("gev1").setIndex("unit", "aF", 4);
    model.result().evaluationGroup("eg2").feature("gev1")
         .setIndex("descr", "\u611f\u5e94\u7535\u5bb9\u5927\u5c0f", 4);
    model.result().evaluationGroup("eg2").feature("gev1").set("differential", true);
    model.result().evaluationGroup("eg2").run();

    model.title("\u68b3\u9a71\u52a8\u97f3\u53c9\u5f0f\u5fae\u901f\u7387\u9640\u87ba\u4eea");

    model
         .description("\u672c\u4f8b\u662f\u7531 Veryst Engineering, LLC. \u7684 James Ransley \u535a\u58eb\u63d0\u4f9b\u7684\u68b3\u9a71\u52a8\u97f3\u53c9\u9640\u87ba\u4eea\u6559\u5b66\u6848\u4f8b\uff0c\u6f14\u793a\u5b8c\u5168\u53c2\u6570\u5316\u7684\u51e0\u4f55\uff0c\u5e7f\u6cdb\u4f7f\u7528\u5404\u79cd\u9009\u62e9\u7279\u5f81\uff0c\u5b9e\u73b0\u4e86\u673a\u7535\u529b\u548c\u54cd\u5e94\u8ba1\u7b97\u7684\u89e3\u6790\u516c\u5f0f\uff0c\u5e76\u5c06\u6570\u503c\u7ed3\u679c\u4e0e\u89e3\u6790\u8ba1\u7b97\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\u3002\u7279\u522b\u503c\u5f97\u4e00\u63d0\u7684\u662f\uff0c\u5176\u4e2d\u4f7f\u7528\u62c9\u4f38\u7b97\u5b50\u6765\u8ba1\u7b97\u7535\u6781\u4e4b\u95f4\u7684\u8ddd\u79bb\uff0c\u4ece\u800c\u8fdb\u884c\u529b\u8ba1\u7b97\u3002");

    model.label("comb_drive_tuning_fork_gyroscope.mph");

    model.param().create("par8");
    model.param("par8").label("\u53c2\u6570 8 - \u5236\u9020\u504f\u5dee");
    model.param("par8").set("theta0", "0.5[deg]");
    model.param("par8").descr("theta0", "\u4fa7\u58c1\u503e\u659c");
    model.param("par8").set("phi0", "45[deg]");
    model.param("par8").descr("phi0", "\u4fa7\u58c1\u65b9\u4f4d\u89d2");
    model.param("par8").set("dt0", "0[um]");
    model.param("par8").descr("dt0", "\u539a\u5ea6\u5dee\u5f02");
    model.param("par8").set("oe0", "0[um]");
    model.param("par8").descr("oe0", "\u8fc7\u523b\u8680");

    model.component("comp1").variable().create("var9");
    model.component("comp1").variable("var9").label("\u53d8\u91cf 9 - \u5236\u9020\u504f\u5dee");
    model.component("comp1").variable("var9").set("theta", "theta0");
    model.component("comp1").variable("var9")
         .descr("theta", "\u4fa7\u58c1 theta \u5dee\u5f02\u968f\u5168\u5c40\u5750\u6807\u53d8\u5316\u7684\u60c5\u51b5");
    model.component("comp1").variable("var9").set("phi", "phi0");
    model.component("comp1").variable("var9")
         .descr("phi", "\u4fa7\u58c1 phi \u5dee\u5f02\u968f\u5168\u5c40\u5750\u6807\u53d8\u5316\u7684\u60c5\u51b5");
    model.component("comp1").variable("var9").set("dX_sw", "Zg*tan(theta)*cos(phi)");
    model.component("comp1").variable("var9")
         .descr("dX_sw", "\u4fa7\u58c1\u5dee\u5f02\u7684 X \u65b9\u5411\u53d8\u5316");

    return model;
  }

  public static Model run4(Model model) {
    model.component("comp1").variable("var9").set("dY_sw", "Zg*tan(theta)*sin(phi)");
    model.component("comp1").variable("var9")
         .descr("dY_sw", "\u4fa7\u58c1\u5dee\u5f02\u7684 Y \u65b9\u5411\u53d8\u5316");
    model.component("comp1").variable("var9").set("sw", "nXg*dX_sw+nYg*dY_sw");
    model.component("comp1").variable("var9").descr("sw", "\u4fa7\u58c1\u6cd5\u5411\u4f4d\u79fb");
    model.component("comp1").variable("var9").set("delta_t_function", "dt0");
    model.component("comp1").variable("var9")
         .descr("delta_t_function", "\u539a\u5ea6\u5dee\u5f02\u968f\u5168\u5c40\u5750\u6807\u53d8\u5316\u7684\u60c5\u51b5");
    model.component("comp1").variable("var9").set("dZ_t", "delta_t_function");
    model.component("comp1").variable("var9")
         .descr("dZ_t", "\u539a\u5ea6\u5dee\u5f02\u7684 Z \u65b9\u5411\u53d8\u5316");
    model.component("comp1").variable("var9").set("dt", "nZg*dZ_t");
    model.component("comp1").variable("var9").descr("dt", "\u539a\u5ea6\u6cd5\u5411\u4f4d\u79fb");
    model.component("comp1").variable("var9").set("oe", "oe0");
    model.component("comp1").variable("var9")
         .descr("oe", "\u8fc7\u523b\u8680\u5dee\u5f02\u968f\u5168\u5c40\u5750\u6807\u53d8\u5316\u7684\u60c5\u51b5");

    model.component("comp1").common().create("free1", "DeformingDomainDeformedGeometry");
    model.component("comp1").common("free1").selection().all();
    model.component("comp1").common("free1").label("\u53d8\u5f62\u57df 1 - \u6881\u5c42");
    model.component("comp1").common("free1").selection().named("box2");
    model.component("comp1").common("free1").set("smoothingType", "laplace");

    model.study("std1").feature("stat").setSolveFor("/frame/material1", false);
    model.study("std2").feature("stat").setSolveFor("/frame/material1", false);
    model.study("std2").feature("eig").setSolveFor("/frame/material1", false);
    model.study("std3").feature("stat").setSolveFor("/frame/material1", false);
    model.study("std3").feature("frlin").setSolveFor("/frame/material1", false);

    model.component("comp1").selection().create("box20", "Box");
    model.component("comp1").selection("box20").label("\u6846 21 - \u5782\u76f4\u8868\u9762");
    model.component("comp1").selection("box20").set("entitydim", 2);
    model.component("comp1").selection("box20").set("zmin", "+delta");
    model.component("comp1").selection("box20").set("zmax", "2*delta");
    model.component("comp1").selection().duplicate("box21", "box20");
    model.component("comp1").selection("box21").label("\u6846 22 - \u9876\u9762");
    model.component("comp1").selection("box21").set("zmin", "t_beam-delta");
    model.component("comp1").selection("box21").set("zmax", "t_beam+delta");
    model.component("comp1").selection("box21").set("condition", "inside");
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("\u76f8\u90bb 1 - \u6881\u5c42\u7684\u5916\u8868\u9762");
    model.component("comp1").selection("adj1").set("input", new String[]{"box2"});
    model.component("comp1").selection().create("int11", "Intersection");
    model.component("comp1").selection("int11").label("\u4ea4\u96c6 11 - \u5782\u76f4\u58c1");
    model.component("comp1").selection("int11").set("entitydim", 2);
    model.component("comp1").selection("int11").set("input", new String[]{"box20", "adj1"});
    model.component("comp1").selection().create("int12", "Intersection");
    model.component("comp1").selection("int12").label("\u4ea4\u96c6 12 - \u951a\u9762");
    model.component("comp1").selection("int12").set("entitydim", 2);
    model.component("comp1").selection("int12").set("input", new String[]{"box1", "geom1_ext2_bnd"});

    model.component("comp1").common().create("pnmd1", "PrescribedNormalMeshDisplacementDeformedGeometry");
    model.component("comp1").common("pnmd1")
         .label("\u6307\u5b9a\u6cd5\u5411\u7f51\u683c\u4f4d\u79fb 1 - \u5782\u76f4\u58c1");
    model.component("comp1").common("pnmd1").selection().named("int11");
    model.component("comp1").common("pnmd1").set("prescribedNormalDisplacement", "-oe+sw");
    model.component("comp1").common().create("pnmd2", "PrescribedNormalMeshDisplacementDeformedGeometry");
    model.component("comp1").common("pnmd2")
         .label("\u6307\u5b9a\u6cd5\u5411\u7f51\u683c\u4f4d\u79fb 2 - \u9876\u9762");
    model.component("comp1").common("pnmd2").selection().named("box21");
    model.component("comp1").common("pnmd2").set("prescribedNormalDisplacement", "dt");
    model.component("comp1").common().create("pnmd3", "PrescribedNormalMeshDisplacementDeformedGeometry");
    model.component("comp1").common("pnmd3")
         .label("\u6307\u5b9a\u6cd5\u5411\u7f51\u683c\u4f4d\u79fb 3 - \u57fa\u9762");
    model.component("comp1").common("pnmd3").selection().named("box1");
    model.component("comp1").common().create("disp1", "PrescribedMeshDisplacementDeformedGeometry");
    model.component("comp1").common("disp1").label("\u6307\u5b9a\u7f51\u683c\u4f4d\u79fb 1 - \u951a\u9762");
    model.component("comp1").common("disp1").selection().named("int12");

    model.study().create("std4");
    model.study("std4")
         .label("\u7814\u7a76 4 - \u5236\u9020\u504f\u5dee\u7684\u9884\u5e94\u529b\u7279\u5f81\u9891\u7387");
    model.study("std4").create("param", "Parametric");
    model.study("std4").feature("param").setIndex("pname", "AC_on", 0);
    model.study("std4").feature("param").setIndex("plistarr", "", 0);
    model.study("std4").feature("param").setIndex("punit", "", 0);
    model.study("std4").feature("param").setIndex("pname", "AC_on", 0);
    model.study("std4").feature("param").setIndex("plistarr", "", 0);
    model.study("std4").feature("param").setIndex("punit", "", 0);
    model.study("std4").feature("param").setIndex("pname", "oe0", 0);
    model.study("std4").feature("param").setIndex("plistarr", "0      0     50", 0);
    model.study("std4").feature("param").setIndex("punit", "nm", 0);
    model.study("std4").feature("param").setIndex("pname", "AC_on", 1);
    model.study("std4").feature("param").setIndex("plistarr", "", 1);
    model.study("std4").feature("param").setIndex("punit", "", 1);
    model.study("std4").feature("param").setIndex("pname", "AC_on", 1);
    model.study("std4").feature("param").setIndex("plistarr", "", 1);
    model.study("std4").feature("param").setIndex("punit", "", 1);
    model.study("std4").feature("param").setIndex("pname", "dt0", 1);
    model.study("std4").feature("param").setIndex("plistarr", "0     100   0", 1);
    model.study("std4").feature("param").setIndex("punit", "nm", 1);
    model.study("std4").feature("param").setIndex("pname", "AC_on", 2);
    model.study("std4").feature("param").setIndex("plistarr", "", 2);
    model.study("std4").feature("param").setIndex("punit", "", 2);
    model.study("std4").feature("param").setIndex("pname", "AC_on", 2);
    model.study("std4").feature("param").setIndex("plistarr", "", 2);
    model.study("std4").feature("param").setIndex("punit", "", 2);
    model.study("std4").feature("param").setIndex("pname", "theta0", 2);
    model.study("std4").feature("param").setIndex("plistarr", "0.5    0    0", 2);
    model.study("std4").feature("param").setIndex("punit", "deg", 2);
    model.study("std4").feature().copy("stat", "std2/stat");
    model.study("std4").feature("stat").setSolveFor("/frame/material1", true);
    model.study("std4").feature().copy("eig", "std2/eig");
    model.study("std4").feature("eig").set("neigs", 2);
    model.study("std4").feature("eig").set("shift", "36600[Hz]");
    model.study("std4").createAutoSequences("all");

    model.sol().create("sol8");
    model.sol("sol8").study("std4");
    model.sol("sol8").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol8");
    model.batch("p1").run("compute");

    model.result().dataset("dset8").set("frametype", "spatial");
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").set("data", "dset8");
    model.result("pg8").setIndex("looplevel", 1, 0);
    model.result("pg8").setIndex("looplevel", 3, 1);
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").label("\u632f\u578b (solid) 1");
    model.result("pg8").set("showlegends", false);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"solid.disp"});
    model.result("pg8").feature("surf1").set("threshold", "manual");
    model.result("pg8").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg8").feature("surf1").set("colortable", "Rainbow");
    model.result("pg8").feature("surf1").set("colortabletrans", "none");
    model.result("pg8").feature("surf1").set("colorscalemode", "linear");
    model.result("pg8").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg8").feature("surf1").create("def", "Deform");
    model.result("pg8").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg8").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("std4EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std4EvgFrq").set("data", "dset8");
    model.result().evaluationGroup("std4EvgFrq")
         .label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 4 - \u5236\u9020\u504f\u5dee\u7684\u9884\u5e94\u529b\u7279\u5f81\u9891\u7387)");
    model.result().evaluationGroup("std4EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std4EvgFrq").run();
    model.result().evaluationGroup().create("std4mpf1", "EvaluationGroup");
    model.result().evaluationGroup("std4mpf1").set("data", "dset8");
    model.result().evaluationGroup("std4mpf1")
         .label("\u53c2\u4e0e\u56e0\u5b50 (\u7814\u7a76 4 - \u5236\u9020\u504f\u5dee\u7684\u9884\u5e94\u529b\u7279\u5f81\u9891\u7387)");
    model.result().evaluationGroup("std4mpf1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std4mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormX", 0);
    model.result().evaluationGroup("std4mpf1").feature("gev1").setIndex("unit", "1", 0);
    model.result().evaluationGroup("std4mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u5e73\u79fb", 0);
    model.result().evaluationGroup("std4mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormY", 1);
    model.result().evaluationGroup("std4mpf1").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std4mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u5e73\u79fb", 1);
    model.result().evaluationGroup("std4mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormZ", 2);
    model.result().evaluationGroup("std4mpf1").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std4mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u5e73\u79fb", 2);
    model.result().evaluationGroup("std4mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormX", 3);
    model.result().evaluationGroup("std4mpf1").feature("gev1").setIndex("unit", "1", 3);
    model.result().evaluationGroup("std4mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u65cb\u8f6c", 3);
    model.result().evaluationGroup("std4mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormY", 4);
    model.result().evaluationGroup("std4mpf1").feature("gev1").setIndex("unit", "1", 4);
    model.result().evaluationGroup("std4mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u65cb\u8f6c", 4);
    model.result().evaluationGroup("std4mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormZ", 5);
    model.result().evaluationGroup("std4mpf1").feature("gev1").setIndex("unit", "1", 5);
    model.result().evaluationGroup("std4mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u65cb\u8f6c", 5);
    model.result().evaluationGroup("std4mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLX", 6);
    model.result().evaluationGroup("std4mpf1").feature("gev1").setIndex("unit", "kg", 6);
    model.result().evaluationGroup("std4mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u5e73\u79fb", 6);
    model.result().evaluationGroup("std4mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLY", 7);
    model.result().evaluationGroup("std4mpf1").feature("gev1").setIndex("unit", "kg", 7);
    model.result().evaluationGroup("std4mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u5e73\u79fb", 7);
    model.result().evaluationGroup("std4mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLZ", 8);
    model.result().evaluationGroup("std4mpf1").feature("gev1").setIndex("unit", "kg", 8);
    model.result().evaluationGroup("std4mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u5e73\u79fb", 8);
    model.result().evaluationGroup("std4mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRX", 9);
    model.result().evaluationGroup("std4mpf1").feature("gev1").setIndex("unit", "kg*m^2", 9);
    model.result().evaluationGroup("std4mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u65cb\u8f6c", 9);
    model.result().evaluationGroup("std4mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRY", 10);
    model.result().evaluationGroup("std4mpf1").feature("gev1").setIndex("unit", "kg*m^2", 10);
    model.result().evaluationGroup("std4mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u65cb\u8f6c", 10);
    model.result().evaluationGroup("std4mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRZ", 11);
    model.result().evaluationGroup("std4mpf1").feature("gev1").setIndex("unit", "kg*m^2", 11);
    model.result().evaluationGroup("std4mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u65cb\u8f6c", 11);
    model.result().evaluationGroup("std4mpf1").run();
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").set("data", "dset8");
    model.result("pg9").setIndex("looplevel", 1, 0);
    model.result("pg9").setIndex("looplevel", 3, 1);
    model.result("pg9").label("\u53d8\u5f62\u51e0\u4f55");
    model.result("pg9").create("mesh1", "Mesh");
    model.result("pg9").feature("mesh1").set("meshdomain", "volume");
    model.result("pg9").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg9").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg9").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg9").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg9").feature("mesh1").feature("sel1").selection()
         .set(1, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 52, 53, 54, 57, 58, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110);
    model.result("pg9").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg9").feature("mesh1").set("qualexpr", "comp1.material.relVol");
    model.result("pg9").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg8").run();
    model.result().evaluationGroup().create("eg3", "EvaluationGroup");
    model.result().evaluationGroup("eg3")
         .label("\u8ba1\u7b97\u7ec4 3 - \u7814\u7a76 4 - \u5236\u9020\u504f\u5dee\u5f15\u8d77\u7684\u9891\u79fb");
    model.result().evaluationGroup("eg3").set("data", "none");
    model.result().evaluationGroup("eg3").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg3").feature("gev1").set("data", "dset8");
    model.result().evaluationGroup("eg3").feature("gev1").setIndex("looplevelinput", "first", 0);
    model.result().evaluationGroup("eg3").feature("gev1").setIndex("expr", "real(freq)-fd", 0);
    model.result().evaluationGroup("eg3").feature("gev1")
         .setIndex("descr", "\u9a71\u52a8\u6a21\u5f0f\u9891\u79fb", 0);
    model.result().evaluationGroup("eg3").feature().duplicate("gev2", "gev1");
    model.result().evaluationGroup("eg3").feature("gev2").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg3").feature("gev2").setIndex("expr", "real(freq)-fs", 0);
    model.result().evaluationGroup("eg3").feature("gev2")
         .setIndex("descr", "\u611f\u5e94\u6a21\u5f0f\u9891\u79fb", 0);
    model.result().evaluationGroup("eg3").run();

    model.study().create("std5");
    model.study("std5").label("\u7814\u7a76 5 - \u5236\u9020\u504f\u5dee\u7684\u9884\u5e94\u529b\u9891\u57df");
    model.study("std5").feature().copy("stat", "std3/stat");
    model.study("std5").feature("stat").setSolveFor("/frame/material1", true);
    model.study("std5").feature().copy("frlin", "std3/frlin");
    model.study("std5").createAutoSequences("all");

    model.sol("sol12").runAll();

    model.result().dataset("dset9").set("frametype", "spatial");
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").set("data", "dset9");
    model.result("pg10").setIndex("looplevel", 2, 0);
    model.result("pg10").setIndex("looplevel", 1, 1);
    model.result("pg10").setIndex("looplevel", 1, 2);
    model.result("pg10").set("frametype", "spatial");
    model.result("pg10").label("\u5e94\u529b (solid) 2");
    model.result("pg10").set("frametype", "spatial");
    model.result("pg10").create("vol1", "Volume");
    model.result("pg10").feature("vol1").set("expr", new String[]{"solid.misesGp_peak"});
    model.result("pg10").feature("vol1").set("threshold", "manual");
    model.result("pg10").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg10").feature("vol1").set("differential", true);
    model.result("pg10").feature("vol1").set("colortable", "Rainbow");
    model.result("pg10").feature("vol1").set("colortabletrans", "none");
    model.result("pg10").feature("vol1").set("colorscalemode", "linear");
    model.result("pg10").feature("vol1").set("resolution", "custom");
    model.result("pg10").feature("vol1").set("refine", 2);
    model.result("pg10").feature("vol1").set("colortable", "Prism");
    model.result("pg10").feature("vol1").create("def", "Deform");
    model.result("pg10").feature("vol1").feature("def").set("differential", true);
    model.result("pg10").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg10").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").set("data", "dset9");
    model.result("pg11").setIndex("looplevel", 2, 0);
    model.result("pg11").setIndex("looplevel", 1, 1);
    model.result("pg11").setIndex("looplevel", 1, 2);
    model.result("pg11").label("\u53d8\u5f62\u51e0\u4f55 1");
    model.result("pg11").create("mesh1", "Mesh");
    model.result("pg11").feature("mesh1").set("meshdomain", "volume");
    model.result("pg11").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg11").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg11").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg11").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg11").feature("mesh1").feature("sel1").selection()
         .set(1, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 52, 53, 54, 57, 58, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110);
    model.result("pg11").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg11").feature("mesh1").set("qualexpr", "comp1.material.relVol");
    model.result("pg11").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg10").run();
    model.result().evaluationGroup().duplicate("eg4", "eg2");
    model.result().evaluationGroup("eg4")
         .label("\u8ba1\u7b97\u7ec4 4 - \u7814\u7a76 5 - \u5236\u9020\u504f\u5dee\u7684\u9884\u5e94\u529b\u9891\u57df");
    model.result().evaluationGroup("eg4").set("data", "dset9");
    model.result().evaluationGroup("eg4").setIndex("looplevelinput", "all", 0);
    model.result().evaluationGroup("eg4").feature("gev1").setIndex("expr", 0, 2);
    model.result().evaluationGroup("eg4").feature("gev1").remove("unit", 2);
    model.result().evaluationGroup("eg4").feature("gev1").remove("descr", 2);
    model.result().evaluationGroup("eg4").feature("gev1").remove("expr", new int[]{2});
    model.result().evaluationGroup("eg4").feature("gev1").remove("unit", 2);
    model.result().evaluationGroup("eg4").feature("gev1").remove("descr", 2);
    model.result().evaluationGroup("eg4").feature("gev1").remove("expr", new int[]{2});
    model.result().evaluationGroup("eg4").run();
    model.result("pg8").run();
    model.result("pg8").label("\u632f\u578b - \u5e26\u4fa7\u58c1\u7684\u9a71\u52a8\u6a21\u5f0f");
    model.result("pg8").set("looplevel", new int[]{1, 1});
    model.result("pg8").run();
    model.result("pg8").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w*20"});
    model.result("pg8").run();
    model.result("pg2").run();
    model.result("pg2").label("\u632f\u578b - \u65e0\u4fa7\u58c1\u7684\u9a71\u52a8\u6a21\u5f0f");
    model.result("pg2").set("looplevel", new int[]{1});
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w*20"});
    model.result("pg2").run();
    model.result("pg5").run();
    model.result("pg5").label("\u5b9e\u90e8 Z \u4f4d\u79fb - \u65e0\u65cb\u8f6c\uff0c\u65e0\u4fa7\u58c1");
    model.result("pg5").run();
    model.result("pg5").feature("vol1").set("unit", "nm");
    model.result("pg5").run();
    model.result("pg5").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w*20"});
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg12", "pg5");
    model.result("pg12").run();
    model.result("pg12").label("\u5b9e\u90e8 Z \u4f4d\u79fb - \u65e0\u65cb\u8f6c\uff0c\u6709\u4fa7\u58c1");
    model.result("pg12").set("data", "dset9");
    model.result("pg12").run();

    model.study().create("std6");
    model.study("std6").create("stat", "Stationary");
    model.study("std6").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std6").label("\u7814\u7a76 6 - \u663e\u793a\u5f62\u72b6\u53d8\u5316");
    model.study("std6").create("param", "Parametric");
    model.study("std6").feature("param").setIndex("pname", "AC_on", 0);
    model.study("std6").feature("param").setIndex("plistarr", "", 0);
    model.study("std6").feature("param").setIndex("punit", "", 0);
    model.study("std6").feature("param").setIndex("pname", "AC_on", 0);
    model.study("std6").feature("param").setIndex("plistarr", "", 0);
    model.study("std6").feature("param").setIndex("punit", "", 0);
    model.study("std6").feature("param").setIndex("pname", "phi0", 0);
    model.study("std6").feature("param").setIndex("plistarr", 0, 0);
    model.study("std6").feature("param").setIndex("punit", "deg", 0);
    model.study("std6").feature("param").setIndex("pname", "AC_on", 1);
    model.study("std6").feature("param").setIndex("plistarr", "", 1);
    model.study("std6").feature("param").setIndex("punit", "", 1);
    model.study("std6").feature("param").setIndex("pname", "AC_on", 1);
    model.study("std6").feature("param").setIndex("plistarr", "", 1);
    model.study("std6").feature("param").setIndex("punit", "", 1);
    model.study("std6").feature("param").setIndex("pname", "theta0", 1);
    model.study("std6").feature("param").setIndex("plistarr", 10, 1);
    model.study("std6").feature("param").setIndex("punit", "deg", 1);
    model.study("std6").createAutoSequences("all");

    model.sol("sol14").runAll();

    model.result().create("pg13", "PlotGroup3D");
    model.result("pg13").set("data", "dset11");
    model.result("pg13").setIndex("looplevel", 1, 0);
    model.result("pg13").label("\u53d8\u5f62\u51e0\u4f55 2");
    model.result("pg13").create("mesh1", "Mesh");
    model.result("pg13").feature("mesh1").set("meshdomain", "volume");
    model.result("pg13").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg13").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg13").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg13").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg13").feature("mesh1").feature("sel1").selection()
         .set(1, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 52, 53, 54, 57, 58, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110);
    model.result("pg13").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg13").feature("mesh1").set("qualexpr", "comp1.material.relVol");
    model.result("pg13").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg13").run();
    model.result().create("pg14", "PlotGroup3D");
    model.result("pg14").run();
    model.result("pg14").label("\u663e\u793a\u5f62\u72b6\u53d8\u5316");
    model.result("pg14").set("data", "dset11");
    model.result("pg14").set("titletype", "label");
    model.result("pg14").set("frametype", "geometry");
    model.result("pg14").create("line1", "Line");
    model.result("pg14").feature("line1").set("expr", "1");
    model.result("pg14").feature("line1").set("coloring", "uniform");
    model.result("pg14").run();

    model
         .title("\u68b3\u9a71\u52a8\u97f3\u53c9\u5f0f\u5fae\u901f\u7387\u9640\u87ba\u4eea\u4e2d\u5236\u9020\u504f\u5dee\u7684\u5f71\u54cd");

    model
         .description("\u672c\u6559\u5b66\u6848\u4f8b\u7531 Veryst Engineering, LLC. \u7684 James Ransley \u535a\u58eb\u63d0\u4f9b\u3002\u8be5\u6a21\u578b\u5bf9\u57fa\u672c\u6a21\u578b\u201c\u68b3\u9a71\u52a8\u97f3\u53c9\u5f0f\u5fae\u901f\u7387\u9640\u87ba\u4eea\u201d\uff08\u4e5f\u7531 Ransley \u535a\u58eb\u63d0\u4f9b\uff09\u8fdb\u884c\u6269\u5c55\uff0c\u6f14\u793a\u5982\u4f55\u5728\u65e0\u9700\u975e\u5e38\u7cbe\u7ec6\u7684\u7f51\u683c\u7684\u60c5\u51b5\u4e0b\uff0c\u7cbe\u786e\u8ba1\u7b97 MEMS \u5668\u4ef6\u5236\u9020\u504f\u5dee\u7684\u5f71\u54cd\u3002\u8fd9\u79cd\u9ad8\u6548\u7684\u5efa\u6a21\u65b9\u6cd5\u57fa\u4e8e COMSOL Multiphysics \u72ec\u7279\u7684\u201c\u53d8\u5f62\u51e0\u4f55\u201d\u529f\u80fd\uff0c\u5176\u4e2d\u4f7f\u7528\u76f8\u540c\u7684\u7f51\u683c\u5b9e\u73b0\u4e86\u7531\u4e8e\u5236\u9020\u7f3a\u9677\u5bfc\u81f4\u7684\u5668\u4ef6\u5f62\u72b6\u53d8\u5316\uff0c\u4ece\u800c\u6d88\u9664\u4e86\u5bf9\u4e0d\u540c\u7684\u51e0\u4f55\u5f62\u72b6\u4f7f\u7528\u4e0d\u540c\u7684\u7f51\u683c\u65f6\u5f15\u5165\u7684\u8bef\u5dee\u3002");

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
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();
    model.sol("sol14").clearSolutionData();

    model.label("comb_drive_tuning_fork_gyroscope_manufacturing_variation.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    model = run3(model);
    run4(model);
  }

}
