/*
 * seated_human_body.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:22 by COMSOL 6.3.0.290. */
public class seated_human_body {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Multibody_Dynamics_Module\\Biomechanics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mbd", "MultibodyDynamics", "geom1");

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").set("storefact", false);
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("outputmap", new String[]{});
    model.study("std1").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").setSolveFor("/physics/mbd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("m_head", "7.24[kg]", "\u5934\u90e8\u8d28\u91cf");
    model.param().set("I_head", "0.411[kg*m^2]", "\u5934\u90e8\u60ef\u6027\u77e9");
    model.param().set("m_torso", "19.9[kg]", "\u8eaf\u5e72\u8d28\u91cf");
    model.param().set("I_torso", "1.627[kg*m^2]", "\u8eaf\u5e72\u60ef\u6027\u77e9");
    model.param().set("m_pelvis", "11.01[kg]", "\u9aa8\u76c6\u8d28\u91cf");
    model.param().set("I_pelvis", "0.692[kg*m^2]", "\u9aa8\u76c6\u60ef\u6027\u77e9");
    model.param().set("m_thigh", "20.35[kg]", "\u5927\u817f\u8d28\u91cf");
    model.param().set("I_thigh", "1.18[kg*m^2]", "\u5927\u817f\u60ef\u6027\u77e9");
    model.param().set("m_viscera", "12.92[kg]", "\u5185\u810f\u8d28\u91cf");
    model.param().set("k1", "113.7[kN/m]", "\u5e73\u52a8\u521a\u5ea6\uff0c\u5934\u90e8-\u8eaf\u5e72\u5173\u8282");
    model.param()
         .set("c1", "0.066[kN/(m/s)]", "\u5e73\u52a8\u963b\u5c3c\u7cfb\u6570\uff0c\u5934\u90e8-\u8eaf\u5e72\u5173\u8282");
    model.param()
         .set("kr1", "0.915[kN*m/rad]", "\u8f6c\u52a8\u521a\u5ea6\uff0c\u5934\u90e8-\u8eaf\u5e72\u5173\u8282");
    model.param()
         .set("cr1", "0.340[kN*m/(rad/s)]", "\u8f6c\u52a8\u963b\u5c3c\u7cfb\u6570\uff0c\u5934\u90e8-\u8eaf\u5e72\u5173\u8282");
    model.param().set("k2", "0.299[kN/m]", "\u5e73\u52a8\u521a\u5ea6\uff0c\u8eaf\u5e72-\u9aa8\u76c6\u5173\u8282");
    model.param()
         .set("c2", "1.79[kN/(m/s)]", "\u5e73\u52a8\u963b\u5c3c\u7cfb\u6570\uff0c\u8eaf\u5e72-\u9aa8\u76c6\u5173\u8282");
    model.param()
         .set("kr2", "0.328[kN*m/rad]", "\u8f6c\u52a8\u521a\u5ea6\uff0c\u8eaf\u5e72-\u9aa8\u76c6\u5173\u8282");
    model.param()
         .set("cr2", "0.724[kN*m/(rad/s)]", "\u8f6c\u52a8\u963b\u5c3c\u7cfb\u6570\uff0c\u8eaf\u5e72-\u9aa8\u76c6\u5173\u8282");
    model.param().set("k3", "6.40[kN/m]", "\u5e73\u52a8\u521a\u5ea6\uff0c\u9aa8\u76c6-\u5927\u817f\u5173\u8282");
    model.param()
         .set("c3", "0.061[kN/(m/s)]", "\u5e73\u52a8\u963b\u5c3c\u7cfb\u6570\uff0c\u9aa8\u76c6-\u5927\u817f\u5173\u8282");
    model.param()
         .set("kr3", "0.162[kN*m/rad]", "\u8f6c\u52a8\u521a\u5ea6\uff0c\u9aa8\u76c6-\u5927\u817f\u5173\u8282");
    model.param()
         .set("cr3", "0.030[kN*m/(rad/s)]", "\u8f6c\u52a8\u963b\u5c3c\u7cfb\u6570\uff0c\u9aa8\u76c6-\u5927\u817f\u5173\u8282");
    model.param().set("k4", "23.55[kN/m]", "\u5e73\u52a8\u521a\u5ea6\uff0c\u5927\u817f-\u5c0f\u817f\u5173\u8282");
    model.param()
         .set("c4", "0.154[kN/(m/s)]", "\u5e73\u52a8\u963b\u5c3c\u7cfb\u6570\uff0c\u5927\u817f-\u5c0f\u817f\u5173\u8282");
    model.param()
         .set("kr4", "0.220[kN*m/rad]", "\u8f6c\u52a8\u521a\u5ea6\uff0c\u5927\u817f-\u5c0f\u817f\u5173\u8282");
    model.param()
         .set("cr4", "0.104[kN*m/(rad/s)]", "\u8f6c\u52a8\u963b\u5c3c\u7cfb\u6570\uff0c\u5927\u817f-\u5c0f\u817f\u5173\u8282");
    model.param().set("kh5", "1.93[kN/m]", "\u6c34\u5e73\u521a\u5ea6\uff0c\u5185\u810f-\u8eaf\u5e72\u5173\u8282");
    model.param()
         .set("ch5", "0.079[kN/(m/s)]", "\u6c34\u5e73\u963b\u5c3c\u7cfb\u6570\uff0c\u5185\u810f-\u8eaf\u5e72\u5173\u8282");
    model.param().set("kv6", "18.37[kN/m]", "\u5782\u76f4\u521a\u5ea6\uff0c\u5185\u810f-\u9aa8\u76c6\u5173\u8282");
    model.param()
         .set("cv6", "0.197[kN/(m/s)]", "\u5782\u76f4\u963b\u5c3c\u7cfb\u6570\uff0c\u5185\u810f-\u9aa8\u76c6\u5173\u8282");
    model.param().set("kh7", "0.905[kN/m]", "\u6c34\u5e73\u521a\u5ea6\uff0c\u5ea7\u6905-\u9aa8\u76c6\u5173\u8282");
    model.param()
         .set("ch7", "0.015[kN/(m/s)]", "\u6c34\u5e73\u963b\u5c3c\u7cfb\u6570\uff0c\u5ea7\u6905-\u9aa8\u76c6\u5173\u8282");
    model.param().set("kv7", "121.3[kN/m]", "\u5782\u76f4\u521a\u5ea6\uff0c\u5ea7\u6905-\u9aa8\u76c6\u5173\u8282");
    model.param()
         .set("cv7", "0.047[kN/(m/s)]", "\u5782\u76f4\u963b\u5c3c\u7cfb\u6570\uff0c\u5ea7\u6905-\u9aa8\u76c6\u5173\u8282");
    model.param().set("kh8", "0.614[kN/m]", "\u6c34\u5e73\u521a\u5ea6\uff0c\u5ea7\u6905-\u5927\u817f\u5173\u8282");
    model.param()
         .set("ch8", "0.014[kN/(m/s)]", "\u6c34\u5e73\u963b\u5c3c\u7cfb\u6570\uff0c\u5ea7\u6905-\u5927\u817f\u5173\u8282");
    model.param().set("kv8", "16.71[kN/m]", "\u5782\u76f4\u521a\u5ea6\uff0c\u5ea7\u6905-\u5927\u817f\u5173\u8282");
    model.param()
         .set("cv8", "8.01[kN/(m/s)]", "\u5782\u76f4\u963b\u5c3c\u7cfb\u6570\uff0c\u5ea7\u6905-\u5927\u817f\u5173\u8282");
    model.param().set("vtt_in", "1[m/s^2]", "\u8f93\u5165\u52a0\u901f\u5ea6");
    model.param().set("i_c", "1", "\u963b\u5c3c\u63a7\u5236\u5668");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "seated_human_body.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("mbd").prop("AutoModeling").set("MassMI", true);
    model.component("comp1").physics("mbd").prop("AutoModeling").runCommand("createRigidDomains");
    model.component("comp1").physics("mbd").feature("rd1").label("\u521a\u6027\u6750\u6599\uff1a\u9aa8\u76c6");
    model.component("comp1").physics("mbd").feature("rd1").feature("mmi1").set("mt", "m_pelvis");
    model.component("comp1").physics("mbd").feature("rd1").feature("mmi1").set("Iz", "I_pelvis");
    model.component("comp1").physics("mbd").feature("rd1").feature("mmi1")
         .set("CenterOfMassType", "CentroidOfSelectedEntities");
    model.component("comp1").physics("mbd").feature("rd1").feature("mmi1").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("rd1").feature("mmi1").feature("cmp1").selection().set(11);
    model.component("comp1").physics("mbd").feature("rd2").label("\u521a\u6027\u6750\u6599\uff1a\u5934\u90e8");
    model.component("comp1").physics("mbd").feature("rd2").feature("mmi1").set("mt", "m_head");
    model.component("comp1").physics("mbd").feature("rd2").feature("mmi1").set("Iz", "I_head");
    model.component("comp1").physics("mbd").feature("rd2").feature("mmi1")
         .set("CenterOfMassType", "CentroidOfSelectedEntities");
    model.component("comp1").physics("mbd").feature("rd2").feature("mmi1").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("rd2").feature("mmi1").feature("cmp1").selection().set(25);
    model.component("comp1").physics("mbd").feature("rd3").label("\u521a\u6027\u6750\u6599\uff1a\u8eaf\u5e72");
    model.component("comp1").physics("mbd").feature("rd3").feature("mmi1").set("mt", "m_torso");
    model.component("comp1").physics("mbd").feature("rd3").feature("mmi1").set("Iz", "I_torso");
    model.component("comp1").physics("mbd").feature("rd3").feature("mmi1")
         .set("CenterOfMassType", "CentroidOfSelectedEntities");
    model.component("comp1").physics("mbd").feature("rd3").feature("mmi1").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("rd3").feature("mmi1").feature("cmp1").selection().set(46);
    model.component("comp1").physics("mbd").feature("rd4").label("\u521a\u6027\u6750\u6599\uff1a\u5185\u810f");
    model.component("comp1").physics("mbd").feature("rd4").feature("mmi1").set("mt", "m_viscera");
    model.component("comp1").physics("mbd").feature("rd4").feature("mmi1")
         .set("CenterOfMassType", "CentroidOfSelectedEntities");
    model.component("comp1").physics("mbd").feature("rd4").feature("mmi1").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("rd4").feature("mmi1").feature("cmp1").selection().set(55);
    model.component("comp1").physics("mbd").feature("rd5").label("\u521a\u6027\u6750\u6599\uff1a\u5927\u817f");
    model.component("comp1").physics("mbd").feature("rd5").feature("mmi1").set("mt", "m_thigh");
    model.component("comp1").physics("mbd").feature("rd5").feature("mmi1").set("Iz", "I_thigh");
    model.component("comp1").physics("mbd").feature("rd5").feature("mmi1")
         .set("CenterOfMassType", "CentroidOfSelectedEntities");
    model.component("comp1").physics("mbd").feature("rd5").feature("mmi1").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("rd5").feature("mmi1").feature("cmp1").selection().set(62);
    model.component("comp1").physics("mbd").feature("rd4").create("pdr1", "PrescribedDispRot", -1);
    model.component("comp1").physics("mbd").feature("rd4").feature("pdr1")
         .set("RotationType", "ConstrainedRotationGroup");
    model.component("comp1").physics("mbd").feature("rd6").label("\u521a\u6027\u6750\u6599\uff1a\u5c0f\u817f");
    model.component("comp1").physics("mbd").create("bsm1", "BaseMotion", -1);
    model.component("comp1").physics("mbd").feature("bsm1").label("\u57fa\u7840\u8fd0\u52a8\uff1a\u5ea7\u6905");
    model.component("comp1").physics("mbd").feature("bsm1").set("BaseMotionType", "Acceleration");
    model.component("comp1").physics("mbd").feature("bsm1").set("ab", new String[]{"0", "vtt_in", "0"});
    model.component("comp1").physics("mbd").create("fxj1", "FixedJoint", -1);
    model.component("comp1").physics("mbd").feature("fxj1")
         .label("\u56fa\u5b9a\u5173\u8282\uff1a\u5934\u90e8-\u8eaf\u5e72");
    model.component("comp1").physics("mbd").feature("fxj1").set("Source", "rd2");
    model.component("comp1").physics("mbd").feature("fxj1").set("Destination", "rd3");
    model.component("comp1").physics("mbd").feature("fxj1").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("fxj1").set("JointElasticity", "ElasticJoint");
    model.component("comp1").physics("mbd").feature("fxj1").feature("cjp1").selection().set(24);
    model.component("comp1").physics("mbd").feature("fxj1").feature("je1")
         .set("k_u", new String[]{"k1", "0", "0", "0", "k1", "0", "0", "0", "k1"});
    model.component("comp1").physics("mbd").feature("fxj1").feature("je1").set("k_th", "kr1");
    model.component("comp1").physics("mbd").feature("fxj1").feature("je1")
         .set("c_u", new String[]{"if(i_c==1,c1,0)", "0", "0", "0", "if(i_c==1,c1,0)", "0", "0", "0", "if(i_c==1,c1,0)"});
    model.component("comp1").physics("mbd").feature("fxj1").feature("je1").set("c_th", "if(i_c==1,cr1,0)");
    model.component("comp1").physics("mbd").feature().duplicate("fxj2", "fxj1");
    model.component("comp1").physics("mbd").feature("fxj2")
         .label("\u56fa\u5b9a\u5173\u8282\uff1a\u8eaf\u5e72-\u9aa8\u76c6");
    model.component("comp1").physics("mbd").feature("fxj2").set("Source", "rd3");
    model.component("comp1").physics("mbd").feature("fxj2").set("Destination", "rd1");
    model.component("comp1").physics("mbd").feature("fxj2").feature("cjp1").selection().set(39);
    model.component("comp1").physics("mbd").feature("fxj2").feature("je1")
         .set("k_u", new String[]{"k2", "0", "0", "0", "k2", "0", "0", "0", "k2"});
    model.component("comp1").physics("mbd").feature("fxj2").feature("je1").set("k_th", "kr2");
    model.component("comp1").physics("mbd").feature("fxj2").feature("je1")
         .set("c_u", new String[]{"if(i_c==1,c2,0)", "0", "0", "0", "if(i_c==1,c2,0)", "0", "0", "0", "if(i_c==1,c2,0)"});
    model.component("comp1").physics("mbd").feature("fxj2").feature("je1").set("c_th", "if(i_c==1,cr2,0)");
    model.component("comp1").physics("mbd").feature().duplicate("fxj3", "fxj2");
    model.component("comp1").physics("mbd").feature("fxj3")
         .label("\u56fa\u5b9a\u5173\u8282\uff1a\u9aa8\u76c6-\u5927\u817f");
    model.component("comp1").physics("mbd").feature("fxj3").set("Source", "rd1");
    model.component("comp1").physics("mbd").feature("fxj3").set("Destination", "rd5");
    model.component("comp1").physics("mbd").feature("fxj3").feature("cjp1").selection().set(14);
    model.component("comp1").physics("mbd").feature("fxj3").feature("je1")
         .set("k_u", new String[]{"k3", "0", "0", "0", "k3", "0", "0", "0", "k3"});
    model.component("comp1").physics("mbd").feature("fxj3").feature("je1").set("k_th", "kr3");
    model.component("comp1").physics("mbd").feature("fxj3").feature("je1")
         .set("c_u", new String[]{"if(i_c==1,c3,0)", "0", "0", "0", "if(i_c==1,c3,0)", "0", "0", "0", "if(i_c==1,c3,0)"});
    model.component("comp1").physics("mbd").feature("fxj3").feature("je1").set("c_th", "if(i_c==1,cr3,0)");
    model.component("comp1").physics("mbd").feature().duplicate("fxj4", "fxj3");
    model.component("comp1").physics("mbd").feature("fxj4")
         .label("\u56fa\u5b9a\u5173\u8282\uff1a\u5927\u817f-\u5c0f\u817f");
    model.component("comp1").physics("mbd").feature("fxj4").set("Source", "rd5");
    model.component("comp1").physics("mbd").feature("fxj4").set("Destination", "rd6");
    model.component("comp1").physics("mbd").feature("fxj4").feature("cjp1").selection().set(64);
    model.component("comp1").physics("mbd").feature("fxj4").feature("je1")
         .set("k_u", new String[]{"k4", "0", "0", "0", "k4", "0", "0", "0", "k4"});
    model.component("comp1").physics("mbd").feature("fxj4").feature("je1").set("k_th", "kr4");
    model.component("comp1").physics("mbd").feature("fxj4").feature("je1")
         .set("c_u", new String[]{"if(i_c==1,c4,0)", "0", "0", "0", "if(i_c==1,c4,0)", "0", "0", "0", "if(i_c==1,c4,0)"});
    model.component("comp1").physics("mbd").feature("fxj4").feature("je1").set("c_th", "if(i_c==1,cr4,0)");
    model.component("comp1").physics("mbd").feature().duplicate("fxj5", "fxj4");
    model.component("comp1").physics("mbd").feature("fxj5")
         .label("\u56fa\u5b9a\u5173\u8282\uff1a\u5185\u810f-\u8eaf\u5e72");
    model.component("comp1").physics("mbd").feature("fxj5").set("Source", "rd4");
    model.component("comp1").physics("mbd").feature("fxj5").set("Destination", "rd3");
    model.component("comp1").physics("mbd").feature("fxj5").feature("cjp1").selection().set(55);
    model.component("comp1").physics("mbd").feature("fxj5").feature("je1")
         .set("k_u", new String[]{"kh5", "0", "0", "0", "0", "0", "0", "0", "kh5"});
    model.component("comp1").physics("mbd").feature("fxj5").feature("je1").set("k_th", 0);
    model.component("comp1").physics("mbd").feature("fxj5").feature("je1")
         .set("c_u", new String[]{"if(i_c==1,ch5,0)", "0", "0", "0", "0", "0", "0", "0", "if(i_c==1,ch5,0)"});
    model.component("comp1").physics("mbd").feature("fxj5").feature("je1").set("c_th", 0);
    model.component("comp1").physics("mbd").feature().duplicate("fxj6", "fxj5");
    model.component("comp1").physics("mbd").feature("fxj6")
         .label("\u56fa\u5b9a\u5173\u8282\uff1a\u5185\u810f-\u9aa8\u76c6");
    model.component("comp1").physics("mbd").feature("fxj6").set("Destination", "rd1");
    model.component("comp1").physics("mbd").feature("fxj6").feature("je1")
         .set("k_u", new String[]{"0", "0", "0", "0", "kv6", "0", "0", "0", "0"});
    model.component("comp1").physics("mbd").feature("fxj6").feature("je1")
         .set("c_u", new String[]{"0", "0", "0", "0", "if(i_c==1,cv6,0)", "0", "0", "0", "0"});
    model.component("comp1").physics("mbd").feature().duplicate("fxj7", "fxj6");
    model.component("comp1").physics("mbd").feature("fxj7")
         .label("\u56fa\u5b9a\u5173\u8282\uff1a\u5ea7\u6905-\u9aa8\u76c6");
    model.component("comp1").physics("mbd").feature("fxj7").set("Source", "bsm1");
    model.component("comp1").physics("mbd").feature("fxj7").feature("cjp1").selection().set(13);
    model.component("comp1").physics("mbd").feature("fxj7").feature("je1")
         .set("k_u", new String[]{"kh7", "0", "0", "0", "kv7", "0", "0", "0", "kh7"});
    model.component("comp1").physics("mbd").feature("fxj7").feature("je1")
         .set("c_u", new String[]{"if(i_c==1,ch7,0)", "0", "0", "0", "if(i_c==1,cv7,0)", "0", "0", "0", "if(i_c==1,ch7,0)"});
    model.component("comp1").physics("mbd").feature().duplicate("fxj8", "fxj7");
    model.component("comp1").physics("mbd").feature("fxj8")
         .label("\u56fa\u5b9a\u5173\u8282\uff1a\u5ea7\u6905-\u5927\u817f");
    model.component("comp1").physics("mbd").feature("fxj8").set("Destination", "rd5");
    model.component("comp1").physics("mbd").feature("fxj8").feature("cjp1").selection().set(61);
    model.component("comp1").physics("mbd").feature("fxj8").feature("je1")
         .set("k_u", new String[]{"kh8", "0", "0", "0", "kv8", "0", "0", "0", "kh8"});
    model.component("comp1").physics("mbd").feature("fxj8").feature("je1")
         .set("c_u", new String[]{"if(i_c==1,ch8,0)", "0", "0", "0", "if(i_c==1,cv8,0)", "0", "0", "0", "if(i_c==1,ch8,0)"});
    model.component("comp1").physics("mbd").feature().duplicate("fxj9", "fxj8");
    model.component("comp1").physics("mbd").feature("fxj9")
         .label("\u56fa\u5b9a\u5173\u8282\uff1a\u5ea7\u6905-\u5c0f\u817f");
    model.component("comp1").physics("mbd").feature("fxj9").set("Destination", "rd6");
    model.component("comp1").physics("mbd").feature("fxj9").feature("cjp1").selection().set(69, 70);
    model.component("comp1").physics("mbd").feature("fxj9").set("JointElasticity", "RigidJoint");
    model.component("comp1").physics("mbd").feature("fxj8")
         .set("JointForcesAndMoments", "ComputedUsingWeakConstraints");
    model.component("comp1").physics("mbd").feature("fxj7")
         .set("JointForcesAndMoments", "ComputedUsingWeakConstraints");

    model.nodeGroup().create("grp2", "Physics", "mbd");
    model.nodeGroup("grp2").placeAfter("bsm1");
    model.nodeGroup("grp2").add("fxj1");
    model.nodeGroup("grp2").add("fxj2");
    model.nodeGroup("grp2").add("fxj3");
    model.nodeGroup("grp2").add("fxj4");
    model.nodeGroup("grp2").add("fxj5");
    model.nodeGroup("grp2").add("fxj6");
    model.nodeGroup("grp2").add("fxj7");
    model.nodeGroup("grp2").add("fxj8");
    model.nodeGroup("grp2").add("fxj9");
    model.nodeGroup("grp2").label("\u56fa\u5b9a\u5173\u8282");

    model.component("comp1").mesh("mesh1").autoMeshSize(7);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - \u7279\u5f81\u9891\u7387");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "m_head", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "kg", 0);
    model.study("std1").feature("param").setIndex("pname", "m_head", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "kg", 0);
    model.study("std1").feature("param").setIndex("pname", "i_c", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0 1", 0);
    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").feature("eig").set("neigs", 12);
    model.study("std1").feature("eig").set("shift", "0");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u632f\u578b (mbd)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").setIndex("looplevel", 2, 1);
    model.result("pg1").set("showlegends", "off");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature("surf1").feature().create("def1", "Deform");
    model.result("pg1").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result().evaluationGroup().create("std1EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std1EvgFrq").set("data", "dset2");
    model.result().evaluationGroup("std1EvgFrq")
         .label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 1 - \u7279\u5f81\u9891\u7387)");
    model.result().evaluationGroup("std1EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std1EvgFrq").run();
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{12, 2});
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{14, 2});
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{11, 1});
    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/mbd", true);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u9891\u7387\u54cd\u5e94");
    model.study("std2").feature("freq").set("plist", "range(2,0.2,20)");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("H_vert", "abs(mbd.rd2.u_tty)/vtt_in");
    model.component("comp1").variable("var1").descr("H_vert", "\u5782\u76f4\u4f20\u9012\u7387");
    model.component("comp1").variable("var1").set("H_rot", "abs(mbd.rd2.th_ttz)/vtt_in");
    model.component("comp1").variable("var1").descr("H_rot", "\u8f6c\u52a8\u4f20\u9012\u7387");
    model.component("comp1").variable("var1").set("M_a", "abs(mbd.fxj7.Fy+mbd.fxj8.Fy)/vtt_in");
    model.component("comp1").variable("var1").descr("M_a", "\u8868\u89c2\u8d28\u91cf");

    model.study("std2").createAutoSequences("all");

    model.sol("sol5").runAll();

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u4f4d\u79fb (mbd)");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").setIndex("looplevel", 91, 0);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature("surf1").feature().create("def1", "Deform");
    model.result("pg2").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u5782\u76f4\u4f20\u9012\u7387");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u9891\u7387 (Hz)");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("expr", new String[]{"H_vert"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u5782\u76f4\u4f20\u9012\u7387"});
    model.result("pg3").feature("glob1").set("unit", new String[]{"1"});
    model.result("pg3").feature("glob1").set("linewidth", 2);
    model.result("pg3").feature("glob1").set("legend", false);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u8f6c\u52a8\u4f20\u9012\u7387");
    model.result("pg4").run();
    model.result("pg4").feature("glob1").setIndex("expr", "H_rot", 0);
    model.result("pg4").feature("glob1").setIndex("unit", "deg/m", 0);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u8868\u89c2\u8d28\u91cf");
    model.result("pg5").run();
    model.result("pg5").feature("glob1").set("expr", new String[]{"M_a"});
    model.result("pg5").feature("glob1").set("descr", new String[]{"\u8868\u89c2\u8d28\u91cf"});
    model.result("pg5").feature("glob1").set("unit", new String[]{"kg"});
    model.result("pg5").run();
    model.result("pg1").run();

    model.title("\u4eba\u4f53\u5750\u59ff\u7684\u751f\u7269\u529b\u5b66\u6a21\u578b");

    model
         .description("\u672c\u4f8b\u4e2d\u5f00\u53d1\u4e86\u4e00\u4e2a\u4eba\u4f53\u7684\u751f\u7269\u529b\u5b66\u6a21\u578b\uff0c\u8bc4\u4f30\u5750\u59ff\u65f6\u5782\u76f4\u632f\u52a8\u7684\u52a8\u6001\u54cd\u5e94\u3002\u4f7f\u7528\u201c\u591a\u4f53\u52a8\u529b\u5b66\u201d\u63a5\u53e3\u5bf9\u4eba\u4f53\u7684\u4e0d\u540c\u90e8\u4f4d\u548c\u5173\u8282\u8fdb\u884c\u5efa\u6a21\uff0c\u540c\u65f6\u7814\u7a76\u4e86\u5168\u8eab\u632f\u52a8 (WBV)\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("seated_human_body.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
